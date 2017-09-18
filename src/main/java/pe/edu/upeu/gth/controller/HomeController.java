package pe.edu.upeu.gth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping(value = { "login", "/" })
	public String hello(Model model) {
		return "login";
	}
	@RequestMapping(value = "/menu")
	public String menu(HttpServletRequest request, HttpServletResponse response) {
		String opc = request.getParameter("opc");
		HttpSession session = request.getSession(true);
		String pagina = "menu";
		try {
			if (opc == null) {
				pagina = "menu";
			}
			if (opc.equals("logout")) {
				session.invalidate();
				pagina = "login";
			}

		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		return pagina;
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            System.out.println("Logged Out Successfully!");
        }
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
