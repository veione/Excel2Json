package com.think.tool.handler;

import cn.hutool.core.convert.Convert;
import org.apache.poi.ss.usermodel.Cell;

public class DoubleTypeHandler extends AbstractTypeHandler {

    @Override
    public Object handle(Cell cell) {
        return Convert.toDouble(super.handle(cell));
    }
}
