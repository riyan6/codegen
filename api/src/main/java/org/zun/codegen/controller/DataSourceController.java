package org.zun.codegen.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zun.codegen.model.entity.DataSourcePo;
import org.zun.codegen.service.DynamicDataSourceService;
import org.zun.codegen.util.DataSourceContextHolder;

import javax.sql.DataSource;

@Tag(name = "数据源服务")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/datasources")
public class DataSourceController {

    private final DynamicDataSourceService dataSourceService;
    private final JdbcTemplate jdbcTemplate;

    @Operation(summary = "添加数据源")
    @RequestMapping(method = RequestMethod.POST)
    public boolean add(@RequestBody DataSourcePo dataSourcePo) {
        // 获取key
        String key = DataSourceContextHolder.get();
        // 添加数据源
        var url = "jdbc:mysql://%s:%s".formatted(dataSourcePo.getHost(), dataSourcePo.getPort());
        DataSource dataSource = dataSourceService.create(url, dataSourcePo.getUsername(), dataSourcePo.getPassword());
        jdbcTemplate.setDataSource(dataSource);
        dataSourceService.add(key, dataSource);
        return true;
    }

}
