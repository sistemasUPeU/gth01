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
import org.springframework.stereotype.Component;
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
import pe.edu.upeu.gth.dao.MailServiceImpl;
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

	@GetMapping("/consolidado")
	public ModelAndView vac_gest_consol(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/vac_gest_consol");

	}

	@GetMapping("/programa_vacaciones")
	public ModelAndView programa_vacaciones(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/aprobar_pv");

	}
	
	@GetMapping("/historial")
	public ModelAndView historial_tramite(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vacaciones/historial_tramite");
	}
	
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

	@Autowired
	ServletContext context;

	@RequestMapping(path = "/confirmarListaFiltrada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String confirmarListaFiltrada() {
		TrabajadorFiltradoDAO DAO = new TrabajadorFiltradoDAO(AppConfig.getDataSource());

		// AbstractApplicationContext context = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		//
		// MailService mailService = (MailService) context.getBean("mailService");
		// mailService.sendEmail(getDummyOrder());
		//
		// ((AbstractApplicationContext) context).close();

		// emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(),
		// mailObject.getSubject(),
		// template,
		// mailObject.getText());

		List<Map<String, Object>> lista = new ArrayList<>();
		lista = DAO.GetEmail();
		String[] arrayEmail = new String[lista.size()];
		System.out.println(GSON.toJson(lista));
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).get("DI_CORREO_PERSONAL"));
			arrayEmail[i] = lista.get(i).get("DI_CORREO_PERSONAL").toString();
		}
		// ms.sendEmail(getDummyOrder(), arrayEmail);
		return GSON.toJson(DAO.CONFIRMAR());
	}

	public static ProductOrder getDummyOrder() {
		ProductOrder order = new ProductOrder();
		order.setOrderId("1111");
		order.setProductName("Thinkpad T510");
		order.setStatus("confirmed");

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setName("Websystique Admin");
		customerInfo.setAddress("WallStreet");
		customerInfo.setEmail("104granados@gmail.com");
		order.setCustomerInfo(customerInfo);
		return order;
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
}
