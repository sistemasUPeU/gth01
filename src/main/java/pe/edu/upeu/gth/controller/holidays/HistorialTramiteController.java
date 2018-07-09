package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import pe.edu.upeu.gth.dao.HistorialTramiteDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@RequestMapping("/vacaciones/historial")
public class HistorialTramiteController {

	
Gson GSON = new Gson();
	
	@Autowired
	public MailService ms;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(path = "/readallHistorialTramite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllHistorialTramite(HttpServletRequest RQ, Authentication authentication) {
		authentication=SecurityContextHolder.getContext().getAuthentication();
		String idtrab = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
		System.out.println(idtrab + " :ID_TRABAJADOR");
		HistorialTramiteDAO DAO = new HistorialTramiteDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READALL(idtrab));
	}
	
	@RequestMapping(path = "/readHistorialTramite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getHistorial(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		System.out.println(id);
		HistorialTramiteDAO DAO = new HistorialTramiteDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READ(id));
	}
}
