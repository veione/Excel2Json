package com.think.tool.handler;

import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 类型处理器接口
 *
 * @author veione
 */
public interface TypeHandler {

    void handle(HSSFSheet _sheet);

    String type();
}
