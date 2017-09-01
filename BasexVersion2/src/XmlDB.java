import org.basex.BaseXServer;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.CreateDB;

import java.io.IOException;


public class XmlDB {

    private static BaseXServer server;

    public static void startBasexServer() throws IOException {

        server = new BaseXServer();
    }

    public static void stopBasexServer() throws IOException {

        server.stop();
    }

    public static ClientSession initDefaultClient() throws IOException {

        return new ClientSession("localhost", 1984, "admin", "admin");
    }

    public static void openDB(ClientSession session, String dbName, String docSource) throws IOException {
        session.execute(new CreateDB(dbName, docSource));
    }

    public static String executeQuery(ClientSession session, String query) throws IOException {

        return session.query(query).execute();
    }
}
