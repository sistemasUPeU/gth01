package pe.edu.upeu.gth.controller.disclaim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.LegajoDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.dto.Legajo;
import pe.edu.upeu.gth.dto.Renuncia;

@Controller
@Scope("request")
@RequestMapping("/renaban/")
public class LegajoController {
	private Gson gson = new Gson();
	Renuncia r = new Renuncia();
	Legajo ob = new Legajo();
	private List<Legajo> lista = new ArrayList<>();
	LegajoDAO ldao = new LegajoDAO(AppConfig.getDataSource());
//	@Autowired
//	 private LegajoDAO legajo;
	@RequestMapping(value = "/legajo", method = RequestMethod.GET)
	public ModelAndView PrimerEnvio(ModelMap model) {
		return new ModelAndView("renuncia/Legajo");		
	}	
	
	// AUTORIZAR RENUNCIA
			@RequestMapping(value = "/Legajo", method = RequestMethod.GET)
			protected void metodosAutorizar(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws ServletException, IOException {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				int op = Integer.parseInt(request.getParameter("opc"));
				switch (op) {
				case 1:
					String lega = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
					out.println(gson.toJson(ldao.legajos(lega)));
					break;
				case 2:
					out.println(gson.toJson(ldao.Buscar_Documentos(request.getParameter("idc"))));
					break;					
				case 3:					
					String idt = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
					out.println(gson.toJson(ldao.legajos_tra(idt)));
					break;
				}

			}

}
