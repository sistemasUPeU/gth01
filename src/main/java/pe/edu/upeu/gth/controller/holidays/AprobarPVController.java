package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.TrabajadorVacDAO;
//import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@RequestMapping("/vacaciones/programa_vacaciones")
public class AprobarPVController {
	DataSource ds = AppConfig.getDataSource();
	TrabajadorVacDAO t = new TrabajadorVacDAO(ds);

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllEmployee() {
		Gson g = new Gson();
		return g.toJson(t.list_trab_vac());
	}

	@RequestMapping(path = "/guardarAprovar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String guardarAprovar(HttpServletRequest request) {
		String usuario = request.getParameter("usuario");
		String apellidos = request.getParameter("id_det");
		Gson g = new Gson();
		return g.toJson(t.apobarVac(usuario, apellidos));
	}
}
