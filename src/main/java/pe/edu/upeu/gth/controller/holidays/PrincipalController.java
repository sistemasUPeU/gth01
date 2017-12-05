package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ControlFirmasDAO;
import pe.edu.upeu.gth.dao.GestionarPrograVacacDAO;
import pe.edu.upeu.gth.dao.TrabajadorFiltradoDAO;
import pe.edu.upeu.gth.dto.CustomUser;

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

	@GetMapping("/consolidado")
	public ModelAndView vac_gest_consol(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_gest_consol");

	}

	@GetMapping("/programa_vacaciones")
	public ModelAndView programa_vacaciones(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/AprobarPV");

	}
//
	@RequestMapping(path = "/readallTrabajadorFiltrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadorFiltrado() {
		TrabajadorFiltradoDAO DAO = new TrabajadorFiltradoDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READALL());
	}

	@RequestMapping(path = "/readallControlFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllControlFirma() {
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READALL());
	}

	@RequestMapping(path = "/confirmarListaFiltrada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String confirmarListaFiltrada() {
		TrabajadorFiltradoDAO DAO = new TrabajadorFiltradoDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.CONFIRMAR());
	}
	
	@RequestMapping(path = "/readFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getFirma(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		System.out.println(id);
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READFECHA(id));
	}
	
	@RequestMapping(path = "/updateFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarFirma(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		int inicio = Integer.parseInt(RQ.getParameter("inicio"));
		int fin = Integer.parseInt(RQ.getParameter("fin"));
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.UPDATEFECHA(id, inicio, fin));
	}

	@RequestMapping(path = "GestionarProgramaVacaciones/readallProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)


//	@RequestMapping(path = "/readallProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody String getAllProgramaVacaciones(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
	GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
	System.out.println(depa);
	return GSON.toJson(DAO.READALL(depa));
		
	}

	@RequestMapping(path = "GestionarProgramaVacaciones/insertProgramaVacaciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String insertProgramaVacaciones(HttpServletRequest request, Authentication authentication) {
		DataSource ds = AppConfig.getDataSource();
		GestionarPrograVacacDAO t = new GestionarPrograVacacDAO(ds);
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] asdf =id_det.split(",");
		Gson g = new Gson();
		return g.toJson(t.apobarVac(usuario, asdf));
	}
	

	
	@GetMapping("/reporte")
	public ModelAndView reportes(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_reportes");
	}
	
	

}
