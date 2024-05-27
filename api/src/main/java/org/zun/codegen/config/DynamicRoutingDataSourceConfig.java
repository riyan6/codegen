package org.zun.codegen.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.zun.codegen.util.DataSourceContextHolder;

public class DynamicRoutingDataSourceConfig extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
