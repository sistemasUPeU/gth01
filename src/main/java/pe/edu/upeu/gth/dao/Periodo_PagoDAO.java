/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.util.DateFormat;

/**
 *
 * @author Cesar
 */
public class Periodo_PagoDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public Periodo_PagoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void InsetarPeriodo_Pago(String ID_PERIODO_PAGO, Double NU_CUOTA,
            String FE_PAGAR, Double CA_MONTO, String ID_DGP, String ES_PER_PAGO) {
        try {
            jt.update("CALL RHSP_INSERT_PERIODO_PAGO(?, ?, ?, ?, ?, ? )", null,
                    NU_CUOTA, DateFormat.toFormat1(FE_PAGAR), CA_MONTO, ID_DGP,
                    ES_PER_PAGO);
        } catch (ParseException ex) {
            Logger.getLogger(Periodo_PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
