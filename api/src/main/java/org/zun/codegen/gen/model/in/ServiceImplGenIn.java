package org.zun.codegen.gen.model.in;

import org.zun.codegen.gen.model.gen.BaseGen;
import lombok.Data;

@Data
public class ServiceImplGenIn extends BaseGen {

    private String entityPackage;
    private String entityName;

    private String mapperPackage;
    private String mapperName;

    private String servicePackage;
    private String serviceName;
}
