package pe.edu.upeu.gth.web.controller.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.Carrera_UniversidadDAO;

@Controller
@Scope("request")
@RequestMapping("/detalle_carrera/")
public class Carrera_InstitucionController {
	Gson gson = new Gson();
	
	Carrera_UniversidadDAO caruni= new Carrera_UniversidadDAO(AppConfig.getDataSource());
	
	Map<String, Object> rpta = new HashMap<String,Object>();
	
	@RequestMapping(value="/institucion", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> institucion(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("ti");
        List<Map<String, Object>> lista = caruni.Istitucion(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        return rpta;
	}
	
	@RequestMapping(value="/ti_inst", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> ti_inst(HttpServletRequest request, HttpServletResponse response) {
		 String reg = request.getParameter("regimen");
         List<Map<String, Object>> lista = caruni.Tipo_Institucion(reg);
         rpta.put("rpta", "1");
         rpta.put("lista", lista);
         return rpta;
	}
	
	@RequestMapping(value="/carrera", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> carrera(HttpServletRequest request, HttpServletResponse response) {
		String reg = request.getParameter("inst");
        List<Map<String, Object>> lista = caruni.Carrera_Id_universidad(reg);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        return rpta;
	}
}
