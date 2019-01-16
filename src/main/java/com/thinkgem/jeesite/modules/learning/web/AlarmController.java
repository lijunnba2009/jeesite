package com.thinkgem.jeesite.modules.learning.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "${adminPath}/learning/alarm")
public class AlarmController {

	
	@RequestMapping(value = {"index", ""})
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "modules/learning/alarm";
	}

}
