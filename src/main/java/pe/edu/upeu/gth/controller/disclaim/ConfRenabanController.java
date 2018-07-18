package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ConfRenabanDAO;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class ConfRenabanController {
	private Gson gson = new Gson();
	ConfRenabanDAO cfr= new ConfRenabanDAO(AppConfig.getDataSource());
	
	// AUTORIZAR RENUNCIA
			@RequestMapping(value = "/ConRenaban", method = RequestMethod.GET)
			protected void metodosAutorizar(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws ServletException, IOException {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				int op = Integer.parseInt(request.getParameter("opc"));
				switch (op) {
				case 1:
//					String tipo = request.getParameter("tipo");
					out.println(gson.toJson(cfr.Renuncia()));
					break;
				case 2:
					out.println(gson.toJson(cfr.Abandono()));
					break;
//				case 4:
//					String idr = request.getParameter("idr");
//					String tipo = request.getParameter("tipo");
//					System.out.println("Esta llegando un tipo:" +tipo);
//					r.setId_renuncia(idr);
//					String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
//					out.println(ra.AutorizarRenuncia(r,idr,idusuario,tipo));
//					break;
//				case 5:
//					out.println(gson.toJson(ra.Autorizado()));
//					break;
//				case 6:
//					String tipo1 = request.getParameter("tipo");
//					String id = request.getParameter("idr");
//					System.out.println(id);
//					String observaciones = request.getParameter("observaciones");				
//					re.setId_renaban(id);
//					re.setObservaciones(observaciones);
//					out.println(ra.RechazarRenuncia(re, tipo1));
//					break;
//				case 7:
//					String tipo2 = request.getParameter("tipo");
//					String idra = request.getParameter("idr");
//					System.out.println(idra);
//					String observacion = request.getParameter("observacion");				
//					ju.setId_renaban(idra);
//					ju.setObservacion(observacion);
//					out.println(ra.JustificarAbandono(ju, tipo2));
//					break;
				}
			}
}
