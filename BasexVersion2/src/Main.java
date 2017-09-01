import org.basex.api.client.ClientSession;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final static String factbook = System.getProperty("user.dir")+"/Factbook.xml";
    private final static String mondial = System.getProperty("user.dir")+"/mondial.xml";
    private static Scanner entrada;
    private static int opcion = 0;

    public static void main(String[] args) {

        entrada = new Scanner(System.in);
        ClientSession session = null;

        try {
            XmlDB.startBasexServer();
            session = XmlDB.initDefaultClient();
            XmlDB.openDB(session,"Factbook",factbook);
            //XmlDB.openDB(session,"mondial",mondial);
        } catch (IOException e) {
            e.printStackTrace();
        }

        do{
            Menu();
            try {
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion){

                case -1:
                    System.out.println("Per escollir una de les opcións has d'introduir el seu número.");
                    pausa(entrada);
                    break;

                case 1:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "db:open-pre(\"factbook\",0)/*:factbook/*:record/*:country/text()"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "count(data(db:open-pre(\"mondial\",0)/*:mondial/*:country/*:name))"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "db:text(\"mondial\", \"Germany\")/parent::*:name/parent::*:country"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "for $i_0 in db:text(\"factbook\", \"Uganda\")/parent::*:country/parent::*:record" +
                                " return substring-before(data($i_0/*/*:population), \"&#xA;\")"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "for $x in db:text(\"factbook\", \"Peru\")/parent::*:country/parent::*:record" +
                                " return data($x/*/capital)"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "for $x in root()/*:mondial/*:country[(name = \"China\")] " +
                                        "return util:last-from(data($x/*/city[(name=\"Shanghai\")]/population))"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    try {
                        System.out.println(XmlDB.executeQuery(session,
                                "data(db:text(\"mondial\", \"Cyprus\")/parent::*:name/parent::*:country/@car_code)"));
                        pausa(entrada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    opcion = 0;
                    try {
                        XmlDB.stopBasexServer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ADEU!!!");
                    break;


            }
        }while (opcion!=0);

    }

    public static void Menu(){
        System.out.println("\t\t\tMENÚ");
        System.out.println("1. Quins països hi ha en el fitxer «factbook.xml»?");
        System.out.println("2. Quants països hi ha? (mondial)");
        System.out.println("3. Quina és la informació sobre Alemanya ? (mondial)");
        System.out.println("4. Quanta gent viu a Uganda segons aquest fitxer? (mondial)");
        System.out.println("5. Quines són les ciutats de Perú que recull aquest fitxer? (mondial)");
        System.out.println("6. Quanta gent viu a Shanghai? (mondial)");
        System.out.println("7. Quin és el codi de matricula de cotxe de Xipre? (mondial)");
        System.out.println("8. Prem qualsevol altra número per sortir del programa.");
        System.out.println("\n\tOpció escollida: ");
    }

    static void pausa(Scanner entrada){
        System.out.println("\n\tPrem intro per tornar al menú ...");
        entrada.nextLine();
    }
}
