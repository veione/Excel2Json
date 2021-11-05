package com.think.tool.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Optional;

/**
 * @author jaysunxiao
 * @version 3.0
 */
public abstract class CellUtils {

    public static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return StringUtils.EMPTY;
        }
        return getCellValue(cell).toString().trim();
    }

    /**
     * 获取单元格值
     *
     * @return 值，类型可能为：Date、Double、Boolean、String
     */
    public static Object getCellValue(Cell cell) {
        if (cell == null) {
            return StringUtils.EMPTY;
        }
        return getCellValue(cell, cell.getCellType());
    }

    /**
     * 获取单元格值<br>
     * 如果单元格值为数字格式，则判断其格式中是否有小数部分，无则返回Long类型，否则返回Double类型
     */
    private static Object getCellValue(Cell cell, CellType cellType) {
        Object value = null;
        switch (cellType) {
            case NUMERIC:
                value = getNumericValue(cell);
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                // 遇到公式时查找公式结果类型
                value = getCellValue(cell, cell.getCachedFormulaResultType());
                break;
            case BLANK:
                CellRangeAddress rangeAddress = getMergedRegionForCell(cell);
                if (rangeAddress != null) {
                    int firstRow = rangeAddress.getFirstRow();
                    int firstCol = rangeAddress.getFirstColumn();
                    Sheet sheet = cell.getRow().getSheet();
                    Cell firstCell = sheet.getRow(firstRow).getCell(firstCol);
                    if (firstCell.getCellType() != CellType.BLANK) {
                        value = getCellValue(firstCell, firstCell.getCellType());
                    }
                }
                value = Optional.ofNullable(value).orElse(StringUtils.EMPTY);
                break;
            case ERROR:
                final FormulaError error = FormulaError.forInt(cell.getErrorCellValue());
                value = (null == error) ? StringUtils.EMPTY : error.getString();
                break;
            default:
                value = cell.getStringCellValue();
        }

        return value;
    }

    private static CellRangeAddress getMergedRegionForCell(Cell c) {
        Sheet s = c.getRow().getSheet();
        for (CellRangeAddress mergedRegion : s.getMergedRegions()) {
            if (mergedRegion.isInRange(c.getRowIndex(), c.getColumnIndex())) {
                return mergedRegion;
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------------------------------------------- Private method start

    /**
     * 获取数字类型的单元格值
     *
     * @return 单元格值，可能为Long、Double、Date
     */
    private static Object getNumericValue(Cell cell) {
        double value = cell.getNumericCellValue();

        CellStyle style = cell.getCellStyle();
        if (null == style) {
            return value;
        }

        // 判断是否为日期
        if (DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }

        String format = style.getDataFormatString();
        // 普通数字
        if (null != format && !format.contains(StringUtils.PERIOD)) {
            long longValue = (long) value;
            if (longValue == value) {
                return Long.valueOf(longValue);
            }
        }
        return value;
    }

}
