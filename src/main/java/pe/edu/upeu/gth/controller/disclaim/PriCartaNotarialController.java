package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.PriCartaNotarialDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Abandono;
import pe.edu.upeu.gth.dto.Rechazo;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class PriCartaNotarialController {
	private Gson gson = new Gson();
	Abandono r = new Abandono();
	Rechazo re = new Rechazo();
	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	PriCartaNotarialDAO ra = new PriCartaNotarialDAO(AppConfig.getDataSource()); 
	Map<String, Object> mp = new HashMap<>();
	public List<String> archi = new ArrayList<>();
	//Primera carta notarial
	
	@RequestMapping(value = "/PrimerEnvio", method = RequestMethod.GET)
	public ModelAndView PrimerEnvio(ModelMap model) {
		return new ModelAndView("abandono/PriCartaNotarial");
	}
	
	@RequestMapping(value = "/primerEnvio", method = RequestMethod.GET)
	protected void metodosEnviar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			out.println(gson.toJson(ra.Autorizar()));
			break;
		case 3:
			out.println(gson.toJson(ra.Pendiente()));
			break;
		}
	}
}
