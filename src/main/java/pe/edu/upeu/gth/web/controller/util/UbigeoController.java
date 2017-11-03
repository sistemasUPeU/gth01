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
import pe.edu.upeu.gth.dao.UbigeoDAO;

@Controller
@Scope("request")
@RequestMapping("/ubigeo/")
public class UbigeoController {
	
	Gson gson = new Gson();
	
	UbigeoDAO ubi = new UbigeoDAO(AppConfig.getDataSource());
	Carrera_UniversidadDAO caruni = new Carrera_UniversidadDAO(AppConfig.getDataSource());
	
	Map<String, Object> rpta = new HashMap<String,Object>();
	
	
	@RequestMapping(value="/dep_nac", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> dep_nac(HttpServletRequest request, HttpServletResponse response) {
		String id_dep = request.getParameter("id_dep");
		List<Map<String, Object>> lista = ubi.Provincia(id_dep);
		rpta.put("list", "1");
		rpta.put("lista", lista);		
		return rpta;
	}
	
	@RequestMapping(value="/pro_nac", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> pro_nac(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id_dist");
        List<Map<String, Object>> lista = ubi.Distrito(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        return rpta;
	}
	
	@RequestMapping(value="/Lista_D", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> Lista_D(HttpServletRequest request, HttpServletResponse response) {
		 List<Map<String, Object>> lista = ubi.Departamento();
         rpta.put("rpta", "1");
         rpta.put("lista", lista);
         return rpta;
	}
	
	@RequestMapping(value="/Listar_P", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object>  Listar_P(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id_dep");
        List<Map<String, Object>> lista = ubi.Provincia(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        return rpta;
	}
	
	@RequestMapping(value="Listar_Di", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object>  Listar_Di(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id_pro");
        List<Map<String, Object>> lista = ubi.Distrito(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        return rpta;
	}
	
}
