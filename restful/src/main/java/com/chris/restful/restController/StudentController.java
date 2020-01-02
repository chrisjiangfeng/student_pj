package com.chris.restful.restController;

import com.chris.ser.po.Curriculum;
import com.chris.ser.po.Evaluate;
import com.chris.ser.po.Student;
import com.chris.ser.po.Studentandcurriculum;
import com.chris.ser.pojo.ManyTable;
import com.chris.ser.service.CurriculumService;
import com.chris.ser.service.EvaluateService;
import com.chris.ser.service.ManyTableService;
import com.chris.ser.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
	
	@Resource(name="studentServiceImpl")
	private StudentService stus;
	
	@Resource(name="curriculumServiceImpl")
	private CurriculumService curs;
	
	@Resource(name="evaluateServiceImpl")
	private EvaluateService evaService;
	//修改密码，点击提交
	@RequestMapping("/stuuppwd")
	public String uppwd(String oldpwd, String newpwd, HttpServletRequest request, Model model)throws Exception{
		HttpSession session = request.getSession();
		Student stu=(Student) session.getAttribute("stu");
		if(stu.getSpassword().equals(oldpwd)){
			stu.setSpassword(newpwd);
			stus.uppwd(stu);
		}else{
			model.addAttribute("pwderr","你的原始密码有误");
			return "student/updatepwd.jsp";
		}
		return "student/uppwdsuccess.jsp";
		
	}
	
	//学生点击进行评教
	@RequestMapping("/aa")
	public String allpj(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		Student stu = (Student) session.getAttribute("stu");
		List<ManyTable> listpj =  mts.getall(stu.getSid());
		if(listpj.size()==0){
			return "student/nocurr.jsp";
		}else{
		model.addAttribute("listpj", listpj);
		return "student/allpingjiao.jsp";	
		}
	}
	
	
	
	@Resource(name="manyTableServiceImpl")
	private ManyTableService mts;
	
	@Resource(name="evaluateServiceImpl")
	private EvaluateService evas;
	
	//学生提交评教
	@RequestMapping("/subpj")
	public String subpj(HttpServletRequest request, int eid, String advise, Model model)throws Exception{
		int  a = Integer.parseInt(request.getParameter("1"));
		int  b = Integer.parseInt(request.getParameter("2"));
		int  c = Integer.parseInt(request.getParameter("3"));
		int  d = Integer.parseInt(request.getParameter("4"));
		int  e = Integer.parseInt(request.getParameter("5"));
		int  f = Integer.parseInt(request.getParameter("6"));
		int  g = Integer.parseInt(request.getParameter("7"));
		int  h = Integer.parseInt(request.getParameter("8"));
		int  i = Integer.parseInt(request.getParameter("9"));
		int  j = Integer.parseInt(request.getParameter("10"));
		int sum=a+b+c+d+e+f+g+h+i+j;
		if(advise==""){
			advise=null;
		}
		Date date=new Date();
		Evaluate eva=new Evaluate();
		eva.setEid(eid);
		eva.setEscore(sum);
		eva.setEcomment(advise);
		eva.setEdate(date);
		evas.uppj(eva);
		HttpSession session = request.getSession();
		Student stu = (Student) session.getAttribute("stu");
		List<ManyTable> listpj =  mts.getall(stu.getSid());
		model.addAttribute("listpj", listpj);
		return "student/allpingjiao.jsp";
	}
	
	//点击添加选课
	@RequestMapping("addmycur")
	public String addmycur(Model model)throws Exception{
		List<Curriculum> listallcur = curs.getallcur();
		model.addAttribute("listallcur", listallcur);
		return "student/addmycur.jsp";
			
	}
	
	
	@RequestMapping("stuaddcur")
	public String stuaddcur(HttpSession session, Model model, int scid ) throws Exception{
		Student stu = (Student) session.getAttribute("stu");
		Studentandcurriculum stucur=new Studentandcurriculum();
		stucur.setScid(scid);
		System.out.println(stu.getSid());
		stucur.setSsid(stu.getSid());
		Studentandcurriculum myselect = stus.findmyselect(stucur);
		if(myselect==null){
			stus.stuaddcur(stucur);
			Evaluate eva=new Evaluate();
			eva.setEsid(stucur.getSsid());
			eva.setEcid(stucur.getScid());
			evaService.addstuselect(eva);
			model.addAttribute("succ", "添加选课成功!");
		}else{
			model.addAttribute("err", "你已经选了该课程!");
		}
		List<Curriculum> listallcur = curs.getallcur();
		model.addAttribute("listallcur", listallcur);
		return "student/addmycur.jsp";
		
	}
	
	
}
