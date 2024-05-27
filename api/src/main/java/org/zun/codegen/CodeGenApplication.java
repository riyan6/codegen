package org.zun.codegen;

import cn.hutool.core.lang.Console;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class CodeGenApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CodeGenApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var docUrl = "http://127.0.0.1:3000/doc.html";
        Console.log("Web应用启动成功，虚拟线程已启用，APi文档访问地址：%s".formatted(docUrl));
    }

}

