package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/GestionarProgramaVacaciones")
public class ProgramacionController {
	@GetMapping("/GestionarProgramaVacaciones")
	public ModelAndView GestionarProgramaVacaciones(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("GestionarProgramaVacaciones");
		return new ModelAndView("vacaciones/GestionarProgramaVacaciones");

	}
}
