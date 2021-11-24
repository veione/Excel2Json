package com.think.tool.parse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.think.tool.constant.Constants;
import com.think.tool.handler.TypeHandler;
import com.think.tool.handler.TypeHandlerFactory;
import com.think.tool.model.SheetItem;
import com.think.tool.utils.CellUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 默认解析实现
 *
 * @author veione
 */
public class DefaultExcelParser implements ExcelParser {

    @Override
    public Map<String, SheetItem> readRows(Sheet sheet) {
        Map<String, SheetItem> resultMap = new HashMap<>(2);
        List<Map<String, Object>> serverRows = new ArrayList<>();
        List<Map<String, Object>> clientRows = new ArrayList<>();
        Iterator<Row> iter = sheet.iterator();
        Row rowDesc = iter.next();
        Row rowName = iter.next();
        Row rowType = iter.next();
        Row rowCs = iter.next();

        // 行数定位到有效数据行，默认是第四行为有效数据行
        while (iter.hasNext()) {
            Row row = iter.next();
            short cellNum = row.getLastCellNum();
            Map<String, Object> cellFieldServerMap = new HashMap<>(cellNum);
            Map<String, Object> cellFieldClientMap = new HashMap<>(cellNum);

            for (int i = 0; i < cellNum; i++) {
                Cell cell = row.getCell(i);
                String cellDesc = CellUtils.getCellStringValue(rowDesc.getCell(i));
                String cellName = CellUtils.getCellStringValue(rowName.getCell(i));
                String cellCs = StrUtil.trim(CellUtils.getCellStringValue(rowCs.getCell(i)));
                // 如果标记了#号开头的则表示跳过该行
                if (StrUtil.isNotEmpty(cellDesc) && cellDesc.startsWith(Constants.IGNORE_TAG)) {
                    continue;
                }
                TypeHandler handler = TypeHandlerFactory.getTypeHandler(CellUtils.getCellStringValue(rowType.getCell(i)));
                Object value = handler.handle(cell);

                if (StrUtil.isEmpty(cellCs) || Constants.ALL_TYPE.equals(cellCs)) {
                    cellFieldServerMap.put(cellName, value);
                    cellFieldClientMap.put(cellName, value);
                } else if (Constants.CLIENT_TYPE.equals(cellCs)) {
                    cellFieldClientMap.put(cellName, value);
                } else if (Constants.SERVER_TYPE.equals(cellCs)) {
                    cellFieldServerMap.put(cellName, value);
                }
            }
            if (CollUtil.isNotEmpty(cellFieldServerMap)) {
                serverRows.add(cellFieldServerMap);
            }
            if (CollUtil.isNotEmpty(cellFieldClientMap)) {
                clientRows.add(cellFieldClientMap);
            }
        }

        resultMap.put(Constants.CLIENT_TYPE, SheetItem.of(sheet.getSheetName(), clientRows));
        resultMap.put(Constants.SERVER_TYPE, SheetItem.of(sheet.getSheetName(), serverRows));
        return resultMap;
    }
}
