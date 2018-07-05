package pe.edu.upeu.gth.dao;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

import pe.edu.upeu.gth.util.DateFormat;

public class TrabajadorDAO {

	private JdbcTemplate jt;
	private Gson gson = new Gson();
    private String sql="";
    public TrabajadorDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    public void INSERT_TRABAJADOR(String ID_TRABAJADOR, String AP_PATERNO, String AP_MATERNO, String NO_TRABAJADOR, String TI_DOC,
            String NU_DOC, String ES_CIVIL, String FE_NAC, String ID_NACIONALIDAD, String ID_DEPARTAMENTO, String ID_PROVINCIA,
            String ID_DISTRITO, String TE_TRABAJADOR, String CL_TRA, String DI_CORREO_PERSONAL, String DI_CORREO_INST,
            String CO_SISTEMA_PENSIONARIO, String ID_SITUACION_EDUCATIVA, String LI_REG_INST_EDUCATIVA, String ES_INST_EDUC_PERU,
            String ID_UNIVERSIDAD_CARRERA, String DE_ANNO_EGRESO, String CM_OTROS_ESTUDIOS, String ES_SEXO, String LI_GRUPO_SANGUINEO,
            String DE_REFERENCIA, String LI_RELIGION, String NO_IGLESIA, String DE_CARGO, String LI_AUTORIDAD, String NO_AP_AUTORIDAD,
            String CL_AUTORIDAD, String ID_NO_AFP, String ES_AFILIADO_ESSALUD, String LI_TIPO_TRABAJADOR, String CA_TIPO_HORA_PAGO_REFEERENCIAL,
            String ES_FACTOR_RH, String LI_DI_DOM_A_D1, String DI_DOM_A_D2, String LI_DI_DOM_A_D3, String DI_DOM_A_D4, String LI_DI_DOM_A_D5,
            String DI_DOM_A_D6, String DI_DOM_A_REF, String ID_DI_DOM_A_DISTRITO, String LI_DI_DOM_LEG_D1, String DI_DOM_LEG_D2,
            String LI_DI_DOM_LEG_D3, String DI_DOM_LEG_D4, String LI_DI_DOM_LEG_D5, String DI_DOM_LEG_D6, String ID_DI_DOM_LEG_DISTRITO,
            String CA_ING_QTA_CAT_EMPRESA, String CA_ING_QTA_CAT_RUC, String CA_ING_QTA_CAT_OTRAS_EMPRESAS, String CM_OBSERVACIONES,
            String US_CREACION, String FE_CREACION, String US_MODIF, String FE_MODIF, String IP_USUARIO, String AP_NOMBRES_PADRE,
            String AP_NOMBRES_MADRE, String ES_TRABAJA_UPEU_C, String AP_NOMBRES_C, String FE_NAC_C,
            String ID_TIPO_DOC_C, String NU_DOC_C, String LI_INSCRIPCION_VIG_ESSALUD_C, String ID_CONYUGUE, String CO_UNIVERSITARIO, String diezmo) {
    	
    	try {
			jt.update("CALL RHSP_INSERT_TRABAJADOR( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", null,AP_PATERNO,AP_MATERNO,NO_TRABAJADOR,TI_DOC, NU_DOC,ES_CIVIL,DateFormat.toFormat1(FE_NAC),ID_NACIONALIDAD,ID_DEPARTAMENTO,
					ID_PROVINCIA,ID_DISTRITO,TE_TRABAJADOR,CL_TRA,DI_CORREO_PERSONAL, DI_CORREO_INST,CO_SISTEMA_PENSIONARIO,ID_SITUACION_EDUCATIVA,LI_REG_INST_EDUCATIVA,ES_INST_EDUC_PERU,
					ID_UNIVERSIDAD_CARRERA,DE_ANNO_EGRESO,CM_OTROS_ESTUDIOS,ES_SEXO,LI_GRUPO_SANGUINEO, DE_REFERENCIA,LI_RELIGION,NO_IGLESIA,DE_CARGO,LI_AUTORIDAD,NO_AP_AUTORIDAD,CL_AUTORIDAD,
					ID_NO_AFP,ES_AFILIADO_ESSALUD,LI_TIPO_TRABAJADOR,CA_TIPO_HORA_PAGO_REFEERENCIAL,ES_FACTOR_RH,LI_DI_DOM_A_D1,DI_DOM_A_D2,LI_DI_DOM_A_D3,DI_DOM_A_D4,LI_DI_DOM_A_D5,
					DI_DOM_A_D6,DI_DOM_A_REF,ID_DI_DOM_A_DISTRITO,LI_DI_DOM_LEG_D1,DI_DOM_LEG_D2,LI_DI_DOM_LEG_D3,DI_DOM_LEG_D4,LI_DI_DOM_LEG_D5,DI_DOM_LEG_D6,ID_DI_DOM_LEG_DISTRITO,
					CA_ING_QTA_CAT_EMPRESA,CA_ING_QTA_CAT_RUC,CA_ING_QTA_CAT_OTRAS_EMPRESAS,CM_OBSERVACIONES,US_CREACION,FE_CREACION,US_MODIF,FE_MODIF,IP_USUARIO,AP_NOMBRES_PADRE,
					AP_NOMBRES_MADRE,ES_TRABAJA_UPEU_C,AP_NOMBRES_C,((FE_NAC_C!=null)?DateFormat.toFormat1(FE_NAC_C):FE_NAC_C),ID_TIPO_DOC_C,NU_DOC_C,LI_INSCRIPCION_VIG_ESSALUD_C,
					ID_CONYUGUE,CO_UNIVERSITARIO,diezmo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<Map<String,Object>> Buscar_Ficha_Trabajador(String iddep, String dni, String nom, String ape_p, String ape_m) {
    	sql = "select d.*,RHFU_VIG_CON (d.id_trabajador) as VI_CONTRATO "
    			+ "from (select * from RHVD_TRABAJADOR) d, RHVD_USUARIO u where u.id_usuario= d.id_usuario_creacion ";
    	nom = nom.toUpperCase();
        ape_p = ape_p.toUpperCase();
        ape_m = ape_m.toUpperCase();
        sql += (!"".equals(dni)) ? " and trim(d.NU_DOC)='" + dni.trim() + "'" : "";
        sql += (!"".equals(nom)) ? " and upper(d.NO_TRABAJADOR)like '%" + nom.trim() + "%'" : "";
        sql += (!"".equals(ape_p)) ? " and upper(d.AP_PATERNO)like '%" + ape_p.trim() + "%'" : "";
        sql += (!"".equals(ape_m)) ? " and upper(d.AP_MATERNO)like '%" + ape_m.trim() + "%'" : "";
        sql += " order by d.ID_TRABAJADOR desc";
        return jt.queryForList(sql);
    }
    
    public List<Map<String,Object>> Buscar_Trabajador_Requerimiento(String iddep, String dni, String nom, String ape_p, String ape_m, String id_req) {
    	sql = "select d.*, ES_PROCESO_TRABAJADOR(d.id_trabajador,?) as es_proceso from (select * from RHVD_TRABAJADOR) d, RHVD_USUARIO u where u.id_usuario= d.id_usuario_creacion ";
        nom = nom.toUpperCase();
        ape_p = ape_p.toUpperCase();
        ape_m = ape_m.toUpperCase();
        sql += (!"".equals(dni)) ? " and d.NU_DOC='" + dni + "'" : "";
        sql += (!"".equals(nom)) ? " and upper(d.NO_TRABAJADOR)like '%" + nom.trim() + "%'" : "";
        sql += (!"".equals(ape_p)) ? " and upper(d.AP_PATERNO)like '%" + ape_p.trim() + "%'" : "";
        sql += (!"".equals(ape_m)) ? " and upper(d.AP_MATERNO)like '%" + ape_m.trim() + "%'" : "";
        sql += " order by d.ID_TRABAJADOR desc";
        return jt.queryForList(sql,id_req);
    }
    
    public List<Map<String,Object>> ListaridTrabajador(String id) {
    	sql = "select * from RHVD_TRABAJADOR where ID_TRABAJADOR=?";
    	return jt.queryForList(sql,id);
    }
    
    public List<Map<String,Object>> LIST_DAT_TR_PLANTILLA(String id) {
    	sql = "select dt.ap_paterno,dt.ap_materno,dt.no_trabajador,dt.nu_doc,dt.li_di_dom_a_d1,dt.di_dom_a_d2,dt.li_di_dom_a_d3,dt.di_dom_a_d4,dt.li_di_dom_a_d5,dt.di_dom_a_d6,"
    			+ "p.no_dep,p.no_puesto,c.fe_desde,c.fe_hasta,c.ca_sueldo,c.ca_bono_alimento,to_char(sysdate,'dd')||' de '||to_char(sysdate,'Month')||' del '||to_char(sysdate,'YYYY') "
    			+ "AS fecha_actual,dt.id_di_dom_a_distrito,u.NO_DISTRITO,u.NO_PROVINCIA,u.NO_DEPARTAMENTO from RHTM_TRABAJADOR dt,RHTM_CONTRATO c,RHVD_PUESTO_DIRECCION p,RHVD_UBIGEO u "
    			+ "where dt.id_trabajador=c.id_trabajador and c.id_puesto=p.id_puesto  and dt.ID_DI_DOM_A_DISTRITO=u.ID_DISTRITO and c.id_contrato=?";
    	return jt.queryForList(sql,id);
    }
    
    public String MAX_ID_DATOS_TRABAJADOR() {
    	sql = "SELECT 'TRB-' ||MAX (SUBSTR(ID_TRABAJADOR,5,8)) FROM RHTM_TRABAJADOR";
    	return jt.queryForObject(sql, String.class);
    }
    
    public void UPDATE_ID_CONYUGUE(String id_conyugue, String id_trabajador, String US_MODIF, String IP_USUARIO) {
    	jt.update("CALL RHSP_UPDATE_ID_CONYUGUE(?, ?, ? ,?)",id_conyugue,id_trabajador,US_MODIF,IP_USUARIO);
    }
    
    public String tipo_planilla(String id_trabajador) {
    	return jt.queryForObject("SELECT ID_TIPO_PLANILLA FROM RHTM_CONTRATO WHERE ID_TRABAJADOR=? and ES_CONTRATO='1'", String.class, id_trabajador);
    }
    
    public void INSERT_HIST_RELIGION(String ID_HIST_INFO_REL, String LI_RELIGION, String NO_IGLESIA, String DE_CARGO, String LI_AUTORIDAD, String NO_AP_AUTORIDAD, String CL_AUTORIDAD, 
    		String ES_HIST_INFO_REL, String ID_TRABAJADOR, String iduser, String FE_MODIF) {
    	jt.update("CALL RHSP_INSERT_HIST_INFO_REL( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",null,LI_RELIGION,NO_IGLESIA,DE_CARGO,LI_AUTORIDAD,NO_AP_AUTORIDAD,CL_AUTORIDAD,ES_HIST_INFO_REL,
    			ID_TRABAJADOR,iduser,FE_MODIF);
    }
    
    public void MOD_ASP_REL(String LI_RELIGION, String NO_IGLESIA, String DE_CARGO, String LI_AUTORIDAD, String NO_AP_AUTORIDAD, String CL_AUTORIDAD, String ID_TRABAJADOR, String US_MODIF, 
    		String IP_USUARIO) {
    	jt.update("CALL RHSP_MOD_RELIGION_TRA( ?, ?, ?, ?, ?, ?, ?, ?, ?)",LI_RELIGION,NO_IGLESIA,DE_CARGO,LI_AUTORIDAD,NO_AP_AUTORIDAD,CL_AUTORIDAD,ID_TRABAJADOR,US_MODIF,IP_USUARIO);
    }
    
    public List<Map<String,Object>> LIST_TRABAJADOR_MOD_REL() {
    	sql = "select id_trabajador, NO_TRABAJADOR , AP_PATERNO , AP_MATERNO  ,NU_DOC_C , LI_RELIGION ,DI_CORREO_PERSONAL, CL_TRA from RHTM_TRABAJADOR where "
    			+ "ID_TRABAJADOR in (select ID_TRABAJADOR from RHTR_HIST_INFO_REL )";
    	return jt.queryForList(sql);
    }
    
    public void INSERT_CUENTA_SUELDO(String ID_CUENTA_SUELDO, String NO_BANCO, String NU_CUENTA, String NU_CUENTA_BANC, String ES_GEM_NU_CUENTA, String NO_BANCO_OTROS, 
    		String ID_TRABAJADOR, String ES_CUENTA_SUELDO) {
    	jt.update("CALL RHSP_INSERT_CUENTA_SUELDO( ?, ?, ?, ?, ?, ?, ?, ?)",null,NO_BANCO,NU_CUENTA,NU_CUENTA_BANC,ES_GEM_NU_CUENTA,NO_BANCO_OTROS,ID_TRABAJADOR,ES_CUENTA_SUELDO);
    }
    
    public String CuentaSueldoTra(String ID_TRABAJADOR) {
    	sql = "select ES_CUENTA_SUELDO from RHTD_CUENTA_SUELDO where ID_TRABAJADOR = ?";
    	return jt.queryForObject(sql, String.class,ID_TRABAJADOR);
    }
    
    public void MOD_DAT_GEN(String AP_PATERNO, String AP_MATERNO, String NO_TRABAJADOR, String TI_DOC, String NU_DOC, String ES_CIVIL, String FE_NAC, String ID_NACIONALIDAD, 
    		String ID_DEPARTAMENTO, String ID_PROVINCIA, String ID_DISTRITO, String TE_TRABAJADOR, String CL_TRA, String DI_CORREO_PERSONAL, String DI_CORREO_INST, 
    		String CO_SISTEMA_PENSIONARIO, String ES_SEXO, String LI_GRUPO_SANGUINEO, String ID_NO_AFP, String ES_AFILIADO_ESSALUD, String LI_TIPO_TRABAJADOR, String ES_FACTOR_RH, 
    		String ID_TRABAJADOR, String US_MODIF, String IP_USUARIO) {
    	try {
			jt.update("CALL RHSP_MOD_TRA_DET_GEN( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",AP_PATERNO,AP_MATERNO,NO_TRABAJADOR,TI_DOC,NU_DOC,ES_CIVIL,
					DateFormat.toFormat1(FE_NAC),ID_NACIONALIDAD,ID_DEPARTAMENTO,ID_PROVINCIA,ID_DISTRITO,TE_TRABAJADOR,CL_TRA,DI_CORREO_PERSONAL,DI_CORREO_INST,CO_SISTEMA_PENSIONARIO,
					ES_SEXO,LI_GRUPO_SANGUINEO,ID_NO_AFP,ES_AFILIADO_ESSALUD,LI_TIPO_TRABAJADOR,ES_FACTOR_RH,ID_TRABAJADOR.trim(),US_MODIF,IP_USUARIO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<Map<String,Object>> List_Cuenta_Sueldo(String idtr) {
    	sql = "SELECT * FROM RHTD_CUENTA_SUELDO WHERE ID_TRABAJADOR = ?";
    	return jt.queryForList(sql,idtr);
    }
    
    public void MOD_ASPEC_ACADEM(String LI_NIVEL_EDUCATIVO, String REGIMEN, String ES_INS_PERU, String CARRERA, String DE_ANNO_EGRESO, String CM_OTROS_ESTUDIOS, 
    		String CA_TIPO_HORA_PAGO_REFERENCIAL, String ID_TRABAJADOR, String CO_UNIVERSITARIO, String US_MODIF, String IP_USUARIO) {
    	jt.update("CALL RHSP_MOD_TRA_ASP_ACAD( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ? )",LI_NIVEL_EDUCATIVO,REGIMEN,ES_INS_PERU,CARRERA,DE_ANNO_EGRESO,CM_OTROS_ESTUDIOS,
    			CA_TIPO_HORA_PAGO_REFERENCIAL,ID_TRABAJADOR,CO_UNIVERSITARIO,US_MODIF,IP_USUARIO);
    }
    
    public void MOD_CUENTA_SUELDO(String NO_BANCO, String NU_CUENTA, String NU_CUENTA_BANC, String ES_GEM_NU_CUENTA, String NO_BANCO_OTROS_SP, String ID_TRABAJADOR, String ES_CUENTA_SUELDO) {
    	jt.update("CALL RHSP_MOD_TRA_CUEN_SUELDO( ?, ?, ?, ?, ?, ?, ?)",NO_BANCO,NU_CUENTA,NU_CUENTA_BANC,ES_GEM_NU_CUENTA,NO_BANCO_OTROS_SP,ID_TRABAJADOR,
    			ES_CUENTA_SUELDO);
    }
    
    public List<Map<String,Object>> List_Uni_Carr() {
    	sql = "SELECT * FROM RHTX_UNIVERSIDAD_CARRERA";
    	return jt.queryForList(sql);
    }
    
    public void MOD_ASPEC_SOCIAL(String LI_DI_DOM_A_D1, String DI_DOM_A_D2, String LI_DI_DOM_A_D3, String DI_DOM_A_D4, String LI_DI_DOM_A_D5, String DI_DOM_A_D6, String DI_DOM_A_REF, 
    		String ID_DI_DOM_A_DISTRITO, String LI_DI_DOM_LEG_D1, String DI_DOM_LEG_D2, String LI_DI_DOM_LEG_D3, String DI_DOM_LEG_D4, String LI_DI_DOM_LEG_D5, String DI_DOM_LEG_D6, 
    		String ID_DOM_LEG_DISTRITO, String CA_ING_QTA_CAT_EMPRESA, String CA_ING_QTA_RUC, String CA_ING_QTA_CAT_OTRAS_EMPRESAS, String ID_TRABAJADOR, String US_MODIF, String IP_USUARIO) {
    	jt.update("CALL RHSP_MOD_TRA_ASP_SOCIAL( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",LI_DI_DOM_A_D1,DI_DOM_A_D2,LI_DI_DOM_A_D3,DI_DOM_A_D4,LI_DI_DOM_A_D5,
    			DI_DOM_A_D6,DI_DOM_A_REF,ID_DI_DOM_A_DISTRITO,LI_DI_DOM_LEG_D1,DI_DOM_LEG_D2,LI_DI_DOM_LEG_D3,DI_DOM_LEG_D4,LI_DI_DOM_LEG_D5,DI_DOM_LEG_D6,ID_DOM_LEG_DISTRITO,
    			CA_ING_QTA_CAT_EMPRESA,CA_ING_QTA_RUC,CA_ING_QTA_CAT_OTRAS_EMPRESAS,ID_TRABAJADOR,US_MODIF,IP_USUARIO);
    }
    
    public String REG_DOC_TRABAJADOR(String trb) {
    	sql = "select count(*) from RHTV_DGP_DOC_ADJ where ID_TRABAJADOR = ?";
    	return jt.queryForObject(sql, String.class,trb);
    }
    
    public String ID_TRB(String user) {
    	sql = "select ID_TRABAJADOR from RHVD_USUARIO where ID_USUARIO = ?";
    	return jt.queryForObject(sql, String.class,user);
    }
    
    public String ip() {
    	String x = "";
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            x = ip.getHostAddress() + "*" + ip.getCanonicalHostName() + "*" + sb.toString();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return x;
    }
    
    public boolean val_nu_doc(String nu_doc) {
    	sql = "select count(*)  from rhtm_trabajador where trim(NU_DOC)=?";
    	return (jt.queryForObject(sql, String.class,nu_doc.trim()).equals("1"));
    }
    
    public String Cod_aps_x_idt(String id_trabajador) {
    	sql = "SELECT CO_APS FROM RHTD_EMPLEADO WHERE ID_TRABAJADOR=?";
    	return jt.queryForObject(sql, String.class, id_trabajador.trim());
    }
    
    public String Cod_huella_x_idt(String id_trabajador) {
    	sql = "SELECT CO_HUELLA_DIGITAL FROM RHTD_EMPLEADO WHERE ID_TRABAJADOR=?";
    	return jt.queryForObject(sql, String.class, id_trabajador.trim());
    }
    
    public int cod_uni_unico(String cod_uni) {
    	sql = "select count (*) from RHTM_TRABAJADOR where CO_UNIVERSITARIO = ?";
    	return jt.queryForObject(sql, Integer.class, cod_uni);
    }
    
    public int ShowPorcentageTrabajador(String idtr) {
        int porcentaje = 0;
        int acum = 0;
    	sql = "SELECT  AP_PATERNO,"
                + "  AP_MATERNO,"
                + "  NO_TRABAJADOR,"
                + "  TI_DOC,"
                + "  NU_DOC,"
                + "  ES_CIVIL,"
                + "  FE_NAC,"
                + "  ID_NACIONALIDAD,"
                + "  ID_DISTRITO,"
                + "  TE_TRABAJADOR,"
                + "  CL_TRA,"
                + "  DI_CORREO_PERSONAL,"
                + "  DI_CORREO_INST,"
                + "  CO_SISTEMA_PENSIONARIO,"
                + "  ID_SITUACION_EDUCATIVA,"
                + "  ES_SEXO,"
                + "  LI_GRUPO_SANGUINEO,"
                + "  LI_RELIGION,"
                + "  NO_IGLESIA,"
                + "  ID_NO_AFP,"
                + "  ES_AFILIADO_ESSALUD,"
                + "  LI_TIPO_TRABAJADOR,"
                + "  LI_DI_DOM_A_D1,"
                + "  DI_DOM_A_D2,"
                + "  LI_DI_DOM_A_D3,"
                + "  DI_DOM_A_D4,"
                + "  LI_DI_DOM_A_D5,"
                + "  DI_DOM_A_D6,"
                + "  DI_DOM_A_REF,"
                + "  ID_DI_DOM_A_DISTRITO,"
                + "  LI_DI_DOM_LEG_D1,"
                + "  DI_DOM_LEG_D2,"
                + "  LI_DI_DOM_LEG_D3,"
                + "  DI_DOM_LEG_D4,"
                + "  LI_DI_DOM_LEG_D5,"
                + "  DI_DOM_LEG_D6,"
                + "  ID_DI_DOM_LEG_DISTRITO,"
                + "  US_CREACION,"
                + "  FE_CREACION,"
                + "  IP_USUARIO FROM rhtm_trabajador where id_trabajador =? ";
    	List<Map<String,Object>> list =jt.queryForList(sql,idtr);
    	for(int i = 0; i < 40; i++) {
    		if (list.get(i) == null) {

            } else {
                acum++;
            }
    	}
    	
    	System.out.println("acum:"+acum);
        porcentaje = (int) ((acum / 40.0) * 100);
        System.out.println("porcentage:"+porcentaje);
        return porcentaje;
    }
    
    public boolean UpdateEsDiezmo(String idtr, int estado) {
    	return (jt.update("CALL rhsp_mod_es_diezmo( ?, ?)",idtr,estado)==1);
    }
    
    public int ShowEsDiezmoTrabajador(String idtr) {
    	sql = "SELECT es_diezmo FROM rhtm_trabajador where id_trabajador =?";
    	return jt.queryForObject(sql, Integer.class,idtr);
    }
    
    public Map<String,Object> ShowAFP_ONP(String idtr) {
    	sql = "SELECT ID_NO_AFP,CO_SISTEMA_PENSIONARIO FROM rhtm_trabajador where id_trabajador =? ";
    	return jt.queryForMap(sql,idtr);
    }
    

	public List<Map<String,Object>> DATOS_TRABAJADOR(String idtr,String iddepa) {
		System.out.println("Este es idtrabajador: " +idtr +" Este otro iddepa "+iddepa);
    	Map<String,Object> liston = null;
    	List<Map<String,Object>> listonazo = new ArrayList<>();
    	Map<String,Object> mapita = null;
    	Map<String,Object> map =  null;
    	String sql2="";
//    	 = "SELECT *FROM\r\n" + 
//    			"(SELECT con.ID_TRABAJADOR, MIN(con.FE_DESDE) AS FECHA_CONTRATO,pues.NO_PUESTO, CAST(SYSDATE AS varchar(12)) AS FECHA_RENUNCIA, tra.NO_TRABAJADOR,tra.AP_PATERNO, tra.AP_MATERNO, con.DE_OBSERVACION\r\n" + 
//    			"FROM RHTM_CONTRATO con\r\n" + 
//    			"LEFT JOIN  RHTR_PUESTO pues ON con.ID_PUESTO=pues.ID_PUESTO \r\n" + 
//    			"LEFT JOIN   RHTM_DGP dgp ON con.ID_DGP=dgp.ID_DGP \r\n" + 
//    			"LEFT JOIN RHTM_TRABAJADOR tra ON  con.ID_TRABAJADOR=tra.ID_TRABAJADOR\r\n" + 
//    			"LEFT JOIN  RHTX_REGIMEN_LABORAL reg ON con.ID_REGIMEN_LABORAL=reg.ID_REGIMEN_LABORAL\r\n" + 
//    			"LEFT JOIN RHTX_SUB_MODALIDAD sub ON con.ID_SUB_MODALIDAD=sub.ID_SUB_MODALIDAD\r\n" + 
//    			"LEFT JOIN RHTX_GRUPO_OCUPACION gr ON con.ID_GRUPO_OCUPACION=gr.ID_GRUPO_OCUPACION \r\n" + 
//    			"LEFT JOIN RHTR_TIPO_PLANILLA tipopla ON con.ID_TIPO_PLANILLA=tipopla.ID_TIPO_PLANILLA\r\n" + 
//    			"LEFT JOIN RHTD_DETALLE_HORARIO dethor ON con.ID_DETALLE_HORARIO=dethor.ID_DETALLE_HORARIO\r\n" + 
//    			"LEFT JOIN RHTC_PLANTILLA_CONTRACTUAL placon ON con.ID_PLANTILLA_CONTRACTUAL=placon.ID_PLANTILLA_CONTRACTUAL\r\n" + 
//    			"LEFT JOIN RHTR_SITUACION_ESPECIAL sitesp ON con.ID_SITUACION_ESPECIAL=sitesp.ID_SITUACION_ESPECIAL \r\n" + 
//    			"WHERE con.ID_TRABAJADOR=? GROUP BY con.ID_TRABAJADOR,pues.NO_PUESTO,tra.NO_TRABAJADOR,tra.AP_PATERNO, tra.AP_MATERNO, con.DE_OBSERVACION) WHERE ROWNUM<2";
    	sql = "SELECT * FROM (SELECT FECHA_CONTRATO,NOMBRES,PATERNO,MATERNO,NOM_PUESTO FROM REN_VIEW_TRABAJADOR WHERE ID_TRABAJADOR=?) WHERE ROWNUM<2 ";
    	sql2="SELECT*FROM(SELECT trab.NO_TRABAJADOR AS NOM, trab.AP_PATERNO AS PAT,trab.AP_MATERNO AS MAT FROM RHTM_TRABAJADOR trab\r\n" + 
    			"LEFT JOIN RHTM_CONTRATO con ON con.ID_TRABAJADOR= trab.ID_TRABAJADOR\r\n" + 
    			"LEFT JOIN RHTR_PUESTO pues ON con.ID_PUESTO=pues.ID_PUESTO\r\n" + 
    			"LEFT JOIN RHTR_SECCION sec ON pues.ID_SECCION=sec.ID_SECCION\r\n" + 
    			"LEFT JOIN RHTD_AREA area ON sec.ID_AREA=area.ID_AREA\r\n" + 
    			"LEFT JOIN RHTX_DEPARTAMENTO dep ON area.ID_DEPARTAMENTO=dep.ID_DEPARTAMENTO\r\n" + 
    			"LEFT JOIN RHTD_EMPLEADO emp ON emp.ID_TRABAJADOR = trab.ID_TRABAJADOR\r\n" + 
    			"LEFT JOIN RHTC_USUARIO usu ON emp.ID_EMPLEADO = usu.ID_EMPLEADO WHERE usu.ID_ROL= 'ROL-0003' AND dep.ID_DEPARTAMENTO=? ORDER BY con.FE_DESDE DESC) WHERE ROWNUM<2";
    	
    	
    	try {   
    		liston = jt.queryForMap(sql,idtr);
        	System.out.println("Mapenado trabajador"+gson.toJson(liston));
        	mapita = jt.queryForMap(sql2,iddepa);
        	System.out.println("Mapeando al depa" + mapita);    
    			map = new HashMap<>();
    			System.out.println("llegado indice 2: "+liston.get("NOMBRES").toString());
    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
//    			String fecha_contrato = formatter.format(liston.get("FECHA_CONTRATO").toString());
    			map.put("FECHA_CONTRATO",liston.get("FECHA_CONTRATO").toString());
    			map.put("NO_TRABAJADOR", liston.get("NOMBRES"));
    			map.put("AP_PATERNO", liston.get("PATERNO"));
    			map.put("AP_MATERNO", liston.get("MATERNO"));
    			map.put("NO_PUESTO", liston.get("NOM_PUESTO"));
    			map.put("NOM", mapita.get("NOM"));
    			map.put("PAT", mapita.get("PAT"));
    			map.put("MAT", mapita.get("MAT"));
    			listonazo.add(map);
			
		} catch (Exception e) {
			System.out.println(gson.toJson("ESto sale del map"+map));
			System.out.println("Error en datos_trabajador " +e);
		}
    		
//    	return jt.queryForList(sql,idtr);
    	return listonazo;
    }
	
	public int limite_renuncia() {
		int x =0;
		try {
			sql = "SELECT VALOR FROM RA_CONFIGURACIONES WHERE ID_CONFIGURACIONES ='RAC-000001'";
			System.out.println(jt.queryForObject(sql, Integer.class));
			x = jt.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		
		return x;
	}
	
}
