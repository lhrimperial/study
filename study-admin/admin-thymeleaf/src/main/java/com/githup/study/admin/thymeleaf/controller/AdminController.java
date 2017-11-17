package com.githup.study.admin.thymeleaf.controller;

import com.githup.study.admin.thymeleaf.domain.po.AdminDO;
import com.githup.study.admin.thymeleaf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * 登录跳转
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/login")
	public String loginGet(Model model) {
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param admin
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/admin/login")
	public String loginPost(AdminDO admin, Model model, HttpSession httpSession) {
		AdminDO adminRes = adminService.findByNameAndPassword(admin);
		if (adminRes != null) {
			httpSession.setAttribute("admin", adminRes);
			return "redirect:../main/dashboard";
		} else {
			model.addAttribute("error", "用户名或密码错误，请重新登录！");
			return "login";
		}
	}

	/**
	 * 注册
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/register")
	public String register(Model model) {
		return "register";
	}

	/**
	 * 仪表板页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/main/dashboard")
	public String dashboard(Model model) {
		return "dashboard";
	}

	/**
	 * 退出
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/admin/logout")
	public String logOut(Model model, HttpSession httpSession) {
		//TODO
		httpSession.removeAttribute("admin");
		return "redirect:login";
	}
}
