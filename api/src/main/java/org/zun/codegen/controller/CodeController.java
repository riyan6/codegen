package org.zun.codegen.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zun.codegen.model.in.CodeGenIn;
import org.zun.codegen.model.out.CodeGenOut;
import org.zun.codegen.service.CodeService;

@CrossOrigin
@Tag(name = "代码生成器")
@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @PostMapping("/gen")
    public CodeGenOut gen(@RequestBody CodeGenIn in) {
        CodeGenOut out = codeService.gen(in);
        return out;
    }

}
