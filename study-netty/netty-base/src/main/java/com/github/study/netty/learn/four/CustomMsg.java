package com.github.study.netty.learn.four;

/**
 *
 */
public class CustomMsg {
    private byte type;
    private byte flage;
    private int length;
    private String body;

    public CustomMsg() {

    }

    public CustomMsg(byte type, byte flage, int length, String body) {
        this.type = type;
        this.flage = flage;
        this.length = length;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getFlage() {
        return flage;
    }

    public void setFlage(byte flage) {
        this.flage = flage;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
