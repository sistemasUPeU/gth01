package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
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
import pe.edu.upeu.gth.dao.Detalle_motivoDAO;
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Detalle_motivo;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class RegistrarRenunciaController {
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	Detalle_motivoDAO det = new Detalle_motivoDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();
	
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
			@RequestParam("idcontrato") String idcon, Authentication authentication) throws IOException {

		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		Renuncia r = new Renuncia();
		String url = "/";
		if (!file.isEmpty()) {
			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";
			try {
				for (MultipartFile fi : file) {
					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					FilenameUtils fich = new FilenameUtils();
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
//					System.out.println("asdasdasdasdasdas" +idusuario);
					System.out.println(idcon);
					r.setFecha(fecha);
					r.setNo_archivo(destFile.getName());
					r.setTi_archivo(FilenameUtils.getExtension(path));
					r.setId_contrato(idcon);
					r.setId_usuario(idusuario);
					r.setTipo("R");
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
}