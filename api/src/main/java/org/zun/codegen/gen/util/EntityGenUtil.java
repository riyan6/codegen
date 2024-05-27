package org.zun.codegen.gen.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.zun.codegen.model.entity.TableColumn;
import org.zun.codegen.gen.model.in.EntityGenIn;
import org.zun.codegen.gen.util.interf.IGen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.javapoet.AnnotationSpec;
import org.springframework.javapoet.ClassName;
import org.springframework.javapoet.CodeBlock;
import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class EntityGenUtil implements IGen {

    public String execute(EntityGenIn genIn, List<TableColumn> columns) {
        // 创建类
        TypeSpec.Builder clazz = TypeSpec.classBuilder(genIn.getJavaName());
        // 类修饰
        clazz.addModifiers(Modifier.PUBLIC);
        clazz.addAnnotation(Data.class);
        clazz.addAnnotation(AllArgsConstructor.class);
        clazz.addAnnotation(NoArgsConstructor.class);
        clazz.addAnnotation(Builder.class);
        // 添加父类
        clazz.superclass(BasePo);
        // 添加注解
        AnnotationSpec tableNameAnnoSpec = AnnotationSpec.builder(TABLE_NAME)
                .addMember("value", "$S", genIn.getTableName())
                .addMember("excludeProperty", "{$S, $S}", "isused", "isdel")
                .build();
        clazz.addAnnotation(tableNameAnnoSpec);
        // 添加注释
        clazz.addJavadoc("""
                <p>%s</p>
                              
                @author riyan6
                @since %s
                """.formatted(genIn.getComment(), DateUtil.format(new Date(), "yyyy-MM-dd")));

        // 添加不同的类属性
        for (TableColumn c : columns) {
            if (StrUtil.equals(c.getField(), "lastoperator") || StrUtil.equals(c.getField(), "lastoperatetime") || StrUtil.equals(c.getField(), "tenantid")
                    || StrUtil.equals(c.getField(), "creator") || StrUtil.equals(c.getField(), "creationdate") || StrUtil.equals(c.getField(), "t_id")) {
                continue;
            }

            FieldSpec.Builder field = FieldSpec.builder(getFieldClassType(c.getType()), c.getField().equals("t_id") ? "tid" : c.getField(), Modifier.PRIVATE)
                    .addAnnotation(AnnotationSpec.builder(ClassName.get("io.swagger.annotations", "ApiModelProperty"))
                            .addMember("value", "$S", c.getComment())
                            .build())
                    .addJavadoc(c.getComment());

            // 如果是主键
            if (StrUtil.equals("PRI", c.getKey())) {
                field.addAnnotation(AnnotationSpec.builder(TABLE_ID)
                        .addMember("type", "$T.ASSIGN_ID", ID_TYPE)
                        .build());
            } else {
                field.addAnnotation(AnnotationSpec.builder(ClassName.get("com.baomidou.mybatisplus.annotation", "TableField"))
                        .addMember("value", "$S", c.getField())
                        .build());
            }

            // setTableFieldAnno(field, c);
            clazz.addField(field.build());
        }

        // Java 文件
        JavaFile javaFile = JavaFile.builder(genIn.getPackageName(), clazz.build())
                .build();
        return toString(genIn.getPackageName(), genIn.getJavaName(), javaFile.toString());
    }


    private Type getFieldClassType(String fieldType) {
        if (fieldType.contains("bigint")) {
            return Long.class;
        }
        if (fieldType.contains("int")) {
            return Integer.class;
        }
        if (fieldType.contains("datetime")) {
            return Date.class;
        }
        if (fieldType.contains("decimal")) {
            return BigDecimal.class;
        }
        if (fieldType.contains("date")) {
            return Date.class;
        }
        return String.class;
    }

    private void setTableFieldAnno(FieldSpec.Builder field, TableColumn column) {
        // 获取到字段名
        String tableFieldName = column.getField();

        // @TableField注解
        AnnotationSpec.Builder anno = AnnotationSpec.builder(TABLE_FIELD);
        switch (tableFieldName) {
            case "creator" -> {
                CodeBlock codeBlock = CodeBlock.of("$T.INSERT", FIELD_FILL);
                anno.addMember("fill", codeBlock);
                field.addAnnotation(anno.build());
            }
            case "createtime" -> {
                CodeBlock codeBlock = CodeBlock.of("$T.INSERT", FIELD_FILL);
                anno.addMember("fill", codeBlock);
                field.addAnnotation(anno.build());
            }
            case "lastoperator" -> {
                CodeBlock codeBlock = CodeBlock.of("$T.INSERT_UPDATE", FIELD_FILL);
                anno.addMember("fill", codeBlock);
                field.addAnnotation(anno.build());
            }
            case "lastoperatetime" -> {
                CodeBlock codeBlock = CodeBlock.of("$T.INSERT_UPDATE", FIELD_FILL);
                anno.addMember("fill", codeBlock);
                field.addAnnotation(anno.build());
            }
            case "tenantid" -> {
                CodeBlock codeBlock = CodeBlock.of("$T.INSERT", FIELD_FILL);
                anno.addMember("fill", codeBlock);
                field.addAnnotation(anno.build());
            }
            case "t_id" -> {
                anno.addMember("value", "$S", "t_id");
                field.addAnnotation(anno.build());
            }
        }
    }
}
