package com.github.study.sboot.vue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 */
@RestController
public class AnswerController {

    @RequestMapping("/getAnswer")
    public String answer() {
        return "receive a question, now i answer it";
    }

    @RequestMapping("getStudent")
    public void getStudent(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String name=req.getParameter("name");
        String sex=req.getParameter("sex");
        byte[] b;
        try {
            b = name.getBytes("ISO-8859-1");
            name=new String(b,"utf-8");
            b = sex.getBytes("ISO-8859-1");
            sex=new String(b,"utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }//用tomcat的格式（iso-8859-1）方式去读。

        System.out.println("姓名："+name+"\t性别："+sex);
        try {
            PrintWriter out=resp.getWriter();
            out.print("[{lattice:\"格子\"},{lattice:\"222\"}]");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
