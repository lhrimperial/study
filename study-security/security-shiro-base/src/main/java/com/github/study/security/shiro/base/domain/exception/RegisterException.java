package com.github.study.security.shiro.base.domain.exception;

import com.github.framework.server.exception.BusinessException;

/**
 *
 */
public class RegisterException extends BusinessException {

    public RegisterException() {
    }

    public RegisterException(String msg) {
        super(msg);
    }
}
