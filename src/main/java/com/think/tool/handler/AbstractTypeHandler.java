package com.think.tool.handler;

import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 抽象类型处理器
 *
 * @author veione
 */
public abstract class AbstractTypeHandler implements TypeHandler {

    @Override
    public void handle(HSSFSheet sheet) {

    }

    @Override
    public String type() {
        return null;
    }
}
