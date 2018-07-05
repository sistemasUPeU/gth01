package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Detalle_motivo;
import pe.edu.upeu.gth.util.DateFormat;

@Controller
@Scope("request")
@RequestMapping("/reportes/")
public class RenabanReportesController {
	DataSource d = AppConfig.getDataSource();
	RenunciaDAO rd = new RenunciaDAO(d);
	Gson gson = new Gson();
	
	
	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_reportes");
	}

	
	@GetMapping("/hola")
	public ModelAndView HOLA(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_renuncia1");
	}
	
	@RequestMapping(value = "/graficando", method = RequestMethod.GET)
	protected void metodosPedidos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			String fecha1 = request.getParameter("fecha1");
			String fecha2 = request.getParameter("fecha2");
			DateFormat df = new DateFormat();
			String f1 = df.toFormat2(fecha1);
			String f2 = df.toFormat2(fecha2);
			out.println(gson.toJson(rd.graficoMotivos2(f1, f2)));
			System.out.println(rd.graficoMotivos2(f1, f2));
			System.out.println(rd.graficoMotivos2("19/01/2015", "19/04/2018"));
			break;


		}

	}

}
