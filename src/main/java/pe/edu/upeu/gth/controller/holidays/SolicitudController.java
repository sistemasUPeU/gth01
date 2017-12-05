package pe.edu.upeu.gth.controller.holidays;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

import net.sf.jasperreports.engine.JRException;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;

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

	@RequestMapping(value = "/registrar")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {

		int opcion = Integer.parseInt(request.getParameter("op"));
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
			}
		}

		return model;// new ModelAndView("redirect:/gth/solicitud/open", model);
	}

	// @RequestMapping(value = "/open" )
	// public String solicitud(HttpServletRequest request, HttpServletResponse
	// response) {
	//
	// return "vacaciones/vac_gest_solic";
	// }

	@RequestMapping("/reporte")
	public String verReporte(Model model, HttpServletRequest request,
			@RequestParam(name = "format", defaultValue = "pdf", required = false) String format) throws JRException {
		// JasperCompileManager.compileReport("D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jrxml");
		System.out.println("reporte");
		String idt = request.getParameter("idtr");
		System.out.println(idt);
		List<Map<String, Object>> sd = vd.llenar_solicitud(idt);
		model.addAttribute("format", format);
		model.addAttribute("datasource", sd);
		model.addAttribute("AUTOR", "Tutor de programacion");
		// abrirReporte(getClass().getResource("/src/main/resources/request_report.jrxml").getPath());
		return "request_report";
	}

	// @RequestMapping(value="/probar", method = RequestMethod.GET)
	// public @ResponseBody String hola (HttpServletRequest request) {
	// System.out.println("entro");
	// String pr= "llego data";
	// System.out.println(pr);
	// return pr;
	// }

	@RequestMapping(path = "/validar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validarTipoSolicitud(HttpServletRequest request, Model model) {
		Gson gs = new Gson();

		String trab = request.getParameter("id");
		System.out.println(trab);
		// String rol = request.getParameter("idrol");
		// System.out.println(trab + "/" + rol);

		int sd = vd.validarTipoSolicitud(trab);
		// String res = sd+"/"+rol;
		System.out.println(gs.toJson(sd));

		//// model.setViewName("home");
		// model.addObject("nro",0);

		// return "vacaciones/vac_gest_solic";
		return gs.toJson(sd);

	}

	// public ActionResult submitForm(String json)
	// {
	// System.Diagnostics.Debug.WriteLine("made it here");
	//
	// var check = System.Web.Helpers.Json.Decode(json);
	//
	// System.Diagnostics.Debug.WriteLine(check);
	// System.Diagnostics.Debug.WriteLine(check.glbBlue);
	//
	// return View();
	// }
	// @RequestMapping(path ="/insertarABC", method = RequestMethod.GET)
	// public String validarTipoSolicitud(@ModelAttribute(value="myData") ArrayList
	// myData) throws ParseException{
	// Gson gs = new Gson();
	// System.out.println(myData);
	//
	//
	// return gs.toJson(myData);
	//
	// }
	//
	// HttpServletRequest request,HttpServletResponse object

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
			HttpServletResponse response,  Authentication authentication) throws IOException {


		int res = 0;
		if (!file.isEmpty()) {

			try {
				for (MultipartFile fi : file) {
					System.out.println(file);
					String path = context.getRealPath("/WEB-INF/") + File.separator + fi.getOriginalFilename();
					File destFile = new File(path);
					fi.transferTo(destFile);
					archi.add(destFile.getName());
					archi.add(destFile.getPath());
					FilenameUtils fich = new FilenameUtils();
					archi.add(FilenameUtils.getExtension(path));
					archi.add(String.valueOf(destFile.length()));
					System.out.println("controller: " +idvac);
					String nombre = destFile.getName();
					String url = destFile.getPath();
					System.out.println(nombre);


	
					res = vd.subirDocumento("", "", url, idvac);
				}

			} catch (IOException | IllegalStateException ec) {
				ec.getMessage();
				ec.printStackTrace();
			}
			System.out.println(gson.toJson(archi));

			System.out.println(res);

		}

		 return "redirect:/vacaciones/";// + url;
		// return gson.toJson(archi);
	}

}
