
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import java.util.Scanner;

/*
PER FER FUNCIONAR EL PROGRAMA PRIMER HEM D'EXECUTAR EL SERVIDOR DE BASEX. PER FER-HO HEM D'INTRODUIR AL TERMINAL
LA SEGÜENT COMANDA :
            java -cp '/ruta/absoluta/cap/al/jar/de/Basex/q/previament/ens/hem/baixat/BaseX861.jar' org.basex.BaseXServer
 */


public class Main {

    private static final String mondialSrc ="doc(\""+System.getProperty("user.dir")+"/mondial.xml\")";
    private static final String factbookSrc ="doc(\""+System.getProperty("user.dir")+"/Factbook.xml\")";
    private static Scanner entrada = new Scanner(System.in);
    private static int opcion = 0;

    public static void main(String[] args) throws XQException {

        XQPreparedExpression expr = null;

        do{
            Main.Menu();

            try {
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion){

                case -1:
                    System.out.println("Per escollir una de les opcións has d'introduir el seu número.");
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 1:
                    try {
                        System.out.println(XQueryDAO.getFactBookPaisos(factbookSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 2:
                    try {
                        System.out.println(XQueryDAO.getNumPaisos(mondialSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 3:
                    try {
                        System.out.println(XQueryDAO.getInfoAlemanya(mondialSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 4:
                    try {
                        System.out.println(XQueryDAO.getLastCensUganda(mondialSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 5:
                    try {
                        System.out.println(XQueryDAO.getPeruCities(mondialSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 6:
                    try {
                        System.out.println(XQueryDAO.getShanghaiPopulation(mondialSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                case 7:
                    try {
                        System.out.println(XQueryDAO.getMatriculaChipre(mondialSrc));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prem intro per tornar al menú");
                    entrada.nextLine();
                    break;

                default:
                    opcion = 0;
                    System.out.println("ADEU!!!");
                    break;

            }

        }while(opcion != 0);


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


}
