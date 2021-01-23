package com.codesp07.springmvcboot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

		@RequestMapping("/")
		public String home() {
//			System.out.println("Home page is Requested");
			return "index.jsp";
		}
		
		
//		@RequestMapping("add")
//		public ModelAndView add(@RequestParam("num1") int i, @RequestParam("num2") int j)
//		{
	//  //	ModelAndView mv = new ModelAndView("result.jsp");
		
//			ModelAndView mv = new ModelAndView();
//			mv.setViewName("result.jsp");
//		
//			int num3 = i + j;
//			mv.addObject("num3",num3);
//			
//			return mv;
//		}
		
		@RequestMapping("add")
		public String add(@RequestParam("num1") int i,@RequestParam("num2") int j,Model m) {
			int num3 = i+j;
			m.addAttribute("num3",num3);
			
			return "result.jsp";
		}
		
		
//		@RequestMapping("add")
//		public String add(HttpServletRequest req) {
//			int i = Integer.parseInt(req.getParameter("num1"));
//			int j = Integer.parseInt(req.getParameter("num2"));
//			
//			int num3 = i+j;
//			HttpSession session = req.getSession();
//			
//			session.setAttribute("num3", num3);
//			return "result.jsp";
//		}
}
