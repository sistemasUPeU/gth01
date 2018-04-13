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

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.Detalle_motivoDAO;
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Detalle_motivo;
import pe.edu.upeu.gth.dto.Renuncia;
import pe.edu.upeu.gth.util.DateFormat;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class RenunciaController {
	
	private Gson gson = new Gson();
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource()); 
	@RequestMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_principal");
	}

	@RequestMapping(value = "/registrationR", method = RequestMethod.GET)
	public ModelAndView registrarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/RegistrarRenuncia");
	}
	//NUEVO
	@RequestMapping(value = "/registrationA", method = RequestMethod.GET)
	public ModelAndView registrarAbandono(ModelMap model) {
		return new ModelAndView("abandono/RegistrarAbandono");
	}


	@RequestMapping(value = "/authorizationR", method = RequestMethod.GET)
	public ModelAndView autorizarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/AutorizarRenuncia");
	}

	@RequestMapping(value = "/processR", method = RequestMethod.GET)
	public ModelAndView procesarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ProcesarRenuncia");
	}

	@RequestMapping(value = "/deliveryR", method = RequestMethod.GET)
	public ModelAndView entregarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_notificar");
	}

	@RequestMapping(value = "/processDetails", method = RequestMethod.GET)
	public ModelAndView detalleP(ModelMap model) {
		return new ModelAndView("renuncia/ren_DetalleP");
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView detalles(ModelMap model) {

		return new ModelAndView("renuncia/ren_detalle");
	}

	@RequestMapping(value = "/letterR", method = RequestMethod.GET)
	public ModelAndView imprimir(ModelMap model) {
		return new ModelAndView("renuncia/ren_emitir");
	}

//	@RequestMapping(path = "/crearR", method = RequestMethod.GET)
//	public String insertarRenuncias(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String url = "/";
//		String array = request.getParameter("array");
//		String[] listaMotivos = array.split(",");
//		System.out.println("arreglo" + listaMotivos);
//		rd.insertarMotivos(listaMotivos);
//		return "redirect:" + url;
//	}

	


	@RequestMapping(value = "/mostrardoc1")
	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext cntx = request.getServletContext();
		// Get the absolute path of the image
		// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
//		PrintWriter out = response.getWriter();

//		List<Map<String, Object>> result1 = rd.cargarMotivo("CTO-001841");
//		System.out.println(gson.toJson(result1));
		System.out.println();

//		String nom = (String) result1.get(0).get("NO_ARCHIVO");
//		String tipo = (String) result1.get(0).get("TI_ARCHIVO");
//		String filename = cntx.getRealPath("/WEB-INF/" + nom.trim());
		String filename = "C:/Users/Deyvis Garcia/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gth/WEB-INF/hola/30122019_130305407813962_2002991644_o.jpg";

//		 String filenam1e ="E:\\TRABAJO\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gth\\WEB-INF\\david\\"+nom;

//		System.out.println(nom + "//" + "//" + filename);
//		System.out.println(nom + "//" + "//" + filenam1e);
//		out.println(filename);
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
		OutputStream out = response.getOutputStream();

		// Copy the contents of the file to the output stream
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();

	}

//	@RequestMapping(value = "/mostrardoc1")
//	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ServletContext cntx = request.getServletContext();
//		// Get the absolute path of the image
//		// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
////		PrintWriter out = response.getWriter();
//
//		List<Map<String, Object>> result1 = rd.cargarMotivo("CTO-002058");
//		System.out.println(gson.toJson(result1));
//		System.out.println();
//
//		String nom = (String) result1.get(0).get("NO_ARCHIVO");
////		String tipo = (String) result1.get(0).get("TI_ARCHIVO");
////		String filename = cntx.getRealPath("/WEB-INF/david/" + nom.trim());
//		 String filename ="E:\\TRABAJO\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gth\\WEB-INF\\david\\norm.jpg";
//
//		System.out.println(nom + "//" + "//" + filename);
////		out.println(filename);
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

	@RequestMapping(value = "/uploaded")
	public void getUploadedPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println(request.getParameter("idtr"));
		// String sql = "SELECT pixel, tipo FROM imagen WHERE nombre = '" + nombre +
		// "'";
		List<Map<String, Object>> result = rd.cargarMotivo(request.getParameter("idtr"));

		if (!result.isEmpty()) {
			byte[] bytes = (byte[]) result.get(0).get("PIXEL");
			String mime = (String) result.get(0).get("TI_ARCHIVO");
			System.out.println(bytes);
			System.out.println(mime);
			// System.out.println(result.get(0));

			response.setHeader("Content-Type", mime);
			response.getOutputStream().write(bytes);
		}
	}

	// <<<<<<< HEAD

	@RequestMapping(value = "/gg", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			// String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.gg()));
			break;

		case 2:/*
				 * Pedido d = new Pedido();
				 * d.setIdfecha(Integer.parseInt(request.getParameter("idfecha")));
				 * d.setHora(request.getParameter("hora"));
				 * d.setIdcurso(Integer.parseInt(request.getParameter("idcurso")));
				 * d.setIdaula(Integer.parseInt(request.getParameter("idaula")));
				 * out.println((hs.create(d)));
				 */
			break;
		}

	}


}
