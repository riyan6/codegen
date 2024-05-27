package org.zun.codegen.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zun.codegen.model.entity.TableColumn;
import org.zun.codegen.service.DatabaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zun.codegen.service.DynamicDataSourceService;

import java.util.List;

@CrossOrigin
@Tag(name = "数据库信息控制器")
@RestController
@RequestMapping("/api/databases")
@RequiredArgsConstructor
public class DatabaseController {

    private final DatabaseService databaseService;

    @Operation(summary = "查询所有数据库")
    @RequestMapping(method = RequestMethod.GET)
    List<String> queryDatabaseList() {
        return databaseService.getAllDatabase();
    }

    @Operation(summary = "查询某个数据库下的所有表")
    @GetMapping("/{database}/tables")
    List<String> queryTables(@PathVariable String database) {
        return databaseService.getAllTableByDatabase(database);
    }

    @Operation(summary = "查询某个数据库下的某个表的所有列")
    @GetMapping("/{database}/{table}/columns")
    List<TableColumn> queryTableColumns(@PathVariable("database") String database, @PathVariable("table") String table) {
        return databaseService.getAllTableColumnByTable(database, table);
    }
}
