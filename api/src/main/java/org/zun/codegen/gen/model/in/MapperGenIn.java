package org.zun.codegen.gen.model.in;

import org.zun.codegen.gen.model.gen.BaseGen;
import lombok.Data;

@Data
public class MapperGenIn extends BaseGen {
    private String entityPackage;
    private String entityName;
}
