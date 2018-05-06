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
import org.bouncycastle.jce.provider.JDKKeyFactory.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/renaban/")
public class RegistrarRenunciaController {
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	Renuncia r = new Renuncia();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	Detalle_motivoDAO det = new Detalle_motivoDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();
	private static String UPLOADED_FOLDER = "C:\\Usuarios\\ASUS\\git\\gth01\\src\\main\\webapp\\resources\\files";
	@RequestMapping(value = "/detalleR", method = RequestMethod.GET)
	protected void metodosPedidos(HttpServletRequest request, HttpServletResponse response,Authentication authentication)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		
		// REGISTRAR RENUNCIA
		case 1:
			String depa = ((CustomUser) authentication.getPrincipal()).getNO_DEP() ;
			String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.Buscar_DetalleTrabajador(dni,depa)));
			break;

		case 2:
			out.println(gson.toJson(rd.mostrarMotivo()));
			break;
		case 3:
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
		case 6:
			//listar RENABAN
			String depa2 = ((CustomUser) authentication.getPrincipal()).getNO_DEP() ;
			out.println(gson.toJson(rd.Renaban(depa2)));
			System.out.println(gson.toJson(rd.Renaban(depa2)));
			break;
		case 7:
			String id = request.getParameter("idr");
			String archivo = request.getParameter("archivo");
			if(rd.eliminarRenaban(id)==1) {
				//ESTO ELIMINARÁ EL ARCHIVO EN EL SERVIDOR
				eliminarArchivo(archivo);
				out.println(1);
			}else {
				out.println(0);
			}
			break;
		case 8:
			String idrenaban = request.getParameter("idrenaban");
			out.println(gson.toJson(rd.buscarRenaban(idrenaban)));
			break;
		}

	}

	@Autowired
	ServletContext context;

	
	@RequestMapping(path = "/reg_ren", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("fecha") String fecha,
			@RequestParam("idcontrato") String idcon, Authentication authentication, HttpServletRequest request) throws IOException {
		ServletContext cntx = request.getServletContext();
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		Renuncia r = new Renuncia();
		String url = "/";
		int exito = 0;
		if (!file.isEmpty()) {
			// String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?,
			// ?, ?)";
			try {
				for (MultipartFile fi : file) {
					String nome= fi.getOriginalFilename();
					SimpleDateFormat simpleDateFormat =
				    new SimpleDateFormat("MMddhhmmss");
					String dateAsString = simpleDateFormat.format(new Date());
					nome="renuncia"+idcon+dateAsString;
					FilenameUtils fich = new FilenameUtils();
					
					String path = UPLOADED_FOLDER  + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome+"."+FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					File destFile = new File(path);
					
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));				
					archi.add(String.valueOf(destFile.length()));
					
					String nombre = destFile.getName();
					String url2 = destFile.getPath();
					System.out.println(nombre+""+url2);
					
					System.out.println(idcon);
					r.setFecha(fecha);
					r.setNo_archivo(destFile.getName());
					r.setTi_archivo(FilenameUtils.getExtension(path));
					r.setId_contrato(idcon);
					r.setId_usuario(idusuario);
					r.setTipo("R");
					exito=rd.crearRenuncia(r);
					
				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));
			System.out.println("respuesta>> " + exito);
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(path = "/updateR", method = RequestMethod.POST)
	public String actualizarRenaban(@RequestParam("file") List<MultipartFile> file, @RequestParam("fecha") String fecha,
			@RequestParam("idrenaban") String idcon, Authentication authentication, HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServletContext cntx = request.getServletContext();
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idusuario = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		Renuncia r = new Renuncia();
		String url = "/";
		if (!file.isEmpty()) {
			try {
				for (MultipartFile fi : file) {
					String nome= fi.getOriginalFilename();
					
					nome="renuncia"+idcon;
					FilenameUtils fich = new FilenameUtils();
					
					String path = UPLOADED_FOLDER  + File.separator + fi.getOriginalFilename();
					path = context.getRealPath("/resources/files/" + nome+"."+FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo " + path);
					File destFile = new File(path);
					
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
					
					String nombre = destFile.getName();
					String url2 = destFile.getPath();
					System.out.println(nombre+""+url2);
					
					System.out.println(idcon);
					r.setFecha(fecha);
					r.setNo_archivo(destFile.getName());
					r.setTi_archivo(FilenameUtils.getExtension(path));
					r.setId_contrato(idcon);
					r.setUsu_mod(idusuario);
					r.setTipo("R");
					if(rd.actualizarRenuncia(r)==1) {
						out.println(1);
					}
					
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
	
	@RequestMapping(value = "/viewdoc")
	public void jarchiv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext cntx = request.getServletContext();

		System.out.println("controller cargar archivo");
		String nombre = request.getParameter("nombre");
		
		String filename = cntx.getRealPath("/resources/files/" + nombre);


		System.out.println(nombre + "//" + "//" + filename);
		
		
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
		System.out.println(out);
		// Copy the contents of the file to the output stream
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();

	}
	
	
	public void eliminarArchivo(String nombre){

		try {
			String filename = context.getRealPath("/resources/files/" + nombre);
			File archivo = new File(filename);

            boolean estatus = archivo.delete();;

            if (!estatus) {

                System.out.println("Error no se ha podido eliminar el  archivo");

           }else{

                System.out.println("Se ha eliminado el archivo exitosamente");

           }
		} catch (Exception e) {
			// TODO: handle exception
		}
		


		

	}
}