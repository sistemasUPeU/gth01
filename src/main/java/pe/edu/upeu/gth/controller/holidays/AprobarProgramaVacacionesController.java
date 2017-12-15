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
import pe.edu.upeu.gth.dao.AprobarProgramaVacaciones;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@RequestMapping("/vacaciones/programa_vacaciones")
public class AprobarProgramaVacacionesController {
	DataSource ds = AppConfig.getDataSource();
	AprobarProgramaVacaciones t = new AprobarProgramaVacaciones(ds);

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSinAprobar(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		Gson g = new Gson();
		return g.toJson(t.listarSinAprobar(depa));
	}

	@RequestMapping(path = "/getAprobados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAprobados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		Gson g = new Gson();
		return g.toJson(t.listarAprobados(depa));
	}

	@RequestMapping(path = "/getRechazados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRechazados(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		Gson g = new Gson();
		return g.toJson(t.listarRechazados(depa));
	}

	@RequestMapping(path = "/guardarAprobar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarAprobar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] idarray = id_det.split(",");
		Gson g = new Gson();
		return g.toJson(t.apobarVac(usuario, idarray));
	}

	@RequestMapping(path = "/guardarObservar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarObservar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String text = request.getParameter("text");
		String emisor = request.getParameter("emisor");
		String receptor = request.getParameter("receptor");
		Gson g = new Gson();
		return g.toJson(t.observarVac(usuario, id_det, text, emisor, receptor));
	}
}
