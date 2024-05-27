package org.zun.codegen.gen.model.in;

import org.zun.codegen.gen.model.gen.BaseGen;
import lombok.Data;

@Data
public class ServiceGenIn extends BaseGen {
    private String entityPackage;
    private String entityName;
}
