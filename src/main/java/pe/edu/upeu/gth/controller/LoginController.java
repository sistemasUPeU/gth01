/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.RolDAO;
import pe.edu.upeu.gth.dao.UsuarioDAO;

@Controller
public class LoginController {

    DataSource d = AppConfig.getDataSource();
    Map<String, Object> mp = new HashMap<String, Object>();
    RolDAO rD = new RolDAO(d);
    UsuarioDAO uD = new UsuarioDAO(d);

    @RequestMapping(value = "/valida")
    public void Logueo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            String Usuario = request.getParameter("username");
            String Clave = request.getParameter("clave");
            ArrayList<Map<String, Object>> user = uD.validar(Usuario, Clave);
            if (Usuario.equals("") && Clave.equals("")) {
                mp.put("rpta", false);
            } else if (user.size() == 1) {
                session.setAttribute("IDUSER", user.get(0).get("ID_USUARIO"));
                session.setAttribute("IDTR", user.get(0).get("ID_TRABAJADOR"));
                session.setAttribute("USER", user.get(0).get("NO_USUARIO"));
                session.setAttribute("IDPER", user.get(0).get("ID_EMPLEADO"));
                session.setAttribute("IDROL", user.get(0).get("ID_ROL"));
                session.setAttribute("CL", user.get(0).get("PW_USUARIO"));
                session.setAttribute("PUESTO_ID", user.get(0).get("ID_PUESTO"));
                session.setAttribute("AREA_ID", user.get(0).get("ID_AREA"));
                session.setAttribute("AREA", user.get(0).get("NO_AREA"));
                session.setAttribute("DEPARTAMENTO", user.get(0).get("NO_DEP"));
                session.setAttribute("SECCION", user.get(0).get("NO_SECCION"));
                session.setAttribute("DEPARTAMENTO_ID", user.get(0).get("ID_DEPARTAMENTO"));
                session.setAttribute("PUESTO", user.get(0).get("NO_PUESTO"));
                session.setAttribute("IDDIR", user.get(0).get("NO_DIRECCION"));
                session.setAttribute("SEXO", user.get(0).get("ES_SEXO"));
                if (user.get(0).get("AP_PATERNO") == null) {
                    session.setAttribute("NOMBRE_AP", "");
                } else {
                    session.setAttribute("NOMBRE_AP", user.get(0).get("AP_PATERNO") + " " + user.get(0).get("AP_MATERNO") + " " + user.get(0).get("NO_TRABAJADOR"));
                }
                session.setAttribute("LIST_MODULO", rD.List_Modulos(user.get(0).get("id_rol")));
                mp.put("rpta", true);

            } else {
                mp.put("rpta", false);
                mp.put("message", "Usuario o clave incorrecta.");
            }
            mp.put("status", true);
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
            mp.put("status", false);
            mp.put("message", ex.getMessage());
        } finally {
            Gson gson = new Gson();
            out.print(gson.toJson(mp));
            out.flush();
            out.close();
        }
    }

}
