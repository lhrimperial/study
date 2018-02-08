package com.githup.study.security.shiro.web.domain.vo;

import java.io.Serializable;

/**
 *
 */
public class ResponseVO<T> implements Serializable {
    private static final long serialVersionUID = 8547681464125232474L;

    private boolean success;
    private String respCode;
    private String respMsg;
    private T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
