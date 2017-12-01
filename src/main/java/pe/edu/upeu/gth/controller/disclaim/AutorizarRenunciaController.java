package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
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
				String idc = request.getParameter("idc");
				out.println(gson.toJson(ra.Buscar_DetalleTrabajador(idc)));
				break;
			case 3:
				out.println(gson.toJson(ra.Pendiente()));
				break;
			case 4:
				String idr = request.getParameter("idr");
				r.setId_renuncia(idr);
				out.println(ra.AutorizarRenuncia(r));
				break;
			}

		}
		
		@Autowired
		ServletContext context;

		
//		@RequestMapping(value = "/mostrardoc1")
//		public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
//			ServletContext cntx = request.getServletContext();
//			// Get the absolute path of the image
//			// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
//	
////			List<Map<String, Object>> result1 = rd.cargarMotivo("REN-000002");
////			System.out.println(gson.toJson(result1));
////			System.out.println();
////	
//			String nom = request.getParameter("archi");
//
//			String filename = context.getRealPath("/WEB-INF/" + nom.trim());
//			// String filename =
//			// "E:\\\\TRABAJO\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp0\\\\wtpwebapps\\\\gth\\\\WEB-INF\\\\JUANCETO.jpg";
//	
//			System.out.println(nom + "//"+ filename);
//			// retrieve mimeType dynamically
//			String mime = cntx.getMimeType(filename);
//			if (mime == null) {
//				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//				return;
//			}
//	
//			response.setContentType(mime);
//			File file = new File(filename);
//			response.setContentLength((int) file.length());
//	
//			FileInputStream in = new FileInputStream(file);
//			OutputStream out = response.getOutputStream();
//	
//			// Copy the contents of the file to the output stream
//			byte[] buf = new byte[1024];
//			int count = 0;
//			while ((count = in.read(buf)) >= 0) {
//				out.write(buf, 0, count);
//			}
//			out.close();
//			in.close();
//	
//		}
		
}
