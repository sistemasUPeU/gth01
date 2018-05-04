/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Cesar
 */
public class ListaDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public ListaDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Nacionalidad() {
        sql = "select * from rhtx_nacionalidad order by no_nacionalidad";
        return jt.queryForList(sql);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Proceso() {
        sql = "select * from rhtv_proceso order by no_proceso";
        return jt.queryForList(sql);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Carrera() {
        sql = "select * from rhtx_carrera";
        return jt.queryForList(sql);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Universidad() {
        sql = "select * from rhtx_universidad order by no_universidad";
        return jt.queryForList(sql);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Auto_mostrar(String id_rol) {
        sql = "select di_url from RHTX_AUTO_MOSTRAR where ID_ROL=?";
        return jt.queryForList(sql, id_rol);
    }

    public List<String> List_Estado_Civil() {
        List<String> list = new ArrayList<String>();
        list.add("Soltero(a)");
        list.add("Casado(a)");
        list.add("Divorcio(a)");
        list.add("Viudo(a)");
        list.add("Separado(a)");
        list.add("Conviviente(a)");
        return list;
    }

    public List<String> List_Doc() {
        List<String> list = new ArrayList<String>();
        list.add("DNI");
        list.add("Carne de Extrangeria");
        list.add("Pasaporte");
        list.add("Partida de  Nacimiento");
        list.add("Libreta Electoral");
        list.add("Libreta Militar");
        list.add("Boleta Inscripción Militar");
        list.add("Permiso para Menores");
        return list;
    }

    public List<String> List_Gs() {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("AB");
        list.add("O");
        return list;
    }

    public List<String> List_Sp() {
        List<String> list = new ArrayList<String>();
        list.add("AFP");
        list.add("ONP");
        list.add("Sin Régimen Privisional(Jubilado,Cesante).");
        list.add("Fuera de Planilla");
        return list;
    }

    public List<String> List_Nom_AFP() {
        List<String> list = new ArrayList<String>();
        list.add("Integra");
        list.add("Prima");
        list.add("Profuturo");
        list.add("Horizonte");
        list.add("Habitat");
        list.add("Ninguno");
        return list;
    }

    public List<String> List_Nivel_Educativo() {
        List<String> list = new ArrayList<String>();
        list.add("Ninguno");
        list.add("Primaria Incompleta");
        list.add("Primaria Completa");
        list.add("Superior No Universitario Incompleto");
        list.add("Superior No Universitario Completo");
        list.add("Superior Universitario Incompleto");
        list.add("Superior Universitario Completo");
        list.add("Superior Post Grado Incompleto");
        list.add("Superior Post Grado Completo");
        return list;
    }

    public List<String> List_Grado_Academico() {
        List<String> list = new ArrayList<String>();
        list.add("Ninguno");
        list.add("Bachiller");
        list.add("Magister");
        list.add("Doctor");
        return list;
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Dom_D1_Id() {
        sql = "select * from rhtx_via order by co_via";
        return jt.queryForList(sql);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Dom_D5_Id() {
        sql = "select * from rhtx_zona";
        return jt.queryForList(sql);
    }

    public List<String> List_Jefe() {
        List<String> list = new ArrayList<String>();
        list.add("No es Jefe");
        list.add("Jefe de Sección");
        list.add("Jefe de Area");
        list.add("Jefe de Departamento");
        list.add("Jefe de Dirección");
        return list;
    }

    public List<String> list_Condicion_contrato() {
        List<String> list = new ArrayList<String>();
        list.add("Contrato");
        list.add("Contrato Independiente");
        list.add("Empleado");
        list.add("Misionero");
        list.add("Práctica Profesional");
        list.add("MFL-Práctica Pre Profesional");
        list.add("Convenio Laboral Juvenil");
        list.add("MFL-Contrato");
        return list;
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_tipo_contrato() {
        sql = "select * from rhtx_tipo_contrato";
        return jt.queryForList(sql);
    }

    public List<String> List_Situacion_Actual() {
        List<String> list = new ArrayList<String>();
        list.add("Activo");
        list.add("Terminó Contrato");
        list.add("Renuncia Voluntaria");
        list.add("Traslado a otra Filial/Institución");
        list.add("No Inicio Relacion Laboral");
        list.add("Cambio de Modalidad Contractual");
        list.add("Abandono de Trabajo");
        return list;
    }

    public String[][] List_H() {
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
    
    public List<String> List_Dom_D3_Id() {
        List<String> list = new ArrayList<String>();
        list.add("Numero");
        list.add("Lote");
        list.add("S/N");
        list.add("Km");
        list.add("Block");
        list.add("Etapa");
        list.add("Departamento");
        list.add("Interior");
        return list;
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Situacion_Educativa() {
        sql = "select * from rhtx_situacion_educativa ";
        return jt.queryForList(sql);
    }

    public List<String> lista_años() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            sql = "select  to_number(to_char(sysdate,'yyyy')) - ? from dual";
            list.add(jt.queryForObject(sql, String.class, i).trim());
        }
        return list;
    }

}
