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
@RequestMapping("/renuncias/")
public class RenunciaController {

	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	Detalle_motivoDAO det = new Detalle_motivoDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();

	@RequestMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_principal");
	}

	@RequestMapping(value = "/registrationR", method = RequestMethod.GET)
	public ModelAndView registrarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_registrar");
	}

	@RequestMapping(value = "/authorizationR", method = RequestMethod.GET)
	public ModelAndView autorizarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_aut");
	}

	@RequestMapping(value = "/processR", method = RequestMethod.GET)
	public ModelAndView procesarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_procesar");
	}

	@RequestMapping(value = "/deliveryR", method = RequestMethod.GET)
	public ModelAndView entregarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_entregar");
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

	@RequestMapping(value = "/detalleR", method = RequestMethod.GET)
	protected void metodosPedidos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.Buscar_DetalleTrabajador(dni)));
			break;

		case 2:
			out.println(gson.toJson(rd.mostrarMotivo()));
			break;
		case 3:
			// System.out.println("Creando...");
			// Renuncia r = new Renuncia();
			// Object s = request.getParameter("file");
			// MultipartFile file = (MultipartFile) s;
			// r.setId_trabajador(request.getParameter("idtr"));
			// // r.setNo_archivo(request.getParameter("no_arch"));
			// // r.setTi_archivo(request.getParameter("ti_arch"));
			// System.out.println(file.getOriginalFilename());
			// System.out.println(file.getContentType());
			// // r.setTam_archivo(file.getContentType());
			// // r.setFecha(request.getParameter("fecha"));
			// out.println(rd.crearRenuncia(r));

			String array = request.getParameter("array");
			String[] listaMotivos = array.split(",");
			System.out.println("arreglo" + listaMotivos);
			out.print(rd.insertarMotivos(listaMotivos));
			break;

		case 4:
			out.println(gson.toJson(rd.cargarMotivo(request.getParameter("idtr"))));
			break;
		case 5:
			Detalle_motivo d = new Detalle_motivo();
			d.setOtros(request.getParameter("otros"));
			out.println(gson.toJson(det.insertarOtros(d)));
			break;
		}

	}

	@Autowired
	ServletContext context;

	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("fecha") String fecha,
			@RequestParam("idcontrato") String idcon) throws IOException {

		Renuncia r = new Renuncia();
		String url = "/";
		if (!file.isEmpty()) {
			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";
			try {
				for (MultipartFile fi : file) {
					String path = context.getRealPath("/WEB-INF/david/") + File.separator + fi.getOriginalFilename();
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					FilenameUtils fich = new FilenameUtils();
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));

					System.out.println(idcon);
					r.setFecha(fecha);
					r.setNo_archivo(destFile.getName());
					r.setTi_archivo(FilenameUtils.getExtension(path));
					r.setId_contrato(idcon);
					rd.crearRenuncia(r);
				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));
		}
		return "redirect:" + url;
	}

	@RequestMapping(value = "/mostrardoc")
	public void jarchiv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {

			out.print(gson.toJson(rd.mostrardocs("DOC-000008")));
			out.flush();
		}
	}

//	@RequestMapping(value = "/mostrardoc1")
//	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ServletContext cntx = request.getServletContext();
//		// Get the absolute path of the image
//		// String filename = cntx.getRealPath("/WEB-INF/dddd.png");
//
//		List<Map<String, Object>> result1 = rd.cargarMotivo("REN-000002");
//		System.out.println(gson.toJson(result1));
//		System.out.println();
//
//		String nom = (String) result1.get(0).get("NO_ARCHIVO");
//		String tipo = (String) result1.get(0).get("TI_ARCHIVO");
//		String filename = context.getRealPath("/WEB-INF/david/" + nom.trim() + "." + tipo.trim());
//		// String filename =
//		// "E:\\\\TRABAJO\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp0\\\\wtpwebapps\\\\gth\\\\WEB-INF\\\\JUANCETO.jpg";
//
//		System.out.println(nom + "//" + tipo + "//" + filename);
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

	// =======

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
