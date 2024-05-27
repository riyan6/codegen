package org.zun.codegen.gen.util;

import cn.hutool.core.date.DateUtil;
import org.zun.codegen.gen.model.in.ServiceImplGenIn;
import org.zun.codegen.gen.util.interf.IGen;
import org.springframework.javapoet.ClassName;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;
import java.util.Date;

@Component
public class ServiceImplGenUtil implements IGen {

    public String execute(ServiceImplGenIn gen) {
        TypeSpec traceExtendInfoServiceImpl = TypeSpec.classBuilder(gen.getJavaName())
                .superclass(ParameterizedTypeName.get(
                        SERVICE_IMPL,
                        ClassName.get(gen.getMapperPackage(), gen.getMapperName()),
                        ClassName.get(gen.getEntityPackage(), gen.getEntityName())))
                .addSuperinterface(ClassName.get(gen.getServicePackage(), gen.getServiceName()))
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Service.class)
                .addJavadoc("""
                        %s
                        
                        @author riyan6
                        @since %s
                        """.formatted(gen.getComment(), DateUtil.format(new Date(), "yyyy-MM-dd")))
                .build();

        JavaFile javaFile = JavaFile.builder(gen.getPackageName(), traceExtendInfoServiceImpl).build();
        return toString(gen.getPackageName(), gen.getJavaName(), javaFile.toString());
    }

}
