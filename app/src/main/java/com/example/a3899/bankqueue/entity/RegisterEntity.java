package com.example.a3899.bankqueue.entity;

/**
 * Created by a3899 on 2017/12/13.
 */

public class RegisterEntity {
    private boolean status;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}
