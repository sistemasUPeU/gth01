package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.GestionarConsolidadoDAO;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@RequestMapping("/vacaciones/consolidado")
public class GestionarConsolidadoController {
	DataSource ds = AppConfig.getDataSource();
	GestionarConsolidadoDAO gc = new GestionarConsolidadoDAO(ds);
	Gson g = new Gson();

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllEmployee() {
		return g.toJson(gc.listarConsolidado());
	}
	@RequestMapping(path = "/guardarAprovarConsolidado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarAprovar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] idarray = id_det.split(",");
		return g.toJson(gc.apobarVacCon(usuario, idarray));
	}
	
	@RequestMapping(path = "/readFechas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getFirma(HttpServletRequest request) {
		String id = request.getParameter("id");
		return g.toJson(gc.readFechas(id));
	}
}
