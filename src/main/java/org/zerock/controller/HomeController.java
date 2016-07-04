package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wayne on 2016. 6. 30..
 *
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
