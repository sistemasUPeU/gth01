package pe.edu.upeu.gth.controller.recruitment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("contrato")
public class ContratoController {
	

	private ModelAndView modelAndView;
	
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public ModelAndView showJspContrato(ModelMap model) {
        modelAndView = new ModelAndView("contratacion/con_contratos", model);
        return modelAndView;
    }
	
	@RequestMapping(value = "/autorizar", method = RequestMethod.GET)
	public ModelAndView showJspMAINContrato(ModelMap model) {
        modelAndView = new ModelAndView("contratacion/con_autorizar", model);
        return modelAndView;
    }
	
	@RequestMapping(value = "/casos_especiales", method = RequestMethod.GET)
	public ModelAndView showJspReqEspeciales(ModelMap model) {
        modelAndView = new ModelAndView("contratacion/con_casos_esp", model);
        return modelAndView;
    }
	
	@RequestMapping(value = "/Detalle_Contractual")
	public ModelAndView showJspDetalle_Contractual(HttpServletRequest request) {
		System.out.println(request.getParameter("idtr"));
        modelAndView = new ModelAndView("trabajador/profile","rpta","asd");
        return modelAndView;
    }
	
	
}
