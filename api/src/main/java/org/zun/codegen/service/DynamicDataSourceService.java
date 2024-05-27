package org.zun.codegen.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;
import org.zun.codegen.util.DataSourceContextHolder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicDataSourceService {

    private final Map<String, DataSource> dataSourceMap = new HashMap<>();

    public DataSource create(String url, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    public void add(String key, DataSource dataSource) {
        dataSourceMap.put(key, dataSource);
    }

    public DataSource get(String key) {
        return dataSourceMap.get(key);
    }

    public boolean empty() {
        return dataSourceMap.isEmpty();
    }

    public boolean has(String key) {
        return dataSourceMap.containsKey(key);
    }

    public boolean has() {
        return dataSourceMap.containsKey(DataSourceContextHolder.get());
    }

    public DataSource get() {
        return dataSourceMap.get(DataSourceContextHolder.get());
    }

}
