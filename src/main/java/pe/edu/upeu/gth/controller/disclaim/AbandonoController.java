package pe.edu.upeu.gth.controller.disclaim;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.AbandonoDAO;
import pe.edu.upeu.gth.dto.Abandono;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class AbandonoController {

	private Gson gs = new Gson();
	Abandono a = new Abandono();
	AbandonoDAO ad = new AbandonoDAO(AppConfig.getDataSource());
	
	@RequestMapping(value = "/firstLetter", method = RequestMethod.GET)
	public ModelAndView detalles(ModelMap model) {

		return new ModelAndView("abandono/DetallePriCarta");
	}
	
}
