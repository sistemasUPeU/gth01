package pe.edu.upeu.gth.controller.holidays;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import net.sf.jasperreports.engine.JRException;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.CustomerInfo;
import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;

@Controller
@Scope("request")
@RequestMapping("/solicitud")

public class SolicitudController {

	SolicitudVacacionesDAO vd = new SolicitudVacacionesDAO(AppConfig.getDataSource());
	Map<String, Object> mp = new HashMap<>();
	Map<String, Object> rpta = new HashMap<String, Object>();
	ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	

	public List<String> archi = new ArrayList<>();
	private Gson gson = new Gson();

	@RequestMapping(value = "/home")
	public String redireccionarHome() {
		return "vacaciones/default";
	}

	@RequestMapping(value = "/tipo")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("entro");
		int opcion = Integer.parseInt(request.getParameter("op"));
		System.out.println(opcion);
		// String rol = request.getParameter("idrol");
		// System.out.println(opcion + "/"+ rol);
		// ModelAndView model = new ModelAndView("redirect:vacaciones/vac_gest_solic");
		ModelAndView model = new ModelAndView();
		
		
		model.setViewName("vacaciones/vac_gest_solic");
		// model.addObject("rol",rol);
		if (opcion == 1) {
			model.addObject("tipo", "Programacion");
		} else {
			if (opcion == 2) {
				model.addObject("tipo", "Reprogramacion");
			}else {
				if(opcion ==3) {
					model.addObject("tipo", "Programacion");
				}
			}
		}

