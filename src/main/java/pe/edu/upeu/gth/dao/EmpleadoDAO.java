/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.interfaces.Operaciones;
import pe.edu.upeu.gth.util.Sql;

/**
 *
 * @author Alfa003
 */
public class EmpleadoDAO {

    private JdbcTemplate jt;
    String sql = "";

    public EmpleadoDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

    public ArrayList<Map<String, Object>> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean add(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean edit(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Map<String, Object>> listar_empleado(){
        String sql_list_emp="select t.ID_TRABAJADOR as ID,co.ID_CONTRATO as IDC,UPPER(t.NO_TRABAJADOR) as NOM,UPPER(t.AP_PATERNO) as A_P,UPPER(t.AP_MATERNO) as A_M,ar.NO_AREA,se.NO_SECCION,pu.NO_PUESTO,TO_CHAR(co.FE_DESDE,'DD/MM/YYYY') as FE,TO_CHAR(co.FE_HASTA,'DD/MM/YYYY')as FE2\n" +
"from RHTD_EMPLEADO e,RRHH.RHTM_TRABAJADOR t,RHTM_CONTRATO co,RHTR_PUESTO pu,RHTR_SECCION se,RHTD_AREA ar\n" +
"WHERE e.ID_TRABAJADOR=t.ID_TRABAJADOR AND co.ID_TRABAJADOR=t.ID_TRABAJADOR and pu.ID_PUESTO=co.ID_PUESTO AND pu.ID_SECCION=se.ID_SECCION and ar.ID_AREA=se.ID_AREA AND co.FE_HASTA>=SYSDATE AND MONTHS_BETWEEN\n" +
"(co.FE_HASTA,co.FE_DESDE)/12>=1 AND co.es_contrato='1'" ;

        return jt.queryForList(sql_list_emp);
    }

    public List<Map<String, Object>> listar_vacaciones(String id) {
        String sql_list_vac = "SELECT A.ID_CONTRATO, A.FE_HASTA,A.FE_DESDE, A.ID_DGP,N.ID_DGP, N.ID_TRABAJADOR, D.ID_TRABAJADOR, D.NO_TRABAJADOR,D.AP_PATERNO,D.AP_MATERNO,D.DI_CORREO_PERSONAL,D.DI_CORREO_INST,D.NU_DOC,D.TE_TRABAJADOR, D.CL_TRA FROM RHTM_CONTRATO A, RHTM_DGP N, RHTM_TRABAJADOR D WHERE A.ID_DGP=N.ID_DGP AND N.ID_TRABAJADOR=D.ID_TRABAJADOR AND A.FE_HASTA>=SYSDATE AND round(MONTHS_BETWEEN(sysdate,to_date(A.FE_DESDE,'YYYY-MM-DD'))/12)=1999";
        return jt.queryForList(sql_list_vac);
    }

    public String Id_Puesto_Personal(String ide) {
        sql = "select id_puesto from RHVD_EMP_PU_DIR_DEP WHERE ID_EMPLEADO=?";
        return jt.queryForObject(sql, String.class, ide);
    }

    public void VALIDAR_EMPLEADO(String id_tra) {
        jt.update("CALL RHSP_VAL_EMP ( ? )", id_tra);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public Map<String, Object> getAllEmployees(Integer PageNumber, Integer PageSize, String direccion, String departamento, String area, String seccion) {
        String queryColumns = "SELECT c.id_contrato,DT.\"ID_TRABAJADOR\",DT.\"AP_PATERNO\",DT.\"AP_MATERNO\",DT.\"NO_TRABAJADOR\",DT.\"TI_DOC\",DT.\"NU_DOC\",DT.\"ES_CIVIL\",\n"
                + "    DT.\"FE_NAC\",DT.\"NO_NACIONALIDAD\",DT.\"NO_DEPARTAMENTO\",DT.\"NO_PROVINCIA\",DT.\"NO_DISTRITO\",DT.\"TE_TRABAJADOR\",DT.\"CL_TRA\",DT.\"DI_CORREO_PERSONAL\",DT.\"DI_CORREO_INST\",\n"
                + "    DT.\"CO_SISTEMA_PENSIONARIO\",DT.\"ID_SITUACION_EDUCATIVA\",DT.\"LI_REG_INST_EDUCATIVA\",DT.\"ES_INST_EDUC_PERU\",\n"
                + "    DT.\"CM_OTROS_ESTUDIOS\",DT.\"ES_SEXO\",DT.\"LI_GRUPO_SANGUINEO\",DT.\"DE_REFERENCIA\",\n"
                + "    DT.\"LI_RELIGION\",DT.\"NO_IGLESIA\",DT.\"DE_CARGO\",DT.\"LI_AUTORIDAD\",DT.\"NO_AP_AUTORIDAD\",DT.\"CL_AUTORIDAD\",\n"
                + "    DT.\"ID_NO_AFP\",DT.\"ES_AFILIADO_ESSALUD\",DT.\"LI_TIPO_TRABAJADOR\",DT.\"CA_TIPO_HORA_PAGO_REFEERENCIAL\",\n"
                + "    DT.\"ES_FACTOR_RH\",DT.\"LI_DI_DOM_A_D1\",\n"
                + "    DT.\"DI_DOM_A_D2\",DT.\"LI_DI_DOM_A_D3\",DT.\"DI_DOM_A_D4\",DT.\"LI_DI_DOM_A_D5\",DT.\"DI_DOM_A_D6\",DT.\"DI_DOM_A_REF\",DT.\"DI_DOM_A_DISTRITO\",DT.\"LI_DI_DOM_LEG_D1\",\n"
                + "    DT.\"DI_DOM_LEG_D2\",DT.\"LI_DI_DOM_LEG_D3\",\n"
                + "    DT.\"DI_DOM_LEG_D4\",DT.\"LI_DI_DOM_LEG_D5\",DT.\"DI_DOM_LEG_D6\",DT.\"DI_DOM_LEG_DISTRITO\",\n"
                + "    DT.\"CA_ING_QTA_CAT_EMPRESA\",DT.\"CA_ING_QTA_CAT_RUC\",DT.\"CA_ING_QTA_CAT_OTRAS_EMPRESAS\",\n"
                + "    DT.\"CM_OBSERVACIONES\",DT.\"US_CREACION\",  DT.\"FE_CREACION\",DT.\"US_MODIF\",DT.\"FE_MODIF\",DT.\"IP_USUARIO\",DT.\"ID_USUARIO_CREACION\",DT.\"ID_UNIVERSIDAD_CARRERA\",\n"
                + "    DT.\"ID_NACIONALIDAD\",DT.\"DISTRITO_NAC\",DT.\"NO_S_EDUCATIVA\",DT.\"AP_NOMBRES_MADRE\",DT.\"AP_NOMBRES_PADRE\",DT.\"ES_TRABAJA_UPEU_C\",DT.\"AP_NOMBRES_C\",DT.\"FE_NAC_C\",\n"
                + "    DT.\"ID_TIPO_DOC_C\",DT.\"NU_DOC_C\",DT.\"LI_INSCRIPCION_VIG_ESSALUD_C\",DT.\"ID_CONYUGUE\",DT.\"NO_CARRERA\",DT.\"NO_UNIVERSIDAD\",DT.\"AR_FOTO\",DT.\"DE_FOTO\",DT.\"ID_FOTO\",DT.\"NO_AR_FOTO\",DT.\"TA_AR_FOTO\",\n"
                + "    dpd.no_puesto ,dpd.no_seccion,dpd.no_area,dpd.id_direccion,dpd.no_dep,dpd.id_departamento ,\n"
                + "    dpd.id_area,dpd.id_seccion,c.id_puesto,e.id_empleado,c.fe_creacion AS fe_creacion_contrato, c.ca_sueldo,to_char(c.fe_desde, 'yyyy-mm-dd') as fe_desde,\n"
                + "    to_char(c.fe_hasta, 'yyyy-mm-dd') as fe_hasta,dt.ID_UNIVERSIDAD,dt.ID_TIPO_INSTITUCION,\n"
                + "    dt.CO_UNIVERSIDAD,dt.ID_CARRERA,dt.CO_NACIONALIDAD,dpd.NO_DIRECCION ";
        String query = "  FROM RHTD_EMPLEADO e,\n"
                + "    RHVD_TRABAJADOR dt ,\n"
                + "    RHTM_CONTRATO c ,\n"
                + "    RHVD_PUESTO_DIRECCION dpd"
                + "  WHERE dt.id_trabajador = c.id_trabajador\n"
                + "  AND e.id_trabajador    = c.id_trabajador\n"
                + "  AND dpd.id_puesto      = c.id_puesto\n"
                + "  AND c.es_contrato      =1  ";
        /*filters*/
        query += (!departamento.equals("")) ? " and dpd.ID_departamento='" + departamento + "' " : "";
        query += (!direccion.equals("")) ? " and dpd.id_direccion='" + direccion + "' " : "";
        query += (!area.equals("")) ? " and dpd.id_area='" + area + "' " : "";
        query += (!seccion.equals("")) ? " and dpd.id_seccion='" + seccion + "' " : "";
        /*end filters*/
        List<Map<String, Object>> list=jt.queryForList(Sql.queryWithPagination(queryColumns + ", %s " + query + " %s", PageNumber, PageSize, "c.fe_creacion"));
        Map<String,Object> mp=new HashMap<String,Object>();
        int total=jt.queryForObject("select count(1) "+ query,Integer.class);
        mp.put("RecordsTotal",total);
        mp.put("RecordsFiltered",total);
        mp.put("data",list);
        return mp;
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Emp() {
        sql = "SELECT * FROM RHVD_LIST_EMPLEADO order by ap_paterno ";
        return jt.queryForList(sql);
    }

    public void Insert_Evaluacion_Emp(String ID_EVALUACION_EMP,
            String ES_EVALUACION, String RE_EVALUACION, String ID_EMPLEADO) {
        jt.update("CALL RHSP_INSERT_EVALUACION_EMP(?, ?, ?, ?)", null, ES_EVALUACION,
                RE_EVALUACION, ID_EMPLEADO);
    }

    public String ID_Empleado(String idtr) {
        sql = "select ID_EMPLEADO from RHTD_EMPLEADO WHERE ID_TRABAJADOR=?";
        return jt.queryForObject(sql, String.class, idtr);
    }

    public String ES_Empleado(String idemp) {
        sql = "Select ES_EVALUACION FROM RHTD_EVALUACION_EMP WHERE ID_EMPLEADO=?";
        return jt.queryForObject(sql, String.class, idemp);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Evaluacion_Emp(String id_emp) {
        sql = "SELECT * FROM RHTD_EVALUACION_EMP WHERE ID_EMPLEADO = ?";
        return jt.queryForList(sql, id_emp);
    }

    public void Mod_Evaluacion_emp(String RE_EVALUACION, String ID_EMPLEADO) {
        jt.update("CALL RHSP_MOD_EVALUACION_EMP(?, ?)", RE_EVALUACION, ID_EMPLEADO);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> id_empleadox_ide(String ide) {
        sql = "SELECT * FROM RHTD_EMPLEADO WHERE "
                + "ID_TRABAJADOR = ? and es_empleado='1'";
        return jt.queryForList(sql, ide.trim());
    }

    public int val_cod_aps_empleado(String idtr) {
        sql = "select COUNT(CO_APS) from RHTD_EMPLEADO where "
                + "ID_TRABAJADOR=? AND co_aps is not null";
        return jt.queryForObject(sql, Integer.class, idtr);
    }

    public boolean Reg_aps(String idtr, int aps) {
        return (jt.update("CALL RHSP_MOD_APS(?, ?)", idtr, aps) != 0);
    }

    public void Reg_cod_huella(String idtr, int cod_huella) {
        jt.update("CALL RHSP_MOD_COD_HUELLA( ?, ?)", idtr.trim(), cod_huella);
    }

    public int val_cod_huella(String idtr) {
        sql = "select COUNT(CO_HUELLA_DIGITAL) from RHTD_EMPLEADO where "
                + "ID_TRABAJADOR=? AND CO_HUELLA_DIGITAL is not null";
        return jt.queryForObject(sql, Integer.class, idtr);
    }

    public int val_aps(String co_aps) {
        sql = "SELECT COUNT (*) FROM RHTD_EMPLEADO WHERE CO_APS = ? ";
        return jt.queryForObject(sql, Integer.class, co_aps);
    }

    public int val_huella(String co_hue) {
        sql = "SELECT COUNT (*) FROM RHTD_EMPLEADO WHERE CO_HUELLA_DIGITAL = ? ";
        return jt.queryForObject(sql, Integer.class, co_hue);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_co_huella(String idtr) {
        sql = "select id_empleado,id_trabajador,CO_HUELLA_DIGITAL from "
                + "rhtd_empleado where id_trabajador=?";
        return jt.queryForList(sql, idtr);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_co_aps(String idtr) {
        sql = "select id_empleado,id_trabajador,co_aps from rhtd_empleado where "
                + "id_trabajador=?";
        return jt.queryForList(sql, idtr);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> getAllEmployeesWithOutUserAccount() {
        sql = "SELECT e.id_empleado,e.id_trabajador,e.ES_EMPLEADO,t.NO_TRABAJADOR"
                + "||' '||t.AP_PATERNO||' '||t.AP_MATERNO as fullnameEmployee FROM "
                + "rhtd_empleado e, rhtm_trabajador t where "
                + "t.ID_TRABAJADOR = e.ID_TRABAJADOR and "
                + "e.id_empleado not in (select id_empleado from rhtc_usuario) and "
                + "e.ES_EMPLEADO='1'";
        return jt.queryForList(sql);
    }

}
