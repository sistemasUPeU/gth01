package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.PriCartaNotarialDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Abandono;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Justificacion;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class PriCartaNotarialController {
	private Gson gson = new Gson();
	Abandono r = new Abandono();
	Justificacion re = new Justificacion();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());

	PriCartaNotarialDAO ra = new PriCartaNotarialDAO(AppConfig.getDataSource()); 
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	//Primera carta notarial
	
	@RequestMapping(value = "/PrimerEnvio", method = RequestMethod.GET)
	public ModelAndView PrimerEnvio(ModelMap model) {
		return new ModelAndView("abandono/PriCartaNotarial");
	}
	
	@RequestMapping(value = "/primerEnvio", method = RequestMethod.GET)
	protected void metodosEnviar(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			out.println(gson.toJson(ra.Enviar()));
			break;
		case 2:
			String ida = request.getParameter("ida");
			out .println(gson.toJson(ra.Buscar_DetalleTrabajador(ida)));
			break;
		case 3:
			String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP() ;
			out.println(gson.toJson(ra.Pendiente(depa)));
			break;
		case 4:
			String idr = request.getParameter("idr");
			r.setIdabandono(idr);
			out.println(ra.AutorizarRenuncia(r));
			break;
		case 5:
			out.println(gson.toJson(ra.Autorizado()));
			break;
		case 6:
			String id = request.getParameter("idr");
			System.out.println(id);
			String observaciones = request.getParameter("observaciones");				
			re.setId_renaban(id);
			re.setObservaciones(observaciones);
			out.println(ra.JustificarAbandono(re));
			break;
		case 7:
			String de = request.getParameter("de");
			String clave = request.getParameter("clave");
			String para = request.getParameter("para");
			String mensaje = request.getParameter("mensaje");
			String asunto = request.getParameter("asunto");
			String foto = request.getParameter("foto");
			// boolean resultado = email.enviarCorreo(de, clave, para, mensaje, asunto);
			out.println(ra.enviarCorreo(de, clave, para, mensaje, asunto, foto));
			break;
		case 8:
			Abandono r = new Abandono();
			r.setIdabandono(request.getParameter("idr"));
			r.setEstado("Notificado");
			// l.setOtros(request.getParameter("otros"));
			// l.setDetalle_otros(request.getParameter("detalle"));
			out.println((ra.notificarAbandono(r)));
			break;
		case 9:
			out.println(gson.toJson(ra.listarNotificados()));
			break;
		}
	}
	
	@Autowired
	ServletContext context;
}
