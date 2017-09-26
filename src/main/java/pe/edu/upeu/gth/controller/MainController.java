/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    Map<String, Object> mp = new HashMap<>();

    @RequestMapping(value = "/components")
    public void Logueo(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException {
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
                    mp.put("pr", pD.listarURLs(Rol, Modulo));
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

}
