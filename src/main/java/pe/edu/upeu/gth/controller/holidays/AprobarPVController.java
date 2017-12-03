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
import pe.edu.upeu.gth.dao.TrabajadorVacDAO;
//import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@RequestMapping("/vacaciones/programa_vacaciones")
public class AprobarPVController {
	DataSource ds = AppConfig.getDataSource();
	TrabajadorVacDAO t = new TrabajadorVacDAO(ds);

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllEmployee(Authentication authentication) {
		String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP();
		System.out.println(depa);
		Gson g = new Gson();
		return g.toJson(t.list_trab_vac(depa));
	}

	@RequestMapping(path = "/guardarAprovar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarAprovar(HttpServletRequest request, Authentication authentication) {
		String usuario = ((CustomUser) authentication.getPrincipal()).getUsername();
		String id_det = request.getParameter("id_det");
		String[] idarray = id_det.split(",");
		Gson g = new Gson();
		return g.toJson(t.apobarVac(usuario, idarray));
	}
}
