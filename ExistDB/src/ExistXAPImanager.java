import net.xqj.exist.ExistXQDataSource;
import org.exist.xmldb.DatabaseInstanceManager;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import java.io.File;

/**
 * Created by pau on 22/03/17.
 */
public class ExistXAPImanager {

   /*
   Yes, I know that the value of the variables "IP","PORT" and "ExistDB" shouldn't have their values hard-coded,
    but I made them to make exercise and testing faster. I would not use this methods outside of non-learning purposes.
    */

    private static final String IP = "localhost";
    private static final String PORT = "8080";
    private static XQConnection connection;


    private static String ExistDB = "xmldb:exist://"+IP+":"+PORT+"/exist/xmlrpc/db";



    public static String getExistDB(){
        return ExistDB;
    }

    public static Collection createCollection(String user, String pass, String collectionName) throws XMLDBException {


        Collection parent = DatabaseManager.getCollection(ExistDB, user, pass);
        CollectionManagementService c =
                (CollectionManagementService) parent.getService("CollectionManagementService", "2.0");

       return c.createCollection(collectionName);
    }

    public static void addResource(File [] file, String collectionName, String user, String pass) throws XMLDBException {

        //Instanciem la col·lecció a la que volem afegir el recurs xml
        Collection collection = DatabaseManager.getCollection(ExistDB+"/"+collectionName, user, pass);

        //Afegim els recursos
        for(File f: file){
            Resource resource = collection.createResource(f.getName(), "XMLResource");
            resource.setContent(f);
            collection.storeResource(resource);
        }

    }

    public static String[] getColectionResourcesList(String collectionName) throws XMLDBException {

        return DatabaseManager.getCollection(ExistDB+"/"+collectionName).listResources();
    }

    public static XMLResource getCollectionResource(String collectionName, String resourceName)
            throws XMLDBException {

        return (XMLResource) DatabaseManager.getCollection(ExistDB+"/"+collectionName).getResource(resourceName);
    }

    public static void ShutDownDB(Collection col) throws XMLDBException {

        DatabaseInstanceManager manager = (DatabaseInstanceManager)
                col.getService("DatabaseInstanceManager", "1.0");
        manager.shutdown();
    }

    public static XQConnection connect() throws XQException {
        XQDataSource source = new ExistXQDataSource();
        source.setProperty("serverName", IP);
        source.setProperty("port", PORT);
        connection = source.getConnection();
        return connection;
    }

    public static void closeCon() throws XQException {
        connection.close();
    }

}
