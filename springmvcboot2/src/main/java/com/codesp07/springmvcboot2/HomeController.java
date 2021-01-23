package com.codesp07.springmvcboot2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codesp07.springmvcboot2.model.Alien;

@Controller
public class HomeController {

	@ModelAttribute
	public void modelData(Model m) {
		m.addAttribute("name","Aliens");
	}
	
	
		@RequestMapping("/")
		public String home() {
//			System.out.println("Home page is Requested");
			return "index.jsp";
		}

//		@PostMapping("addAlien")
		@RequestMapping("addAlien")
		public String addAlien(@ModelAttribute Alien a) { //modelAttribute is also responsible to add the data in the memory
			//whatever is coming from the client is setting up in a
			//addAlien(@ModelAttribute("a1") Alien a) it can also accept parameter if in result it is set by a different name apart from class name
			
			return "result.jsp";
		}
		
		
//	//here we are taking two values at a time using @Requestparam	and checking 1 by 1 
//		@RequestMapping("addAlien")
//		public String addAlien(@RequestParam("aid") int aid, @RequestParam("aname") String aname, Model m) {
//			Alien a = new Alien();
//			a.setAid(aid);
//			a.setAname(aname);
//			
//			m.addAttribute("alien",a);
//			
//			return "result.jsp";
//		}
}
