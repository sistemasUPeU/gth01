package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ConfiguracionDAO;

@Controller
@Scope("request")
@RequestMapping("/configuraciones")
public class ConfiguracionesController {
	DataSource ds = AppConfig.getDataSource();
	ConfiguracionDAO co =  new ConfiguracionDAO(ds);
	Gson gs = new Gson();
	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		
		return new ModelAndView("vacaciones/vac_configurar");
	}
	@RequestMapping(path = "/validar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validarConsolidado() {
		String x="1";
		String res = gs.toJson(co.consolidadoExists());
		System.out.println(res);
		if(res.equals("null")) {
			System.out.println("entro");
			x = "0";
		}
		System.out.println(x);
		return x;
	}
	
	@RequestMapping(path = "/insertarConsolidado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String crear(HttpServletRequest request) {
		String alerta = request.getParameter("fecha");
		
		

		return gs.toJson(co.crearConsolidado(alerta));
	}
	
	
	@RequestMapping(path = "/insertarPrograma", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String crearConfigPrograma(HttpServletRequest request) {
		String fecha = request.getParameter("fecha");
		
		

		return gs.toJson(co.crearConfigPrograma(fecha));
	}
	@RequestMapping(path = "/insertarSolicitud", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String crearConfigSolicitud(HttpServletRequest request) {
		String fecha1 = request.getParameter("fecha");
		
		

		return gs.toJson(co.crearConfigSolicitud(fecha1));
	}
	
	
	
	
}
