package pe.edu.upeu.gth.controller.recruitment;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("trabajador")
public class TrabajadorController {

	private ModelAndView modelAndView;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfile(ModelMap model) {
        modelAndView = new ModelAndView("trabajador/profile", model);
        return modelAndView;
    }
	
}
