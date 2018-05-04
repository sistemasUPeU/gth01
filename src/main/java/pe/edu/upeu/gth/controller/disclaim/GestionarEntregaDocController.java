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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.LegajoDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Legajo;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class GestionarEntregaDocController {
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	LegajoDAO ldao = new LegajoDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/listarxd", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			// String dni = request.getParameter("dni");
			String depa = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
			out.println(gson.toJson(rd.gg(depa)));
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
			// PrintWriter out = response.getWriter();

			List<Map<String, Object>> result1 = rd.cargarMotivo("REN-000002");
			System.out.println(gson.toJson(result1));
			System.out.println();

			String nom = (String) result1.get(0).get("NO_ARCHIVO");
			String tipo = (String) result1.get(0).get("TI_ARCHIVO");
			// String filename = cntx.getRealPath("/WEB-INF/david/" + nom.trim() + "." +
			// tipo.trim());
			String filename = "C:/Users/Deyvis Garcia/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gth/WEB-INF/d/29791352_2077305459260623_8230612622916918394_n.jpg";

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

		case 6:
			String idr = request.getParameter("idr");
			String tipo1 = request.getParameter("tipo1");
			System.out.println("Esta llegando un idr:" +idr);
			r.setId_renuncia(idr);
			String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
			out.println(rd.notificarRenuncia(r,idusuario,tipo1));
//			Renuncia r1 = new Renuncia();
//			r1.setId_renuncia(request.getParameter("idr"));
//			System.out.println(r1.getId_renuncia());
//			// r.setEstado("Notificado");
//			// l.setOtros(request.getParameter("otros"));
//			// l.setDetalle_otros(request.getParameter("detalle"));
//			out.println(rd.notificarRenuncia(r1));
			break;

		case 7:
			out.println(gson.toJson(rd.listarNotificados()));
			break;

		}

	}

	@RequestMapping(value = "/entregar", method = RequestMethod.GET)
	protected void metodosPedidos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			Legajo l = new Legajo();
			l.setIdrenuncia(request.getParameter("idr"));

			// String dni = request.getParameter("dni");
			out.println(ldao.insertarMaxrRenuncia(l));
			break;
		}

	}

	@RequestMapping(path = "/holamundo", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("archivo") List<MultipartFile> file,
			@RequestParam("not_idr") String idr) throws IOException {

		Renuncia r = new Renuncia();
		String url = "/renuncias/deliveryR";
		int x = 0;
		if (!file.isEmpty()) {
			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";
			try {
				for (MultipartFile fi : file) {
					x++;
					System.out.println(x);
					List<String> archi = new ArrayList<>();
					String path = context.getRealPath("/WEB-INF/hola/") + File.separator + fi.getOriginalFilename();
					File destFile = new File(path);
					fi.transferTo(destFile);
					System.out.println(path);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					// FilenameUtils fich = new FilenameUtils();
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
					// System.out.println("ARCHIVO " + path);
					// System.out.println(idcon);
					// r.setFecha(fecha);
					// r.setNo_archivo(destFile.getName());
					// r.setTi_archivo(FilenameUtils.getExtension(path));
					// r.setId_contrato(idcon);
					// r.setId_usuario(idusuario);
					// rd.crearRenuncia(r);
					System.out.println(gson.toJson(archi));
					System.out.println(idr);
//					Legajo l = new Legajo();
//					l.setNo_archivo(destFile.getName());
//					l.setTi_archivo(FilenameUtils.getExtension(path));
//					if (x == 1) {
//						l.setId_tipo_doc("DLE-000001");
//					}
//					if (x == 2) {
//						l.setId_tipo_doc("DLE-000003");
//					}
//
//					if (x == 3) {
//						l.setId_tipo_doc("DLE-000002");
//					}
//					if (x == 4) {
//						l.setId_tipo_doc("DLE-000004");
//					}
//					System.out.println(l.getId_tipo_doc());
//					ldao.InsertarDocBenfSoc(l);

				}
//				System.out.println(idr);
//				Renuncia r1 = new Renuncia();
//				r1.setId_renuncia(idr);
//				ldao.EntregarRenuncia(r1);

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}

		}
		return "redirect:" + url;
	}



}
