package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.GestionarPrograVacacDAO;
import pe.edu.upeu.gth.dto.CustomUser;



@Controller
@RequestMapping("/vacaciones/gestionar_programa")
public class ProgramacionController {
	Gson GSON = new Gson();
	DataSource ds = AppConfig.getDataSource();
	GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(ds);

	@Autowired
	ServletContext context;
	
	@RequestMapping(path = "GestionarProgramaVacaciones/readallProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllProgramaVacaciones(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
//		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return GSON.toJson(DAO.READALL(depa));
	}

	@RequestMapping(path = "GestionarProgramaVacaciones/TrabajadoresConSoliProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadoresConSoli(Authentication authentication) {	
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
//		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		System.out.println(depa);
		return GSON.toJson(DAO.TrabajadoresConSoli(depa));
	}
	@RequestMapping(path = "GestionarProgramaVacaciones/TrabajadoresAprobados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllTrabajadoresAprobados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
//		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
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
	
	@RequestMapping(path = "/readFechaMod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String leerFechaMod(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
//		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READFECHA(id));
	}
	
	@RequestMapping(path = "/updateFechaMod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarFechaMod(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		String inicio = RQ.getParameter("inicio");
		String fin = RQ.getParameter("fin");
//		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.UPDATEFECHA(id, inicio, fin));
	}
}
