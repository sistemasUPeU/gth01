package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ControlFirmasDAO;
import pe.edu.upeu.gth.dao.GestionarConsolidadoDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@RequestMapping("/vacaciones/controlar")
public class ControlFirmasController {

	Gson GSON = new Gson();
	
	@Autowired
	public MailService ms;
	
	@Autowired
	ServletContext context;
	
	ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
	
	@RequestMapping(path = "/readallControlFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllControlFirma() {
		return GSON.toJson(DAO.READALL());
	}
	
	@RequestMapping(path = "/readFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getFirma(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		return GSON.toJson(DAO.READFECHA(id));
	}
	
	@RequestMapping(path = "/updatePapeletaFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int actualizarPapeletaFirma(HttpServletRequest RQ, Authentication authentication) {
    	authentication=SecurityContextHolder.getContext().getAuthentication();
		String idtrab = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id = RQ.getParameter("id");
		String[] id_det_arr = new String[1];
		id_det_arr[0] = id;
		GestionarConsolidadoDAO GC = new GestionarConsolidadoDAO(AppConfig.getDataSource());
		return GC.insertHistorial(idtrab, id_det_arr, "PAS-000052", 3, "PAS-000090", 5);
	}
}
