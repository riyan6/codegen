package org.zun.codegen.gen.util;

import cn.hutool.core.date.DateUtil;
import org.zun.codegen.gen.model.in.MapperGenIn;
import org.zun.codegen.gen.util.interf.IGen;
import lombok.SneakyThrows;
import org.springframework.javapoet.ClassName;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.util.Date;

@Component
public final class MapperGenUtil implements IGen {

//    @SneakyThrows
//    public String execute(MapperGenIn genIn) {
//        // 实体类
//        ClassName entityClass = ClassName.get(genIn.getEntityPackage(), genIn.getEntityName());
//
//        // 获取 BaseMapper 与实体类的包裹对象，即 BaseMapper<T>
//        ParameterizedTypeName parent = ParameterizedTypeName.get(BASE_MAPPER, entityClass);
//
//        // 创建类
//        TypeSpec.Builder clazz = TypeSpec.interfaceBuilder(genIn.getJavaName())
//                .addModifiers(Modifier.PUBLIC)
//                .addSuperinterface(parent)
//                .addJavadoc("""
//                        %s
//
//                        @author riyan6
//                        @since %s
//                        """.formatted(genIn.getComment(), DateUtil.format(new Date(), "yyyy-MM-dd")));
//
//        // java文件
//        JavaFile javaFile = JavaFile.builder(genIn.getPackageName(), clazz.build()).build();
//        return toString(genIn.getPackageName(), genIn.getJavaName(), javaFile.toString());
//    }

}
