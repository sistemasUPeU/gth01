/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.controller.principal;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.PrivilegioDAO;
import pe.edu.upeu.gth.dao.RolDAO;
import pe.edu.upeu.gth.dto.CustomUser;

/**
 *
 * @author Leandro Burgos
 */
@Controller
public class MainController {

	DataSource d = AppConfig.getDataSource();
	PrivilegioDAO pD = new PrivilegioDAO(d);
	RolDAO rD = new RolDAO(d);
	Map<String, Object> mp = new HashMap<String, Object>();
	Map<String, Object> sr = new HashMap<String, Object>();

	@RequestMapping(value = "/components")
	public void Logueo(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		String opc = request.getParameter("opc");
		String idm;
		try {
			switch (opc) {
			case "privilegios":
				String Rol = ((CustomUser) authentication.getPrincipal()).getID_ROL();
				String Modulo = session.getAttribute("ModE").toString();
				sr.put("pr", pD.listarURLs(Rol, Modulo));
				sr.put("usuario", ((CustomUser) authentication.getPrincipal()));
				mp.put("datos", sr);
				break;
			case "modulos":
				mp.put("lista", ((CustomUser) authentication.getPrincipal()).getLIST_MODULO());
				break;
			case "redMod":
				idm = request.getParameter("idmod");
				if (!idm.equals("")) {
					session.setAttribute("ModE", idm);
					mp.put("rpta", true);
				} else {
					mp.put("rpta", false);
				}
				break;
			case "puesto":
				Map<String, Object> sr = new HashMap<String, Object>();
				sr.put("dep", ((CustomUser) authentication.getPrincipal()).getNO_DEP());
				sr.put("area", ((CustomUser) authentication.getPrincipal()).getNO_AREA());
				sr.put("seccion", ((CustomUser) authentication.getPrincipal()).getNO_SECCION());
				sr.put("puesto", ((CustomUser) authentication.getPrincipal()).getNO_PUESTO());
				sr.put("idrol", ((CustomUser) authentication.getPrincipal()).getID_ROL());
				mp.put("info_puesto", sr);
				break;
			case "usuario":
				mp.put("datos_usuario", ((CustomUser) authentication.getPrincipal()).getNOMBRE_AP());
				break;
			}

		} catch (Exception e) {
			mp.put("rpta", false);
			System.out.println("Error controlador COMPONENTS : " + e);
		}
		Gson gson = new Gson();
		out.println(gson.toJson(mp));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/administrador")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("home");
	}
	

}
