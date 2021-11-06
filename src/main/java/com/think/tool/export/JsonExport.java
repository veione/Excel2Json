package com.think.tool.export;

import cn.hutool.core.io.FileUtil;
import com.think.tool.model.SheetItem;
import com.think.tool.utils.JsonUtils;

/**
 * Json导出实现
 *
 * @author veione
 */
public class JsonExport implements IExport {

    @Override
    public void export(String path, SheetItem item) {
        String content = JsonUtils.object2StringTurbo(item.getRows());
        FileUtil.writeUtf8String(content, path + item.getSheetName() + ".json");
    }
}
