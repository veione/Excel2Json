package com.think.tool;

import cn.hutool.core.util.StrUtil;
import com.think.tool.handler.TypeHandler;
import com.think.tool.handler.TypeHandlerFactory;
import com.think.tool.model.SheetItem;
import com.think.tool.utils.CellUtils;
import com.think.tool.utils.JsonUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws Exception {
        Workbook wb = WorkbookFactory.create(new File("D:\\items.xlsx"));
        int sheetSize = wb.getNumberOfSheets();
        List<SheetItem> items = new ArrayList<>(sheetSize);

        for (int i = 0; i < sheetSize; i++) {
            Sheet sheet = wb.getSheetAt(i);
            SheetItem item = readRows(sheet);
            items.add(SheetItem.of(item.getSheetName(), item.getRows()));
        }

        wb.close();

        for (SheetItem item : items) {
            System.out.println("导出Json文件：" + item.getSheetName() + ".json");
            System.out.println(JsonUtils.object2StringTurbo(item.getRows()));
        }
        /**
         * 如果是数组则以以下格式进行输入：
         * [123, 456, 789]
         * 如果是对象则以以下格式进入输入：
         * {id: 123, value: 456}
         */
    }

    public static SheetItem readRows(Sheet sheet) {
        List<Map<String, Object>> rows = new ArrayList<>();
        Iterator<Row> iter = sheet.iterator();
        Row rowDesc = iter.next();
        Row rowName = iter.next();
        Row rowType = iter.next();
        Row rowCs = iter.next();

        // 行数定位到有效数据行，默认是第四行为有效数据行
        while (iter.hasNext()) {
            Row row = iter.next();
            short cellNum = row.getLastCellNum();
            Map<String, Object> cellFieldMap = new HashMap<>(cellNum);

            for (int i = 0; i < cellNum; i++) {
                Cell cell = row.getCell(i);
                String cellDesc = CellUtils.getCellStringValue(rowDesc.getCell(i));
                String cellName = CellUtils.getCellStringValue(rowName.getCell(i));
                // 如果标记了#号开头的则表示跳过该行
                if (StrUtil.isNotEmpty(cellDesc) && cellDesc.startsWith("#")) {
                    continue;
                }
                TypeHandler handler = TypeHandlerFactory.getTypeHandler(CellUtils.getCellStringValue(rowType.getCell(i)));
                Object value = handler.handle(cell);
                cellFieldMap.put(cellName, value);
            }
            rows.add(cellFieldMap);
        }

        return SheetItem.of(sheet.getSheetName(), rows);
    }
}
