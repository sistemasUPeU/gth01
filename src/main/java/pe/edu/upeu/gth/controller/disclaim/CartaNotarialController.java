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
public class CartaNotarialController {
	private Gson gson = new Gson();
	Abandono r = new Abandono();
	Justificacion ju = new Justificacion();
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
			String tipo2 = request.getParameter("tipo");
			String idra = request.getParameter("idr");
			System.out.println(idra);
			String observacion = request.getParameter("observacion");				
			ju.setId_renaban(idra);
			ju.setObservacion(observacion);
			out.println(ra.JustificarAbandono(ju, tipo2));
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
			String idan = request.getParameter("idra");
			String tipo1 = request.getParameter("tipo1");
			System.out.println("Esta llegando un idan:" + idan);
			r.setIdabandono(idan);
			String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
			out.println(ra.notificarAbandono(r,idusuario,tipo1));
//			Renuncia r1 = new Renuncia();
//			r1.setId_renuncia(request.getParameter("idr"));
//			System.out.println(r1.getId_renuncia());
//			// r.setEstado("Notificado");
//			// l.setOtros(request.getParameter("otros"));
//			// l.setDetalle_otros(request.getParameter("detalle"));
//			out.println(rd.notificarRenuncia(r1));
			break;
		}
	}
	//Segunda carta Notarial
	@RequestMapping(value = "/SegundoEnvio", method = RequestMethod.GET)
	protected void SegundaCarta(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException{		
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			String idar = request.getParameter("idra");
			String tipo2 = request.getParameter("tipo1");
			System.out.println("Esta llegando un idan:" + idar);
			r.setIdabandono(idar);
			String idusuario1 = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
			out.println(ra.SegundaCarta(r,idusuario1,tipo2));
			break;
		case 2:
			String ids = request.getParameter("ids");
			out .println(gson.toJson(ra.Buscar_Detalle(ids)));
			break;
		case 3:
			String tipo = request.getParameter("tipo");
			String idra = request.getParameter("idr");
			System.out.println(idra);
			String observacion = request.getParameter("observacion");				
			ju.setId_renaban(idra);
			ju.setObservacion(observacion);
			out.println(ra.JustificarSegundaCarta(ju, tipo));
			break;
		}
	}
	
	@Autowired
	ServletContext context;
}
