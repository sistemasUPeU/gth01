package pe.edu.upeu.gth.controller.inbox;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.AutorizacionDAO;
import pe.edu.upeu.gth.dao.CorreoDAO;
import pe.edu.upeu.gth.dao.DgpDAO;
import pe.edu.upeu.gth.dao.EmpleadoDAO;
import pe.edu.upeu.gth.dao.NotificationDAO;
import pe.edu.upeu.gth.dto.CustomUser;

@Controller
@Scope("request")
@RequestMapping("/autorizacion/")
public class AutorizacionController {

	PrintWriter out;
	Gson gson = new Gson();
	String Rol;
	String ide;
	String idu;
	String idp;
	String iddep;
	String iddir;
	AutorizacionDAO a = new AutorizacionDAO(AppConfig.getDataSource());
	NotificationDAO notdao = new NotificationDAO(AppConfig.getDataSource());
	CorreoDAO correo = new CorreoDAO();
	DgpDAO dgp = new DgpDAO(AppConfig.getDataSource());
	EmpleadoDAO e = new EmpleadoDAO(AppConfig.getDataSource());
	/* permisos */
	boolean permisoAsigFam = false;
	boolean permisoEsSistema = false;
	boolean permissionDireccionFilter = false;
	boolean permissionDepartFilter = false;
	boolean permissionPuestoFilter = false;
	Map<String, Object> rpta = new HashMap<String, Object>();
	Map<String, Object> mp = new HashMap<>();

	@GetMapping("aceptar")
	public void aceptar(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String iddgp = request.getParameter("IDDETALLE_DGP");
		String estado = "1";
		/* Cambiar con un trigger al momento de insertar */
		System.out.println("Call List_id_Autorizacion");
		cargardatos(authentication);
		List<Map<String, Object>> autDGP = a.List_id_Autorizacion(ide, idu, iddgp);
		/* Cambiar con un trigger al momento de insertar */
		System.out.println("Call List_id_Autorizacion");
		if (autDGP.size() == 1) {
			System.out.println("Enter to Autorizacion DGP");
			Map<String, Object> vAut = autDGP.get(0);
			System.out.println("1 :" + vAut.get("NU_PASOS"));
			System.out.println("2 :" + vAut.get("ID_PASOS"));
			System.out.println("3 :" + vAut.get("CO_PASOS"));
			System.out.println("4 :" + vAut.get("ID_DETALLE_REQ_PROCESO"));
			System.out.println("5 :" + ide);
			/*
			 * Cambiar con un trigger al momento de insertar (esta generando mucho retraso)
			 */
			// dgp.VAL_DGP_PASOS();
			/* Autorización */
			a.Insert_Autorizacion("", iddgp, estado, (String) vAut.get("NU_PASOS"), "", idu, "", "",
					(String) vAut.get("CO_PASOS"), ide, (String) vAut.get("ID_DETALLE_REQ_PROCESO"),
					(String) vAut.get("ID_PASOS"));
			/* Notificaciones */
			String username = ((CustomUser) authentication.getPrincipal()).getUsername();

			List<String> ids = notdao.PrevSteps(iddgp);
			String idusuarioprev;
			for (int i = 0; i < ids.size(); i++) {
				idusuarioprev = ids.get(i);
				notdao.Registrar(Rol, "0", "0", "Empleado autorizado por " + username,
						"trabajador?idtr=" + (String) vAut.get("ID_TRABAJADOR") + "&opc=list",
						vAut.get("NO_TRABAJADOR") + " " + vAut.get("AP_PATERNO") + " " + vAut.get("AP_MATERNO"), "1",
						idusuarioprev, false);
			}
			/* End Notify */
			// sesion.setAttribute("List_id_Autorizados", a.List_Autorizados(idp));

		} else {
			System.out.println("Enter to Autorizacion academico");
			List<Map<String, Object>> autAcademico = a.List_Autorizacion_Academico(idp, idu, iddgp);
			if (autAcademico.size() == 1) {
				Map<String, Object> vAutAcademico = autAcademico.get(0);
				System.out.println("Academico");
				System.out.println("1 :" + vAutAcademico.get("NU_PASOS"));
				System.out.println("2 :" + vAutAcademico.get("ID_PASOS"));
				System.out.println("3 :" + vAutAcademico.get("CO_PASOS"));
				System.out.println("4 :" + vAutAcademico.get("ID_DETALLE_REQ_PROCESO"));
				System.out.println("5 :" + idp);
				a.Insert_Autorizacion("", iddgp, estado, (String) vAutAcademico.get("NU_PASOS"), "", idu, "", "",
						(String) vAutAcademico.get("CO_PASOS"), idp,
						(String) vAutAcademico.get("ID_DETALLE_REQ_PROCESO"), (String) vAutAcademico.get("ID_PASOS"));

			}
		}
		rpta.put("lista", a.List_id_Autorizacion(ide, idu, ""));
		respuesta(response);
	}

