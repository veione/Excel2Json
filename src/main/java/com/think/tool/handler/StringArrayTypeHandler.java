package com.think.tool.handler;

import cn.hutool.core.convert.Convert;
import org.apache.poi.ss.usermodel.Cell;

public class StringArrayTypeHandler extends AbstractTypeHandler {

    @Override
    public Object handle(Cell cell) {
        return Convert.toStrArray(super.handle(cell));
    }
}
