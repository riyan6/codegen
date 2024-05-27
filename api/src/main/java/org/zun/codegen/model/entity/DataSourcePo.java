package org.zun.codegen.model.entity;

import lombok.Data;

@Data
public class DataSourcePo {

    private String host;
    private Integer port;
    private String username;
    private String password;

}
