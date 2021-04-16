package com.brabos.bahia.instagram.test.dto;

import java.io.Serializable;

public class NewCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
