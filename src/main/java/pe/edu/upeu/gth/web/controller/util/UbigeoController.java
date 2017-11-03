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

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.Carrera_UniversidadDAO;
import pe.edu.upeu.gth.dao.UbigeoDAO;

@Controller
@Scope("request")
@RequestMapping("/ubigeo/")
public class UbigeoController {
	
	PrintWriter out;
	
	Gson gson = new Gson();
	
	UbigeoDAO ubi = new UbigeoDAO(AppConfig.getDataSource());
	Carrera_UniversidadDAO caruni = new Carrera_UniversidadDAO(AppConfig.getDataSource());
	
	Map<String, Object> rpta = new HashMap<String,Object>();
	
	@GetMapping("dep_nac")
	public void dep_nac(HttpServletRequest request, HttpServletResponse response) {
		String id_dep = request.getParameter("id_dep");
		List<Map<String, Object>> lista = ubi.Provincia(id_dep);
		rpta.put("list", "1");
		rpta.put("lista", lista);		
		respuesta(response);
	}
	
	@GetMapping("pro_nac")
	public void pro_nac(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id_dist");
        List<Map<String, Object>> lista = ubi.Distrito(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
	}
	
	@GetMapping("Lista_D")
	public void Lista_D(HttpServletRequest request, HttpServletResponse response) {
		 List<Map<String, Object>> lista = ubi.Departamento();
         rpta.put("rpta", "1");
         rpta.put("lista", lista);
	}
	
	@GetMapping("Listar_P")
	public void Listar_P(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id_dep");
        List<Map<String, Object>> lista = ubi.Provincia(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
	}
	
	@GetMapping("Listar_Di")
	public void Listar_Di(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id_pro");
        List<Map<String, Object>> lista = ubi.Distrito(id);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
	}
	
	
	public void respuesta(HttpServletResponse response) {
		try {
			rpta.put("rpta", true);
			out = response.getWriter();
			out.println(gson.toJson(rpta));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			rpta.put("rpta", "-1");
			rpta.put("mensaje", e.getMessage());
			out.print(gson.toJson(rpta));
			out.flush();
			out.close();
		}
	}
}
