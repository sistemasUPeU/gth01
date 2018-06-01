package pe.edu.upeu.gth.controller.recruitment;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.Carrera_UniversidadDAO;
import pe.edu.upeu.gth.dao.Datos_Hijo_TrabajadorDAO;
import pe.edu.upeu.gth.dao.DgpDAO;
import pe.edu.upeu.gth.dao.DireccionDAO;
import pe.edu.upeu.gth.dao.DocumentoDAO;
import pe.edu.upeu.gth.dao.EmpleadoDAO;
import pe.edu.upeu.gth.dao.Hist_Estado_CivilDAO;
import pe.edu.upeu.gth.dao.ListaDAO;
import pe.edu.upeu.gth.dao.TipoHoraPagoDAO;
import pe.edu.upeu.gth.dao.Tipo_DocumentoDAO;
import pe.edu.upeu.gth.dao.TrabajadorDAO;
import pe.edu.upeu.gth.dao.UbigeoDAO;
import pe.edu.upeu.gth.dto.CustomUser;
import pe.edu.upeu.gth.util.CCriptografiar;
import pe.edu.upeu.gth.util.DateFormat;

@Controller
@Scope("request")
@RequestMapping("/trabajador/")
public class TrabajadorController {

	private ModelAndView modelAndView;
	Map<String, Object> mp = new HashMap<>();
	DataSource d = AppConfig.getDataSource();
	ListaDAO li=new ListaDAO(d);
	DireccionDAO dir =new DireccionDAO(d);
	UbigeoDAO ub=new UbigeoDAO(d);
	Tipo_DocumentoDAO tdoc = new Tipo_DocumentoDAO(d);
	TrabajadorDAO tr = new TrabajadorDAO(d);
	Datos_Hijo_TrabajadorDAO h = new Datos_Hijo_TrabajadorDAO(d);
	DocumentoDAO doc = new DocumentoDAO(d);
	DgpDAO dgp = new DgpDAO(d);
	Carrera_UniversidadDAO cu = new Carrera_UniversidadDAO(d);
	EmpleadoDAO em = new EmpleadoDAO(d);
	Hist_Estado_CivilDAO ec = new Hist_Estado_CivilDAO(d);
	TipoHoraPagoDAO thp = new TipoHoraPagoDAO(d);
	Gson gson = new Gson();
	String idrol = "";
    String iduser = "";
    String iddep = "";
    boolean permisoShowAFP_SP = false;
    public void cargardatos(Authentication authentication) {
    	authentication=SecurityContextHolder.getContext().getAuthentication();
		idrol = ((CustomUser) authentication.getPrincipal()).getID_ROL();
		//String ide = ((CustomUser) authentication.getPrincipal()).getID_EMPLEADO();
		iduser = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		//String idp = ((CustomUser) authentication.getPrincipal()).getID_PUESTO();
		iddep=((CustomUser) authentication.getPrincipal()).getID_DEPARTAMENTO();
		//String iddir=((CustomUser) authentication.getPrincipal()).getID_DIRECCION();
		if(idrol.equals("ROL-0006")) {
			permisoShowAFP_SP = true;
		}
    }
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfile() {        
        mp.put("msg", "leandro");        
        return new ModelAndView("trabajador/profile","rpta",gson.toJson(mp));
    }
	
