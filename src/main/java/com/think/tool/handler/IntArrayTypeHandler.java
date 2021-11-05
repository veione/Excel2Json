package com.think.tool.handler;

import cn.hutool.core.convert.Convert;
import com.think.tool.utils.CellUtils;
import org.apache.poi.ss.usermodel.Cell;

public class IntArrayTypeHandler extends AbstractTypeHandler {

    @Override
    public Object handle(Cell cell) {
        Object value = CellUtils.getCellStringValue(cell);
        return Convert.toIntArray(value);
    }
}
