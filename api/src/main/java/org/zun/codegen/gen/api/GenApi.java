package org.zun.codegen.gen.api;

import org.zun.codegen.model.entity.TableColumn;
import org.zun.codegen.service.DatabaseService;
import org.zun.codegen.gen.model.in.MapperGenIn;
import org.zun.codegen.gen.model.in.ServiceGenIn;
import org.zun.codegen.gen.model.in.ServiceImplGenIn;
import org.zun.codegen.gen.util.EntityGenUtil;
import org.zun.codegen.gen.model.in.EntityGenIn;
import org.zun.codegen.gen.util.MapperGenUtil;
import org.zun.codegen.gen.util.ServiceGenUtil;
import org.zun.codegen.gen.util.ServiceImplGenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "生成代码控制器")
@RestController
@RequestMapping("/api/gen")
@RequiredArgsConstructor
public class GenApi {
    private final EntityGenUtil entityGen;
    private final MapperGenUtil mapperGenUtil;
    private final ServiceGenUtil serviceGenUtil;
    private final ServiceImplGenUtil serviceImplGenUtil;
    private final DatabaseService databaseService;

    @Operation(summary = "获取实体代码")
    @SneakyThrows
    @PostMapping("/entity")
    public String genEntity(@RequestBody EntityGenIn genIn) {
        List<TableColumn> columns = databaseService.getAllTableColumnByTable(genIn.getTableName());
        return entityGen.execute(genIn, columns);
    }

    @Operation(summary = "获取数据持久层代码")
    @PostMapping("/mapper")
    public String genMapper(@RequestBody MapperGenIn genIn) {
        return mapperGenUtil.execute(genIn);
    }

    @Operation(summary = "获取业务逻辑代码")
    @PostMapping("/service")
    public String genService(@RequestBody ServiceGenIn genIn) {
        return serviceGenUtil.execute(genIn);
    }

    @Operation(summary = "获取控制器代码")
    @PostMapping("/serviceImpl")
    public String genServiceImpl(@RequestBody ServiceImplGenIn genIn) {
        return serviceImplGenUtil.execute(genIn);
    }

    @Operation(summary = "获取整个模块代码")
    @PostMapping("/module")
    public String genModule() {
        return "";
    }
}
