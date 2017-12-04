
package pe.edu.upeu.gth.controller.holidays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.GestionarPrograVacacDAO;

@Controller
@Scope("request")
@RequestMapping("/GestionarProgramaVacaciones")
public class ProgramacionController {
	
//	Gson GSON = new Gson();
//	@GetMapping("/GestionarProgramaVacaciones")
//	public ModelAndView GestionarProgramaVacaciones(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("GestionarProgramaVacaciones");
//		return new ModelAndView("vacaciones/GestionarProgramaVacaciones");
//
//	}
//	@RequestMapping(path = "/readallProgramaVacaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody String getAllProgramaVacaciones() {
//		GestionarPrograVacacDAO DAO = new GestionarPrograVacacDAO(AppConfig.getDataSource());
//		return GSON.toJson(DAO.READALL());
//	}
}
//=======
//package pe.edu.upeu.gth.controller.holidays;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@Scope("request")
//@RequestMapping("/GestionarProgramaVacaciones")
//public class ProgramacionController {
//	@GetMapping("/GestionarProgramaVacaciones")
//	public ModelAndView GestionarProgramaVacaciones(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("GestionarProgramaVacaciones");
//		return new ModelAndView("vacaciones/GestionarProgramaVacaciones");
//
//	}
//}
//>>>>>>> branch 'modulo-vacaciones' of https://github.com/sistemasUPeU/gth01.git
