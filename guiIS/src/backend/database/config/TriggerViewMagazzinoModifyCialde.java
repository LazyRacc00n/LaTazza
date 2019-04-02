package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;

public class TriggerViewMagazzinoModifyCialde extends TriggerMagazzino implements Trigger {

    protected static final String TRIGGER_PATH="\"backend.database.config.TriggerViewMagazzinoModifyCialde\"";

    private static final String TABLE_NAME_CIALDE="LATAZZASCHEMA.CIALDE";
    private static final String TRIGGER_NAME_INSERT_CIALDE="Insert_View_Magazzino_cialde";
    private static final String TRIGGER_NAME_DELETE_CIALDE="Delete_View_Magazzino_cialde";
    private static final String TRIGGER_NAME_UPDATE_CIALDE="Update_View_Magazzino_cialde";
    private static final String CREATE_TRIGGER_STATEMENT_INSERT_CIALDE = "CREATE TRIGGER " + TRIGGER_NAME_INSERT_CIALDE + " AFTER INSERT ON "+ TABLE_NAME_CIALDE +" FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_DELETE_CIALDE = "CREATE TRIGGER " + TRIGGER_NAME_DELETE_CIALDE + " AFTER DELETE ON "+ TABLE_NAME_CIALDE +" FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_UPDATE_CIALDE = "CREATE TRIGGER " + TRIGGER_NAME_UPDATE_CIALDE + " AFTER UPDATE ON "+ TABLE_NAME_CIALDE +" FOR EACH ROW CALL " + TRIGGER_PATH;


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    private static void deleteTipoCialde(String tipo) throws  SQLException{
        PreparedStatement stat;
        stat =connection.prepareStatement("DELETE from "+ TABLE_NAME_MAGAZZINO+" where tipo='"+ tipo+"'");
        stat.executeUpdate();
        stat.close();
    }

    private static void insertTipoCialde(String tipo) throws SQLException{
        PreparedStatement stat;
        stat =connection.prepareStatement("insert into "+TABLE_NAME_MAGAZZINO+" values ('"+ tipo +"', 0)");
        stat.executeUpdate();
        stat.close();
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {



        if(oldRow != null)deleteTipoCialde((String) oldRow[0]);
        else
            if(newRow != null)insertTipoCialde((String) newRow[0]);
            else
            System.out.println("UPDATEEEEEEE");

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initTrigger(Connection conn)  {
        Statement stat= null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_STATEMENT_INSERT_CIALDE);
            stat.execute(CREATE_TRIGGER_STATEMENT_DELETE_CIALDE);
            stat.execute(CREATE_TRIGGER_STATEMENT_UPDATE_CIALDE);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
