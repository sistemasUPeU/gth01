/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.config.AppConfig;

/**
 *
 * @author Cesar
 */
public class Formato_HorarioDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public Formato_HorarioDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void Insert_Horario(String ID_TIPO_HORARIO, String NO_HORARIO, String DE_HORARIO, String ES_HORARIO, Double CA_HORAS, String id_dep, String id_ar, String id_sec) {
        jt.update("CALL RHSP_INSERT_TIPO_HORARIO (?,?,?,?,?,?,?,?)", null,
                NO_HORARIO, DE_HORARIO, ES_HORARIO, CA_HORAS, id_dep, id_ar, id_sec);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Tipo_Horario() {
        sql = "SELECT id_tipo_horario,no_horario,de_horario,es_horario,ca_horas,"
                + "es_turno_formato_h(ID_TIPO_HORARIO) as  ca_formato FROM "
                + "RHTR_TIPO_HORARIO";
        return jt.queryForList(sql);
    }

    public void Insert_Formato_Horario(String ID_FORMATO_HORARIO, String NO_TURNO, String NO_DIA, String HO_DESDE, String HO_HASTA, String ES_F_HORARIO, String ID_TIPO_HORARIO) {
        jt.update("CALL RHSP_INSERT_FORMATO_HORARIO (?,?,?,?,?,?,?)", null,
                NO_TURNO, NO_DIA, HO_DESDE, HO_HASTA, ES_F_HORARIO, ID_TIPO_HORARIO);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Formato_Horario(String idth) {
        sql = "Select * from RHTR_FORMATO_HORARIO where ID_TIPO_HORARIO =?";
        return jt.queryForList(sql, idth);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Tipo_Horario() {
        sql = "select  * from RHTR_TIPO_HORARIO where trim(es_horario) ='1'";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Tipo_HorarioDep(String iddep) {
        sql = "select * from RHTR_TIPO_HORARIO where trim(es_horario) ='1' AND "
                + "(ID_DEPARTAMENTO=? OR ID_DEPARTAMENTO IS NULL) ";
        return jt.queryForList(sql, iddep);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Tipo_HorarioSec(String idsec) {
        AreaDAO a = new AreaDAO(AppConfig.getDataSource());
        SeccionDAO s = new SeccionDAO(AppConfig.getDataSource());
        String idArea = (String) s.getSecctionById(idsec).get("id_area");
        String idDepartamento = (String) a.getAreaById(idArea).get("id_departamento");
        sql = "select  * from RHTR_TIPO_HORARIO where trim(es_horario) ='1' AND ( ID_SECCION=? OR ID_SECCION IS NULL) ";
        sql += (idDepartamento != null) ? " and (ID_DEPARTAMENTO='" + idDepartamento + "' or id_departamento is null) " : "";
        sql += (idArea != null) ? " and (id_area='" + idArea + "' or id_area is null) " : "";
        return jt.queryForList(sql, idsec);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Formato_h(String id_th) {
        sql = "select * from RHTR_FORMATO_HORARIO where "
                + "ID_TIPO_HORARIO=? order by ID_FORMATO_HORARIO asc ";
        return jt.queryForList(sql, id_th);
    }

    public String[][] List_D() {
        String[][] l = new String[7][2];
        l[0][0] = "lun";
        l[0][1] = "Lunes";

        l[1][0] = "mar";
        l[1][1] = "Martes";

        l[2][0] = "mie";
        l[2][1] = "Miercoles";

        l[3][0] = "jue";
        l[3][1] = "Jueves";

        l[4][0] = "vie";
        l[4][1] = "Viernes";

        l[5][0] = "sab";
        l[5][1] = "Sabado";

        l[6][0] = "dom";
        l[6][1] = "Domingo";

        return l;
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Lista_Plantilla_Puesto(String id) {
        sql = "select pc.ID_PLANTILLA_CONTRACTUAL,pc.NO_PLANTILLA,"
                + "pc.NO_ARCHIVO from RHTC_PLANTILLA_CONTRACTUAL pc, "
                + "RHTC_PLANTILLA_PUESTO pp where pp.ID_PLANTILLA_CONTRACTUAL = "
                + "pc.ID_PLANTILLA_CONTRACTUAL and pp.ES_PLANTILLA_PUESTO='1' and "
                + "pc.ES_PLAN_CONTRACTUAL='1'";
        String sql = "select   pc.ID_PLANTILLA_CONTRACTUAL,pc.NO_PLANTILLA,pc.NO_ARCHIVO  from RHTC_PLANTILLA_CONTRACTUAL pc , RHTC_PLANTILLA_PUESTO pp where pp.ID_PLANTILLA_CONTRACTUAL = pc.ID_PLANTILLA_CONTRACTUAL and pp.ES_PLANTILLA_PUESTO='1' and pc.ES_PLAN_CONTRACTUAL='1'";
        if (id.substring(0, 3).equals("PUT")) {
            sql += " and pp.id_puesto='" + id.trim() + "'";
        }

        if (id.substring(0, 3).equals("DIR")) {
            sql += " and pp.ID_DIRECCION='" + id.trim() + "' or pp.ID_DIRECCION='0' and pp.ID_DEPARTAMENTO='0' and pp.ID_AREA='0' and  pp.ID_SECCION='0'";
        }
        if (id.substring(0, 3).equals("DPT")) {
            sql += " and pp.ID_DEPARTAMENTO='" + id.trim() + "' or pp.ID_DIRECCION='0' and pp.ID_DEPARTAMENTO='0' and pp.ID_AREA='0' and  pp.ID_SECCION='0'";
        }
        if (id.substring(0, 3).equals("ARE")) {
            sql += " and pp.ID_AREA='" + id.trim() + "' or pp.ID_AREA='0'";
        }
        if (id.substring(0, 3).equals("SEC")) {
            sql += " and pp.ID_SECCION='" + id.trim() + "' or pp.ID_SECCION='0'";
        }
        sql += "group by pc.ID_PLANTILLA_CONTRACTUAL,pc.NO_PLANTILLA,pc.NO_PLANTILLA,pc.NO_ARCHIVO ";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Horario_dgp(String id_dgp) {
        sql = "SELECT h.ID_HORARIO,h.DIA_HORARIO,h.HO_DESDE,h.HO_HASTA FROM "
                + "RHTD_DETALLE_HORARIO dh,RHTC_HORARIO h WHERE "
                + "h.ID_DETALLE_HORARIO = dh.ID_DETALLE_HORARIO and dh.ID_DGP=?";
        return jt.queryForList(sql, id_dgp.trim());
    }
    
    public void Eliminar_turno(String id_turno) {
        jt.update("CALL RHSP_ELIMINAR_TURNO (?)",id_turno);
    }
    
    public List<Map<String, Object>> List_Tipo_Horarios() {
        sql = "select * from RHVD_TIPO_HORARIO";
        return jt.queryForList(sql);
    }
    
    public String ultimo_Tipo_Horario() {
        sql = "SELECT MAX(ID_TIPO_HORARIO) AS IDTH FROM RHTR_TIPO_HORARIO";
        return jt.queryForObject(sql, String.class);
    }
    
    public void Editar_FH(String ID_TIPO_HORARIO, String NO_HORARIO, 
            String DE_HORARIO, String ES_HORARIO, Double CA_HORAS, String id_dep, 
            String id_ar, String id_sec) {
        jt.update("CALL RHSP_MODIFICAR_TIPO_HORARIO (?,?,?,?,?,?,?,?)",
                ID_TIPO_HORARIO,NO_HORARIO,DE_HORARIO,ES_HORARIO,CA_HORAS,id_dep,
                id_ar,id_sec);
    }
    
    public void Eliminar_horario(String id) {
        jt.update("CALL RHSP_ELIMINAR_TIPO_HORARIO (?)",id);
    }
    
    public void Eliminar_formato_horario(String id) {
        jt.update("CALL RHSP_ELIMINAR_FORMATO_HORARIO(?)",id);
    }
    
    public void StatUpdate(String id, String es) {
        jt.update("CALL RHSP_UPDATE_TIPO_HORARIO(?,?)",id,es);
    }
    
}
