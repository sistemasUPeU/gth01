package pe.edu.upeu.gth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping(value= {"index","/"})
	public String hello(Model model) {

		model.addAttribute("name", "John Doe");

		return "index";
	}
}