		return model;// new ModelAndView("redirect:/gth/solicitud/open", model);
	}



	@RequestMapping(path="/reporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String verReporte(Model model, HttpServletRequest request,HttpServletResponse response,
			@RequestParam(name = "format", defaultValue = "pdf", required = false) String format) throws JRException, IOException {
		// JasperCompileManager.compileReport("D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jrxml");
		ServletContext cntx = request.getServletContext();
		String idt = "";

		
		try {
			System.out.println("reporte");
			idt = request.getParameter("idtr");
//			String fechainicio1 = request.getParameter("feinicio1");
//			String fechafin1 = request.getParameter("fefin1");
			System.out.println("--------------reporte controller pdf---------------");
			System.out.println(idt);
//			System.out.println(fechainicio1+", " + fechafin1);

//			vd.llenar_solicitud(idt,fechainicio1,fechafin1, cntx , response);
			List<Map<String, Object>> sd = vd.getFechasVacaciones(idt);
			
			
			String[] fecha_array_inicio = new String[3];
			String[] fecha_array_fin = new String[3];
			int tamano = sd.size();
			if(tamano==1) {
				fecha_array_inicio[1] = "-";
				fecha_array_fin[1] = "-";
				fecha_array_inicio[2] = "-";
				fecha_array_fin[2] = "-";
			}else if(tamano == 2) {
				fecha_array_inicio[2] = "-";
				fecha_array_fin[2] = "-";
			}
			
			
			for (int i = 0; i < sd.size(); i++) {
				
		
				fecha_array_inicio[i] = sd.get(i).get("FECHA_INICIO").toString();
					
				fecha_array_fin[i] = sd.get(i).get("FECHA_FIN").toString();

				
			}
			
			System.out.println(sd);
			System.out.println(fecha_array_inicio[0] + " , " +fecha_array_fin[0] + " , " +fecha_array_inicio[1]+ " , " +fecha_array_fin[1]+ " , " +fecha_array_inicio[2]+ " , " + fecha_array_fin[2]);

			model.addAttribute("format", format);
			model.addAttribute("datasource", sd);
			model.addAttribute("fechain1", fecha_array_inicio[0]);
			model.addAttribute("fechain2", fecha_array_fin[1]);
			model.addAttribute("fechain3", fecha_array_inicio[2]);
			model.addAttribute("fechafin1",fecha_array_fin[0]);
			model.addAttribute("fechafin2", fecha_array_inicio[1]);
			model.addAttribute("fechafin3", fecha_array_fin[2]);
			model.addAttribute("txtidtrab", idt);
			model.addAttribute("realPath", cntx.getRealPath("/resources/img/"));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error controller, reporte: " + e);
		}
		
				
		//mostrar archivos
		System.out.println("controller cargar archivo");

		
//		String filename = cntx.getRealPath("/resources/files/solicitud/" + idt + "/SVP_"+idt+".pdf");
////		String filename = UPLOADED_FOLDER+"\\" + nombre;
////		 String filenam1e ="E:\\CONEIA\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gth\\WEB-INF\\h\\"+nom;
//
//		System.out.println( "//" + "//" + filename);
//		
//		
//		String mime = cntx.getMimeType(filename);
//		if (mime == null) {
//			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		}
//
//		response.setContentType(mime);
//		File file = new File(filename);
//		response.setContentLength((int) file.length());
//
//		FileInputStream in = new FileInputStream(file);
//		OutputStream out = response.getOutputStream();
//		System.out.println(out);
//		// Copy the contents of the file to the output stream
//		byte[] buf = new byte[1024];
//		int count = 0;
//		while ((count = in.read(buf)) >= 0) {
//			out.write(buf, 0, count);
//		}
//		out.close();
//		in.close();
		return "request_report";
	}



	@RequestMapping(path = "/validar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validarTipoSolicitud(HttpServletRequest request, Model model) {
		Gson gs = new Gson();
		ServletContext cntx = request.getServletContext();
		String ruta = cntx.getRealPath("/resources/img/");
		
		System.out.println(ruta);
		String trab = request.getParameter("id");
		System.out.println(trab);
		// String rol = request.getParameter("idrol");
		// System.out.println(trab + "/" + rol);

		int sd = vd.validarTipoSolicitud(trab);
		// String res = sd+"/"+rol;
		System.out.println("validación de tipo solicitud" + gs.toJson(sd));

		//// model.setViewName("home");
		// model.addObject("nro",0);

		// return "vacaciones/vac_gest_solic";
		return gs.toJson(sd);

	}
	
	@RequestMapping(path = "/existenciasolicitud", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validarSolicitudSubida(HttpServletRequest request, Model model) {
		Gson gs = new Gson();

		String idtrab = request.getParameter("id");
		System.out.println(idtrab);
		// String rol = request.getParameter("idrol");
		// System.out.println(trab + "/" + rol);

		Map<String, Object> sd = vd.validarSolicitudSubida(idtrab);
		
		
		// String res = sd+"/"+rol;
		System.out.println("respuesta existencia solicitud" + gs.toJson(sd));

		return gs.toJson(sd);

	}
	
	
	@RequestMapping(path = "/mostrarpriv", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mostrarpriv(HttpServletRequest request, Model model, Authentication authentication) {
		Gson gs = new Gson();
		String idtra = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
	
		System.out.println(idtra);
		// String rol = request.getParameter("idrol");
		// System.out.println(trab + "/" + rol);

		List<Map<String, Object>> sd = vd.mostrarprivilegios(idtra);
		int respuesta = Integer.parseInt(sd.get(0).get("VA_PRIVILEGIO").toString());
		
		// String res = sd+"/"+rol;
		System.out.println("respuesta existencia solicitud" + gs.toJson(sd));

		return gs.toJson(respuesta);

	}
	
	


	@RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validarTipoSolicitudAAA(HttpServletRequest request) {
		Gson gs = new Gson();
		System.out.println("llega");
		String ini = request.getParameter("inicio");
		String fin = request.getParameter("final");
		String idt = request.getParameter("idt");
		String user = request.getParameter("user");
		String tipo = request.getParameter("tipo");
		System.out.println(ini + " " + fin + " " + idt + " " + user + " " + tipo);

		String[] fechas_inicio = ini.split("-");
		System.out.println(fechas_inicio[0]);

		String[] fechas_fin = fin.split("-");
		System.out.println(fechas_fin[0]);

		return gs.toJson(vd.insertarSolicitud(fechas_inicio, fechas_fin, idt, tipo, user));
		// System.out.println(gs.toJson(request.getParameter("data")));//(request.getParameter("data"),
		// ArrayList<String> )request.getParameter("data"), String));
		// return object.toString();
	}

	// @RequestMapping(value ="/insertarfdd",method = RequestMethod.POST)
	// public @ResponseBody String validarTipoSolicitudAA(@RequestBody String array)
	// {
	// Gson gs = new Gson();
	// System.out.println("llega");
	// System.out.println(array);
	//// System.out.println(gs.toJson(request.getParameter("data")));//(request.getParameter("data"),
	// ArrayList<String> )request.getParameter("data"), String));
	//
	// //return object.toString();
	// return "0";
	// }
	//
	//
	//
	// @RequestMapping(value ="/insertarsdf",method = RequestMethod.POST)
	// public void insertar(@ModelAttribute(value="data") Object myData) {
	// Gson gs = new Gson();
	// System.out.println("llega");
	//// System.out.println(gs.(myData));
	// //System.out.println(request.getParameter("data"));
	//
	//
	// //return object.toString();
	// }
	//
	// @Autowired
	// ServletContext context;
	@Autowired
	ServletContext context;
	@RequestMapping(path = "/archivos", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") List<MultipartFile> file, @RequestParam("idvac") String idvac,
			HttpServletResponse response,  HttpServletRequest request, Authentication authentication) throws IOException {
		ServletContext cntx = request.getServletContext();
		String result = null;

		int res = 0;
		if (!file.isEmpty()) {

			try {
				for (MultipartFile fi : file) {
					System.out.println(file);
					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
					String nome= fi.getOriginalFilename();
					nome="SOL_"+idvac.replace(" ","");
					FilenameUtils fich = new FilenameUtils();
					path = cntx.getRealPath("/resources/files/solicitud/" + nome+"."+FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo: " + path);
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
					String nombre = destFile.getName();
					String url = destFile.getPath();
					System.out.println("controller: " +idvac);
					System.out.println(nombre);
					res = vd.subirDocumento("", "", nombre, idvac);
					System.out.println("respuesta de update" + res);
					result = "redirect:/vacaciones/";
				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));

			System.out.println(res);

		}

		System.out.println(result);
		return result;
	}
	

	@RequestMapping(path = "/papeleta", method = RequestMethod.POST)
	public String SubirPapeleta(@RequestParam("file") List<MultipartFile> file, @RequestParam("idvac") String idvac, HttpServletResponse response, HttpServletRequest request,  Authentication authentication) throws IOException {
		ServletContext cntx = request.getServletContext();
		int res = 0;
		String result = null;
		if (!file.isEmpty()) {
			try {
				for (MultipartFile fi : file) {
					System.out.println(file);
					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
					String nome= fi.getOriginalFilename();
					nome="PAP_"+idvac.replace(" ","");
					FilenameUtils fich = new FilenameUtils();
					path = cntx.getRealPath("/resources/files/papeleta/" + nome+"."+FilenameUtils.getExtension(path));
					System.out.println("ruta del archivo: " + path);
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
					String nombre = destFile.getName();
					String url = destFile.getPath();
					System.out.println("controller: " +idvac);
					System.out.println(nombre);
					res = vd.subirPapeleta("", "", nombre, idvac);
					result = "redirect:/vacaciones/";
				}
			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
				result = "0";
			}
			System.out.println(gson.toJson(archi));
			System.out.println(res);
		}
		System.out.println(result);
		
		return result;
	}
}