	@RequestMapping(value = "/Form_Reg", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> infoTrabajador() {
		System.out.println("Llega al controlador");
		Map<String, Object> sr = new HashMap<>();
		sr.put("List_Carrera", li.List_Carrera());
		sr.put("List_Nacionalidad", li.List_Nacionalidad());
		sr.put("List_Universidad", li.List_Universidad());
		sr.put("List_Departamento", ub.List_Departamento());
		sr.put("List_Situacion_Educativa", li.List_Situacion_Educativa());
		sr.put("Listar_via", dir.Listar_via());
		sr.put("Listar_zona", dir.Listar_zona());
		sr.put("Listar_tipo_doc", tdoc.Listar_tipo_doc());
		sr.put("list_año", li.lista_años());
		mp.put("Reg_Trabajador", sr);
        return mp;
    }
	
	@RequestMapping(value = "/Registrar", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public ModelAndView infoTrabajador(@RequestBody HttpServletRequest request,Authentication authentication) {
		String AP_PATERNO = request.getParameter("APELLIDO_P");
        String AP_MATERNO = request.getParameter("APELLIDO_M");
        String NO_TRABAJADOR = request.getParameter("NOMBRES");
        String TI_DOC = request.getParameter("TIPO_DOC");
        String NU_DOC = request.getParameter("NRO_DOC");
        String ES_CIVIL = request.getParameter("ESTADO_CIVIL");
        String FE_NAC = request.getParameter("FECHA_NAC");
        String ID_NACIONALIDAD = request.getParameter("NACIONALIDAD");
        String ID_DEPARTAMENTO = null;
        String ID_PROVINCIA = null;
        String ID_DISTRITO = request.getParameter("DISTRITO");
        String TE_TRABAJADOR = request.getParameter("TELEFONO");
        String CL_TRA = request.getParameter("CELULAR");
        String DI_CORREO_PERSONAL = request.getParameter("CORREO_PERSONAL");
        String DI_CORREO_INST = request.getParameter("CORREO_INST");
        String CO_SISTEMA_PENSIONARIO = request.getParameter("SISTEMA_PENSIONARIO");
        String LI_NIVEL_EDUCATIVO = request.getParameter("NIVEL_EDUCATIVO");
        String CARRERA = request.getParameter("CARRERA");
        String REGIMEN = request.getParameter("REGIMEN");
        String ES_INST_PERU = request.getParameter("ES_INST_PERU");
        String DE_ANNO_EGRESO = request.getParameter("A_EGRESO");
        String CM_OTROS_ESTUDIOS = request.getParameter("OTROS_ESTUDIOS");
        String ES_SEXO = request.getParameter("SEXO");
        String LI_GRUPO_SANGUINEO = request.getParameter("GRUPO_SANGUINEO");
        String DE_REFERENCIA = request.getParameter("DE_REFERENCIA");
        String LI_RELIGION = request.getParameter("RELIGION");
        String NO_IGLESIA = request.getParameter("IGLESIA");
        String DE_CARGO = request.getParameter("CARGO");
        String LI_AUTORIDAD = request.getParameter("AUTORIDAD");
        String NO_AP_AUTORIDAD = request.getParameter("AUT_APELLIDOSNOMBRES");
        String CL_AUTORIDAD = request.getParameter("AUT_CELULAR");
        String ID_NO_AFP = request.getParameter("NOMBRE_AFP_ID");
        String ES_AFILIADO_ESSALUD = request.getParameter("AFILIADO_ESSALUD_ID");
        String LI_TIPO_TRABAJADOR = request.getParameter("TIPO_TRABAJADOR_ID");
        String CA_TIPO_HORA_PAGO_REFEERENCIAL = request.getParameter("TIPO_HORA_PAGO_REFEERENCIAL");
        String ES_FACTOR_RH = request.getParameter("FACTOR_RH_ID");
        String LI_DI_DOM_A_D1 = request.getParameter("DIR_DOM_A_D1_ID");
        String DI_DOM_A_D2 = request.getParameter("DIR_DOM_A_D2");
        String LI_DI_DOM_A_D3 = request.getParameter("DIR_DOM_A_D3_ID");
        String DI_DOM_A_D4 = request.getParameter("DIR_DOM_A_D4");
        String LI_DI_DOM_A_D5 = request.getParameter("DIR_DOM_A_D5_ID");
        String DI_DOM_A_D6 = request.getParameter("DIR_DOM_A_D6");
        String DI_DOM_A_REF = request.getParameter("DIR_DOM_A_REF");
        String ID_DI_DOM_A_DISTRITO = request.getParameter("DIR_DOM_A_DISTRITO_ID");
        String LI_DI_DOM_LEG_D1 = request.getParameter("DIR_DOM_LEG_D1_ID");
        String DI_DOM_LEG_D2 = request.getParameter("DIR_DOM_LEG_D2");
        String LI_DI_DOM_LEG_D3 = request.getParameter("DIR_DOM_LEG_D3_ID");
        String DI_DOM_LEG_D4 = request.getParameter("DIR_DOM_LEG_D4");
        String LI_DI_DOM_LEG_D5 = request.getParameter("DIR_DOM_LEG_D5_ID");
        String DI_DOM_LEG_D6 = request.getParameter("DIR_DOM_LEG_D6");
        String ID_DI_DOM_LEG_DISTRITO = request.getParameter("DIR_DOM_LEG_DISTRITO_ID");
        String CA_ING_QTA_CAT_EMPRESA = request.getParameter("ING_QTA_CAT_EMPRESA");
        String CA_ING_QTA_CAT_RUC = request.getParameter("ING_QTA_CAT_RUC");
        String CA_ING_QTA_CAT_OTRAS_EMPRESAS = request.getParameter("ING_QTA_CAT_OTRAS_EMPRESAS");
        String CM_OBSERVACIONES = request.getParameter("OBSERVACIONES");
        String US_CREACION = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
        String FE_CREACION = request.getParameter("FECHA_CREACION");
        String US_MODIF = request.getParameter("US_MODIF");
        String FE_MODIF = request.getParameter("FE_MODIF");
        String IP_USUARIO = request.getParameter("USUARIO_IP");

        String AP_NOMBRES_PADRE = request.getParameter("APELLIDOS_NOMBRES_PADRE");
        String AP_NOMBRES_MADRE = request.getParameter("APELLIDOS_NOMBRES_MADRE");
        String ES_TRABAJA_UPEU_C = request.getParameter("TRABAJA_UPEU_CONYUGUE");
        String AP_NOMBRES_C = request.getParameter("APELLIDO_NOMBRES_CONYUGUE");
        String FE_NAC_C = request.getParameter("FECHA_NAC_CONYUGUE");
        String ID_TIPO_DOC_C = request.getParameter("TIPO_DOC_ID");
        String NU_DOC_C = request.getParameter("NRO_DOC_C");
        String LI_INSCRIPCION_VIG_ESSALUD_C = request.getParameter("INSCRIPCION_VIG_ESSALUD");
        String ID_CONYUGUE = request.getParameter("CONYUGUE");
        int num_hijo = Integer.parseInt(request.getParameter("num_hijo"));
        //REGISTRAR EN TABLA CUENTA SUELDO
        String NO_BANCO = request.getParameter("BANCO");
        String NU_CUENTA = (request.getParameter("CUENTA") == null) ? "no tiene" : request.getParameter("CUENTA");
        String NU_CUENTA_BANC = request.getParameter("CUENTA_BANC");
        String ES_GEM_NU_CUENTA = (request.getParameter("GEN_NU_CUEN") == null) ? "0" : "1";
        String NO_BANCO_OTROS = request.getParameter("BANCO_OTROS");
        String ES_CUENTA_SUELDO = request.getParameter("ES_CUENTA_SUELDO");
        String CO_UNIVERSITARIO = request.getParameter("COD_UNI");

        String ES_DIEZMO = "0";
        if (request.getParameter("diezmo") != null) {
            ES_DIEZMO = "1";
        } else {
            ES_DIEZMO = "0";
        }

        //VALIDACIONES CUENTA SUELDO
        if (NO_BANCO.equals("") && ES_GEM_NU_CUENTA.equals("0")) {
            ES_CUENTA_SUELDO = "0";
        } else {
            ES_CUENTA_SUELDO = "1";
        }
        if (tr.val_nu_doc(NU_DOC)) {
        	//CHECK THE RETURN VALUE. CHANGE VIEW IF NECESSARY
            System.out.print("Trabajador ya existe!");
            mp.put("msg", "0");
            return new ModelAndView("Vista/Dgp/Documento/Reg_Documento","rpta",gson.toJson(mp));
        }else {
        	//CHECK THE RETURN VALUE
        	FE_NAC = DateFormat.toFormat3(FE_NAC);
            FE_NAC_C = DateFormat.toFormat3(FE_NAC_C);

            try {
				System.out.println(" fecha nac :" + DateFormat.toFormat1(FE_NAC));
	            System.out.println(" fecha nac C :" + DateFormat.toFormat1(FE_NAC_C));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            tr.INSERT_TRABAJADOR(null, AP_PATERNO, AP_MATERNO, NO_TRABAJADOR, TI_DOC, NU_DOC, ES_CIVIL, FE_NAC, ID_NACIONALIDAD, ID_DEPARTAMENTO, ID_PROVINCIA, ID_DISTRITO, TE_TRABAJADOR, CL_TRA, DI_CORREO_PERSONAL, DI_CORREO_INST, CO_SISTEMA_PENSIONARIO, LI_NIVEL_EDUCATIVO, REGIMEN, ES_INST_PERU, CARRERA, DE_ANNO_EGRESO, CM_OTROS_ESTUDIOS, ES_SEXO, LI_GRUPO_SANGUINEO, DE_REFERENCIA, LI_RELIGION, NO_IGLESIA, DE_CARGO, LI_AUTORIDAD, NO_AP_AUTORIDAD, CL_AUTORIDAD, ID_NO_AFP, ES_AFILIADO_ESSALUD, LI_TIPO_TRABAJADOR, CA_TIPO_HORA_PAGO_REFEERENCIAL, ES_FACTOR_RH, LI_DI_DOM_A_D1, DI_DOM_A_D2, LI_DI_DOM_A_D3, DI_DOM_A_D4, LI_DI_DOM_A_D5, DI_DOM_A_D6, DI_DOM_A_REF, ID_DI_DOM_A_DISTRITO, LI_DI_DOM_LEG_D1, DI_DOM_LEG_D2, LI_DI_DOM_LEG_D3, DI_DOM_LEG_D4, LI_DI_DOM_LEG_D5, DI_DOM_LEG_D6, ID_DI_DOM_LEG_DISTRITO, CA_ING_QTA_CAT_EMPRESA, CA_ING_QTA_CAT_RUC, CA_ING_QTA_CAT_OTRAS_EMPRESAS, CM_OBSERVACIONES, US_CREACION, FE_CREACION, US_MODIF, FE_MODIF, IP_USUARIO, AP_NOMBRES_PADRE, AP_NOMBRES_MADRE,
                    ES_TRABAJA_UPEU_C, AP_NOMBRES_C, FE_NAC_C, ID_TIPO_DOC_C, NU_DOC_C, LI_INSCRIPCION_VIG_ESSALUD_C,
                    ID_CONYUGUE, CO_UNIVERSITARIO, ES_DIEZMO);
            String idtr = tr.MAX_ID_DATOS_TRABAJADOR();
            tr.INSERT_CUENTA_SUELDO(null, NO_BANCO, NU_CUENTA, NU_CUENTA_BANC, ES_GEM_NU_CUENTA, NO_BANCO_OTROS, idtr, ES_CUENTA_SUELDO);
            tr.INSERT_HIST_RELIGION(null, LI_RELIGION, NO_IGLESIA, DE_CARGO, LI_AUTORIDAD, NO_AP_AUTORIDAD, CL_AUTORIDAD, "1", idtr, iduser, FE_MODIF);
            US_MODIF = iduser;
            IP_USUARIO = tr.ip();
            tr.UPDATE_ID_CONYUGUE(idtr, ID_CONYUGUE, US_MODIF, IP_USUARIO);
            for (int i = 1; i <= num_hijo; i++) {
                String AP_PATERNO_H = request.getParameter("APELLIDO_P_H" + i);
                String AP_MATERNO_H = request.getParameter("APELLIDO_M_H" + i);
                String NO_HIJO_TRABAJADOR = request.getParameter("NOMBRE_H" + i);
                String FE_NACIMIENTO = request.getParameter("FECHA_NAC_H" + i);
                String ES_SEXO_H = request.getParameter("SEXO_H" + i);
                String ES_TIPO_DOC = request.getParameter("TIPO_DOC_ID_H" + i);
                String NU_DOC_H = request.getParameter("NRO_DOC_H" + i);
                String ES_PRESENTA_DOCUMENTO = null;
                String ES_INSCRIPCION_VIG_ESSALUD = request.getParameter("ESSALUD_H" + i);
                String ES_ESTUDIO_NIV_SUPERIOR = request.getParameter("EST_SUP_H" + i);
                String ES_DATOS_HIJO_TRABAJADOR = "1";
                if (NU_DOC_H != null) {
                    if (!NU_DOC_H.equals("")) {
                        FE_NACIMIENTO = DateFormat.toFormat3(FE_NACIMIENTO);
                        h.INSERT_DATOS_HIJO_TRABAJADOR(null, idtr, AP_PATERNO_H, AP_MATERNO_H, NO_HIJO_TRABAJADOR, FE_NACIMIENTO, ES_SEXO_H, ES_TIPO_DOC, NU_DOC_H, ES_PRESENTA_DOCUMENTO, ES_INSCRIPCION_VIG_ESSALUD, ES_ESTUDIO_NIV_SUPERIOR, US_CREACION, FE_CREACION, US_MODIF, FE_MODIF, IP_USUARIO, ES_DATOS_HIJO_TRABAJADOR);
                    }
                }
                mp.put("List_Hijos", doc.List_Hijos(idtr));
                mp.put("Documentos", doc.Documentos());
                mp.put("Lis_doc_trabajador", doc.Lis_doc_trabajador(idtr));
                mp.put("List_Conyugue", doc.List_Conyugue(idtr));
                mp.put("pro", "regTR");
                mp.put("idtr", idtr);
                /*int s = doc.List_Req_nacionalidad(idtr);
                int num_ad = doc.List_Adventista(idtr);
                int count = doc.count_documentos_x_tra(idtr);*/
            }
            return new ModelAndView("Vista/Dgp/Documento/Reg_Documento","rpta",gson.toJson(mp));
        }

    }
	
	@RequestMapping("/Buscar")
	public ModelAndView Buscar(@RequestBody HttpServletRequest request) {
		String Buscar = request.getParameter("busqueda");
        String dni = request.getParameter("dni");
        String nom = request.getParameter("nom");
        String ape_mat = request.getParameter("ape_mat");
        String ape_pat = request.getParameter("ape_pat");
        String id_req = request.getParameter("id_req");
        String Text = request.getParameter("text");
        mp.put("text", Text);
        if (("Buscar".equals(Buscar) & (!"".equals(dni) | !"".equals(nom) | !"".equals(ape_mat) | !"".equals(ape_pat)))) {
        	String busc = (String) request.getParameter("busc");
        	if (busc != null) {
                mp.put("ListarTrabajador2", tr.Buscar_Trabajador_Requerimiento(iddep, dni, nom, ape_pat, ape_mat, id_req));
                mp.put(nom, dgp.VAL_OPC_DGP(dni));
            }
            return new ModelAndView("Vista/Dgp/Generar_Dgp","rpta",gson.toJson(mp));
        }else {
        	return new ModelAndView("Vista/Dgp/Generar_Dgp","rpta",gson.toJson(mp));
        }
	}
	
	@RequestMapping("/Buscar_Trabajador")
	public ModelAndView Buscar_Trabajador(@RequestBody HttpServletRequest request) {
		String Buscar = request.getParameter("busqueda");
        String dni = request.getParameter("dni");
        String nom = request.getParameter("nom");
        String ape_mat = request.getParameter("ape_mat");
        String ape_pat = request.getParameter("ape_pat");
        System.out.println(ape_pat);
        System.out.println(ape_mat);
        System.out.println(nom);
        if (("Buscar".equals(Buscar) & (!"".equals(dni) | !"".equals(nom) | !"".equals(ape_mat) | !"".equals(ape_pat)))) {
        	mp.put("ListarTrabajador", tr.Buscar_Ficha_Trabajador(iddep, dni, nom, ape_pat, ape_mat));
        	mp.put(nom, dgp.VAL_OPC_DGP(dni));
        	return new ModelAndView("Vista/Trabajador/Ficha_Trabajador","rpta",gson.toJson(mp));
        } else {
        	return new ModelAndView("Vista/Trabajador/Ficha_Trabajador");
        }
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
		mp.put("List_Cuenta_Sueldo", tr.List_Cuenta_Sueldo(idtr));
		mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
		mp.put("List_Universidad", li.List_Universidad());
		mp.put("List_tipo_institucion", cu.List_Tipo_Ins());
		mp.put("List_Ubigeo", ub.List_Distrito());
		mp.put("Listar_tipo_doc", tdoc.Listar_tipo_doc());
		mp.put("id_empleadox_ide", em.id_empleadox_ide(idtr));
		mp.put("idtr",idtr);
		if (request.getParameter("dgp") != null) {
			mp.put("dgp",request.getParameter("dgp"));
			return new ModelAndView("Vista/Trabajador/Detalle_Trabajador","rpta",gson.toJson(mp));
        } else {
        	return new ModelAndView("Vista/Trabajador/Detalle_Trabajador","rpta",gson.toJson(mp));
        }
	}
	
	@RequestMapping("/list_reg_tra")
	public ModelAndView list_reg_tra(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        String me = request.getParameter("aa");
        String op = request.getParameter("a");
        if (op != null) {
        	me="t";
        }
        mp.put("ms", me);
        mp.put("List_Cuenta_Sueldo", tr.List_Cuenta_Sueldo(idtr));
        mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        mp.put("List_Universidad", li.List_Universidad());
        mp.put("List_tipo_institucion", cu.List_Tipo_Ins());
        mp.put("List_Ubigeo", ub.List_Distrito());
        mp.put("Listar_tipo_doc", tdoc.Listar_tipo_doc());
        mp.put("idtr", idtr.trim());
        return new ModelAndView("Vista/Trabajador/Detalle_Trabajador","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/actualizar")
	public ModelAndView actualizar(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
		mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
		mp.put("idtr",idtr.trim());
		return new ModelAndView("Vista/Trabajador/Detalle_Trabajador","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Documento_Trabajador")
	public ModelAndView Documento_Trabajador(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
		mp.put("Lis_doc_trabajador_hab", doc.Lis_doc_trabajador_hab(idtr));
		mp.put("idtr",idtr.trim());
		return new ModelAndView("Vista/Trabajador/List_Doc_Trabajador","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/aut")
	public ModelAndView aut(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        int val_aps = 0;
        int val_huella = 0;
        String iddgp = request.getParameter("iddetalle_dgp");
        String puesto_id = request.getParameter("puesto_id");
        String cod = request.getParameter("cod");
        String idpasos = request.getParameter("idpasos");
        String drp = request.getParameter("IDDETALLE_REQ_PROCESO");
        String np = request.getParameter("nup");
        int num_c_dgp = dgp.VALIDAR_DGP_CONTRATO(iddgp);
        val_aps = em.val_cod_aps_empleado(idtr);
        val_huella = em.val_cod_huella(idtr);
        mp.put("id_empleadox_ide", em.id_empleadox_ide(idtr));
        mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        mp.put("List_Auto_mostrar", li.List_Auto_mostrar(idrol));
        mp.put("List_", li.List_Auto_mostrar(idrol));
		mp.put("idtr",idtr.trim());
		mp.put("aut","1");
		mp.put("dgp",iddgp);
		mp.put("p",puesto_id);
		mp.put("c",cod);
		mp.put("pas",idpasos);
		mp.put("drp",drp);
		mp.put("np",np);
		mp.put("vnc",num_c_dgp);
		mp.put("val_aps",val_aps);
		mp.put("val_huella",val_huella);
        return new ModelAndView("Vista/Trabajador/Detalle_Trabajador","rpta",gson.toJson(mp));
	}
	
	@RequestMapping(value="/Mostrar_Cod_APS", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> Mostrar_Cod_APS(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("tr");
        List<Map<String,Object>> e = em.id_empleadox_ide(idtr);
        if (e.size() == 0) {
            mp.put("msg", "0");
        } else {
            mp.put("content", e);
            mp.put("msg", "1");
        }
        return mp;
	}
	
	@RequestMapping(value="/reg_aps_masivo", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> reg_aps_masivo(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        int co_aps = Integer.parseInt(request.getParameter("cod"));
        em.Reg_aps(idtr, co_aps);
        mp.put("id_empleadox_ide", em.id_empleadox_ide(idtr));
        mp.put("rpta", true);
        return mp;
	}
	
	@RequestMapping(value="/registrar_huella", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> registrar_huella(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        int co_huella = Integer.parseInt(request.getParameter("cod"));
        em.Reg_cod_huella(idtr, co_huella);
        mp.put("rpta", true);
        return mp;
	}
	
	@RequestMapping("/Editar_Dat_Gen")
	public ModelAndView Editar_Dat_Gen(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        String edit = request.getParameter("edit");
        mp.put("3e", tr.ListaridTrabajador(idtr));
        mp.put("List_Nacionalidad", li.List_Nacionalidad());
        mp.put("List_Departamento", ub.List_Departamento());
        mp.put("List_Provincia", ub.List_Provincia());
        mp.put("List_Distrito", ub.List_DistritoTra());
        mp.put("Listar_tipo_doc", tdoc.Listar_tipo_doc());
        mp.put("idtr",idtr.trim());
        mp.put("edit",edit);
        return new ModelAndView("Vista/Trabajador/Mod_Datos_Generales","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Modificar_Dat_Gen")
	public ModelAndView Modificar_Dat_Gen(@RequestBody HttpServletRequest request) {
		String edit = request.getParameter("editar");
        String ID_TRABAJADOR = request.getParameter("IDTR");
        String AP_PATERNO = request.getParameter("APELLIDO_P");
        String AP_MATERNO = request.getParameter("APELLIDO_M");
        String NO_TRABAJADOR = request.getParameter("NOMBRES");
        String ES_SEXO = request.getParameter("SEXO");
        String FE_NAC = request.getParameter("FECHA_NAC");
        String ID_NACIONALIDAD = request.getParameter("NACIONALIDAD");
        String ID_DEPARTAMENTO = null;
        String ID_PROVINCIA = null;
        String ID_DISTRITO = request.getParameter("DISTRITO");
        String TI_DOC = "";
        String NU_DOC = "";
        if (edit.equals("ok")) {
            TI_DOC = request.getParameter("TI_DOC");
            NU_DOC = request.getParameter("NU_DOC");
        } else {
            TI_DOC = request.getParameter("TIPO_DOC");
            NU_DOC = request.getParameter("NRO_DOC");
        }
        String ES_CIVIL = request.getParameter("ESTADO_CIVIL");
        String LI_GRUPO_SANGUINEO = request.getParameter("GRUPO_SANGUINEO");
        String ES_FACTOR_RH = request.getParameter("FACTOR_RH_ID");
        String TE_TRABAJADOR = request.getParameter("TELEFONO");
        String CL_TRA = request.getParameter("CELULAR");
        String DI_CORREO_PERSONAL = request.getParameter("CORREO_PERSONAL");
        String DI_CORREO_INST = request.getParameter("CORREO_INST");
        String CO_SISTEMA_PENSIONARIO = request.getParameter("SISTEMA_PENSIONARIO");
        String ID_NO_AFP = request.getParameter("NOMBRE_AFP_ID");
        String ES_AFILIADO_ESSALUD = request.getParameter("AFILIADO_ESSALUD_ID");
        String LI_TIPO_TRABAJADOR = request.getParameter("TIPO_TRABAJADOR_ID");
        String idtr = request.getParameter("idtr");
        String US_MODIF = iduser;
        String IP_USUARIO = tr.ip();
        String FE_MODIFICACION = "";
        String ES_CIVIL_A = request.getParameter("ES_CIVIL_A");
        String ES_REGISTRO = request.getParameter("ES_REGISTRO");
        if (!ES_CIVIL.equals(ES_CIVIL_A)) {
            ec.INSERT_HIST_ESTADO_CIVIL(null, ES_CIVIL_A, FE_MODIFICACION, US_MODIF, ID_TRABAJADOR, ES_REGISTRO);
        }
        FE_NAC = DateFormat.toFormat3(FE_NAC);
        tr.MOD_DAT_GEN(AP_PATERNO, AP_MATERNO, NO_TRABAJADOR, TI_DOC, NU_DOC, ES_CIVIL, FE_NAC, ID_NACIONALIDAD, ID_DEPARTAMENTO, ID_PROVINCIA, ID_DISTRITO, TE_TRABAJADOR, CL_TRA, DI_CORREO_PERSONAL, DI_CORREO_INST, CO_SISTEMA_PENSIONARIO, ES_SEXO, LI_GRUPO_SANGUINEO, ID_NO_AFP, ES_AFILIADO_ESSALUD, LI_TIPO_TRABAJADOR, ES_FACTOR_RH, idtr, US_MODIF, IP_USUARIO);
        mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        mp.put("idtr",idtr.trim());
        if (edit.equals("ok")) {
            mp.put("edit",edit);
            return new ModelAndView("Vista/Trabajador/Datos_Generales","rpta",gson.toJson(mp));
        } else {
        	return new ModelAndView("Vista/Trabajador/Datos_Generales","rpta",gson.toJson(mp));
        }
	}
	
	@RequestMapping("/Editar_Asp_Acad")
	public ModelAndView Editar_Asp_Acad(@RequestBody HttpServletRequest request) {
        String idtr = request.getParameter("idtr");
        String edit = request.getParameter("edit");
        List<Map<String,Object>> li1 = tr.List_Cuenta_Sueldo(idtr);
        if (!li1.isEmpty()) {
        	mp.put("List_Cuenta_Sueldo", li1);
        } else {
            tr.INSERT_CUENTA_SUELDO(null, null, null, null, "0", null, idtr, "0");
        }
        if (((String)li1.get(0).get("es_cuenta_sueldo")).trim().equals("1")) {
            edit = "ok";
        }
        mp.put("List_tipo_institucion", cu.List_Tipo_Ins());
        mp.put("list_año", li.lista_años());
        mp.put("List_Universidad", li.List_Universidad());
        mp.put("List_Carrera", li.List_Carrera());
        mp.put("List_Situacion_Educativa", li.List_Situacion_Educativa());
        mp.put("idtr",idtr.trim());
        mp.put("edit",edit);
        return new ModelAndView("Vista/Trabajador/Mod_Aspecto_Academico","rpta",gson.toJson(mp));
	}
	

	@RequestMapping("/Modificar_Asp_Acad")
	public ModelAndView Modificar_Asp_Acad(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        //ASPECTO ACADEMICO
        String LI_NIVEL_EDUCATIVO = request.getParameter("NIVEL_EDUCATIVO");
        String CARRERA = request.getParameter("CARRERA");
        String REGIMEN = request.getParameter("REGIMEN");
        String ES_INST_PERU = request.getParameter("ES_INST_PERU");
        String DE_ANNO_EGRESO = request.getParameter("A_EGRESO");
        String CM_OTROS_ESTUDIOS = request.getParameter("OTROS_ESTUDIOS");
        String CA_TIPO_HORA_PAGO_REFEERENCIAL = request.getParameter("TIPO_HORA_PAGO_REFEERENCIAL");
        String CO_UNIVERSITARIO = request.getParameter("CO_UNIVERSITARIO");
        String US_MODIF = iduser;
        String IP_USUARIO = tr.ip();
        System.out.print("1");
        tr.MOD_ASPEC_ACADEM(LI_NIVEL_EDUCATIVO, REGIMEN, ES_INST_PERU, CARRERA, DE_ANNO_EGRESO, CM_OTROS_ESTUDIOS, CA_TIPO_HORA_PAGO_REFEERENCIAL, idtr, CO_UNIVERSITARIO, US_MODIF, IP_USUARIO);
        //MODIFICAR CUENTA SUELDO
        System.out.print("2");
        String NO_BANCO = "";
        String NU_CUENTA = "";
        String NU_CUENTA_BANC = "";
        String ES_GEM_NU_CUENTA = "";
        String NO_BANCO_OTROS = "";

        if (request.getParameter("BANCO") != null) {
            NO_BANCO = (request.getParameter("BANCO") == null) ? "" : request.getParameter("BANCO");
            NU_CUENTA = (request.getParameter("CUENTA") == null) ? "" : request.getParameter("CUENTA");
            NU_CUENTA_BANC = (request.getParameter("CUENTA_BANC") == null) ? "" : request.getParameter("CUENTA_BANC");
            ES_GEM_NU_CUENTA = (request.getParameter("GEN_NU_CUEN") == null) ? "0" : request.getParameter("GEN_NU_CUEN");
            NO_BANCO_OTROS = (request.getParameter("BANCO_OTROS") == null) ? "" : request.getParameter("BANCO_OTROS");
            String ES_CUENTA_SUELDO = request.getParameter("ES_CUENTA_SUELDO");
            if (NO_BANCO.equals("")) {
                ES_CUENTA_SUELDO = "0";
            } else {
                ES_CUENTA_SUELDO = "1";
            }
            System.out.print("3");
            tr.MOD_CUENTA_SUELDO(NO_BANCO, NU_CUENTA, NU_CUENTA_BANC, ES_GEM_NU_CUENTA, NO_BANCO_OTROS, idtr, ES_CUENTA_SUELDO);
            System.out.print("4");
        }
        mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        mp.put("List_Cuenta_Sueldo", tr.List_Cuenta_Sueldo(idtr));
        mp.put("idtr",idtr.trim());
        return new ModelAndView("Vista/Trabajador/Aspecto_Academico","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Listar_Asp_Social")
	public ModelAndView Listar_Asp_Social(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
		mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
		mp.put("Listar_via", dir.Listar_via());
		mp.put("Listar_zona", dir.Listar_zona());
		mp.put("ListarDir_Dom", li.List_Dom_D3_Id());
		mp.put("List_Provincia", ub.List_Provincia());
		mp.put("List_Distrito", ub.List_DistritoTra());
		mp.put("List_Ubigeo", ub.List_Distrito());
		mp.put("List_Departamento", ub.List_Departamento());
		mp.put("List_Dom_D1_Id", li.List_Dom_D1_Id());
		mp.put("List_Dom_D5_Id", li.List_Dom_D5_Id());
		mp.put("List_Dom_D3_Id", li.List_Dom_D3_Id());
        mp.put("idtr",idtr.trim());
		return new ModelAndView("Vista/Trabajador/Aspecto_Social","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Editar_Asp_Soc")
	public ModelAndView Editar_Asp_Soc(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
		mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
		mp.put("Listar_via", dir.Listar_via());
		mp.put("Listar_zona", dir.Listar_zona());
		mp.put("ListarDir_Dom", li.List_Dom_D3_Id());
		mp.put("List_Provincia", ub.List_Provincia());
		mp.put("List_Distrito", ub.List_DistritoTra());
		mp.put("List_Ubigeo", ub.List_Distrito());
		mp.put("List_Departamento", ub.List_Departamento());
		mp.put("List_Dom_D1_Id", li.List_Dom_D1_Id());
		mp.put("List_Dom_D5_Id", li.List_Dom_D5_Id());
		mp.put("List_Dom_D3_Id", li.List_Dom_D3_Id());
        mp.put("idtr",idtr.trim());
		return new ModelAndView("Vista/Trabajador/Mod_Aspecto_Social","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Modificar_Asp_Soc")
	public ModelAndView Modificar_Asp_Soc(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        String LI_DI_DOM_A_D1 = request.getParameter("DIR_DOM_A_D1_ID");
        String DI_DOM_A_D2 = request.getParameter("DIR_DOM_A_D2");
        String LI_DI_DOM_A_D3 = request.getParameter("DIR_DOM_A_D3_ID");
        String DI_DOM_A_D4 = request.getParameter("DIR_DOM_A_D4");
        String LI_DI_DOM_A_D5 = request.getParameter("DIR_DOM_A_D5_ID");
        String DI_DOM_A_D6 = request.getParameter("DIR_DOM_A_D6");
        String DI_DOM_A_REF = request.getParameter("DIR_DOM_A_REF");
        String ID_DI_DOM_A_DISTRITO = request.getParameter("DIR_DOM_A_DISTRITO_ID");
        String LI_DI_DOM_LEG_D1 = request.getParameter("DIR_DOM_LEG_D1_ID");
        String DI_DOM_LEG_D2 = request.getParameter("DIR_DOM_LEG_D2");
        String LI_DI_DOM_LEG_D3 = request.getParameter("DIR_DOM_LEG_D3_ID");
        String DI_DOM_LEG_D4 = request.getParameter("DIR_DOM_LEG_D4");
        String LI_DI_DOM_LEG_D5 = request.getParameter("DIR_DOM_LEG_D5_ID");
        String DI_DOM_LEG_D6 = request.getParameter("DIR_DOM_LEG_D6");
        String ID_DI_DOM_LEG_DISTRITO = request.getParameter("DIR_DOM_LEG_DISTRITO_ID");
        String CA_ING_QTA_CAT_EMPRESA = request.getParameter("ING_QTA_CAT_EMPRESA");
        String CA_ING_QTA_CAT_RUC = request.getParameter("ING_QTA_CAT_RUC");
        String CA_ING_QTA_CAT_OTRAS_EMPRESAS = request.getParameter("ING_QTA_CAT_OTRAS_EMPRESAS");
        String US_MODIF = iduser;
        String IP_USUARIO = tr.ip();
        mp.put("idtr",idtr.trim());
        tr.MOD_ASPEC_SOCIAL(LI_DI_DOM_A_D1, DI_DOM_A_D2, LI_DI_DOM_A_D3, DI_DOM_A_D4, LI_DI_DOM_A_D5, DI_DOM_A_D6, DI_DOM_A_REF, ID_DI_DOM_A_DISTRITO, LI_DI_DOM_LEG_D1, DI_DOM_LEG_D2, LI_DI_DOM_LEG_D3, DI_DOM_LEG_D4, LI_DI_DOM_LEG_D5, DI_DOM_LEG_D6, ID_DI_DOM_LEG_DISTRITO, CA_ING_QTA_CAT_EMPRESA, CA_ING_QTA_CAT_RUC, CA_ING_QTA_CAT_OTRAS_EMPRESAS, idtr, US_MODIF, IP_USUARIO);
        mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        return new ModelAndView("Vista/Trabajador/Aspecto_Social","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Editar_Asp_Rel")
	public ModelAndView Editar_Asp_Rel(@RequestBody HttpServletRequest request, Authentication authentication) {
		String idtr = request.getParameter("idtr");
		mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        String iduser = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
        mp.put("iduser",iduser);
        return new ModelAndView("Vista/Trabajador/Historial_Religion/Mod_Asp_Religioso","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Modificar_Asp_Rel")
	public ModelAndView Modificar_Asp_Rel(@RequestBody HttpServletRequest request) {
		String idtr = request.getParameter("idtr");
        String LI_RELIGION = request.getParameter("RELIGION");
        String NO_IGLESIA = request.getParameter("IGLESIA");
        String DE_CARGO = request.getParameter("CARGO");
        String LI_AUTORIDAD = request.getParameter("AUTORIDAD");
        String NO_AP_AUTORIDAD = request.getParameter("AUT_APELLIDOSNOMBRES");
        String CL_AUTORIDAD = request.getParameter("AUT_CELULAR");
        String FE_MODIF = "";
        tr.INSERT_HIST_RELIGION(null, LI_RELIGION, NO_IGLESIA, DE_CARGO, LI_AUTORIDAD, NO_AP_AUTORIDAD, CL_AUTORIDAD, "1", idtr, iduser, FE_MODIF);
        tr.MOD_ASP_REL(LI_RELIGION, NO_IGLESIA, DE_CARGO, LI_AUTORIDAD, NO_AP_AUTORIDAD, CL_AUTORIDAD, idtr, iduser, tr.ip());
        mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
        return new ModelAndView("Vista/Trabajador/Aspecto_Social","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/edit_perfil")
	public ModelAndView edit_perfil(Authentication authentication) {
		String iduser = ((CustomUser) authentication.getPrincipal()).getID_USUARIO();
		String idtr = tr.ID_TRB(iduser);
		mp.put("List_Cuenta_Sueldo", tr.List_Cuenta_Sueldo(idtr));
		mp.put("ListaridTrabajador", tr.ListaridTrabajador(idtr));
		mp.put("List_Universidad", li.List_Universidad());
		mp.put("List_tipo_institucion", cu.List_Tipo_Ins());
		mp.put("List_Ubigeo", ub.List_Distrito());
		mp.put("Listar_tipo_doc", tdoc.Listar_tipo_doc());
		mp.put("id_empleadox_ide", em.id_empleadox_ide(idtr));
        mp.put("idtr",idtr.trim());
        mp.put("edit","ok");
		return new ModelAndView("trabajador/profile","rpta",gson.toJson(mp));
	}
	
	@RequestMapping("/Form_Cambiar_Clave")
	public String Form_Cambiar_Clave() {
		return "Vista/Usuario/Cambiar_Pwd";
	}
	
	@RequestMapping(value="/Val_num_Doc", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> Val_num_Doc(@RequestBody HttpServletRequest request, Authentication authentication) {
		String nu_doc = request.getParameter("doc");
        mp.put("rpta", "1");
        mp.put("nu_doc", tr.val_nu_doc(nu_doc));
        return mp;
	}
	
	@RequestMapping(value="/validar_cod_uni", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> validar_cod_uni(@RequestBody HttpServletRequest request, Authentication authentication) {
		String cod_uni = request.getParameter("cod_uni");
        int n = tr.cod_uni_unico(cod_uni);
        mp.put("rpta", "1");
        mp.put("cod", n);
        return mp;
	}
	
	@RequestMapping("/reg_trb")
	public String reg_trb() {
		return "Vista/Trabajador/Ficha_Trabajador";
	}
	
	@RequestMapping(value="/ShowPorcentageTrabajador", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> ShowPorcentageTrabajador(@RequestBody HttpServletRequest request, Authentication authentication) {
		String id = request.getParameter("id");
		mp.put("porcentaje", tr.ShowPorcentageTrabajador(id));
        return mp;
	}
	
	@RequestMapping(value="/UpdateEsDiezmo", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> UpdateEsDiezmo(@RequestBody HttpServletRequest request, Authentication authentication) {
		String idtr = request.getParameter("id");
        int estado = Integer.parseInt(request.getParameter("estado"));
        mp.put("status", tr.UpdateEsDiezmo(idtr, estado));
        mp.put("rpta", "1");
        return mp;
	}
	
	@RequestMapping(value="/ShowEsDiezmoTrabajador", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> ShowEsDiezmoTrabajador(@RequestBody HttpServletRequest request, Authentication authentication) {
		String idtr = request.getParameter("id");
        int i = tr.ShowEsDiezmoTrabajador(idtr);
        String html = "";
        html += "             <label  id='titu'>    Descuento de Diezmo: </label>    ";
        html += "   <div class='input-group'> "
                + "          <span class='form-control' style='padding: 5px;padding-left: 10px'> ¿Autorizar descuento?</span>     "
                + "          <span class='input-group-addon'>";
        html += "                              <span class='onoffswitch'>";
        if (i == 1) {
            html += "                                <input type='checkbox' name='diezmo' value='1' checked=''   class='onoffswitch-checkbox cbkDiezmo' id='st3'>";
        } else if (i == 0) {
            html += "                                <input type='checkbox' name='diezmo' value='1'    class='onoffswitch-checkbox cbkDiezmo' id='st3'>";
        }
        html += "                                   <label class='onoffswitch-label' for='st3'> ";

        html += "                                   <span class='onoffswitch-inner' data-swchon-text='SI' data-swchoff-text='NO'></span> ";
        html += "                                     <span class='onoffswitch-switch'></span> ";
        html += "                                      </label> ";
        html += "                              </span>";
        html += "                      </span>"
                + "</div>"
                + "</div>";

        mp.put("html", html);
        mp.put("rpta", "1");
        return mp;
	}
	
	@RequestMapping(value="/ModDiezmoDetalleTrabajador", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> ModDiezmoDetalleTrabajador(@RequestBody HttpServletRequest request, Authentication authentication) {
		String idtr = request.getParameter("id");
        int i = tr.ShowEsDiezmoTrabajador(idtr);
        String html = "";
        html += "   <div class='input-group'> "
                + "          <span class='form-control' style='padding: 5px;padding-left: 10px'> ¿Autorizar descuento?</span>     "
                + "          <span class='input-group-addon'>";
        html += "                              <span class='onoffswitch'>";
        if (i == 1) {
            html += "                                <input type='checkbox' name='diezmo' value='1' checked=''   class='onoffswitch-checkbox cbkDiezmo' id='st3'>";
        } else if (i == 0) {
            html += "                                <input type='checkbox' name='diezmo' value='1'    class='onoffswitch-checkbox cbkDiezmo' id='st3'>";
        }
        html += "                                   <label class='onoffswitch-label' for='st3'> ";

        html += "                                   <span class='onoffswitch-inner' data-swchon-text='SI' data-swchoff-text='NO'></span> ";
        html += "                                     <span class='onoffswitch-switch'></span> ";
        html += "                                      </label> ";
        html += "                              </span>";
        html += "                      </span>"
                + "</div>"
                + "</div>";

        mp.put("html", html);
        mp.put("rpta", "1");
        return mp;
	}
	
	@RequestMapping(value="/ShowAFP_SP", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> ShowAFP_SP(@RequestBody HttpServletRequest request, Authentication authentication) {
		String idtr = request.getParameter("id");
		Map<String, Object> data = tr.ShowAFP_ONP(idtr);
        String html = "";
        if (permisoShowAFP_SP) {

            html += " <div class='row'>";
            html += " <div class='col-md-8'><strong>Nombre AFP:</strong>";
            html += " </div>";
            html += " <div class='col-md-4'>";
            for (int w = 0; w < li.List_Nom_AFP().size(); w++) {
                if (((String)data.get("ID_NO_AFP")).trim().equals(w + 1 + "")) {
                    html += li.List_Nom_AFP().get(w);
                }
            }
            html += " </div>";
            html += " </div>";
            html += " <div class='row'>";
            html += " <div class='col-md-8'><strong>Sistema Pensionario:</strong>";
            html += " </div>";
            html += " <div class='col-md-4'>";
            for (int dd = 0; dd < li.List_Sp().size(); dd++) {
                if (((String)data.get("CO_SISTEMA_PENSIONARIO")).trim().equals(dd + 1 + "")) {
                    html += (li.List_Sp().get(dd));
                }
            }
            html += " </div>";
            html += " </div>";
        }
        mp.put("html", html);
        mp.put("rpta", "1");
        return mp;
	}
	
	@RequestMapping(value="/ShowDialogFotoTrabajador", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> ShowDialogFotoTrabajador(@RequestBody HttpServletRequest request, Authentication authentication) {
		String idtr = request.getParameter("id");
        String html = "<div class='dialog-message' title='Dialog Simple Title'>"
                + "            <p>La imagen todavia no esta <b>Confirmada</b> aun confirme para poder guardar la imagen</p>"
                + "            <div class='hr hr-12 hr-double'></div>"
                + "            <div class='pre-avatar col-md-offset-1 center-block'>"
                + "                <img class='pre_foto thumbnail center-block' style='max-height: 300px; max-width: 500px; min-height:200px; min-width:400px;' />"
                + "            </div>"
                + "            <p>Si la imagen está <b>borrosa</b> es porque el tamaño es muy pequeño, no se preocupe, se verá mejor en el menú</p>"
                + "            <div class='hr hr-12 hr-double'></div>"
                + "                 <div class='avatar-user center-block'>"
                + "                     <a href='javascript:void(0)' id='show-shortcut' >"
                + "                         <img class='pre_foto bounceIn animated center-block' style='height:100px;width:100px;'/>"
                + "                     </a>  "
                + "                     <a href='javascript:void(0)' id='show-shortcut' >"
                + "                         <img class='pre_foto bounceIn animated center-block' style='border-radius:50%;height:70px;width:70px;'/>"
                + "                     </a>  "
                + "                 </div>"
                + "            <div style='display:none' class='progressbar'>"
                + "                <div class='progress-label progress-bar progress-primary'>Loading...</div> "
                + "            </div>"
                + "        </div>";
        mp.put("html", html);
        mp.put("rpta", "1");
        return mp;
	}
	
	@RequestMapping(value="/getTiHoraPago", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> getTiHoraPago(@RequestBody HttpServletRequest request, Authentication authentication) {
		System.out.println("enter to getTiHoraPago");
        String idtr="";
		try {
			idtr = CCriptografiar.Desencriptar(String.valueOf(request.getParameter("idtr")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // System.out.println((request.getParameter("idtr")));
        //  System.out.println("id:"+CCriptografiar.Encriptar("TRB-002172"));
        String html = "";
        List<Map<String, Object>> t = thp.listTiHoraPagoByIdTrabajador(idtr);
        html += "<select  name='TiHoraPago' class='form-control input-sm TiHoraPago bounceIn animated' required>";
        if (t.isEmpty()) {
            html += "<option>[Sin datos]</option>";
        } else {
            html += "<option >[Seleccione]</option>";
            for (int i = 0; i < t.size(); i++) {
                html += "<option value='" + CCriptografiar.Encriptar((String)t.get(i).get("ID_TI_HORA_PAGO")) + "' data-valor='" + t.get(i).get("CA_TI_HORA_PAGO") + "'>S/." + t.get(i).get("CA_TI_HORA_PAGO")
                        + " ( " + t.get(i).get("FE_CREACION") + ") </option>";
            }
        }
        html += "</select>";
        mp.put("html", html);
        mp.put("status", true);
        return mp;
	}
	
	//REPORTES
	
	@RequestMapping(value = "/print")
	// public @ResponseBody
	String verReporte(Model model, Authentication authentication) throws ParseException {
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String idtr = ((CustomUser) authentication.getPrincipal()).getID_TRABAJADOR();
		String iddepa = ((CustomUser) authentication.getPrincipal()).getID_DEPARTAMENTO();
		System.out.println(idtr);

		System.out.println("asdasdasdasdasd");
		model.addAttribute("format", "pdf");
		model.addAttribute("datasource", (tr.DATOS_TRABAJADOR(idtr,iddepa)));
		List<Map<String,Object>> carta = tr.DATOS_TRABAJADOR(idtr,iddepa);
		System.out.println("");
		System.out.println(" Esto es del controller: "+carta);
//		System.out.println(carta.get(7).toString());

		
        String oldstring = carta.get(0).get("FECHA_CONTRATO").toString();
        LocalDateTime datetime = LocalDateTime.parse(oldstring, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        String newstring = datetime.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        System.out.println(newstring);
        model.addAttribute("FECHA_CONTRATO", newstring);
		model.addAttribute("NO_TRABAJADOR", carta.get(0).get("NO_TRABAJADOR"));
		model.addAttribute("AP_PATERNO", carta.get(0).get("AP_PATERNO"));
		model.addAttribute("AP_MATERNO", carta.get(0).get("AP_MATERNO"));
		model.addAttribute("NO_PUESTO", carta.get(0).get("NO_PUESTO"));
		model.addAttribute("NOM",carta.get(0).get("NOM"));
		model.addAttribute("PAT", carta.get(0).get("PAT"));
		model.addAttribute("MAT", carta.get(0).get("MAT"));
		return "renuncia2_report";
	}

}
