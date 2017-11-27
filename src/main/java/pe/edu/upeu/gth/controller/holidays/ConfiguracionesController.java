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
@RequestMapping("/configuraciones")
public class ConfiguracionesController {
	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_configurar");
	}
}
