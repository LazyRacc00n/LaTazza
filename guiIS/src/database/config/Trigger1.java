package database.config;
import org.h2.api.Trigger;

import java.sql.*;

public class Trigger1 implements Trigger {

    // private Connection conn;
    //private Statement stat;
    //private String triggerName, table,schema;
    //private boolean before;
    //private int type; //1: Insert, 2: Update, 4: Delete, 8: select
    //private ResultSet cV,cP,magazzino;

    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) throws SQLException {

        //conn=connection; schema=s; triggerName =t1; table =t2; before=b; type=i;
        //stat=conn.createStatement();
        /*cV=stat.executeQuery("select sum(numero_cialde) "+
                " from LATAZZASCHEMA.COMPRA_VISITATORE T "+
                " where tipo_cialda = (select tipo_cialda from LATAZZASCHEMA.COMPRA_VISITATORE where T.TIPO_CIALDA=COMPRA_VISITATORE.TIPO_CIALDA); ");
        cP=stat.executeQuery("select sum(numero_cialde)\n"+
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n"+
                " where tipo_cialda = (select tipo_cialda from LATAZZASCHEMA.COMPRA_DIPENDENTE T where T.TIPO_CIALDA=COMPRA_DIPENDENTE.TIPO_CIALDA);");

        magazzino=stat.executeQuery("select sum(QTA*50)\n"+
                " from LATAZZASCHEMA.MAGAZZINO\n"+
                " where tipoCialda=(select TIPOCIALDA from LATAZZASCHEMA.MAGAZZINO T where T.TIPOCIALDA=MAGAZZINO.TIPOCIALDA);");
        */
    }

    /* public Trigger1(Connection conn) throws SQLException {


         init(conn,"LATAZZASCHEMA","CHECKNUMCIALDE","LATAZZASCHEMA.COMPRA_VISITATORE",false,1);

     }
 */
    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        PreparedStatement stat;
        ResultSet cV, cP, rif;

        String q1="select sum(numero_cialde) " +
                " from LATAZZASCHEMA.COMPRA_VISITATORE T " +
                " where tipo_cialda = ?; ";
        String q2="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                " where tipo_cialda = ?";
        String q3="select sum(qta*50)\n" +
                " from LATAZZASCHEMA.RIFORNIMENTO\n" +
                " where tipoCialda=?";

        stat= conn.prepareStatement(q1);
        stat.setNString (1, (String) newRow[2]); // sostituisce newRow[2] (tipo_cialda) al ?
        cV=stat.executeQuery(q1);
        cV.next();

        stat= conn.prepareStatement(q2);
        stat.setNString (1, (String) newRow[2]);
        cP = stat.executeQuery(q2);
        cP.next();

        stat= conn.prepareStatement(q3);
        stat.setNString (1, (String) newRow[2]);
        rif=stat.executeQuery(q3);
        rif.next();


        if ((cV.getInt(1) + cP.getInt(1) > rif.getInt(1)))
            throw new SQLException("Numero di cialde da comprare superiore a quelle disponibili in magazzino.");

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }
}
