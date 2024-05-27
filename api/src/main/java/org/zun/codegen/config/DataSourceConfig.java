package org.zun.codegen.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zun.codegen.service.DynamicDataSourceService;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final DynamicDataSourceService dynamicDataSourceService;

    @Bean
    public DataSource dataSource() {
        DynamicRoutingDataSourceConfig dynamicRoutingDataSource = new DynamicRoutingDataSourceConfig();
        Map<Object, Object> targetDataSources = new HashMap<>();
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        return dynamicRoutingDataSource;
    }

}
