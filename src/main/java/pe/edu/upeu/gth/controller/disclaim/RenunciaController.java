package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import pe.edu.upeu.gth.dao.RenAutorizarDAO;
import pe.edu.upeu.gth.dao.RenunciaDAO;

@Controller
@Scope("request")
@RequestMapping("/renuncias/")
public class RenunciaController {

	Map<String, Object> mp = new HashMap<>();

	RenunciaDAO rd = new RenunciaDAO(AppConfig.getDataSource());
	RenAutorizarDAO ra = new RenAutorizarDAO(AppConfig.getDataSource());
	private Gson gson = new Gson();

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
		return new ModelAndView("renuncia/ren_aut");
	}

	@RequestMapping(value = "/processR", method = RequestMethod.GET)
	public ModelAndView procesarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_procesar");
	}

	@RequestMapping(value = "/deliveryR", method = RequestMethod.GET)
	public ModelAndView entregarRenuncia(ModelMap model) {
		return new ModelAndView("renuncia/ren_entregar");
	}

	@RequestMapping(value = "/processDetails", method = RequestMethod.GET)
	public ModelAndView detalleP(ModelMap model) {
		return new ModelAndView("renuncia/ren_DetalleP");
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView detalles(ModelMap model) {
		return new ModelAndView("renuncia/ren_detalle");
	}

	@RequestMapping(value = "/letterR", method = RequestMethod.GET)
	public ModelAndView imprimir(ModelMap model) {
		return new ModelAndView("renuncia/ren_emitir");
//<<<<<<< HEAD
	}

	// @RequestMapping("/BuscarDNIDetalleR")
	// public ModelAndView Buscar_Trabajador(@RequestBody HttpServletRequest
	// request) {
	//
	// String dni = request.getParameter("dni");
	//
	// System.out.println(dni);
	// if (!"".equals(dni)) {
	// mp.put("ListarTrabajador", rd.Buscar_DetalleTrabajadorR(dni));
	// return new ModelAndView("/registrationR","rpta",gson.toJson(mp));
	// } else {
	// return new ModelAndView("/registrationR");
	// }
	// }

//=======
//    }
	
//	@RequestMapping("/BuscarDNIDetalleR")
//	public ModelAndView Buscar_Trabajador(@RequestBody HttpServletRequest request) {
//	
//        String dni = request.getParameter("dni");
//
//        System.out.println(dni);
//        if (!"".equals(dni)) {
//        	mp.put("ListarTrabajador", rd.Buscar_DetalleTrabajadorR(dni));
//        	return new ModelAndView("/registrationR","rpta",gson.toJson(mp));
//        } else {
//        	return new ModelAndView("/registrationR");
//        }
//	}
	
	//Registar Renuncia
//>>>>>>> branch 'modulo-renuncias' of https://github.com/sistemasUPeU/gth01.git
	@RequestMapping(value = "/detalleR", method = RequestMethod.GET)
	protected void metodosPedidos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.Buscar_DetalleTrabajador(dni)));
			break;

		case 2:
			out.println(gson.toJson(rd.mostrarMotivo()));
			break;
		}

	}
//<<<<<<< HEAD

	@RequestMapping(value = "/gg", method = RequestMethod.GET)
	protected void metodosPedidos2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
//			String dni = request.getParameter("dni");
			out.println(gson.toJson(rd.gg()));
			break;

		case 2:/*
				 * Pedido d = new Pedido();
				 * d.setIdfecha(Integer.parseInt(request.getParameter("idfecha")));
				 * d.setHora(request.getParameter("hora"));
				 * d.setIdcurso(Integer.parseInt(request.getParameter("idcurso")));
				 * d.setIdaula(Integer.parseInt(request.getParameter("idaula")));
				 * out.println((hs.create(d)));
				 */
			break;
		}

	}

//=======
	
	//Autorizar Renuncia
	@RequestMapping(value = "/AutorizarR", method = RequestMethod.GET)
	protected void metodosAutorizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			out.println(gson.toJson(ra.Autorizar()));
			break;			
		case 2:/*
			Pedido d = new Pedido();
			d.setIdfecha(Integer.parseInt(request.getParameter("idfecha")));
			d.setHora(request.getParameter("hora"));
			d.setIdcurso(Integer.parseInt(request.getParameter("idcurso")));
			d.setIdaula(Integer.parseInt(request.getParameter("idaula")));
			out.println((hs.create(d)));*/
			break;
		}	
			
	}
	
//>>>>>>> branch 'modulo-renuncias' of https://github.com/sistemasUPeU/gth01.git
}
