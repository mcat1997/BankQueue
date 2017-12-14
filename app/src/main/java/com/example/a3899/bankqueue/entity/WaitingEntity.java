package com.example.a3899.bankqueue.entity;

/**
 * Created by a3899 on 2017/12/14.
 */

public class WaitingEntity {
    private boolean status;
    private Integer num;
    private Integer counterId;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCounterId() {
        return counterId;
    }

    public void setCounterId(Integer counterId) {
        this.counterId = counterId;
    }
}
