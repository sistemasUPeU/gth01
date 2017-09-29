package pe.edu.upeu.gth.controller.processCore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.DetalleReqProcesoDAO;
import pe.edu.upeu.gth.dao.ProcesoDAO;
import pe.edu.upeu.gth.util.CCriptografiar;

@Controller
@RequestMapping("/proceso/")
public class ProcesoController {
	ProcesoDAO p =new ProcesoDAO(AppConfig.getDataSource());
	DetalleReqProcesoDAO iddrp = new DetalleReqProcesoDAO(AppConfig.getDataSource());
    Map<String, Object> rpta = new HashMap<String, Object>();
    Gson gson = new Gson();
	PrintWriter out;
	
	@GetMapping("Mantenimiento")
	public void Mantenimiento(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("Plazo/Reg_Plazo_Dgp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("Registrar")
	public void Registrar(HttpServletRequest request, HttpServletResponse response) {
		String nom = request.getParameter("nom");
        String desc = request.getParameter("desc");
        p.create(nom, desc);
        rpta.put("rpta", "1");
        respuesta(response);
	}
	
	@GetMapping("Modificar")
	public void Modificar(HttpServletRequest request, HttpServletResponse response) {
		String idpro = request.getParameter("id");
        String nom = request.getParameter("nom");
        String desc = request.getParameter("desc");
        p.editprocess(idpro, nom, desc);
        rpta.put("rpta", "1");
        respuesta(response);
	}
	
	@GetMapping("Listar")
	public void Listar(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> lista = p.List_Proceso();
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        respuesta(response);
	}
	
	@GetMapping("ListarT")
	public void ListarT(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> lista = p.List_AllProceso();
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        respuesta(response);
	}
	
	@GetMapping("Listar_Pro_Paso_Id")
	public void Listar_Pro_Paso_Id(HttpServletRequest request, HttpServletResponse response) {
		String id_req = request.getParameter("id_req");
        String id_pro = request.getParameter("id_pro");
        String id_dir = request.getParameter("id_dir");
        String id_dep = request.getParameter("id_dep");
        String id_area = request.getParameter("id_area");
        String id_ti_planilla = request.getParameter("id_ti_planilla");
		List<Map<String, Object>> lista = p.List_Pro_Paso_Id(id_req, id_pro, id_dir, id_dep, id_area, id_ti_planilla);
        rpta.put("rpta", "1");
        rpta.put("lista", lista);
        respuesta(response);
	}
	
	@GetMapping("Eliminar")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) {
	}
	
	@GetMapping("statupdate")
	public void statupdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
        String es = request.getParameter("es");
        p.statupdate(id, es);
        System.out.println("All is ok in ProcesoController.statupdate " + es + " " + id);
	}
	
	@GetMapping("insertDetalleReqProceso")
	public void insertDetalleReqProceso(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> drp=new HashMap<String,Object>();
		drp.put("esReqProceso", "1");
		String area = "0";
        String departamento = "0";
        if (request.getParameter("area") .equals("")) {
            area = "0";
        } else {
            area = request.getParameter("area");
        }
        if (request.getParameter("departamento") .equals("")) {
            departamento = "0";
        } else {
            departamento = request.getParameter("departamento");
        }
        drp.put("idArea", area);
        drp.put("idDepartamento", departamento);
        drp.put("idDireccion", request.getParameter("direccion"));
        drp.put("idProceso", request.getParameter("proceso"));
        drp.put("idRequerimiento", request.getParameter("requerimiento"));
        rpta.put("serialize", drp);
        String id = iddrp.insertDetalleReqProceso(drp);
        rpta.put("id", CCriptografiar.Encriptar(id));
        rpta.put("status", true);
	}
	
	public void respuesta(HttpServletResponse response) {
		try {
			out = response.getWriter();
	        out.println(gson.toJson(rpta));
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			rpta.put("rpta", "-1");
            rpta.put("mensaje", e.getMessage());
            out.print(gson.toJson(rpta));
            out.flush();
            out.close();
		}
	}
	
}
