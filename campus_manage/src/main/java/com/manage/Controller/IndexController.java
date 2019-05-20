package com.manage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/{urlName}")
	public String indexUrl(@PathVariable("urlName") String urlName) {
		return urlName;
	}
}
