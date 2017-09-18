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
public class PagoDocenteDAO {
    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public PagoDocenteDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }
    
   /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> getPagoDocenteByIdProcCA(String idpca) {
        sql = "select ID_PAGO_DOCENTE, NU_CUOTA,CA_CUOTA,ES_PAGO_DOCENTE,"
                + "ID_PROCESO_CARGA_AC ,to_char(fe_pago,'yyyy/mm/dd') as fe_pago "
                + "from RHTD_PAGO_DOCENTE where ID_PROCESO_CARGA_AC='" + idpca + "'";
        return jt.queryForList(sql);
    }
    
}
