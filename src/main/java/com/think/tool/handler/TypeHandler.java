package com.think.tool.handler;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 类型处理器接口
 *
 * @author veione
 */
public interface TypeHandler {

    Object handle(Cell cell);

    String type();
}
