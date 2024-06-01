package org.zun.codegen.model.in;

import lombok.Data;

/**.
 * <p>代码生成请求类</p>
 *
 * @author riyan6
 * @since 2024-05-30
 */
@Data
public class CodeGenIn {
    private String author;
    private String classTopMetadata;
    private String projectName;

    private String tableName;

    private String entityClassName;
    private String entityAttributeNameType;
    private String entityClassDoc;
    private String entityPackage;

}
