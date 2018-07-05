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
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dao.VerificarRenunciaDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Justificacion;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class VerificarRenunciaController {
	private Gson gson = new Gson();
	Renuncia r = new Renuncia();
	Rechazo re = new Rechazo();
	Justificacion ju = new Justificacion();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	VerificarRenunciaDAO ra = new VerificarRenunciaDAO(AppConfig.getDataSource()); 
	RenAutorizarDAO rea = new RenAutorizarDAO(AppConfig.getDataSource())
;	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	
	@RequestMapping(value = "/verificarRenuncia", method = RequestMethod.GET)
	public ModelAndView PrimerEnvio(ModelMap model) {
		return new ModelAndView("renuncia/VerificarRen");
	}
	
	//VerificarRenuncia
	@RequestMapping(value = "/Verificar", method = RequestMethod.GET)
	protected void metodosAutorizar(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			out.println(gson.toJson(ra.Autorizar()));
			break;
		case 2:
			String idc = request.getParameter("idc");
			out.println(gson.toJson(ra.Buscar_DetalleTrabajador(idc)));
			break;
		case 3:
			String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP() ;
			out.println(gson.toJson(ra.Pendiente(depa)));
			break;
		case 4:
			String idr = request.getParameter("idr");
			String tipo = request.getParameter("tipo");
			System.out.println("Esta llegando un idr:" +idr);
			r.setId_renuncia(idr);
			String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
			out.println(ra.AutorizarRenuncia(r,idusuario,tipo));
			break;
		case 5:
			out.println(gson.toJson(ra.Autorizado()));
			break;
		case 6:
//			String id = request.getParameter("idr");
//			System.out.println(id);
//			String observaciones = request.getParameter("observaciones");				
//			re.setId_renaban(id);
//			re.setObservaciones(observaciones);
//			out.println(ra.RechazarRenuncia(re));
//			break;
		case 7: 
			String idrol = ((CustomUser) authentication.getPrincipal()).getID_ROL();
			String tipon = request.getParameter("tipo");
			if(ra.BuscarRol(idrol,tipon)==1)
			{
				out.println(1);
			}else {
				out.println(0);
			}
			break;
		
		}

	}
	
	@Autowired
	ServletContext context;

	
//	@RequestMapping(value = "/mostrardoc1")
//	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ServletContext cntx = request.getServletContext();
//		// Get the absolute path of the image
//		// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
//
////		List<Map<String, Object>> result1 = rd.cargarMotivo("REN-000002");
////		System.out.println(gson.toJson(result1));
////		System.out.println();
////
//		String nom = request.getParameter("archi");
//
//		String filename = context.getRealPath("/WEB-INF/" + nom.trim());
//		// String filename =
//		// "E:\\\\TRABAJO\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp0\\\\wtpwebapps\\\\gth\\\\WEB-INF\\\\JUANCETO.jpg";
//
//		System.out.println(nom + "//"+ filename);
//		// retrieve mimeType dynamically
//		String mime = cntx.getMimeType(filename);
//		if (mime == null) {
//			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			return;
//		}
//
//		response.setContentType(mime);
//		File file = new File(filename);
//		response.setContentLength((int) file.length());
//
//		FileInputStream in = new FileInputStream(file);
//		OutputStream out = response.getOutputStream();
//
//		// Copy the contents of the file to the output stream
//		byte[] buf = new byte[1024];
//		int count = 0;
//		while ((count = in.read(buf)) >= 0) {
//			out.write(buf, 0, count);
//		}
//		out.close();
//		in.close();
//
//	}
}
