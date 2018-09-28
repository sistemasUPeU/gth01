package pe.edu.upeu.gth.controller.disclaim;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	private static String UPLOADED_FOLDER = "C:\\Usuarios\\ASUS\\git\\gth01\\src\\main\\webapp\\resources\\files";
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
			//JUSTIFICAR PRIMERA CARTA 16/07/2018
			String idra = request.getParameter("idr");
			String observacion = request.getParameter("observacion");				
			out.println(ra.JustificarPrimeraCarta(idra, observacion));
			break;
		case 7:
			String de = request.getParameter("de");
			String clave = request.getParameter("clave");
			String para = request.getParameter("para");
			String mensaje = request.getParameter("mensaje");
			String asunto = request.getParameter("asunto");
			String foto = request.getParameter("foto");
			// boolean resultado = email.enviarCorreo(de, clave, para, mensaje, asunto);
//			out.println(ra.enviarCorreo(de, clave, para, mensaje, asunto, foto));
			break;
		case 8:
			String idan = request.getParameter("idra");
			String tipo1 = request.getParameter("tipo1");
			System.out.println("Esta llegando un idan:" + idan);
			r.setIdabandono(idan);
			String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
//			out.println(ra.notificarAbandono(r,idusuario,tipo1));
//			Renuncia r1 = new Renuncia();
//			r1.setId_renuncia(request.getParameter("idr"));
//			System.out.println(r1.getId_renuncia());
//			// r.setEstado("Notificado");
//			// l.setOtros(request.getParameter("otros"));
//			// l.setDetalle_otros(request.getParameter("detalle"));
//			out.println(rd.notificarRenuncia(r1));
			break;
		case 9:
			String idrenaban = request.getParameter("idrenaban");
			out.println(ra.PreguntarPrimera(idrenaban));
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
		
			break;
		case 2:
			String ids = request.getParameter("ids");
			out.println(gson.toJson(ra.Buscar_Detalle(ids)));
			break;
		case 3:
			//JUSTIFICAR PRIMERA CARTA 16/07/2018
			String idra = request.getParameter("idr");
			String observacion = request.getParameter("observacion");				
			out.println(ra.JustificarSegundaCarta(idra, observacion));
			break;
		case 4:
			out.println(ra.DerivadoSegundaCarta());
			break;
		}
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(path = "/SendLetter", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("fecha") String fecha,@RequestParam("correo") String correo,@RequestParam("idrenaban") String idrenaban,
			@RequestParam("idcontrato") String idcon, @RequestParam("idtrabajador") String idt,@RequestParam("asunto") String asunto,@RequestParam("mensaje") String mensaje,
			Authentication authentication) throws IOException{
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		
		byte[] bytes = mensaje.getBytes(StandardCharsets.ISO_8859_1);
		mensaje = new String(bytes, StandardCharsets.UTF_8);
		String url = "/";
		if (!file.isEmpty()) {
			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";
			try {
				for (MultipartFile fi : file) {
//					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
//					File destFile = new File(path);
					String nome= fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome="cartaNotarial1"+idcon+dateAsString;
					FilenameUtils fich = new FilenameUtils();
					
					//ASIGNEMOS UN NOMBRE AL ARCHIVO
					String path = UPLOADED_FOLDER  + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome+"."+FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					
					//AGREGUEMOS EL ARCHIVO AL SERVIDOR
					File destFile = new File(path);
					fi.transferTo(destFile);
					
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));

					if(ra.enviarCorreo(correo, mensaje, asunto, path,nome,idusuario,idrenaban)==1) {
						System.out.println("SE HA ENVIADO EL CORREO");
					}else {
						System.out.println("HUBO UN PROBLEMA, NO SE ENVIÓ EL CORREO");
					}
					
				}

			} catch (IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));
		}
		return "redirect:" + url;
	}
	
	@RequestMapping(path = "/SendLetter2", method = RequestMethod.POST)
	public String segundaCartaNotarial(@RequestParam("file") List<MultipartFile> file, @RequestParam("fecha") String fecha,@RequestParam("correo") String correo,@RequestParam("idrenaban") String idrenaban,
			@RequestParam("idcontrato") String idcon, @RequestParam("idtrabajador") String idt,@RequestParam("asunto") String asunto,@RequestParam("mensaje") String mensaje,
			Authentication authentication) throws IOException{
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		
		byte[] bytes = mensaje.getBytes(StandardCharsets.ISO_8859_1);
		mensaje = new String(bytes, StandardCharsets.UTF_8);
		String url = "/";
		if (!file.isEmpty()) {
			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";
			try {
				for (MultipartFile fi : file) {
//					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
//					File destFile = new File(path);
					String nome= fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome="cartaNotarial2"+idcon+dateAsString;
					FilenameUtils fich = new FilenameUtils();
					
					//ASIGNEMOS UN NOMBRE AL ARCHIVO
					String path = UPLOADED_FOLDER  + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome+"."+FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					
					//AGREGUEMOS EL ARCHIVO AL SERVIDOR
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));

					if(ra.enviarCorreo2(correo, mensaje, asunto, path,nome,idusuario,idrenaban)==1) {
						System.out.println("SE HA ENVIADO EL CORREO");
					}else {
						System.out.println("HUBO UN PROBLEMA, NO SE ENVIÓ EL CORREO");
					}
					
				}

			} catch (IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));
		}
		return "redirect:" + url;
	}
}
