package com.think.tool.model;

import java.util.List;
import java.util.Map;

/**
 * Sheet项
 *
 * @author veione
 */
public class SheetItem {

    private String sheetName;

    private List<Map<String, Object>> rows;

    public SheetItem(String sheetName, List<Map<String, Object>> rows) {
        this.sheetName = sheetName;
        this.rows = rows;
    }

    public static SheetItem of(String sheetName, List<Map<String, Object>> rows) {
        return new SheetItem(sheetName, rows);
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
