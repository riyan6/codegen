package org.zun.codegen.gen.util.interf;


import cn.hutool.core.date.DateUtil;
import org.springframework.javapoet.ClassName;

import java.util.Date;

public interface IGen {

    ClassName BasePo = ClassName.get("com.base.framework.support.domain.po", "BasePo");

    ClassName ID_TYPE = ClassName.get("com.baomidou.mybatisplus.annotation", "IdType");
    ClassName TABLE_ID = ClassName.get("com.baomidou.mybatisplus.annotation", "TableId");
    ClassName TABLE_FIELD = ClassName.get("com.baomidou.mybatisplus.annotation", "TableField");
    ClassName FIELD_FILL = ClassName.get("com.baomidou.mybatisplus.annotation", "FieldFill");
    ClassName TABLE_NAME = ClassName.get("com.baomidou.mybatisplus.annotation", "TableName");
    ClassName BASE_MAPPER = ClassName.get("com.baomidou.mybatisplus.core.mapper", "BaseMapper");
    ClassName I_SERVICE = ClassName.get("com.baomidou.mybatisplus.extension.service", "IService");
    ClassName SERVICE_IMPL = ClassName.get("com.baomidou.mybatisplus.extension.service.impl", "ServiceImpl");

    default String toString(String packageName, String fileName, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("""
                /**
                 * XXX技术有限公司 版权所有Copyright (C) 2024
                 *
                 * @projectName: pbms-parent
                 * @Package: %s
                 * @ClassName: %s
                 * @CreateUser: riyan6
                 * @CreateDate: %s
                 */
                
                """.formatted(packageName, fileName, DateUtil.format(new Date(), "yyyy-MM-dd")));

        String[] lines = content.split(System.lineSeparator());
        for (String line : lines) {
            if (line.contains("/**")) {
                sb.append(line.replace("/**", "/**.")).append(System.lineSeparator());
            } else {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

}
