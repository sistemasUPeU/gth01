package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@Scope("request")
@RequestMapping("/reportes/")
public class ReporteRenunciasController {
	
	Gson gson = new Gson();
	
	
	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_reportes");
	}

	
	@GetMapping("/hola")
	public ModelAndView HOLA(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_renuncia1");
	}

}
