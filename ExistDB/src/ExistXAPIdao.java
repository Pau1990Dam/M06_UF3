
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

public class ExistXAPIdao {


    private static XQPreparedExpression expr = null;


    public static String XpathQuery(String Existdb, String collectionName, String query) throws XMLDBException {

        org.xmldb.api.base.Collection col = DatabaseManager.getCollection
                (Existdb+"/"+collectionName);

        XPathQueryService xpqs = (XPathQueryService)col.getService("XPathQueryService", "2.0");
        xpqs.setProperty("indent", "yes");
        ResourceSet result = xpqs.query(query);
        ResourceIterator i = result.getIterator();
        Resource res = null;
        StringBuilder answer = new StringBuilder();

        while(i.hasMoreResources()) {
            res = i.nextResource();
            answer.append(res.getContent()).append("\n");
        }

        return answer.toString();
    }

    public static String getFactBookPaisos(String docSource) throws ClassNotFoundException, XQException,
            InstantiationException, IllegalAccessException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x := " +
                        docSource
                        +"/factbook/record/country\n" +
                        "let $y := distinct-values($x)\n" +
                        "return data($x)");

        return queryExecution();
    }

    public static String getNumPaisos(String docSource) throws ClassNotFoundException, XQException,
            InstantiationException, IllegalAccessException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x :=" +
                        " distinct-values("+
                        docSource
                        +"/mondial/country)\n"
                        +"return count($x)"
        );

        return queryExecution();
    }

    public static String getInfoAlemanya(String docSource) throws XQException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x := " +
                        docSource+
                        "/mondial/country[name=\"Germany\"] "+
                        "return $x"
        );

        return queryExecution();
    }

    public static String getLastCensUganda(String docSource) throws XQException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x := " +
                        docSource+
                        "/mondial/country[name=\"Uganda\"] " +
                        "return util:last-from(data($x/population))"
        );

        return queryExecution();
    }

    public static String getPeruCities(String docSource) throws XQException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x:= " +
                        docSource +
                        "/mondial/country[name=\"Peru\"] " +
                        "return data($x/*/city/name)"
        );

        return queryExecution();
    }

    public static String getShanghaiPopulation(String docSource) throws XQException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x:= " +
                        docSource+
                        "/mondial/country[name=\"China\"]\n" +
                        "return util:last-from(data($x/*/city[(name=\"Shanghai\")]/population))"
        );

        return queryExecution();
    }

    public static String getMatriculaChipre(String docSource) throws XQException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        expr = ExistXAPImanager.connect().prepareExpression(
                "let $x:= " +
                        docSource+
                        "/mondial/country[name=\"Cyprus\"]\n" +
                        "return data($x/@car_code) "
        );

        return queryExecution();
    }

    /**
     * Aquest mètode executa la consulta creada des del mètode que el truca y retorna el resultat en un String
     * @return
     * @throws XQException
     */

    private static String queryExecution() throws XQException {

        String answer ="";
        XQResultSequence result = expr.executeQuery();

        while(result.next()) {
            // iterar resutats
            answer += result.getItemAsString(null)+"\n";
        }

        // Taquem lae expressió y connecxió
        expr.close();
        ExistXAPImanager.closeCon();

        return answer;
    }

}
