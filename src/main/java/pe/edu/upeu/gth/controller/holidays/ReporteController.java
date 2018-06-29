package pe.edu.upeu.gth.controller.holidays;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ReporteVacacionesDAO;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;

@Controller
@Scope("request")
@RequestMapping("/reporte")
public class ReporteController {
	
	static List<Map<String, Object>> lista2 = new ArrayList<>();
	private Gson gson = new Gson();

	ReporteVacacionesDAO rd = new ReporteVacacionesDAO(AppConfig.getDataSource());
	
	@RequestMapping("/listar")
	public void reportes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fecha1 = request.getParameter("fecha1");
		String fecha2 = request.getParameter("fecha2");
		PrintWriter out = response.getWriter();

           
          lista2 = rd.reportePorDepartamento(fecha1,fecha2 );
		
		 out.println(gson.toJson(lista2));

	}

}
