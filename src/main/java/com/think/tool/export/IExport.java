package com.think.tool.export;

import com.think.tool.model.SheetItem;

/**
 * 导出接口
 *
 * @author veione
 */
public interface IExport {
    /**
     * 导出json到指定路径
     *
     * @param path   文件路径
     * @param item   导出的item
     */
    void export(String path, SheetItem item);
}
