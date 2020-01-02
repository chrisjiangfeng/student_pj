package com.chris.restful.restController;

import com.chris.ser.bo.PageBean;
import com.chris.ser.po.Admin;
import com.chris.ser.po.Curriculum;
import com.chris.ser.po.Student;
import com.chris.ser.po.Teacher;
import com.chris.ser.pojo.Details;
import com.chris.ser.pojo.ManyTable;
import com.chris.ser.pojo.Tea_Curri;
import com.chris.ser.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
	
	@Resource(name="manyTableServiceImpl")
	private ManyTableService mts;
	
	@Resource(name="studentServiceImpl")
	private StudentService stus;
	
	@Resource(name="teacherServiceImpl")
	private TeacherService teas;
	
	@Resource(name="adminServiceImpl")
	private AdminService adms;
	
	@Resource(name="curriculumServiceImpl")
	private CurriculumService curs;

	@RequestMapping("/seetea")
	public String seetea(Model model) throws Exception{
		List<Tea_Curri> listtea_curri = mts.gettea_curri();
		model.addAttribute("listtea_curri", listtea_curri);
		return "admin/seetea.jsp";	
	}
	
	
	@RequestMapping("/seedetails")
	public String seedetails(int cid, Model model) throws Exception{
		List<Details> listdetails = mts.getdetails(cid);
		int sum=0;
		int flag=0;
		for(Details det:listdetails){
			if(det.getEscore()==null){
				continue;
			}
			sum+=det.getEscore();
			flag++;
		}
		if(flag==0){
			return  "pjnotfinish.jsp";
		}
		double avg=sum/flag;
		int b=(int) (avg/10);
		String grade;
		switch(b){
		case 10:grade="非常优秀";break;
		case 9:grade="优秀";break;
		case 8:grade="良好";break;
		case 7:grade="中等";break;
		case 6:grade="及格";break;
		default:grade="不合格";
		}
		model.addAttribute("grade", grade);
		model.addAttribute("avg", avg);
		model.addAttribute("listdetails", listdetails);
		return "admin/details.jsp";
		
	}
	
	
	@RequestMapping("/viewstu")
	public String viewstu(Model model, int currPage) throws Exception{
		int pageSize=5;
		int count = stus.getcount();
		int totalPage=(int) Math.ceil((count*1.0/pageSize));
		if(currPage<=0){
			currPage=1;
		}
		if(currPage>=totalPage){
			currPage=totalPage;
		}
		PageBean<Student> pb = stus.getfenyestu(currPage, pageSize);
//		List<Student> listallstu = stus.getallstu();
		List<Student> liststu = pb.getList();
		model.addAttribute("pb", pb);
		model.addAttribute("liststu", liststu);
		return "admin/viewstu.jsp";
		
	}
	
	
	@RequestMapping("/viewtea")
	public String viewtea(Model model, int currPage) throws Exception{
		int pageSize=5;
		int count = teas.getcount();
		int totalPage=(int) Math.ceil((count*1.0/pageSize));
		if(currPage<=0){
			currPage=1;
		}
		if(currPage>=totalPage){
			currPage=totalPage;
		}
		PageBean<Teacher> pb = teas.getfenyetea(currPage, pageSize);
//		List<Teacher> listalltea = teas.getalltea();
		List<Teacher> listtea = pb.getList();
		model.addAttribute("pb", pb);
		model.addAttribute("listtea",listtea);
		return "admin/viewtea.jsp";
		
	}
	
	@RequestMapping("/addstu")
	public String viewtea(Student stu, Model model) throws Exception{
		Student getstu = stus.getstu(stu.getSid());
		if(getstu==null){
			stus.insertstu(stu);
			model.addAttribute("succ", "添加成功");
		}else{
			model.addAttribute("err", "已存在该编号的学生");
		}
		return "admin/addstu.jsp";
		
	}
	
	
	
	@RequestMapping("/addtea")
	public String viewtea(Teacher tea, Model model) throws Exception{
		Teacher gettea = teas.gettea(tea.getTid());
		if(gettea==null){
			teas.inserttea(tea);
			model.addAttribute("succ", "添加成功");
		}else{
			model.addAttribute("err", "已存在该编号的教师");
		}
		return "admin/addtea.jsp";
		
	}
	
	//点击查看课程
	@RequestMapping("viewcurri")
	public String viewcurri(Model model, int currPage) throws Exception{
		int pageSize=10;
		int count = curs.getcount();
		int totalPage=(int) Math.ceil((count*1.0/pageSize));
		if(currPage<=0){
			currPage=1;
		}
		if(currPage>=totalPage){
			currPage=totalPage;
		}
		PageBean<Curriculum> pb = curs.getfenyecur(currPage, pageSize);
		List<Curriculum> listcur = pb.getList();
		model.addAttribute("pb", pb);
		model.addAttribute("listcur",listcur);
		return "admin/viewcurri.jsp";
		
	}
	
	
	//修改密码，点击提交
		@RequestMapping("/admuppwd")
		public String uppwd(String oldpwd, String newpwd, HttpServletRequest request, Model model)throws Exception{
			HttpSession session = request.getSession();
			Admin adm=(Admin) session.getAttribute("adm");
			if(adm.getApassword().equals(oldpwd)){
				adm.setApassword(newpwd);
				adms.uppwd(adm);
			}else{
				model.addAttribute("pwderr","你的原始密码有误");
				return "admin/updatepwd.jsp";
			}
			return "admin/uppwdsuccess.jsp";
			
		}
	
		
		
	//点击添加课程功能
		@RequestMapping("addcurri")
		public String addcurri(Model model) throws Exception{
			List<Teacher> listalltea = teas.getalltea();
			model.addAttribute("listtea",listalltea);
			return "admin/addcurri.jsp";
				
		}
		
	//填写课程信息后，点击提交
		@RequestMapping("submitcurri")
		public String submitcurri(Curriculum cur, Model model) throws Exception{
			Curriculum curri = curs.getbyid(cur.getCid());
			if(curri==null){
				curs.addcur(cur);
				model.addAttribute("succ", "添加成功");
			}else{
				model.addAttribute("err", "该编号的课程已存在");
			}
			List<Teacher> listalltea = teas.getalltea();
			model.addAttribute("listtea",listalltea);
			return "admin/addcurri.jsp";
			
		}
	
		
		//点击学生选课
		@RequestMapping("stuselectcur")
		public String stuselectcur(Model model, int currPage)throws Exception{
			int pageSize=10;
			int count = stus.getcount();
			int totalPage=(int) Math.ceil((count*1.0/pageSize));
			if(currPage<=0){
				currPage=1;
			}
			if(currPage>=totalPage){
				currPage=totalPage;
			}
			PageBean<Student> pb = stus.getfenyestu(currPage, pageSize);
//			List<Student> listallstu = stus.getallstu();
			List<Student> liststu = pb.getList();
			model.addAttribute("pb", pb);
			model.addAttribute("liststu", liststu);
			return "admin/stuselectcur.jsp";
		}
		
		
		
		//点击教师任课
		@RequestMapping("teaselectcur")
		public String teaselectcur(Model model, int currPage)throws Exception{
			int pageSize=10;
			int count = teas.getcount();
			int totalPage=(int) Math.ceil((count*1.0/pageSize));
			if(currPage<=0){
				currPage=1;
			}
			if(currPage>=totalPage){
				currPage=totalPage;
			}
			PageBean<Teacher> pb = teas.getfenyetea(currPage, pageSize);
			List<Teacher> listalltea = pb.getList();
			model.addAttribute("pb", pb);
			model.addAttribute("listtea",listalltea);
			return "admin/teaselectcur.jsp";
		}
		
		
		//点击选课详情
		@RequestMapping("selectcurdetails")
		public String selectcurdetails(Model model, String ssid, String sname)throws Exception{
			List<ManyTable> liststu_curbystuid = mts.getstu_curbystuid(ssid);
			if(liststu_curbystuid.size()==0){
				model.addAttribute("sname", sname);
				return "admin/noselectcur.jsp";
			}
			model.addAttribute("liststu_curbystuid", liststu_curbystuid);
			model.addAttribute("sname", sname);
			return "admin/stucurdetails.jsp";
			
		}
		
		//点击教师任课详情
		@RequestMapping("teacurdetails")
		public String teacurdetails(Model model, String tid, String tname)throws Exception{
			List<Curriculum> listcurbytid = curs.getcurbytid(tid);
			if(listcurbytid.size()==0){
				model.addAttribute("tname", tname);
				return "admin/teanocur.jsp";
			}
			model.addAttribute("listcurbytid", listcurbytid);
			model.addAttribute("tname", tname);
			model.addAttribute("tid", tid);
			return "admin/teacurdetails.jsp";
				
		}
		
		
		
		
		//修改学生信息
		@RequestMapping("updatestu")
		public String updatestu(Student stu, Model model)throws Exception{
			stus.updatestu(stu);
			List<Student> listallstu = stus.getallstu();
			model.addAttribute("liststu", listallstu);
			return "/viewstu?currPage=1";
			
		}
		
		
		//修改教师信息
		@RequestMapping("updatetea")
		public String updatetea(Teacher tea, Model model)throws Exception{
			teas.updatetea(tea);
			List<Teacher> listalltea = teas.getalltea();
			model.addAttribute("listtea",listalltea);
			return "/viewtea?currPage=1";
			
		}
		
		
		//删除学生
		@RequestMapping("/delstu")
		public String  delstu(HttpServletRequest request)throws Exception{
			String sid = request.getParameter("sid");
			stus.delstu(sid);
			return "/viewstu?currPage=1";
			
		}
		
		//删除老师
		@RequestMapping("/deltea")
		public String deltea(String tid)throws Exception{
			teas.deltea(tid);
			return "/viewtea?currPage=1";
			
		}
		
		//删除课程
		@RequestMapping("/deletecurriculumbyid")
		public String deletecurriculumbyid(int cid)throws Exception{
			curs.deletecur(cid);
			return "/viewcurri?currPage=1";	
		}
		
		//点击修改课程
		@RequestMapping("/updatecurr")
		public String updatecurr(Model model)throws Exception{
			List<Teacher> listalltea = teas.getalltea();
			model.addAttribute("listtea",listalltea);
			return "admin/updatecurr.jsp";
				
		}
		
		
		//课程里点击确认修改
		@RequestMapping("/querenxiugai")
		public String querenxiugai(Model model, Curriculum curr){
			curs.querenxiugai(curr);
			return "viewcurri?currPage=1";
		}
		
		//按姓名查询学生
		@RequestMapping("/nameselect")
		public String nameselect(String name, Model model)throws Exception{
			List<Student> liststu = stus.findbyname(name);
			if(liststu.size()==0){
				return "admin/NewFile1.jsp";
			}
			model.addAttribute("liststu", liststu);
			return "admin/NewFile2.jsp";	
		}
		
		
		//按姓名查询老师
				@RequestMapping("/tnameselect")
				public String tnameselect(String name, Model model)throws Exception{
					List<Teacher> listtea = teas.findteabyname(name);
					if(listtea.size()==0){
						return "admin/NewFile3.jsp";
					}
					model.addAttribute("listtea", listtea);
					return "admin/NewFile4.jsp";	
				}
}
