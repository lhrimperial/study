package com.github.study.sboot.vue.controller;

import com.github.framework.server.shared.domain.dto.ResponseDTO;

/**
 *
 **/
public class AbstractController {

    protected ResponseDTO returnSuccess() {
        return new ResponseDTO(true,"success","successfully!");
    }

    protected ResponseDTO returnError(String message) {
        return new ResponseDTO(false, "error", message);
    }
}
