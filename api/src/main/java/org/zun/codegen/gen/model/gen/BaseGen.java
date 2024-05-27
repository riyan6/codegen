package org.zun.codegen.gen.model.gen;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public abstract class BaseGen {
    /**
     * 包名
     */
    private String packageName;

    /**
     * Java 文件名
     */
    private String javaName;

    /**
     * 备注
     */
    private String comment;

}
