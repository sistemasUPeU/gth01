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
import pe.edu.upeu.gth.dao.ControlFirmasDAO;
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
	
	@RequestMapping(path = "/readallControlFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllControlFirma() {
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READALL());
	}
	
	@RequestMapping(path = "/readFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getFirma(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		System.out.println(id);
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.READFECHA(id));
	}

	//USELESS
	@RequestMapping(path = "/updateFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarFirma(HttpServletRequest RQ, Authentication authentication) {
    	authentication=SecurityContextHolder.getContext().getAuthentication();
		String idtrab = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id = RQ.getParameter("id");
		int inicio = Integer.parseInt(RQ.getParameter("inicio"));
		int fin = Integer.parseInt(RQ.getParameter("fin"));
		System.out.println(idtrab + " SIIII");
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.UPDATEFECHA(id, inicio, fin, idtrab));
	}
	
	@RequestMapping(path = "/updatePapeletaFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarPapeletaFirma(HttpServletRequest RQ, Authentication authentication) {
    	authentication=SecurityContextHolder.getContext().getAuthentication();
		String idtrab = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id = RQ.getParameter("id");
		System.out.println(idtrab + " " + id);
		ControlFirmasDAO DAO = new ControlFirmasDAO(AppConfig.getDataSource());
		return GSON.toJson(DAO.UPDATEPAPELETA(id, idtrab));
	}
}
