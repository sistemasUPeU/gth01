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

/**
 *
 * @author Cesar
 */
public class PlantillaContractualDAO {
    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public PlantillaContractualDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }
    
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_PLantillas(String id_puesto) {
        sql = "SELECT (pl.ID_PLANTILLA_CONTRACTUAL)AS id_platilla_c,"
                    + "(pl.NO_PLANTILLA)as no_plantilla,(pl.NO_ARCHIVO) as no_arch "
                    + "FROM RHTC_PLANTILLA_PUESTO p,RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_PUESTO=? AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO='1' UNION "
                    + "SELECT pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,"
                    + "pl.NO_ARCHIVO FROM RHTC_PLANTILLA_PUESTO p,"
                    + "RHTC_PLANTILLA_CONTRACTUAL pl WHERE p.ID_SECCION=(SELECT "
                    + "ID_SECCION FROM RHVD_PUESTO_DIRECCION WHERE "
                    + "ID_PUESTO=?) AND p.ID_PUESTO='0' AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO='1' UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,pl.NO_ARCHIVO "
                    + "FROM RHTC_PLANTILLA_PUESTO p,RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_AREA=(SELECT ID_AREA FROM RHVD_PUESTO_DIRECCION "
                    + "WHERE ID_PUESTO=?)AND "
                    + "p.ID_SECCION='0'AND p.ID_PUESTO='0'AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO='1'UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA, "
                    + "pl.NO_ARCHIVO FROM RHTC_PLANTILLA_PUESTO p,"
                    + "RHTC_PLANTILLA_CONTRACTUAL pl WHERE p.ID_DEPARTAMENTO=("
                    + "SELECT ID_DEPARTAMENTO FROM RHVD_PUESTO_DIRECCION WHERE "
                    + "ID_PUESTO=?) AND p.ID_AREA='0'AND "
                    + "p.ID_SECCION='0' AND p.ID_PUESTO='0' AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO='1' UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,pl.NO_ARCHIVO "
                    + "FROM RHTC_PLANTILLA_PUESTO p,RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_DIRECCION=(SELECT ID_DIRECCION FROM "
                    + "RHVD_PUESTO_DIRECCION WHERE "
                    + "ID_PUESTO=?)AND "
                    + "p.ID_DEPARTAMENTO='0'AND p.ID_AREA='0' AND p.ID_SECCION='0' "
                    + "AND p.ID_PUESTO='0' AND pl.ID_PLANTILLA_CONTRACTUAL = "
                    + "p.ID_PLANTILLA_CONTRACTUAL AND p.ES_PLANTILLA_PUESTO='1' "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, "
                    + "pl.NO_ARCHIVO FROM RHTC_PLANTILLA_PUESTO p, "
                    + "RHTC_PLANTILLA_CONTRACTUAL pl WHERE p.ID_DIRECCION='0' AND "
                    + "p.ID_DEPARTAMENTO='0' AND p.ID_AREA='0' AND p.ID_SECCION='0' "
                    + "AND p.ID_PUESTO='0' AND pl.ID_PLANTILLA_CONTRACTUAL = "
                    + "p.ID_PLANTILLA_CONTRACTUAL AND p.ES_PLANTILLA_PUESTO='1' "
                    + "order by NO_PLANTILLA";
        return jt.queryForList(sql,id_puesto.trim(),id_puesto.trim(),
                id_puesto.trim(),id_puesto.trim(),id_puesto.trim());
    }
    
    public void Crear_Plantilla(String no_pl, String User_crea) {
        jt.update("CALL RHSP_INSERT_PLANTILLA( ?,?,?,?,?,?,?,? )", null,no_pl,
                null,User_crea.trim(),null,null,null,null);
    }
    
    public String Obt_no_arch() {
        sql = "select NO_ARCHIVO from RHTC_PLANTILLA_CONTRACTUAL where "
                + "ID_PLANTILLA_CONTRACTUAL=(SELECT 'PLC-'||lpad(to_char(MAX("
                + "TO_NUMBER(SUBSTR(ID_PLANTILLA_CONTRACTUAL,5,8)))),6,'0') FROM "
                + "RHTC_PLANTILLA_CONTRACTUAL)";
        return jt.queryForObject(sql, String.class);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_PLanti_x_sel(String id_pu, String id_sec, 
            String id_are, String id_dep, String id_dir) {
        sql = "SELECT (pl.ID_PLANTILLA_CONTRACTUAL)AS id_platilla_c,"
                    + "(pl.NO_PLANTILLA)AS no_plantilla,(pl.NO_ARCHIVO)AS no_arch "
                    + "FROM RHTC_PLANTILLA_PUESTO p,RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_PUESTO =? AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO ='1' UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, pl.NO_ARCHIVO "
                    + "from RHTC_PLANTILLA_CONTRACTUAL pl,RHTC_PLANTILLA_PUESTO p "
                    + "WHERE p.ID_SECCION=? AND "
                    + "p.ID_PUESTO ='0' AND pl.ID_PLANTILLA_CONTRACTUAL = "
                    + "p.ID_PLANTILLA_CONTRACTUAL AND p.ES_PLANTILLA_PUESTO ='1' "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, "
                    + "pl.NO_ARCHIVO FROM RHTC_PLANTILLA_PUESTO p, "
                    + "RHTC_PLANTILLA_CONTRACTUAL pl WHERE "
                    + "p.ID_AREA =? AND p.ID_SECCION='0' AND "
                    + "p.ID_PUESTO ='0' AND pl.ID_PLANTILLA_CONTRACTUAL = "
                    + "p.ID_PLANTILLA_CONTRACTUAL AND p.ES_PLANTILLA_PUESTO ='1' "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, "
                    + "pl.NO_ARCHIVO FROM RHTC_PLANTILLA_PUESTO p, "
                    + "RHTC_PLANTILLA_CONTRACTUAL pl WHERE "
                    + "p.ID_DEPARTAMENTO =? AND "
                    + "p.ID_AREA ='0' AND p.ID_SECCION ='0' AND p.ID_PUESTO ='0' "
                    + "AND pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL "
                    + "AND p.ES_PLANTILLA_PUESTO ='1' UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, pl.NO_ARCHIVO "
                    + "FROM RHTC_PLANTILLA_PUESTO p, RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_DIRECCION =? AND "
                    + "p.ID_DEPARTAMENTO ='0' AND p.ID_AREA ='0' AND "
                    + "p.ID_SECCION ='0' AND p.ID_PUESTO ='0'AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO ='1' UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, pl.NO_ARCHIVO "
                    + "FROM RHTC_PLANTILLA_PUESTO p, RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_DIRECCION ='0' AND p.ID_DEPARTAMENTO ='0' AND "
                    + "p.ID_AREA='0' AND p.ID_SECCION ='0' AND p.ID_PUESTO ='0' AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL AND "
                    + "p.ES_PLANTILLA_PUESTO ='1'";
        return jt.queryForList(sql,id_pu.trim(),id_sec.trim(),
                id_are.trim(),id_dep.trim(),id_dir.trim());
    }
    
    public String List_pl_con_x_id(String id_plan_con) {
        String sql = "SELECT NO_ARCHIVO FROM RHTC_PLANTILLA_CONTRACTUAL WHERE "
                + "ID_PLANTILLA_CONTRACTUAL=?";
        return jt.queryForObject(sql, String.class,id_plan_con.trim());
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_PLant_x_sel(String id_pu, String id_sec, 
            String id_are, String id_dep, String id_dir) {
        sql = "select cc.* ,dd.no_direccion,dd.no_dep,dd.no_area,"
                    + "dd.no_seccion,dd.no_puesto from (SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,pl.NO_ARCHIVO,"
                    + "p.ES_PLANTILLA_PUESTO,p.ID_PLANTILLA_PUESTO FROM "
                    + "RHTC_PLANTILLA_PUESTO p, RHTC_PLANTILLA_CONTRACTUAL pl WHERE "
                    + "p.ID_DIRECCION='0'AND p.ID_DEPARTAMENTO='0'AND p.ID_AREA='0' "
                    + "AND p.ID_SECCION='0'AND p.ID_PUESTO='0' AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,"
                    + "pl.NO_ARCHIVO,p.ES_PLANTILLA_PUESTO,p.ID_PLANTILLA_PUESTO "
                    + "FROM RHTC_PLANTILLA_PUESTO p,RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_PUESTO=? AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,"
                    + "pl.NO_ARCHIVO,p.ES_PLANTILLA_PUESTO,p.ID_PLANTILLA_PUESTO "
                    + "FROM RHTC_PLANTILLA_PUESTO p, RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_SECCION=? AND p.ID_PUESTO='0' "
                    + "AND pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,"
                    + "pl.NO_ARCHIVO, p.ES_PLANTILLA_PUESTO,p.ID_PLANTILLA_PUESTO "
                    + "FROM RHTC_PLANTILLA_PUESTO p, RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_AREA=? AND p.ID_SECCION='0'"
                    + "AND p.ID_PUESTO='0'AND pl.ID_PLANTILLA_CONTRACTUAL = "
                    + "p.ID_PLANTILLA_CONTRACTUAL UNION SELECT "
                    + "pl.ID_PLANTILLA_CONTRACTUAL, pl.NO_PLANTILLA, pl.NO_ARCHIVO,"
                    + "p.ES_PLANTILLA_PUESTO,ID_PLANTILLA_PUESTO FROM "
                    + "RHTC_PLANTILLA_PUESTO p, RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_DEPARTAMENTO=? AND "
                    + "p.ID_AREA='0'AND p.ID_SECCION='0'AND p.ID_PUESTO='0'AND "
                    + "pl.ID_PLANTILLA_CONTRACTUAL = p.ID_PLANTILLA_CONTRACTUAL "
                    + "UNION SELECT pl.ID_PLANTILLA_CONTRACTUAL,pl.NO_PLANTILLA,"
                    + "pl.NO_ARCHIVO,p.ES_PLANTILLA_PUESTO,p.ID_PLANTILLA_PUESTO "
                    + "FROM RHTC_PLANTILLA_PUESTO p,RHTC_PLANTILLA_CONTRACTUAL pl "
                    + "WHERE p.ID_DIRECCION=? AND "
                    + "p.ID_DEPARTAMENTO='0'AND p.ID_AREA='0'AND p.ID_SECCION='0'"
                    + "AND p.ID_PUESTO='0'AND pl.ID_PLANTILLA_CONTRACTUAL = "
                    + "p.ID_PLANTILLA_CONTRACTUAL ) cc, (select "
                    + "pp.ID_PLANTILLA_PUESTO,case when d.NO_DIRECCION is null "
                    + "then 'Todos' else d.NO_DIRECCION end as no_direccion, case "
                    + "when de.NO_DEP is null then  'Todos' else de.NO_DEP end as "
                    + "no_dep, case when a.NO_AREA is null then 'Todos' else "
                    + "a.NO_AREA end as no_area, case when s.NO_SECCION is null "
                    + "then 'Todos' else s.NO_SECCION end as no_seccion,case when "
                    + "pu.NO_PUESTO is null then 'Todos' else pu.NO_PUESTO end as "
                    + "no_puesto from RHTC_PLANTILLA_PUESTO pp  left outer join "
                    + "RHTX_DIRECCION d on (pp.ID_DIRECCION=d.ID_DIRECCION) left "
                    + "outer join RHTX_DEPARTAMENTO de on (de.ID_DEPARTAMENTO="
                    + "pp.ID_DEPARTAMENTO) left outer join RHTD_AREA a on ("
                    + "a.id_area=pp.ID_AREA) left outer join RHTR_SECCION s on ("
                    + "s.ID_SECCION=pp.ID_SECCION) left outer join RHTR_PUESTO pu "
                    + "on (pu.ID_PUESTO=pp.id_puesto) ) dd where "
                    + "dd.id_plantilla_puesto=cc.id_plantilla_puesto";
        return jt.queryForList(sql, id_pu.trim(),id_sec.trim(),id_are.trim(),
                id_dep.trim(),id_dir.trim());
    }
    
    public void Insertar_pertenencia(String id_plt_con, String dir, String dep, 
            String area, String sec, String puesto, String id_user) {
        jt.update("CALL RHSP_INSERT_PLANTILLA_PUEST( ?,?,?,?,?,?,? )", 
                dir.trim(), dep.trim(), area.trim(), sec.trim(), puesto, id_user);
    }
    
    public String ob_id_pl_max() {
        sql = "select ID_PLANTILLA_CONTRACTUAL from "
                + "RHTC_PLANTILLA_CONTRACTUAL where "
                + "ID_PLANTILLA_CONTRACTUAL=(SELECT 'PLC-'||lpad(to_char(MAX("
                + "TO_NUMBER(SUBSTR(ID_PLANTILLA_CONTRACTUAL,5,8)))),6,'0')  "
                + "FROM RHTC_PLANTILLA_CONTRACTUAL)";
        return jt.queryForObject(sql, String.class);
    }
    
    public void Activar_pl_pu(String id_pp, String id_user) {
        jt.update("CALL RHSP_ACTIVAR_PLANTILLA_PUESTO(?, ?)",id_pp, id_user);
    }
    
    public void Desactivar_pl_pu(String id_pp, String id_user) {
        jt.update("CALL RHSP_DES_PLANTILLA_PUESTO(?, ?)",id_pp, id_user);
    }
    
    public boolean Update_Name_File(String id, String nombre) {
        return (jt.update("CALL rhsp_update_nombre_plantilla(?, ?)",id, nombre)!=0);
    }
    
}
