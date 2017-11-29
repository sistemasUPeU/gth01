package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.TrabajadorFiltradoDAO;


@Controller
@Scope("request")
@RequestMapping("/vacaciones")

public class PrincipalController {

	Gson GSON = new Gson();
	
	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/default");
	}

	@GetMapping("/GestionarProgramaVacaciones")
	public ModelAndView GestionarProgramaVacaciones(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/GestionarProgramaVacaciones");

	}
	@GetMapping("/control_firma_vacaciones")
	public ModelAndView control_firma_vacaciones(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/control_firma_vacaciones");
	}
	
	@GetMapping("/gestionar_lista_filtrada")
	public ModelAndView gestionar_lista_filtrada(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/gestionar_lista_filtrada");

	}
	
	@RequestMapping(path = "/readallTrabajadorFiltrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadorFiltrado() {
		TrabajadorFiltradoDAO DAO = new TrabajadorFiltradoDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READALL());
	}
	
	@GetMapping("/programa_vacaciones")
	public ModelAndView aprobarPV(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/AprobarPV");

	}
}
