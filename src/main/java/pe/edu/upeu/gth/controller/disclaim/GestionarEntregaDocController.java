package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.LegajoDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Legajo;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class GestionarEntregaDocController {

	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	LegajoDAO ldao = new LegajoDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();

	@RequestMapping(value = "/listarxd", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			// String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.gg()));
			break;

		case 2:
			out.println(gson.toJson(rd.correo(request.getParameter("idc"))));
			break;

		case 3:
			String de = request.getParameter("de");
			String clave = request.getParameter("clave");
			String para = request.getParameter("para");
			String mensaje = request.getParameter("mensaje");
			String asunto = request.getParameter("asunto");
			// boolean resultado = email.enviarCorreo(de, clave, para, mensaje, asunto);
			out.println(rd.enviarCorreo(de, clave, para, mensaje, asunto));
			break;

		case 4:
			Legajo l = new Legajo();
			l.setIdtrabajador(request.getParameter("idtr"));
			l.setId_tipo_doc("DLE-000001");
			l.setOtros(request.getParameter("otros"));
			l.setDetalle_otros(request.getParameter("detalle"));
			out.println(ldao.insertarLegajo(l));
			break;

		case 5:

			ServletContext cntx = request.getServletContext();
			// Get the absolute path of the image
			// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
//			PrintWriter out = response.getWriter();

			List<Map<String, Object>> result1 = rd.cargarMotivo("REN-000002");
			System.out.println(gson.toJson(result1));
			System.out.println();

			String nom = (String) result1.get(0).get("NO_ARCHIVO");
			String tipo = (String) result1.get(0).get("TI_ARCHIVO");
//			String filename = cntx.getRealPath("/WEB-INF/david/" + nom.trim() + "." + tipo.trim());
			 String filename ="E:/TRABAJO/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gth/WEB-INF/david/norm.jpg";

			System.out.println(nom + "//" + tipo + "//" + filename);
			out.println(filename);
			// retrieve mimeType dynamically
			String mime = cntx.getMimeType(filename);
			if (mime == null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
	
			response.setContentType(mime);
			File file = new File(filename);
			response.setContentLength((int) file.length());
	
			FileInputStream in = new FileInputStream(file);
			OutputStream out1 = response.getOutputStream();
	
			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out1.write(buf, 0, count);
			}
			out1.close();
			in.close();
			break;

		}

	}

}