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
	public @ResponseBody String getAllControlFirma(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		return GSON.toJson(DAO.READALL(depa));
	}

	@RequestMapping(path = "/readFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getFirma(HttpServletRequest RQ) {
		String id = RQ.getParameter("id");
		return GSON.toJson(DAO.READFECHA(id));
	}

	@RequestMapping(path = "/updatePapeletaFirma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int actualizarPapeletaFirma(HttpServletRequest RQ, Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idtrab = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id = RQ.getParameter("id");
		String[] id_det_arr = new String[1];
		id_det_arr[0] = id;
		GestionarConsolidadoDAO GC = new GestionarConsolidadoDAO(AppConfig.getDataSource());
		return GC.insertHistorial(idtrab, id_det_arr, "PAS-000052", 3, "PAS-000090", 5);
	}

	@RequestMapping(path = "/updateFirma", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String actualizarFirma(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id = request.getParameter("id");
		int inicio = Integer.parseInt(request.getParameter("inicio"));
		int fin = Integer.parseInt(request.getParameter("fin"));
		int fsm = Integer.parseInt(request.getParameter("fsm"));
		String[] id_det_arr = new String[1];
		id_det_arr[0] = id;
		GestionarConsolidadoDAO GC = new GestionarConsolidadoDAO(AppConfig.getDataSource());
		ControlFirmasDAO CF = new ControlFirmasDAO(AppConfig.getDataSource());
		if (inicio == 1 && fin == 1 && fsm == 3) {
			GC.insertHistorial(usuario, id_det_arr, "PAS-000090", 5, "PAS-000092", 6);
			GC.insertHistorial(usuario, id_det_arr, "PAS-000092", 6, "PAS-000092", 7);
			CF.ACTUALIZAR_ESTADO(id);
		} else if (inicio == 1 && fin == 1 && fsm == 1) {
			GC.insertHistorial(usuario, id_det_arr, "PAS-000092", 6, "PAS-000092", 7);
			CF.ACTUALIZAR_ESTADO(id);
		} else if (inicio == 1 && fin == 0 && fsm == 3) {
			GC.insertHistorial(usuario, id_det_arr, "PAS-000090", 5, "PAS-000092", 6);
		}
		return GSON.toJson(GC.updateFechas(id, inicio, fin));
	}
}
