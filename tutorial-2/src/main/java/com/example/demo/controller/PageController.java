package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
	}
	
	@RequestMapping(value= {"/challenge", "challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if(name.isPresent()) {
			model.addAttribute("name", name.get());
		}
		else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String generator(@RequestParam(value="a", defaultValue="0") Integer a, @RequestParam(value="b", defaultValue="0") Integer b, @RequestParam(value="isi", defaultValue="hm") String isi, Model model) {
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		if(a==0) {
			String hms = "hm";
			model.addAttribute("isi", multipleWords(hms, b));
		}
		
		else if(b==0) {
			String hms = "h" + multipleMs("m", a);
			model.addAttribute("isi", hms);
		}
		
		else {
			String hms = "h" + multipleMs("m", a);
			model.addAttribute("isi", multipleWords(hms, b));
		}
		
		return "generator";
	}
	
	public static String multipleMs(String s, int times) {
		for(int i=0; i<times-1; i++) {
			s+=s.charAt(0);
		}
		return s;
	}
	
	public static String multipleWords(String s, int times) {
		int length = s.length();
		for(int j=0; j<times-1; j++) {
			s+=" ";
			s+=s.substring(0, length);
		}
		return s;
	}

	
}
