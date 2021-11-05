package com.think.tool.exception;

import com.think.tool.utils.StringUtils;

/**
 * @author jaysunxiao
 * @version 3.0
 */
public class UnknownException extends RuntimeException {

    public UnknownException(Throwable cause) {
        super(cause);
    }

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String template, Object... args) {
        super(StringUtils.format(template, args));
    }

    public UnknownException(Throwable cause, String message) {
        super(message, cause);
    }

    public UnknownException(Throwable cause, String template, Object... args) {
        super(StringUtils.format(template, args), cause);
    }

}
