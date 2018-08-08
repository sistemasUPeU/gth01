package pe.edu.upeu.gth.controller.holidays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.AdelantoVacacionesDAO;
import pe.edu.upeu.gth.dao.ControlFirmasDAO;
import pe.edu.upeu.gth.dao.GestionarPrograVacacDAO;
import pe.edu.upeu.gth.dao.HistorialTramiteDAO;
import pe.edu.upeu.gth.dao.MailServiceImpl;
import pe.edu.upeu.gth.dao.Notificacion_VacDAO;
import pe.edu.upeu.gth.dao.TrabajadorFiltradoDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.CustomerInfo;
import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@Scope("request")
@Component
@RequestMapping("/vacaciones")

public class PrincipalController {

	Gson GSON = new Gson();
	// @Autowired
	// public MailServiceImpl emailService;

	@Autowired
	public MailService ms;

	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_gest_solic");
	}

	@GetMapping("/solicitud")
	public ModelAndView solicitud(HttpServletRequest request, HttpServletResponse response) {
		// int op= Integer.parseInt(request.getParameter("op"));
		System.out.println("estoy en solicitud");

		return new ModelAndView("vacaciones/vac_gest_solic");
	}

	@GetMapping("/gestionar_programa")
	public ModelAndView gestionar_programa(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/gestionar_programa");
	}

	@GetMapping("/control_firma_vacaciones")
	public ModelAndView control_firma_vacaciones(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/control_firma_vacaciones");
	}

	@GetMapping("/gestionar_lista_filtrada")
	public ModelAndView gestionar_lista_filtrada(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/gestionar_lista_filtrada");
	}
	
	@GetMapping("/historial")
	public ModelAndView historial_tramite(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/historial_tramite");
	}

	@GetMapping("/consolidado")
	public ModelAndView vac_gest_consol(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_gest_consol");

	}
	@GetMapping("/adelanto_vac")
	public ModelAndView adelanto_vac(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/adelanto_vac");

	}
	@RequestMapping(path = "/readallTrabajadorFiltradoAdelanto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadorFiltradoAdelanto() {
		AdelantoVacacionesDAO DAO = new AdelantoVacacionesDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READALL());
	}
	@GetMapping("/programa_vacaciones")
	public ModelAndView programa_vacaciones(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/aprobar_pv");

	}

	@GetMapping("/generar_papeleta")
	public ModelAndView generar_papeleta(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/generar_papeleta");

	}

	@RequestMapping(path = "GestionarProgramaVacaciones/readallProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	// @RequestMapping(path = "/readallProgramaVacaciones", method =
	// RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody String getAllProgramaVacaciones(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return GSON.toJson(DAO.READALL(depa));

	}

	@RequestMapping(path = "GestionarProgramaVacaciones/TrabajadoresConSoliProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	// @RequestMapping(path = "/readallProgramaVacaciones", method =
	// RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody String getAllTrabajadoresConSoli(Authentication authentication) {
		
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return GSON.toJson(DAO.TrabajadoresConSoli(depa));

	}
	@RequestMapping(path = "/insertarAdelanto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String insertarAdelanto(Authentication authentication, HttpServletRequest RQ) {
		String idtra = RQ.getParameter("idtra");
		AdelantoVacacionesDAO DAO = new AdelantoVacacionesDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.INSERTAR_ADELANTO(idtra));
	}

	@RequestMapping(path = "GestionarProgramaVacaciones/TrabajadoresAprobados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	// @RequestMapping(path = "/readallProgramaVacaciones", method =
	// RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody String getAllTrabajadoresAprobados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return GSON.toJson(DAO.TrabajadoresAprobados(depa));

	}

	@RequestMapping(path = "GestionarProgramaVacaciones/insertProgramaVacaciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String insertProgramaVacaciones(HttpServletRequest request, Authentication authentication) {
		DataSource ds = AppConfig.getDataSource();
		GestionarPrograVacacDAO t = new GestionarPrograVacacDAO(ds);
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] asdf = id_det.split(",");
		Gson g = new Gson();
		return g.toJson(t.apobarVac(usuario, asdf));
	}

	@GetMapping("/reporte")
	public ModelAndView reportes(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_reportes");
	}
	
	@RequestMapping(path = "/readFechaMod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String leerFechaMod(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READFECHA(id));
	}
	
	@RequestMapping(path = "/updateFechaMod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarFechaMod(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		String inicio = RQ.getParameter("inicio");
		String fin = RQ.getParameter("fin");
		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.UPDATEFECHA(id, inicio, fin));
	}
	
	@RequestMapping(path = "/getNoti", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String GetNoti(HttpServletRequest request, Authentication authentication) {
		DataSource ds = AppConfig.getDataSource();
		Notificacion_VacDAO t = new Notificacion_VacDAO(ds);
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		String traba = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
		Gson g = new Gson();
		return g.toJson(t.GetNoti(depa, traba));
	}

	@RequestMapping(path = "/updateNoti", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateNoti(HttpServletRequest request, Authentication authentication) {
		DataSource ds = AppConfig.getDataSource();
		Notificacion_VacDAO t = new Notificacion_VacDAO(ds);
		String idnoti = request.getParameter("idnoti");
		t.updateNoti(idnoti);
		return "redirect:";
	}
}
