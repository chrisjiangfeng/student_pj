package com.chris.restful.restController;


import com.chris.ser.po.Curriculum;
import com.chris.ser.po.Evaluate;
import com.chris.ser.po.Teacher;
import com.chris.ser.service.EvaluateService;
import com.chris.ser.service.ManyTableService;
import com.chris.ser.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeacherController {
	
	@Resource(name="teacherServiceImpl")
	private TeacherService teas;
	
	//修改密码
	@RequestMapping("/teauppwd")
	public String uppwd(String oldpwd, String newpwd, HttpServletRequest request, Model model)throws Exception{
		HttpSession session = request.getSession();
		Teacher tea= (Teacher) session.getAttribute("tea");
		if(tea.getTpassword().equals(oldpwd)){
			tea.setTpassword(newpwd);
			teas.uppwd(tea);
		}else{
			model.addAttribute("pwderr","你的原始密码有误");
			return "teacher/updatepwd.jsp";
		}
		return "teacher/uppwdsuccess.jsp";
		
	}
	
	
	@Resource(name="manyTableServiceImpl")
	private ManyTableService mts;
	
	//教师点击查看评教
		@RequestMapping("/seepj")
		public String seepj(HttpServletRequest request, Model model) throws Exception{
			HttpSession session = request.getSession();
			Teacher tea=(Teacher)session.getAttribute("tea");
			String tid = tea.getTid();
			List<Curriculum> listcur = mts.getcur(tid);
			model.addAttribute("listcur",listcur);
			return "teacher/seepingjiao.jsp";
			
		}
		
		@Resource(name="evaluateServiceImpl")
		private EvaluateService evas;
		
		//教师详情查看
		@RequestMapping("/view")
		public String view(HttpServletRequest request, Model model)throws Exception{
			int cid = Integer.parseInt(request.getParameter("cid"));
			List<Evaluate> selectcinzai = evas.selectcinzai(cid);
			if(selectcinzai.size()==0){
				return "pjnotfinish.jsp";
			}
			List<Evaluate> listeva = mts.geteva(cid);
			int sum=0;
			int temp=0;
			int flag=0;
			for(Evaluate eva:listeva){
				if(eva.getEscore()==null){
					continue;
				}
				sum+=eva.getEscore();
				flag++;
				if(eva.getEcomment()!=null&&eva.getEcomment()!=""){
					temp++;
				}
			}
			if(flag==0){
				return "pjnotfinish.jsp";
			}
			if(temp==0){
				model.addAttribute("no", "你没有收到任何来自学生的评价语!");
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
			model.addAttribute("listeva", listeva);
			model.addAttribute("cid", cid);
			return "teacher/viewpingjiao.jsp";
			
		}
		
	
	
	
}
