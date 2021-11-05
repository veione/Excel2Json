package com.think.tool.exception;

import com.think.tool.utils.StringUtils;

/**
 * @author jaysunxiao
 * @version 3.0
 */
public class RunException extends RuntimeException {

    public RunException(Throwable cause) {
        super(cause);
    }

    public RunException(String message) {
        super(message);
    }

    public RunException(String template, Object... args) {
        super(StringUtils.format(template, args));
    }

    public RunException(Throwable cause, String message) {
        super(message, cause);
    }

    public RunException(Throwable cause, String template, Object... args) {
        super(StringUtils.format(template, args), cause);
    }

}
