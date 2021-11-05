package com.think.tool.handler;

import cn.hutool.core.convert.Convert;
import org.apache.poi.ss.usermodel.Cell;

public class IntTypeHandler extends AbstractTypeHandler {

    @Override
    public Object handle(Cell cell) {
        return Convert.toInt(super.handle(cell));
    }
}
