package pe.edu.upeu.gth.controller.disclaim;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class RenunciaController {

	
	@RequestMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("renuncia/ren_principal");
	}
	
	@RequestMapping(value = "/registrationR", method = RequestMethod.GET)
	public ModelAndView registrarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_registrar");
    }
	
	@RequestMapping(value = "/authorizationR", method = RequestMethod.GET)
	public ModelAndView autorizarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_autorizar");
    }
	
	@RequestMapping(value = "/processR", method = RequestMethod.GET)
	public ModelAndView procesarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_procesar");
    }
	
	@RequestMapping(value = "/deliveryR", method = RequestMethod.GET)
	public ModelAndView entregarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_entregar");
    }
	
	@RequestMapping(value = "/ProcesarDetalleR", method = RequestMethod.GET)
	public ModelAndView ProcesarDetalleR(ModelMap model) {
		return new ModelAndView("renuncia/ren_DetalleP");
    }
	
	
}


