package com.tsystems.exception;

public class CTCExecption extends Exception {

    public CTCExecption() {
    }

    public CTCExecption(String msg) {
        super(msg);
    }

    public CTCExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public CTCExecption(Throwable cause) {
        super(cause);
    }
}
