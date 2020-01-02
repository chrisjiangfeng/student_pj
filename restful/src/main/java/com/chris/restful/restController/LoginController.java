package com.chris.restful.restController;


import com.chris.ser.po.Admin;
import com.chris.ser.po.Student;
import com.chris.ser.po.Teacher;
import com.chris.ser.service.AdminService;
import com.chris.ser.service.StudentService;
import com.chris.ser.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	private StudentService stds;
	 
	@Autowired
	private TeacherService tcs;

	@Autowired
	private AdminService ads;



	@RequestMapping("/test")
    public String qewr(String id, String pwd ){
        System.out.println(id);
        return null;
    }


	@RequestMapping("/login")
	public String login(String id, String pwd, int leixin, Model model, HttpServletRequest request)throws Exception{
		System.out.println("哈哈哈哈哈哈哈哈哈哈哈");
		if(leixin==1){
			Student stu = stds.getstu(id);
			if(stu==null){
				model.addAttribute("err", "账号或密码错误");
				return "login2.jsp";
			}
			if(pwd.equals(stu.getSpassword())){
				HttpSession session = request.getSession();
				session.setAttribute("stu", stu);
				return "student/studentInterface.jsp";
			}
		}else if(leixin==2){
			Teacher tea = tcs.gettea(id);
			if(tea==null){
				model.addAttribute("err", "账号或密码错误");
				return "login2.jsp";
			}
			if(pwd.equals(tea.getTpassword())){
				HttpSession session = request.getSession();
				session.setAttribute("tea", tea);
				return "teacher/teacherInterface.jsp";
			}
		}else {
			Admin adm =ads.getadm(id);
			if(adm==null){
				model.addAttribute("err", "账号或密码错误");
				return "login2.jsp";
			}
			if(pwd.equals(adm.getApassword())){
				HttpSession session = request.getSession();
				session.setAttribute("adm", adm);
				return "admin/adminInterface.jsp";
			}
		}
			
			model.addAttribute("err", "账号或密码错误");
			return "login2.jsp";
		
	}
	
	
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "login2.jsp";
	}
	
	
}
