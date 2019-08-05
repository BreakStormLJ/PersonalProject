package com.liujin.domain;

import java.io.Serializable;

/**
 * @program: myDay13
 * @description: 状态提示
 * @author: liujin
 * @create: 2019-07-29 19:44
 **/
public class Msg implements Serializable {
    private Integer status;    //0代表不可用，1代表可用
    private String msg;

    public Msg() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
