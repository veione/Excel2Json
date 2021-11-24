package com.think.tool.parse;

import com.think.tool.model.SheetItem;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

/**
 * Excel解析接口
 *
 * @author veione
 */
public interface ExcelParser {
    /**
     * 读取数据
     *
     * @param sheet
     * @return
     */
    Map<String, SheetItem> readRows(Sheet sheet);
}
