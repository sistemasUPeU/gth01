package pe.edu.upeu.gth.controller.holidays;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.CorreoDAO;
import pe.edu.upeu.gth.dao.SolicitudVacacionesDAO;

@Controller
@Scope("request")
@RequestMapping("/solicitud")

public class SolicitudController {
	
	SolicitudVacacionesDAO vd = new SolicitudVacacionesDAO(AppConfig.getDataSource());
	Map<String, Object> mp = new HashMap<>();
	Map<String, Object> rpta = new HashMap<String, Object>();
	ArrayList<Map<String, Object>>  list =new ArrayList<Map<String, Object>>();
	
	@RequestMapping(value = "/registrar")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		
		int opcion = Integer.parseInt(request.getParameter("op"));
//		String rol = request.getParameter("idrol");
//		System.out.println(opcion + "/"+ rol);
	//	ModelAndView model =  new ModelAndView("redirect:vacaciones/vac_gest_solic");
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("vacaciones/vac_gest_solic");
//		model.addObject("rol",rol);
		if (opcion == 1) {
			model.addObject("tipo","Programacion");
		}else {
			if (opcion == 2) {
				model.addObject("tipo","Reprogramacion");
			}
		}

      	return model;//new ModelAndView("redirect:/gth/solicitud/open", model);
	}
	
	
//	@RequestMapping(value = "/open" )
//	public String solicitud(HttpServletRequest request, HttpServletResponse response) {
//
//      	return "vacaciones/vac_gest_solic";
//	}
	
	@RequestMapping("/reporte")
    public String verReporte(Model model,HttpServletRequest request, @RequestParam(name = "format", defaultValue = "pdf",required = false) String format) throws JRException {
		//JasperCompileManager.compileReport("D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jrxml");
        System.out.println("reporte");
		String idt = request.getParameter("idtr");
        System.out.println(idt);
        List<Map<String, Object>> sd = vd.llenar_solicitud(idt);
		model.addAttribute("format", format);
        model.addAttribute("datasource", sd);
        model.addAttribute("AUTOR", "Tutor de programacion");
        //abrirReporte(getClass().getResource("/src/main/resources/request_report.jrxml").getPath());
        return "request_report";
    }
	
//	@RequestMapping(value="/probar", method = RequestMethod.GET)
//	public @ResponseBody String hola (HttpServletRequest request) {
//		System.out.println("entro");
//		String pr= "llego data";
//		System.out.println(pr);
//		return pr;
//	}
	
	
	@RequestMapping(path ="/validar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String validarTipoSolicitud(HttpServletRequest request, Model model){
		Gson gs = new Gson();


		String trab = request.getParameter("id");
       // String rol = request.getParameter("idrol");
		//System.out.println(trab + "/" + rol);

      int sd = vd.validarTipoSolicitud(trab);
      //String res = sd+"/"+rol;
      System.out.println(gs.toJson(sd));

////    model.setViewName("home");
//    model.addObject("nro",0);

        //return "vacaciones/vac_gest_solic";
      return gs.toJson(sd);

    }
	
//	public ActionResult submitForm(String json)
//    {
//        System.Diagnostics.Debug.WriteLine("made it here");
//
//        var check = System.Web.Helpers.Json.Decode(json);
//
//        System.Diagnostics.Debug.WriteLine(check);
//        System.Diagnostics.Debug.WriteLine(check.glbBlue);
//
//        return View();
//    }     
	@RequestMapping(path ="/insertarABC", method = RequestMethod.GET)
    public String validarTipoSolicitud(@ModelAttribute(value="myData") ArrayList myData) throws ParseException{
		Gson gs = new Gson();
			System.out.println(myData);

	
      return gs.toJson(myData);

    }
//	
	//HttpServletRequest request,HttpServletResponse object
	
	@RequestMapping(value ="/insertar",method = RequestMethod.POST)
    public void validarTipoSolicitudAAA(HttpServletRequest request,HttpServletResponse object) {
		Gson gs = new Gson();
		System.out.println("llega");
		String ini = request.getParameter("inicio");
		String fin = request.getParameter("final");
		System.out.println(ini + " "+ fin);
//		System.out.println(gs.toJson(request.getParameter("data")));//(request.getParameter("data"), ArrayList<String> )request.getParameter("data"), String));

		//return object.toString();

    }
	
	@RequestMapping(value ="/insertarfdd",method = RequestMethod.POST)
    public @ResponseBody String validarTipoSolicitudAA(@RequestBody  String array) {
		Gson gs = new Gson();
		System.out.println("llega");
		System.out.println(array);
//		System.out.println(gs.toJson(request.getParameter("data")));//(request.getParameter("data"), ArrayList<String> )request.getParameter("data"), String));

		//return object.toString();
		return "0";
    }
	
	

	@RequestMapping(value ="/insertarsdf",method = RequestMethod.POST)
    public void insertar(@ModelAttribute(value="data") Object myData) {
		Gson gs = new Gson();
		System.out.println("llega");
//		System.out.println(gs.(myData));
		//System.out.println(request.getParameter("data"));

		
		//return object.toString();
    }
}
