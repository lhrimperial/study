package com.githup.study.dubbo.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.lvmama.tnt.common.domain.PageResponseDTO;
import com.lvmama.tnt.export.code.api.domain.vo.ExportProductVO;
import com.lvmama.tnt.export.code.api.service.ITntCodeGoodsRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longhairen
 * @create 2017-12-11 10:09
 * @description
 **/
@RestController
public class HelloController {

    @Autowired
    private ITntCodeGoodsRemoteService tntCodeGoodsRemoteService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/invoke")
    public String invoke() {
        ExportProductVO vo = new ExportProductVO();
        vo.setPageSize(10);
        vo.setPageNo(1);
        PageResponseDTO responseDTO =  tntCodeGoodsRemoteService.findByPage(vo);
        return JSON.toJSONString(responseDTO);
    }
}
