package com.thinkgem.jeesite.modules.learning.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/learning/gallery")
public class GalleryController {

	
	@RequestMapping(value = {"index", ""})
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "modules/learning/gallery";
	}
	
	@RequestMapping(value = {"statistics", ""})
	public String statistics(HttpServletRequest request, HttpServletResponse response) {
		return "modules/learning/gallery_statistics";
	}
	

}
