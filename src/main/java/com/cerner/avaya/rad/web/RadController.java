package com.cerner.avaya.rad.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RadController {
	private static final Logger log = LoggerFactory.getLogger(RadController.class);

	@RequestMapping("/")
	public String home(Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("auth", auth);
		return "home";
	}

	@RequestMapping("/anon")
	public String anon(Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		log.info("Auth Model:");
		log.info("auth: " + auth.toString());
		log.info("details: " + auth.getDetails().toString());
		log.info("principal: " + auth.getPrincipal().toString());
		
		model.addAttribute("auth", auth);
		return "anon";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("auth", auth);
		return "admin";
	}
}
