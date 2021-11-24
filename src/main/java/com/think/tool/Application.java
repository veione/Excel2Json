package com.think.tool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.think.tool.constant.Constants;
import com.think.tool.export.IExport;
import com.think.tool.export.JsonExport;
import com.think.tool.handler.TypeHandler;
import com.think.tool.handler.TypeHandlerFactory;
import com.think.tool.model.Config;
import com.think.tool.model.SheetItem;
import com.think.tool.parse.DefaultExcelParser;
import com.think.tool.parse.ExcelParser;
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
        printWelcome();
        if (args.length == 1) {
            String json = FileUtil.readUtf8String(args[0]);
            Config config = JsonUtils.string2ObjectTurbo(json, Config.class);
            JsonUtils.setFormat(config.isFormat());

            File input = new File(config.getInput());
            File[] files = input.listFiles();
            IExport export = new JsonExport();
            ExcelParser excelParser = new DefaultExcelParser();

            int sheetCount = 0;
            int fileSize = 0;

            for (File file : files) {
                if (Constants.XLSX.equals(FileUtil.extName(file)) || Constants.XLS.equals(FileUtil.extName(file))) {
                    try(Workbook wb = WorkbookFactory.create(file)) {
                        int sheetSize = wb.getNumberOfSheets();
                        List<SheetItem> serverItems = new ArrayList<>(sheetSize);
                        List<SheetItem> clientItems = new ArrayList<>(sheetSize);
                        fileSize++;

                        for (int i = 0; i < sheetSize; i++) {
                            Sheet sheet = wb.getSheetAt(i);
                            Map<String, SheetItem> item = excelParser.readRows(sheet);
                            SheetItem clientItem = item.get(Constants.CLIENT_TYPE);
                            SheetItem serverItem = item.get(Constants.SERVER_TYPE);
                            clientItems.add(SheetItem.of(clientItem.getSheetName(), clientItem.getRows()));
                            serverItems.add(SheetItem.of(serverItem.getSheetName(), serverItem.getRows()));
                            sheetCount++;
                        }

                        for (SheetItem item : serverItems) {
                            export.export(config.getOutput().getServer(), item);
                        }
                        for (SheetItem item : clientItems) {
                            export.export(config.getOutput().getClient(), item);
                        }
                    }
                }
            }
            System.out.println("Finished done enjoy it :) file count: " + fileSize + ", sheet count: " + sheetCount);
        } else {
            printHelp();
        }
    }

    private static void printWelcome() {
        System.out.println("**********************************************");
        System.out.println("                  EXCEL 2 JSON                ");
        System.out.println("**********************************************");
        System.out.println();
    }

    private static void printHelp() {
        System.out.println("HOW TO USE :");
        System.out.println("you should provide 1 arguments , specify the config file");
        System.out.println();
        System.out.println("like: ");
        System.out.println("java -jar excel2json.jar conf.json");
        System.out.println();
    }
}
