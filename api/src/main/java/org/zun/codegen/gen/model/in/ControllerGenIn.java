package org.zun.codegen.gen.model.in;

import org.zun.codegen.gen.model.gen.BaseGen;
import lombok.Data;

@Data
public class ControllerGenIn extends BaseGen {
    private String tableName;
    private String entityPackage;
    private String entityName;
}
