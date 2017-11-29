package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class AutorizarRenunciaController {
	private Gson gson = new Gson();
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource()); 
	// Autorizar Renuncia
		@RequestMapping(value = "/AutorizarR", method = RequestMethod.GET)
		protected void metodosAutorizar(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			int op = Integer.parseInt(request.getParameter("opc"));
			switch (op) {
			case 1:
				out.println(gson.toJson(ra.Autorizar()));
				break;
			case 2:
				out.println(gson.toJson(ra.DetalleAutorizar()));
				break;
			}

		}
		
		@RequestMapping(value = "/listarRen", method = RequestMethod.GET)
		protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			int op = Integer.parseInt(request.getParameter("opc"));
			switch (op) {
			case 1:
				// String dni = request.getParameter("dni");
				out.println(gson.toJson(rd.gg()));
				break;

			}

		}
}
