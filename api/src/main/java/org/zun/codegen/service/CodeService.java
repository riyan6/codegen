package org.zun.codegen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zun.codegen.gen.util.EntityGenUtil;
import org.zun.codegen.model.entity.TableColumn;
import org.zun.codegen.model.in.CodeGenIn;
import org.zun.codegen.model.out.CodeGenOut;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final DatabaseService databaseService;
    private final EntityGenUtil entityGenUtil;

    public CodeGenOut gen(CodeGenIn in) {
        // 获取类的全部列数据
        List<TableColumn> columns = databaseService.getAllTableColumnByTable(in.getTableName());
        // 判断是否生成
        String entityContent = entityGenUtil.execute(in, columns);

        return CodeGenOut.builder()
                .entity(entityContent)
                .build();
    }
}
