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
public class Carrera_UniversidadDAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public Carrera_UniversidadDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    /*EVALUAR EL USO DE ESTE MÉTODO (NOMBRE DE VARIABLES)*/
    public List<Map<String, Object>> Tipo_Institucion(String id_reg) {
        sql = "select id_tipo_institucion,no_tipo_institucion,co_regimen  from rhtr_tipo_institucion where trim(co_regimen)=?";
        return jt.queryForList(sql,id_reg.trim());
    }
    /*EVALUAR EL USO DE ESTE MÉTODO (NOMBRE DE VARIABLES)*/
    public List<Map<String, Object>> Istitucion(String id) {
        sql = "select id_universidad,no_universidad "
                + "from rhtx_universidad where "
                + "trim(id_tipo_institucion)=? "
                + "ORDER BY no_universidad asc ";
        return jt.queryForList(sql,id.trim());
    }
    /*EVALUAR EL USO DE ESTE MÉTODO (NOMBRE DE VARIABLES)*/
    public List<Map<String, Object>> Carrera_Id_universidad(String id) {
        sql = "select uc.id_universidad_carrera,trim(c.no_carrera) "
                + "as no_carrera  from rhtx_carrera c , "
                + "rhtx_universidad_carrera uc where "
                + "trim(uc.id_carrera) = trim(c.id_carrera) and "
                + "trim(uc.id_universidad)=? "
                + "order by no_carrera asc";
        return jt.queryForList(sql,id.trim());
    }
    /*EVALUAR EL USO DE ESTE MÉTODO (DTO OBJECT WAS REPLACED, WE USE MAP OBJECT INSTEAD)*/
    public List<Map<String,Object>> List_Tipo_Ins() {
        sql = "select * from rhtr_tipo_institucion ";
        return jt.queryForList(sql);
    }
    /*EVALUAR EL USO DE ESTE MÉTODO (DTO OBJECT WAS REPLACED, WE USE MAP OBJECT INSTEAD)*/
    public List<Map<String,Object>> List_Carrera() {
        sql = "SELECT NO_CARRERA FROM RHTX_CARRERA GROUP BY NO_CARRERA ORDER BY NO_CARRERA";
        return jt.queryForList(sql);
    }
    
}