	@GetMapping("HDGP")
	public ModelAndView HDGP(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String iddgp = request.getParameter("iddgp");
		out.print(iddgp);
		dgp.HABILITAR_DGP(iddgp);
		cargardatos(authentication);
		setPermisos();
		if (permissionDepartFilter) {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO(iddep, "", "", false));
		}
		if (permissionDireccionFilter) {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO("", iddir, "", false));
		}
		if (permissionPuestoFilter) {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO("", "", idp, false));
		} else {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO(iddep, "", "", false));
		}
		// sesion.setAttribute("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO(iddep));
		// response.sendRedirect("Vista/Dgp/Proceso_Dgp.jsp");
		// It needs to be checked
		return new ModelAndView("Dgp/Proceso_Dgp", "rpta", gson.toJson(rpta));
	}

	@GetMapping("eliminarDGP")
	public ModelAndView eliminarDGP(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		String iddgp = request.getParameter("iddgp");
		dgp.eliminarDGP(iddgp);
		cargardatos(authentication);
		setPermisos();
		if (permissionDepartFilter) {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO(iddep, "", "", false));
		}
		if (permissionDireccionFilter) {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO("", iddir, "", false));
		}
		if (permissionPuestoFilter) {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO("", "", idp, false));
		} else {
			rpta.put("LIST_DGP_PROCESO", dgp.LIST_DGP_PROCESO(iddep, "", "", false));
		}
		// It needs to be checked
		return new ModelAndView("Dgp/Proceso_Dgp", "rpta", gson.toJson(rpta));
	}

	@GetMapping("Rechazar")
	public ModelAndView Rechazar(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		cargardatos(authentication);
		String iddgp = request.getParameter("IDDETALLE_DGP");
		String comentario = request.getParameter("comentario");
		String estado = "2";
		String nropaso = request.getParameter("NROPASO");
		// String usuario_ip = "";
		String cod = request.getParameter("COD");
		String iddrp = request.getParameter("IDDETALLE_REQ_PROCESO");
		String idpasos = request.getParameter("IDPASOS");
		String nombres = request.getParameter("NOMBRES");
		String idtrab = request.getParameter("IDTRAB");
		/* Cambiar con un trigger al momento de insertar */
		dgp.VAL_DGP_PASOS();
		dgp.RECHAZAR_DGP(iddgp);
		String id_autorizacion = (String) a
				.Insert_Autorizacion_dev("", iddgp, estado, nropaso, "", idu, "", "", cod.trim(), idp, iddrp, idpasos)
				.get("ID_TABLE");
		a.Insert_comentario_Aut("", id_autorizacion, iddgp, idu, "1", id_autorizacion, comentario);
		/* Notificaciones */
		String username = ((CustomUser) authentication.getPrincipal()).getUsername();

		List<String> ids = notdao.PrevSteps(iddgp);
		String idusuarioprev;
		for (int i = 0; i < ids.size(); i++) {
			idusuarioprev = ids.get(i);
			notdao.Registrar(Rol, "0", "0", "Empleado rechazado por " + username,
					"trabajador?idtr=" + idtrab + "&opc=list", nombres, "1", idusuarioprev, false);
		}
		rpta.put("List_id_Autorizacion", a.List_id_Autorizacion(idp, idu, ""));
		rpta.put("List_id_Autorizados", a.List_Autorizados(idp));
		return new ModelAndView("Vista/Dgp/Autorizar_Requerimiento.jsp?r=ok", "rpta", gson.toJson(rpta));
	}

	@GetMapping("Autorizacion_CD")
	public ModelAndView Autorizacion_CD(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		cargardatos(authentication);
		rpta.put("List_Autorizacion_Academico", a.List_Autorizacion_Academico(idp, idu, ""));
		return new ModelAndView("Vista/Academico/Autorizar_Carga_Academica.jsp", "rpta", gson.toJson(rpta));
	}

	@GetMapping("headerTableAutorizacionCA")
	public void headerTableAutorizacionCA(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		cargardatos(authentication);
		String htmlHeader = "";
		if (Rol.trim().equals("ROL-0007") | Rol.trim().equals("ROL-0001")) {

			htmlHeader += "  <tr>";
			htmlHeader += " <th class='hasinput' colspan='14' style='width:95%' ></th>";
			htmlHeader += "<th class='hasinput'>";
			htmlHeader += " <button   rel='tooltip' data-placement='left' data-original-title='Autorizar y Procesar codigo de huella digital'  class='btn btn-warning btn-circle btn-lg  btn_cod_huella'>";
			htmlHeader += "  <i class='glyphicon glyphicon-ok'></i></button>";
			htmlHeader += " </th>";
			htmlHeader += "   </tr>";
		}
		if (Rol.trim().equals("ROL-0009")) {
			htmlHeader += "     <tr>";
			htmlHeader += "   <th class='hasinput' colspan='14' style='width:95%' ></th>";
			htmlHeader += "   <th class='hasinput'  style='' >";
			htmlHeader += "      <button   rel='tooltip' data-placement='left' data-original-title='Autorizar y Procesar código aps'";
			htmlHeader += "            class='btn bg-color-magenta txt-color-white btn-circle btn-lg  btn_cod_aps'>";
			htmlHeader += "        <i class='glyphicon glyphicon-ok'></i></button>";
			htmlHeader += "    </th>";
			htmlHeader += "  </tr>";
		}
		if (Rol.trim().equals("ROL-0006")) {
			htmlHeader += "   <tr>";
			htmlHeader += "  <th class='hasinput' colspan='15' style='width:95%' ></th>";
			htmlHeader += "  <th class='hasinput'>";
			htmlHeader += "     <button   disabled rel='tooltip' data-placement='top' data-original-title='Procesar Firmas'  class='btn btn-primary btn-circle btn-sm btn_pro_firma'>";
			htmlHeader += "       <i class='glyphicon glyphicon-ok'></i></button>";
			htmlHeader += " </th>";
			htmlHeader += "  <th class='hasinput' >";
			htmlHeader += "   <button  disabled rel='tooltip' data-placement='top' data-original-title='Procesar a remuneraciones'  class='btn btn-default btn-circle btn-sm btn_pro_remuneracion'>";
			htmlHeader += "     <i class='glyphicon glyphicon-ok'></i></button>";
			htmlHeader += "   </th>";
			htmlHeader += "   <th class='hasinput'  style='' >";
			htmlHeader += " <button  disabled  rel='tooltip' data-placement='top' data-original-title='Procesar a Firmas y Envio a Remuneraciones'  class='btn btn-warning btn-circle btn-sm btnProcesarFirmaAndRem'>";
			htmlHeader += "    <i class='glyphicon glyphicon-ok'></i></button>";
			htmlHeader += "   </th>";
			htmlHeader += "   </tr>";
		}
		htmlHeader += "  <tr data-hide='phone,tablet'>";
		htmlHeader += "   <th><strong>Nro</strong></th>";
		htmlHeader += "    <th data-hide='phone,tablet'><strong>Acción</strong></th>";
		htmlHeader += "  <th>Mes</th>";
		htmlHeader += "   <th data-hide='phone,tablet'><strong>Foto</strong> </th>";
		htmlHeader += "   <th data-class='expand' ><strong>Apellidos Y Nombres</strong></th>";
		htmlHeader += "    <th data-hide='phone,tablet'><strong>Puesto</strong></th>";
		htmlHeader += "    <th data-hide='phone,tablet'><strong>Area</strong></th>";
		htmlHeader += "    <th data-hide='phone,tablet'><strong>Departamento</strong></th>";
		htmlHeader += "   <th data-hide='phone,tablet'><strong>Requerimiento</strong></th>";
		htmlHeader += "  <th data-hide='phone,tablet'><strong>Descripción</strong></th>";
		htmlHeader += "   <th  data-hide='phone,tablet'>Fecha de Creación</th>  ";
		htmlHeader += "    <th  data-hide='phone,tablet'>Motivo</th>  ";
		htmlHeader += "   <th  data-hide='phone,tablet'>MFL</th>  ";
		if (iddep.equals("DPT-0019")) {
			htmlHeader += "  <th><strong>¿Cumplio Plazos?</strong></th>";
			if (Rol.trim().equals("ROL-0006")) {
				htmlHeader += "   <th><strong>¿Contrato Elaborado?</strong></th>";
				htmlHeader += "    <th><strong>¿Firmo Contrato?</strong></th>";
				htmlHeader += " <th><strong>Enviar a Rem.</strong></th>";
				htmlHeader += "  <th><strong>¿Contrato Subido?</strong></th>";
			}
		}
		if (Rol.trim().equals("ROL-0009")) {
			htmlHeader += "  <th><strong>Código APS</strong></th>";
		}
		if (Rol.trim().equals("ROL-0007") | Rol.trim().equals("ROL-0001")) {
			htmlHeader += " <th><strong>Código Huella</strong></th>";
		}
		htmlHeader += "  </tr>";
		rpta.put("htmlHeader", htmlHeader);
		String htmlBody = "";
		int num_cod_aps = 0;
		int num_cod_huella = 0;
		List<Map<String, Object>> listaAutCA = a.List_Autorizacion_Academico(idp, idu, "");
		for (int f = 0; f < listaAutCA.size(); f++) {
			Map<String, Object> autCA = (Map<String, Object>) listaAutCA.get(f);
			htmlBody += "<tr>";
			htmlBody += "<td>" + (f + 1) + "</td>";
			htmlBody += "   <td>";
			htmlBody += " <div class='btn-group'>";
			htmlBody += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>";
			htmlBody += "<i class='fa fa-gear fa-lg'></i>";
			htmlBody += "  <i class='fa fa-caret-down'></i>";
			htmlBody += "  </button>";
			htmlBody += " <ul class='dropdown-menu'>";
			htmlBody += "  <li><a href='../../dgp?iddgp=" + ((String) autCA.get("ID_DGP")).trim()
					+ "&opc=Seguimiento'>Ver Proceso</a></li>";
			htmlBody += "  <li><a href='../../documento?iddgp=" + ((String) autCA.get("ID_DGP")).trim() + "&idtr="
					+ ((String) autCA.get("ID_TRABAJADOR")).trim() + "&opc=Ver_Documento'>Ver Documentos</a></li>";
			htmlBody += "   <li><a  data-valor='" + ((String) autCA.get("ID_DGP")).trim() + ";"
					+ ((String) autCA.get("ID_TRABAJADOR")).trim() + ";" + ((String) autCA.get("AP_PATERNO")).trim()
					+ " " + ((String) autCA.get("AP_MATERNO")).trim() + " "
					+ ((String) autCA.get("NO_TRABAJADOR")).trim()
					+ "' class='click' data-toggle='modal' data-target='#myModal' data-backdrop='static' data-keyboard='false' onclick='sendAjax('')' >Comentario</a></li>";
			if (Integer.parseInt((String) autCA.get("ELAB_CONTRATO")) > 0) {
				htmlBody += "<li>";
				htmlBody += "  <a href='../../contrato?idtr=" + ((String) autCA.get("ID_TRABAJADOR")).trim()
						+ "&opc=Detalle_Contractual'>Ver Contrato</a></li>";
			}
			htmlBody += "   <li class='divider'></li>";
			htmlBody += " <li>";
			htmlBody += " <li>";

			htmlBody += "   <a href='../../trabajador?idtr=" + ((String) autCA.get("ID_TRABAJADOR")).trim()
					+ "&IDDETALLE_REQ_PROCESO=" + ((String) autCA.get("ID_DETALLE_REQ_PROCESO")).trim()
					+ "&iddetalle_dgp=" + ((String) autCA.get("ID_DGP")).trim() + "&p="
					+ ((String) autCA.get("ID_PUESTO")).trim() + "&cod=" + ((String) autCA.get("CO_PASOS")).trim()
					+ "&idpasos=" + ((String) autCA.get("ID_PASOS")).trim() + "&autorizacion=1&opc=aut&nup="
					+ ((String) autCA.get("NU_PASOS")).trim() + "'>";
			if (Rol != null) {
				if (Rol.trim().equals("ROL-0006")) {
					htmlBody += "Hacer Contrato";
				} else {
					htmlBody += "Autorizar";
				}
			}
			htmlBody += " </a>";
			htmlBody += " </li>";
			htmlBody += "  </ul>";
			htmlBody += " </div>";
			htmlBody += " </td>";
			htmlBody += " <td >";
			htmlBody += a.Mes_plazo(((String) autCA.get("ID_DGP")).trim());
			htmlBody += "  </td>   ";
			if (((String) autCA.get("AR_FOTO")).trim() == null) {
				htmlBody += " <td>";
				htmlBody += " <img class='user_avatar_" + ((String) autCA.get("ID_TRABAJADOR")).trim()
						+ "' src='../../img/avatar_default.jpg'  width='30'  height='30'>";
				htmlBody += "</td>";
			} else {
				htmlBody += " <td>";
				htmlBody += "   <img class='user_avatar_" + ((String) autCA.get("ID_TRABAJADOR")).trim()
						+ "' src='../../Archivo/Fotos/" + ((String) autCA.get("AR_FOTO")).trim()
						+ "'  width='30'  height='30'>";
				htmlBody += " </td>";
			}
			htmlBody += "  <td >" + ((String) autCA.get("AP_PATERNO")).trim() + " "
					+ ((String) autCA.get("AP_MATERNO")).trim() + " " + ((String) autCA.get("NO_TRABAJADOR")).trim()
					+ "</td>";
			htmlBody += " <td >" + ((String) autCA.get("NO_PUESTO")).trim() + "</td>";
			htmlBody += "  <td >" + ((String) autCA.get("NO_AREA")).trim() + "</td>";
			htmlBody += "<td >" + ((String) autCA.get("NO_DEP")).trim() + "</td>";
			htmlBody += " <td >" + ((String) autCA.get("NO_REQ")).trim() + "</td>";
			htmlBody += " <input type='hidden' class='val_aut" + (f + 1) + " valAut' "
					+ " value='&IDDETALLE_REQ_PROCESO=" + ((String) autCA.get("ID_DETALLE_REQ_PROCESO")).trim()
					+ "&IDDETALLE_DGP=" + ((String) autCA.get("ID_DGP")).trim() + "&p="
					+ ((String) autCA.get("ID_PUESTO")).trim() + "&COD=" + ((String) autCA.get("CO_PASOS")).trim()
					+ "&IDPASOS=" + ((String) autCA.get("ID_PASOS")).trim() + "&NROPASO="
					+ ((String) autCA.get("ID_PASOS")).trim() + "&IDTR=" + ((String) autCA.get("ID_TRABAJADOR")).trim()
					+ "'/>";
			htmlBody += "    <input type='hidden' class='val_firm" + (f + 1) + "' value='&IDDETALLE_DGP="
					+ ((String) autCA.get("ID_DGP")).trim() + "&IDTR=" + ((String) autCA.get("ID_TRABAJADOR")).trim()
					+ "'/>";
			htmlBody += "   <input type='hidden' class='correos_" + (f + 1) + " correoTrabajador' value='&IDTR="
					+ ((String) autCA.get("ID_TRABAJADOR")).trim() + "&co_inst=" + "&co_pers='/>";
			htmlBody += " <td class='text-info'>";
			htmlBody += "    <a href='../../trabajador?idtr=" + ((String) autCA.get("ID_TRABAJADOR")).trim()
					+ "&IDDETALLE_REQ_PROCESO=" + ((String) autCA.get("ID_DETALLE_REQ_PROCESO")).trim()
					+ "&iddetalle_dgp=" + ((String) autCA.get("ID_DGP")).trim() + "&p="
					+ ((String) autCA.get("ID_PUESTO")).trim() + "&cod=" + ((String) autCA.get("CO_PASOS")).trim()
					+ "&idpasos=" + ((String) autCA.get("ID_PASOS")).trim() + "+&autorizacion=1&opc=aut&nup="
					+ ((String) autCA.get("NU_PASOS")).trim() + "'>";
			htmlBody += "       <strong>" + ((String) autCA.get("DE_PASOS")).trim() + "</strong>";
			htmlBody += "   </a>";
			htmlBody += "  </td>";
			htmlBody += "   <td >" + ((String) autCA.get("FE_CREACION")).trim() + "</td>";
			htmlBody += " <td>";
			htmlBody += "No registrado";
			htmlBody += " </td> ";
			htmlBody += "  <td>";
			htmlBody += "No registrado";
			htmlBody += " </td> ";
			if (iddep.equals("DPT-0019")) {
				htmlBody += "<td>";
				if (Integer.parseInt(((String) autCA.get("VAL_PLAZO")).trim()) > 0) {
					htmlBody += " <a href='../../plazo_dgp?opc=Ver_detalle_plazo&iddgp="
							+ ((String) autCA.get("ID_DGP")).trim()
							+ "' class='label label-danger popoverPlazo'  data-toggle='popover' data-trigger='hover' data-placement='top' title='Record de plazos cumplidos' data-content=\""
							+ ((String) autCA.get("VER_LIST_PLAZO")).trim()
							+ "\" data-html='true'> <strong>No cumplio plazos</strong></a>";
					htmlBody += " </td>";
				} else if (Integer.parseInt(((String) autCA.get("VAL_PLAZO")).trim()) == 0) {
					htmlBody += "   <a href='../../plazo_dgp?opc=Ver_detalle_plazo&iddgp="
							+ ((String) autCA.get("ID_DGP")).trim()
							+ "' class='label label-primary popoverPlazo' data-toggle='popover' data-trigger='hover' data-placement='top' title='Record de plazos cumplidos' data-content=\""
							+ ((String) autCA.get("VER_LIST_PLAZO")).trim()
							+ "\" data-html='true'> <strong>Cumplio plazos</strong></a></td>";
				}
				if (Rol.equals("ROL-0006")) {

					htmlBody += "<td >";
					if (Integer.parseInt(((String) autCA.get("ELAB_CONTRATO")).trim()) == 0) {
						htmlBody += "No";
					} else {
						htmlBody += "Si";
					}
					htmlBody += "  </td> ";
					htmlBody += " <td>";
					if (Integer.parseInt(((String) autCA.get("VAL_FIRM_CONTRATO")).trim()) == 0) {
						if (Integer.parseInt(((String) autCA.get("ELAB_CONTRATO")).trim()) == 0) {
							htmlBody += "  !Falta procesar¡";
						} else {
							htmlBody += "    <div class='smart-form'>";
							htmlBody += "    <label class='toggle'><input type='checkbox' value='" + (f + 1)
									+ "'  class='firm_contr'  name='estado' name='checkbox-toggle' ><i data-swchon-text='SI' data-swchoff-text='NO'></i></label>";
							htmlBody += "   </div>";
						}
					} else {
						htmlBody += " Si";
					}
					htmlBody += "    </td>";
					htmlBody += "    <td>";
					if (Integer.parseInt(((String) autCA.get("VAL_FIRM_CONTRATO")).trim()) != 0
							& Integer.parseInt(((String) autCA.get("ELAB_CONTRATO")).trim()) != 0) {

						htmlBody += "    <div class='smart-form'>";
						htmlBody += "       <label class='toggle'><input type='checkbox' value='" + (f + 1)
								+ "'  class='env_rem" + (f + 1)
								+ "envioRem'  name='estado' name='checkbox-toggle' ><i data-swchon-text='SI' data-swchoff-text='NO'></i></label>";
						htmlBody += " </div>";

					} else {
						htmlBody += "¡Falta Procesar!";
					}

					htmlBody += " </td>";
					htmlBody += " <td>";
					htmlBody += " No";
					htmlBody += "  </td>";
				}
				if (Rol.equals("ROL-0009")) {
					if (Integer.parseInt(((String) autCA.get("VAL_COD_APS_EMPLEADO")).trim()) == 0) {
						num_cod_aps++;
						htmlBody += " <td>";
						htmlBody += "  <input type='text' name='cod_aps' maxlength='6' class='cod_aps" + (f + 1)
								+ " inp_cod_aps' style='width:50px'/>";
						htmlBody += "</td>";
						htmlBody += " <input type='hidden' name='idtr'  class='idtr" + (f + 1)
								+ " idTrabajador' value='" + ((String) autCA.get("ID_TRABAJADOR")).trim() + "' />";
					} else {
						htmlBody += "  <td>";
						htmlBody += "  <strong>" + ((String) autCA.get("CO_APS")).trim() + "</strong>";
						htmlBody += "  </td>";
					}
				}
				if (Rol.trim().equals("ROL-0007") | Rol.trim().equals("ROL-0001")) {
					if (Integer.parseInt(((String) autCA.get("VAL_COD_HUELLA_EMP")).trim()) == 0) {
						num_cod_huella++;

						htmlBody += "  <td>";
						htmlBody += "   <input type='text' name='cod_huella' maxlength='6' class='form-control cod_huella"
								+ (f + 1) + " inp_cod_huella' style='width:70px'/>";
						htmlBody += "   </td>";
						htmlBody += " <input type='hidden' name='idtr'  class='idtr=" + (f + 1) + " value='"
								+ ((String) autCA.get("ID_TRABAJADOR")).trim() + "' />";
					} else {
						htmlBody += "  <td class='' >";
						htmlBody += "  <div class='input-group' >";

						htmlBody += "   <input class='form-control' placeholder=''  style='width: 70px;' type='text' value='"
								+ ((String) autCA.get("CO_HUELLA_DIGITAL")).trim() + "'>";
						htmlBody += "     <span class='input-group-addon'  >";
						htmlBody += "     <span class='checkbox'>";
						htmlBody += " <label >";
						htmlBody += "   <input type='checkbox' class='checkbox style-0 cbHuellaItem' >";
						htmlBody += "       <span></span>";
						htmlBody += "    </label>";
						htmlBody += "  </span>";
						htmlBody += "  </span>";

						htmlBody += "  </div>";
						htmlBody += "  </td>";
					}
				}
				htmlBody += " </tr>";
			}

		}
		rpta.put("htmlBody", htmlBody);
		rpta.put("access", true);
		respuesta(response);
	}

	@GetMapping("mens_cod_aps")
	public ModelAndView mens_cod_aps(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		cargardatos(authentication);
		codapsAndCodhuella();
		// It needs to be checked
		return new ModelAndView("Vista/Dgp/Autorizar_Requerimiento?m=si", "rpta", gson.toJson(rpta));
	}

	@GetMapping("mens_cod_huella")
	public ModelAndView mens_cod_huella(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		cargardatos(authentication);
		codapsAndCodhuella();
		// It needs to be checked
		return new ModelAndView("Vista/Dgp/Autorizar_Requerimiento?h=si", "rpta", gson.toJson(rpta));
	}

	@GetMapping("Enviar_Correo")
	public void Enviar_Correo(HttpServletRequest request, HttpServletResponse response) {
		String emailSubject = request.getParameter("from");
		String pwdSubject = request.getParameter("pwdSubject");
		String to = request.getParameter("to");
		String from = request.getParameter("from");
		String asunto = request.getParameter("asunto");
		String cuerpo = request.getParameter("cuerpo");
		String[] emails = to.split(",");
		for (String email : emails) {
			System.out.println("email:" + email);
			correo.Enviar(emailSubject, pwdSubject, to, from, asunto, cuerpo);
		}
		System.out.print("Ejecutando envio de correos");
		/*
		 * correo.Enviar("jairleo95@gmail.com", "jairleo95@gmail.com",
		 * "CARPETA LABORAL - UPEU", "Estimado(a) Colaborador(a),\n" +
		 * "Compartimos la siguiente información\n \n" +
		 * "- Bienestar para el trabajador\n" +
		 * "- Reglamento de Control de Asistencia\n" + "- Reglamento de trabajo\n" +
		 * "- Boletín Informativo - sistema pensionario\n \n" + "Saludos Cordiales");
		 */
		rpta.put("sendto", emails);
		rpta.put("status", true);
		respuesta(response);
	}

	@GetMapping("List_Dgp_Aut")
	public void List_Dgp_Aut(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		cargardatos(authentication);
		String draw = request.getParameter("draw");
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int pageNumber = (start / length) + 1;
		String anno = request.getParameter("anno");
		int mes = 0;
		if (request.getParameter("mes") != null) {
			if (!request.getParameter("mes").equals("")) {
				mes = Integer.parseInt(request.getParameter("mes"));
			}
		}
		List<Map<String, Object>> lista = a.List_Dgp_Autorizados(idu, pageNumber, length, mes, anno);
		int size = a.getListAuthorizeRequirementsSize(idu, mes, anno);
		rpta.put("rpta", "1");
		rpta.put("draw", draw);
		rpta.put("recordsTotal", size);
		rpta.put("recordsFiltered", size);
		rpta.put("data", lista);
		respuesta(response);
	}

	@GetMapping("ValBtnAutorizacion")
	public void ValBtnAutorizacion(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		cargardatos(authentication);
		String html = "";
		String idtr = request.getParameter("trabajador");
		if (Rol.trim().equals("ROL-0009")) {
			int val_aps = val_aps = e.val_cod_aps_empleado(idtr);
			if (val_aps > 0) {
				html = "<button class='btn btn-labeled btn-success btn-autor' type='button'>"
						+ "                            <span class='btn-label'><i class='glyphicon glyphicon-ok'></i></span>PROCESAR REQUERIMIENTO "
						+ "                        </button>";
			} else {
				html = "<div class='alert alert-warning fade in'><i class='fa-fw fa fa-warning'></i><strong>Atención!</strong> Usted no puede <strong>AUTORIZAR</strong> el requerimiento, debe primero registrar el <strong>Código APS</strong>.</div>";
			}
		} else if (Rol.trim().equals("ROL-0007") | Rol.trim().equals("ROL-0001")) {
			int val_huella = e.val_cod_huella(idtr);
			if (val_huella > 0) {
				html = "<button class='btn btn-labeled btn-success btn-autor' type='button'>"
						+ "                            <span class='btn-label'><i class='glyphicon glyphicon-ok'></i></span>AUTORIZAR REQUERIMIENTO "
						+ "                        </button>";

			} else {
				html = "<div class='alert alert-warning fade in'><i class='fa-fw fa fa-warning'></i><strong>Atención!</strong> Usted no puede <strong>AUTORIZAR</strong> el requerimiento, debe primero registrar el <strong>Código de Huella Digital</strong>.</div>";
			}
		} else {
			html = "<button class='btn btn-labeled btn-success btn-autor' type='button'>"
					+ "                            <span class='btn-label'><i class='glyphicon glyphicon-ok'></i></span>AUTORIZAR REQUERIMIENTO "
					+ "                        </button>";
		}
		rpta.put("rpta", "1");
		rpta.put("data", html);
		respuesta(response);
	}

	@GetMapping("ShowListProcesarReq")
	public void ShowListProcesarReq(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		boolean tipo_lista = Boolean.parseBoolean((request.getParameter("tipo_lista")));
		String html_table = "";
		cargardatos(authentication);
		setPermisos();
		if (tipo_lista) {
			html_table += "<table id='table_procesar' class='table table-striped table-bordered table-hover' width='100%'>";
			html_table += "<thead><tr>";
			html_table += (permisoAsigFam & permisoEsSistema)
					? " <th class='hasinput' colspan='7' style='width:95%' ></th>"
					: "<th class='hasinput' colspan='7' style='width:95%' ></th>";
			html_table += (permisoAsigFam)
					? "<th class='hasinput'  ><center><button  class='btn btn-primary btn-circle btn-lg btnAsigFam'><i class='glyphicon glyphicon-ok'></i></button></center></th>"
					: "";
			html_table += (permisoEsSistema)
					? " <th class='hasinput' ><center><button  class='btn bg-color-blueDark txt-color-white  btn-circle btn-lg btnActSisEs'><i class='glyphicon glyphicon-ok'></i></button></center></th>"
					: "";
			html_table += "</tr>" + "  <tr data-hide='phone,tablet'> <th><strong>Nro</strong></th>"
					+ "  <th><strong>Mes - Año </strong></th>"
					+ " <th data-class='expand' ><strong>Apellidos Y Nombres</strong></th>"
					+ "  <th data-hide='phone,tablet'><strong>Puesto</strong></th>"
					+ " <th data-hide='phone,tablet'><strong>Area</strong></th>"
					+ "  <th data-hide='phone,tablet'><strong>Departamento</strong></th>"
					+ "  <th data-hide='phone,tablet'><strong>Requerimiento</strong></th>";
			html_table += (permisoAsigFam) ? " <th  data-hide='phone,tablet'>Asig. Fam.</th> " : "";
			html_table += (permisoEsSistema) ? "<th  data-hide='phone,tablet'>T-REGISTRO</th>" : "";
			html_table += "</tr></thead>";
		} else {

			html_table += "<table id='table_autorizados' class='table table-striped table-bordered table-hover' width='100%'>";
			html_table += "<thead>" + "  <tr data-hide='phone,tablet'> <th><strong>Nro</strong></th>"
					+ "   <th><strong>Mes - Año </strong></th>"
					+ " <th data-class='expand' ><strong>Apellidos Y Nombres</strong></th>"
					+ "  <th data-hide='phone,tablet'><strong>Puesto</strong></th>"
					+ " <th data-hide='phone,tablet'><strong>Area</strong></th>"
					+ "  <th data-hide='phone,tablet'><strong>Departamento</strong></th>"
					+ "  <th data-hide='phone,tablet'><strong>Requerimiento</strong></th>";
			html_table += (permisoAsigFam) ? " <th  data-hide='phone,tablet'>Asig. Fam.</th> " : "";
			html_table += (permisoEsSistema) ? "<th  data-hide='phone,tablet'>T-REGISTRO</th>" : "";
			html_table += "</tr></thead>";
		}
		if (tipo_lista) {
			html_table += "<tbody class='tbody_procesar_req'> </tbody> ";
		} else {
			html_table += "<tbody class='tbody_procesar_req_aut'> </tbody> ";
		}
		html_table += "</table>";
		List<Map<String, Object>> lista = a.List_procesar_req(tipo_lista, permisoAsigFam, permisoEsSistema);
		String text_html = "";
		for (int i = 0; i < lista.size(); i++) {
			Map<String, ?> x = lista.get(i);
			// x.get("idtr") String idtr = cr.Encriptar(x.get("idtr") + "");
			text_html += "<tr>";
			text_html += "<td>" + (i + 1) + "</td>";
			text_html += "<td>" + x.get("mes") + " - " + x.get("anno") + "</td>";
			text_html += "<td><a href='../../trabajador?idtr=" + x.get("idtr") + "&opc=list&dgp=" + x.get("iddgp")
					+ "'>" + x.get("ap_p") + " " + x.get("ap_m") + " " + x.get("nombre") + "</a></td>";
			text_html += "<td>" + x.get("puesto") + "</td>";
			text_html += "<td>" + x.get("area") + "</td>";
			text_html += "<td>" + x.get("dep") + "</td>";
			text_html += "<td>" + x.get("req") + "</td>";
			if (x.get("es_asignacion_f").equals("0")) {
				text_html += (permisoAsigFam)
						? "<td class='smart-form'><center><label class='toggle'><input type='checkbox' name='checkbox-toggle' class='chkAsigFam"
								+ (i) + "' value='" + x.get("iddgp")
								+ "'><i data-swchon-text='SI' data-swchoff-text='NO'></i></label></center></td>"
						: "";
			} else {
				text_html += (permisoAsigFam) ? "<td>Si</td>" : "";
			}
			if (x.get("es_activ_sis").equals("0")) {
				text_html += (permisoEsSistema)
						? "<td class='smart-form' ><center><label class='toggle'><input type='checkbox' name='checkbox-toggle' class='chkActSistEs"
								+ (i) + "' value='" + x.get("iddgp")
								+ "' ><i data-swchon-text='SI' data-swchoff-text='NO'></i></label></center></td>"
						: "";
			} else {
				text_html += (permisoEsSistema) ? "<td>Si</td>" : "";
			}
			text_html += "</tr>";
		}
		rpta.put("rpta", "1");
		rpta.put("lista", lista.size());
		rpta.put("text_html", text_html);
		rpta.put("html_table", html_table);
		respuesta(response);
	}

	@GetMapping("ListProcesarReq")
	public String ListProcesarReq() {
		return "Vista/Dgp/Procesar_Req";
	}

	@GetMapping("UpdateStatusDgp_Procesar")
	public void UpdateStatusDgp_Procesar(HttpServletRequest request, HttpServletResponse response) {
		boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		String[] array = request.getParameterValues("json[]");
		a.UpdateDgp_EstadoProcesar(array, tipo, estado);
		rpta.put("rpta", "1");
		rpta.put("aaas", array);
		respuesta(response);
	}

	@GetMapping("ShowCkbEstado_procesarIndiviual")
	public void ShowCkbEstado_procesarIndiviual(HttpServletRequest request, HttpServletResponse response) {
		String iddgp = request.getParameter("iddgp");
		List<Map<String, Object>> lista = a.ShowCkbEstado_procesarIndiviual(iddgp);
		if (lista.size() > 0) {
			Map<String, ?> x = lista.get(0);
			int es_sis = Integer.parseInt(x.get("es_sis_estado") + "");
			int es_asiFam = Integer.parseInt(x.get("es_asig_fam") + "");
			String html_ckbAsigFam = "";
			String html_ckbEs_Sis = "";
			if (es_asiFam == 0) {
				html_ckbAsigFam += (permisoAsigFam)
						? "  <div class='col-md-8'><strong>¿Asignación Familiar?</strong></div><div class='col-md-4'><label class='toggle'>"
								+ "<input type='checkbox' name='checkbox-toggle' class='ckbAsigFam' value='" + iddgp
								+ "'><i data-swchon-text='SI' data-swchoff-text='NO'></i></label>" + "</div>"
						: "<div class='col-md-8'><strong>¿Asignación Familiar?</strong></div><div class='col-md-4'><label class='toggle'>No</div>";
			} else if (es_asiFam == 1) {
				html_ckbAsigFam += (permisoAsigFam)
						? "<div class='col-md-8'><strong>¿Asignación Familiar?</strong></div><div class='col-md-4'>"
								+ "<label class='toggle'><input type='checkbox' checked='' name='checkbox-toggle' class='ckbAsigFam' value='"
								+ iddgp + "'><i data-swchon-text='SI' data-swchoff-text='NO'></i></label></div>"
						: "<div class='col-md-8'><strong>¿Asignación Familiar?</strong></div><div class='col-md-4'><label class='toggle'>Si</div>";
			}
			if (es_sis == 0) {
				html_ckbEs_Sis += (permisoEsSistema)
						? " <div class='col-md-8'><strong>¿T-REGISTRO?</strong></div><div class='col-md-4'><label class='toggle'>"
								+ "<input type='checkbox'  name='checkbox-toggle' class='ckbEstSistema' value='" + iddgp
								+ "'><i data-swchon-text='SI' data-swchoff-text='NO'></i></label></div>"
						: "<div class='col-md-8'><strong>¿T-REGISTRO?</strong></div><div class='col-md-4'><label class='toggle'>No</div>";
			} else if (es_sis == 1) {
				html_ckbEs_Sis += (permisoEsSistema)
						? " <div class='col-md-8'><strong>¿T-REGISTRO?</strong></div><div class='col-md-4'><label class='toggle'>"
								+ "<input type='checkbox' checked='' name='checkbox-toggle' class='ckbEstSistema' value='"
								+ iddgp + "'><i data-swchon-text='SI' data-swchoff-text='NO'></i></label></div>"
						: "<div class='col-md-8'><strong>¿T-REGISTRO?</strong></div><div class='col-md-4'><label class='toggle'>Si</div>";
			}
			rpta.put("ckbAsigFam", html_ckbAsigFam);
			rpta.put("ckbEs_Sis", html_ckbEs_Sis);
			rpta.put("rpta", "1");
			respuesta(response);
		}
	}

	@GetMapping("/")
	public ModelAndView principal(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("contratacion/con_autorizar");
	}

	@RequestMapping(value = "/listas")
	public void Logueo(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String opc = request.getParameter("opc");
		cargardatos(authentication);
		try {
			switch (opc) {
			case "con_autorizar":
				rpta.put("List_id_Autorizacion", a.List_id_Autorizacion(idp, idu, ""));
				break;
			}

		} catch (Exception e) {
			rpta.put("rpta", false);
			System.out.println("Error controlador COMPONENTS : " + e);
		}
		Gson gson = new Gson();
		out.println(gson.toJson(rpta));
		out.flush();
		out.close();
	}

	public void codapsAndCodhuella() {
		rpta.put("List_id_Autorizacion", a.List_id_Autorizacion(idp, idu, ""));
		rpta.put("List_id_Autorizados", a.List_Autorizados(idp));
	}

	public void cargardatos(Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		Rol = ((CustomUser) authentication.getPrincipal()).getID_ROL();
		ide = ((CustomUser) authentication.getPrincipal()).getID_EMPLEADO();
		idu = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		idp = ((CustomUser) authentication.getPrincipal()).getID_PUESTO();
		iddep = ((CustomUser) authentication.getPrincipal()).getID_DEPARTAMENTO();
		iddir = ((CustomUser) authentication.getPrincipal()).getID_DIRECCION();
	}

	public void setPermisos() {
		switch (Rol) {
		case "ROL-0009":
			permisoAsigFam = true;
			permisoEsSistema = true;
			break;
		case "ROL-0017":
			permisoAsigFam = true;
			break;
		case "ROL-0018":
			permisoEsSistema = true;
			break;
		case "ROL-0001":
			permisoEsSistema = true;
			permisoAsigFam = true;
			permissionDepartFilter = true;
			break;
		case "ROL-0008":
			permissionDireccionFilter = true;
			break;
		case "ROL-0010":
			permissionPuestoFilter = true;
			break;
		default:
			permissionDepartFilter = true;
			break;
		}
	}

	public void respuesta(HttpServletResponse response) {
		try {
			rpta.put("rpta", true);
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
