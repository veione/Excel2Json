package com.think.tool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.think.tool.export.IExport;
import com.think.tool.export.JsonExport;
import com.think.tool.handler.TypeHandler;
import com.think.tool.handler.TypeHandlerFactory;
import com.think.tool.model.Config;
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

/**
 * 程序运行入口
 *
 * @author veione
 */
public class Application {

    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************");
        System.out.println("                  EXCEL 2 JSON                ");
        System.out.println("**********************************************");
        System.out.println();
        if (args.length == 1) {
            String json = FileUtil.readUtf8String(args[0]);
            Config config = JsonUtils.string2ObjectTurbo(json, Config.class);

            File input = new File(config.getInput());
            File[] files = input.listFiles();
            IExport export = new JsonExport();

            int sheetCount = 0;
            int fileSize = 0;

            for (File file : files) {
                if ("xlsx".equals(FileUtil.extName(file)) || "xls".equals(FileUtil.extName(file))) {
                    Workbook wb = WorkbookFactory.create(file);
                    int sheetSize = wb.getNumberOfSheets();
                    List<SheetItem> items = new ArrayList<>(sheetSize);
                    fileSize++;

                    for (int i = 0; i < sheetSize; i++) {
                        Sheet sheet = wb.getSheetAt(i);
                        SheetItem item = readRows(sheet);
                        items.add(SheetItem.of(item.getSheetName(), item.getRows()));
                        sheetCount++;
                    }

                    wb.close();

                    for (SheetItem item : items) {
                        export.export(config.getOutput().getServer(), item);
                    }
                }
            }
            System.out.println("Finished done enjoy it :) file count: " + fileSize + ", sheet count: " + sheetCount);
        } else {
            System.out.println("HOW TO USE :");
            System.out.println("you should provide 1arguments , specify the config file");
            System.out.println();
            System.out.println("like: ");
            System.out.println("java -jar excel2json.jar conf.json");
            System.out.println();
        }
    }

    /**
     * 读取数据行
     *
     * @param sheet
     * @return
     */
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
