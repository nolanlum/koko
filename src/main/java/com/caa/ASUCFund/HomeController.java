package com.caa.ASUCFund;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	Log log = LogFactory.getLog(HomeController.class);

	@RequestMapping(value="/")
	public String home() {
		log.info("herp");

		return "WEB-INF/views/home.jsp";
	}
}
