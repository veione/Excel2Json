package com.think.tool.exception;

import com.think.tool.utils.StringUtils;

/**
 * @author jaysunxiao
 * @version 3.0
 */
public class AssertException extends RuntimeException {

    public AssertException(String message) {
        super(message);
    }

    public AssertException(String template, Object... args) {
        super(StringUtils.format(template, args));
    }
}
