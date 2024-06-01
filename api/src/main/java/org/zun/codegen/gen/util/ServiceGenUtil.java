package org.zun.codegen.gen.util;

import cn.hutool.core.date.DateUtil;
import org.zun.codegen.gen.model.in.ServiceGenIn;
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
public class ServiceGenUtil implements IGen {

//    @SneakyThrows
//    public String execute(ServiceGenIn gen) {
//        TypeSpec traceExtendInfoService = TypeSpec.interfaceBuilder(gen.getJavaName())
//                .addModifiers(Modifier.PUBLIC)
//                .addSuperinterface(ParameterizedTypeName.get(
//                        I_SERVICE,
//                        ClassName.get(gen.getEntityPackage(), gen.getEntityName())))
//                .addJavadoc("""
//                        %s
//
//                        @author riyan6
//                        @since %s
//                        """.formatted(gen.getComment(), DateUtil.format(new Date(), "yyyy-MM-dd")))
//                .build();
//
//        JavaFile javaFile = JavaFile.builder(gen.getPackageName(), traceExtendInfoService).build();
//        return toString(gen.getPackageName(), gen.getJavaName(), javaFile.toString());
//    }

}
