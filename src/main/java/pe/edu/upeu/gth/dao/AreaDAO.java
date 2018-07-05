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
public class AreaDAO {
    private JdbcTemplate jt;
    private String sql="";
    public AreaDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    public Map<String,Object> getAreaById(String idArea){
        sql = "Select * from rhtd_area where id_area=?";
        return jt.queryForMap(sql,idArea);
    }
    public List<Map<String,Object>> ListArea(){
        sql = "Select * from rhtd_area ";
        return jt.queryForList(sql);
    }
    public List<Map<String,Object>> ListAreaByDeptId(String id_departamento){
        sql = "Select * from rhtd_area where id_departamento=? order by no_area";
        return jt.queryForList(sql,id_departamento);
    }
    public List<Map<String,Object>> selec_area(String id_pu){
        sql = "SELECT ID_AREA FROM RHTR_SECCION where ID_SECCION=(SELECT ID_SECCION "
                + "from RHTR_PUESTO where ID_PUESTO=?)";
        return jt.queryForList(sql,id_pu.trim());
    }
    public List<Map<String,Object>> List_Area_Lima(){
        sql = "SELECT NO_AREA FROM RHVD_PUESTO_DIRECCION where "
                + "ID_DIRECCION='DIR-0002' OR ID_DIRECCION='DIR-0001' "
                + "OR ID_DIRECCION='DIR-0003' OR ID_DIRECCION='DIR-0004' "
                + "GROUP BY NO_AREA ORDER by NO_AREA";
        return jt.queryForList(sql);
    }
    public boolean crear_area(String nombre,String ncorto,String estado,String idDep){
        boolean x=false;
        int i=0;
        i=jt.update("CALL RHSP_INSERT_AREA( ?, ?, ?, ?, ?)", null,nombre,ncorto,estado,idDep);
        if(i==1){
            x=true;
        }
        return x;
    }
    public boolean editar_area(String idArea, String nombre,String ncorto,String estado,String idDep){
        boolean x=false;
        int i=0;
        i=jt.update("CALL RHSP_MOD_AREA( ?, ?, ?, ?, ?)", idArea,nombre,ncorto,estado,idDep);
        if(i==1){
            x=true;
        }
        return x;
    }
    public boolean activar_area(String idArea){
        boolean x=false;
        int i=0;
        i=jt.update("CALL RHSP_ACTIVAR_AREA(?)", idArea);
        if(i==1){
            x=true;
        }
        return x;
    }
    public boolean desactivar_area(String idArea){
        boolean x=false;
        int i=0;
        i=jt.update("CALL RHSP_DESACTIVAR_AREA(?)", idArea);
        if(i==1){
            x=true;
        }
        return x;
    }
    public boolean eliminar_area(String idArea){
        boolean x=false;
        int i=0;
        i=jt.update("CALL RHSP_ELIMINAR_AREA(?)", idArea);
        if(i==1){
            x=true;
        }
        return x;
    }
    public List<Map<String, Object>> List_area_es(String idDep) {
        sql = "Select * from rhtd_area where id_departamento=? and ES_AREA='1' order by no_area";
        return jt.queryForList(sql, idDep.trim());
    }
}
