package pe.edu.upeu.gth.controller.holidays;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JRException;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.GenerarPapeletaDAO;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@RequestMapping("/vacaciones/generar_papeleta")
public class GenerarPapeletaController {
	Gson g = new Gson();
	DataSource ds = AppConfig.getDataSource();
	GenerarPapeletaDAO gp = new GenerarPapeletaDAO(ds);

	@RequestMapping(path = "/validar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validarTipoSolicitud(Authentication authentication) {
		String idtra = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
		return g.toJson(gp.generarPapaleta(idtra));
	}

	@RequestMapping(path = "/reporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String verReporte(Model model, HttpServletRequest request, Authentication authentication,
			@RequestParam(name = "format", defaultValue = "pdf", required = false) String format) throws JRException {
		String idtra = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
		ServletContext cntx = request.getServletContext();
		String realPath = cntx.getRealPath("/resources/img/");
		try {
			List<Map<String, Object>> sd = gp.generarPapaleta(idtra);
			model.addAttribute("format", format);
			model.addAttribute("datasource", sd);
			model.addAttribute("realPath", realPath);
			model.addAttribute("AUTOR", "Tutor de programacion");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error controller, reporte: " + e);
		}
		return "papeleta_report";
	}
}