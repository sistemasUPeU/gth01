package pe.edu.upeu.gth.controller.recruitment;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.DireccionDAO;
import pe.edu.upeu.gth.dao.ListaDAO;
import pe.edu.upeu.gth.dao.Tipo_DocumentoDAO;
import pe.edu.upeu.gth.dao.UbigeoDAO;

@Controller
@Scope("request")
@RequestMapping("trabajador")
public class TrabajadorController {

	private ModelAndView modelAndView;
	Map<String, Object> mp = new HashMap<>();
	DataSource d = AppConfig.getDataSource();
	ListaDAO li=new ListaDAO(d);
	DireccionDAO dir =new DireccionDAO(d);
	UbigeoDAO ub=new UbigeoDAO(d);
	Tipo_DocumentoDAO tdoc = new Tipo_DocumentoDAO(d);
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfile(ModelMap model) {
        modelAndView = new ModelAndView("trabajador/profile", model);
        return modelAndView;
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
	
	/*@RequestMapping(value = "/Registrar", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> infoTrabajador(@RequestBody HttpServletRequest request,Authentication authentication) {
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
        
        
        return mp;
    }
	*/
	
	
}
