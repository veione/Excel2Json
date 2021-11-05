package com.think.tool.handler;

import com.think.tool.utils.CellUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 抽象类型处理器
 *
 * @author veione
 */
public abstract class AbstractTypeHandler implements TypeHandler {

    @Override
    public Object handle(Cell cell) {
        return CellUtils.getCellValue(cell);
    }

    @Override
    public String type() {
        return null;
    }
}
