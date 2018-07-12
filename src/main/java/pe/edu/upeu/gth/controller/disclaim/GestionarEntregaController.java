package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.LegajoDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.CustomUser;

import pe.edu.upeu.gth.dto.Legajo;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class GestionarEntregaController {
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	LegajoDAO ldao = new LegajoDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	Legajo ob = new Legajo();
	private static String UPLOADED_FOLDER = "C:\\Usuarios\\ASUS\\git\\gth01\\src\\main\\webapp\\resources\\files";
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/listarxd", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
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

		// case 4:
		// Legajo l = new Legajo();
		// l.setIdtrabajador(request.getParameter("idtr"));
		// l.setId_tipo_doc("DLE-000001");
		// l.setOtros(request.getParameter("otros"));
		// l.setDetalle_otros(request.getParameter("detalle"));
		// out.println(ldao.insertarLegajo(l));
		// break;
//		case 4:
//			Legajo l = new Legajo();
//			l.setIdcontrato(request.getParameter("idc"));
//			l.setIdtrabajador(request.getParameter("idtr"));
//			out.println(ldao.insertarLegajo(l));
//			break;
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
			String idra = request.getParameter("idra");
			String tipo1 = request.getParameter("tipo1");
			System.out.println("Esta llegando un idr:" + idra);
			r.setId_renuncia(idra);
			String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
			out.println(rd.notificarRenuncia(r, idusuario, tipo1));
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

	}

	@RequestMapping(path = "/holamundo", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("liquidacion") List<MultipartFile> liquidacion,
			@RequestParam("cts") List<MultipartFile> cts, @RequestParam("certificado") List<MultipartFile> certificado,
			@RequestParam("remu") List<MultipartFile> remu, @RequestParam("idc") String idc,
			@RequestParam("idt") String idt, @RequestParam("idra") String idr, @RequestParam("tipon") String tipo,
			@RequestParam("fecha1") String fecha1,@RequestParam("fecha2") String fecha2,@RequestParam("fecha3") String fecha3,
			@RequestParam("fecha4") String fecha4)
			throws IOException {
		System.out.println("zzzzz idrrrr "+idr+"aaaaa tipoo "+tipo+"idc "+idc+"idt "+idt);
		Renuncia r = new Renuncia();
		Legajo l = new Legajo();
		String url = "/renaban/deliveryR";
		// LegajoDAO le =LegajoDAO();
		int x = 0;

		// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
		// ?, ?)";
		try {
//			l.setIdcontrato(idc);
//			l.setIdtrabajador(idt);
//			ldao.insertarLegajo(l);
			if (!remu.isEmpty() && !liquidacion.isEmpty() && !certificado.isEmpty() && !cts.isEmpty()) {
				for (MultipartFile fi : liquidacion) {
					x++;
					System.out.println(x);
					List<String> archi1 = new ArrayList<>();
					String nome = fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome = "liquidacion" + idc + dateAsString;
					String path = UPLOADED_FOLDER + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome + "." + FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi1.add(FilenameUtils.getExtension(path));
					archi1.add(String.valueOf(destFile.length()));
					System.out.println(gson.toJson(archi));
					Legajo leg = new Legajo();
					leg.setNo_archivo(destFile.getName());
					leg.setTi_archivo(FilenameUtils.getExtension(path));
					leg.setDescripcion("LIQUIDACIÓN");
					leg.setFecha_registro(fecha1);					
					if(tipo.equals("R")) {
						leg.setId_procesos("PCO-000005");
					}else {
						leg.setId_procesos("PCO-000006");
					}
					leg.setId_contrato(idc);
					leg.setId_trabajador(idt);
					ldao.InsertarDocBenfSoc(leg);
				}
				for (MultipartFile fi : cts) {
					x++;
					System.out.println(x);
					List<String> archi2 = new ArrayList<>();
					String nome = fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome = "cts" + idc + dateAsString;
					String path = UPLOADED_FOLDER + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome + "." + FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi2.add(destFile.getName());
					archi2.add(destFile.getPath());
					// FilenameUtils fich = new FilenameUtils();
					archi2.add(FilenameUtils.getExtension(path));
					archi2.add(String.valueOf(destFile.length()));
					System.out.println(gson.toJson(archi2));
					Legajo leg = new Legajo();
					leg.setNo_archivo(destFile.getName());
					leg.setTi_archivo(FilenameUtils.getExtension(path));
					leg.setDescripcion("CTS");
					leg.setFecha_registro(fecha2);
					if(tipo.equals("R")) {
						leg.setId_procesos("PCO-000005");
					}else {
						leg.setId_procesos("PCO-000006");
					}
					leg.setId_contrato(idc);
					leg.setId_trabajador(idt);
					ldao.InsertarDocBenfSoc(leg);
				}
				for (MultipartFile fi : certificado) {
					x++;
					System.out.println(x);
					List<String> archi3 = new ArrayList<>();
					String nome = fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome = "certificado" + idc + dateAsString;
					String path = UPLOADED_FOLDER + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome + "." + FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi3.add(destFile.getName());
					archi3.add(destFile.getPath());
					// FilenameUtils fich = new FilenameUtils();
					archi3.add(FilenameUtils.getExtension(path));
					archi3.add(String.valueOf(destFile.length()));
					System.out.println(gson.toJson(archi3));
					Legajo leg = new Legajo();
					leg.setNo_archivo(destFile.getName());
					leg.setTi_archivo(FilenameUtils.getExtension(path));
					leg.setDescripcion("CERTIFICADO DE TRABAJO");
					leg.setFecha_registro(fecha3);
					if(tipo.equals("R")) {
						leg.setId_procesos("PCO-000005");
					}else {
						leg.setId_procesos("PCO-000006");
					}
					leg.setId_contrato(idc);
					leg.setId_trabajador(idt);
					ldao.InsertarDocBenfSoc(leg);
				}
				for (MultipartFile fi : remu) {
					x++;
					System.out.println(x);
					List<String> archi4 = new ArrayList<>();
					String nome = fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome = "remuneraciones" + idc + dateAsString;
					String path = UPLOADED_FOLDER + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome + "." + FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi4.add(destFile.getName());
					archi4.add(destFile.getPath());
					// FilenameUtils fich = new FilenameUtils();
					archi4.add(FilenameUtils.getExtension(path));
					archi4.add(String.valueOf(destFile.length()));
					Legajo leg = new Legajo();
					leg.setNo_archivo(destFile.getName());
					leg.setTi_archivo(FilenameUtils.getExtension(path));
					leg.setDescripcion("REMUNERACIONES");
					leg.setFecha_registro(fecha4);
					if(tipo.equals("R")) {
						leg.setId_procesos("PCO-000005");
					}else {
						leg.setId_procesos("PCO-000006");
					}
					leg.setId_contrato(idc);
					leg.setId_trabajador(idt);
					ldao.InsertarDocBenfSoc(leg);

				}
			}
			System.out.println("LLEGANDO AL CONTROLLER " + idr + ".. " + tipo);
			ldao.actualizarPasoFinal(idr, tipo);

		} catch (IOException | IllegalStateException ec) {
			ec.getMessage();
			ec.printStackTrace();
		}

		return "redirect:" + url;
	}

}
