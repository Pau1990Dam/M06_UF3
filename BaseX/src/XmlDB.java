
import net.xqj.basex.BaseXXQDataSource;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;

/**
 * Aquesta classe serveix per conectar i desconectar a un Servidor ja iniciat de BaseX
 */
public class XmlDB {

    private static XQConnection con;

    /**
     * Aquest mètode retorna un objecte XQConnection inicialitat amb els paràmetres per defecte d'un servidor BaseX
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws XQException
     */
    public static XQConnection connetc()
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, XQException {

        XQDataSource xqs = new BaseXXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "1984");
        xqs.setProperty("user","admin");
        xqs.setProperty("password","admin");

        con = xqs.getConnection();

        return con;
    }

    /**
     * Aquest métdoe serveix per tncar la conexió iniciada en el métode anterior.
     * @throws XQException
     */
    public static void closeCon() throws XQException {
        con.close();
    }
}
