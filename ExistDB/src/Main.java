import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.xquery.XQException;
import java.io.File;
import java.util.Scanner;

/**
 * Created by pau on 22/03/17.
 */
public class Main {

    private static String user = "admin";
    private static String pass = "admin";
    private static String newCollectionName = "pauCollection";
    private static String resourcePathMondial = "mondial.xml";
    private static String resourcePathFactbook = "Factbook.xml";

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        File [] recursosXML = new File[2];
        recursosXML [0] = new File(resourcePathMondial);
        recursosXML [1] = new File(resourcePathFactbook);
        Collection collection;

        try {

            System.out.println("CONNECTEM AMB EL SGDB-XML eXist-db...");
            ExistXAPIregister.register();

            System.out.println("Creem una nova bd o Collection.");
            collection = ExistXAPImanager.createCollection(user,pass,newCollectionName);

            System.out.println("Afegim nous recursos en aquesta nova col·lecció que hem creat.");
            ExistXAPImanager.addResource(recursosXML,newCollectionName,user,pass);

            System.out.println("\nLlistem els recursos de la colecció que hem creat.");
            String recursos[] = ExistXAPImanager.getColectionResourcesList(newCollectionName);
            System.out.println("Recursos:");
            for (int i = 0; i < recursos.length; i++) {
                System.out.println("\t"+recursos[i]);
            }

            System.out.println();
            //Això es per poder-ho veure tot en la terminal, troç a troç
            System.out.println("Presiona intro per continuar...");
            entrada.nextLine();

            System.out.println("Agafem i imprimim els recursos de la colecció que hem creat");
            XMLResource res = ExistXAPImanager.getCollectionResource(newCollectionName,resourcePathMondial);

            System.out.println("##################################"+resourcePathMondial
                    +"##################################");
            System.out.println(res.getDocumentId());
            System.out.println(res.getContent());
            System.out.println("##########################################################################");

            System.out.println("Presiona intro per continuar...");
            entrada.nextLine();

            System.out.println("##########################################################################");
            System.out.println();
            System.out.println("#################################"+resourcePathFactbook
                    +"#################################");
            res = ExistXAPImanager.getCollectionResource(newCollectionName,resourcePathFactbook);
            System.out.println(res.getDocumentId());
            System.out.println(res.getContent());
            System.out.println("##########################################################################");

            System.out.println("Presiona intro per continuar...");
            entrada.nextLine();

            System.out.println("######################################CONSULTES######################################");
            System.out.println();
            System.out.print("\nFem una consulta a una col·lecció. Ex, Quin es el codi de Matrícula de Xipre? ->");
            System.out.print(ExistXAPIdao.getMatriculaChipre("collection('"+newCollectionName+"')"));

            System.out.print("\nRepetim la mateixa consulta pero, només apuntant al recurs "+resourcePathMondial+" ->");
            System.out.print(ExistXAPIdao.getMatriculaChipre("doc('/db/"+newCollectionName+"/"+resourcePathMondial+"')"));

            System.out.println("Presiona intro per continuar...");
            entrada.nextLine();

            System.out.println("Fem una consulta a la colecció sencera de manera directa, es a dir sense establir una conexió.");
            System.out.println(ExistXAPIdao.XpathQuery(ExistXAPImanager.getExistDB(),newCollectionName,"//country"));

            System.out.println("Presiona intro per continuar...");
            entrada.nextLine();

            System.out.println("Fem la mateixa query però, ara només amb "+resourcePathFactbook);
            System.out.println(ExistXAPIdao.XpathQuery(ExistXAPImanager.getExistDB(),newCollectionName,
                    "doc('Factbook.xml')//country"));

            System.out.println();
            System.out.println("Tanquem la BD");
            ExistXAPImanager.ShutDownDB(collection);

        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        } catch (IllegalAccessException e) {
           e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (XMLDBException e) {
           e.printStackTrace();
        } catch (XQException e) {
            e.printStackTrace();
        }
    }
}

/*
Crei una col·lecció
Afegeixi un recurs a una col·lecció
Que retorni un recurs (i per exemple l'imprimeixi per pantalla)
Que faci una consulta en una col·lecció
Que faci consultes sobre una col·lecció
 */