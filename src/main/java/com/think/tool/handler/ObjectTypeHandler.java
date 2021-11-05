package com.think.tool.handler;

import com.think.tool.utils.CellUtils;
import com.think.tool.utils.JsonParser;
import org.apache.poi.ss.usermodel.Cell;

public class ObjectTypeHandler extends AbstractTypeHandler {

    @Override
    public Object handle(Cell cell) {
        return JsonParser.parseJSON(CellUtils.getCellStringValue(cell));
    }
}
