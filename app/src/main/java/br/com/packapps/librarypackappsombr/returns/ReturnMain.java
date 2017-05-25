package br.com.packapps.librarypackappsombr.returns;

import java.util.List;

/**
 * Created by paulolinhares on 25/05/17.
 */

public class ReturnMain {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
