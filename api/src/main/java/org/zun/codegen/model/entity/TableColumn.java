package org.zun.codegen.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableColumn {

    /**
     * 属性名
     */
    private String field;

    private String type;

    private String comment;

    private String key;

}
