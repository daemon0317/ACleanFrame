package com.framework.android.exception;

/**
 * com.framework.android.exception
 * Created by daemon on 2016/5/25 0025.
 * 说明：
 */
public class NotInitializeError extends RuntimeException {
    private static final long serialVersionUID = 115361L;

    public NotInitializeError() {
    }

    public NotInitializeError(String detailMessage) {
        super(detailMessage);
    }

    public NotInitializeError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotInitializeError(Throwable throwable) {
        super(throwable);
    }
}
