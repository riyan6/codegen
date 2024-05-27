package org.zun.codegen.service;

import org.zun.codegen.model.entity.TableColumn;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;
    private final List<String> sysDatabases = List.of("information_schema", "mysql", "performance_schema", "sys");

    public List<String> getAllDatabase() {
        List<String> list = jdbcTemplate.queryForList("show databases", String.class);
        return list.stream().filter(r -> !sysDatabases.contains(r)).toList();
    }

    public List<String> getAllTableByDatabase(String database) {
        var sql = "show tables from %s".formatted(database);
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<TableColumn> getAllTableColumnByTable(String database, String table) {
        var sql = "show full columns from %s.%s".formatted(database, table);
        return jdbcTemplate.query(sql, (rs, rowNum) -> TableColumn.builder()
                .field(rs.getString("Field"))
                .type(rs.getString("Type"))
                .comment(rs.getString("Comment"))
                .build());
    }

    public List<TableColumn> getAllTableColumnByTable(String datasource) {
        var sql = "show full columns from %s".formatted(datasource);
        return jdbcTemplate.query(sql, (rs, rowNum) -> TableColumn.builder()
                .field(rs.getString("Field"))
                .type(rs.getString("Type"))
                .comment(rs.getString("Comment"))
                .key(rs.getString("Key"))
                .build());
    }


}
