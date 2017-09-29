package pe.edu.upeu.gth.controller.principal;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("/login")
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			System.out.println("EstÃ¡ autenticado");
			return "menu";
		} else {
			System.out.println("No estÃ¡ autenticado");
			return "login";
		}*/
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Usuario y/o contraseña incorrecta!");
		}

		if (logout != null) {
			model.addObject("msg", "Has cerrado sesión.");
		}
		model.setViewName("login");
		
		return model;
	}

	@GetMapping(value = { "menu", "/"  })
	public String menu(HttpServletRequest request, HttpServletResponse response) {
		String opc = request.getParameter("opc");
		String pagina = "menu";
		/*
		 * try { if (opc == null) { pagina = "menu"; } if (opc.equals("logout")) {
		 * session.invalidate(); pagina = "login"; }
		 * 
		 * } catch (Exception e) { System.out.println("Error : " + e); }
		 */
		return pagina;
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			System.out.println("Logged Out Successfully!");
		}
		try {
			response.sendRedirect("login?logout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(value = { "home"})
	public String home(HttpServletRequest request, HttpServletResponse response) {
		String opc = request.getParameter("opc");
		HttpSession session = request.getSession(true);
		String pagina = "home";
		return pagina;
	}
}
