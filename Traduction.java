import java.util.ArrayList;
public class Traduction extends Principal {
    /**
     * Écrit un String html à partir des symboles (unicodes)
     * donnés en paramètre. Au milieu du String, il y a appel
     * à la méthode ajuster et tableau pour changer l'ordre
     * des symboles et creer un tableau dynamiquement.
     * @param symboles
     * @param lignes
     * @param plusGrandeLigne
     * @return
     */
    public static String ecrire(ArrayList<String> symboles, ArrayList lignes, int plusGrandeLigne) {
        String unicode;
        unicode = symboles.toString();
        if(unicode.contains("[") || unicode.contains("]") || unicode.contains(",") ||
                unicode.contains("nouvelle ligne")){
            unicode = unicode.replace("[", "");
            unicode = unicode.replace("]", "");
            unicode = unicode.replace(",", "");
            unicode = unicode.replace("nouvelle ligne", "");
        }
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>TP 2</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<hr>\n" +
                "<table>\n";
        symboles = ajuster(symboles, lignes, plusGrandeLigne, unicode);
        String html2 = tableau(symboles, lignes, plusGrandeLigne, unicode);
        String html3=        "</table>\n" +
                "<hr>\n" +
                "</body>\n" +
                "</html>";
        html = html + html2 + html3;
        return html;
    }
    /**
     * Ajuster l'ordre des symboles pour l'affichage.
     * @param symboles
     * @param lignes
     * @param plusGrandeLigne
     * @param unicode
     * @return
     */
    private static ArrayList ajuster (ArrayList symboles, ArrayList lignes,
                                      int plusGrandeLigne, String unicode) {
        symboles.ensureCapacity(symboles.size()*2);
        for (int i=0; i<lignes.size(); i++) {
            while ((int) lignes.get(i) < plusGrandeLigne) {
                symboles.add(i*plusGrandeLigne + (int) lignes.get(i)+i,
                        "&nbsp&nbsp&nbsp");
                //&nbsp sert pour alligner
                lignes.set(i, (int) lignes.get(i)+1);
            }
        }
        System.out.println(symboles);
        return symboles;
    }
    /**
     * Créer un tableau dynamiquement qui va contenir les unicodes
     * qui correspondent aux bonnes positions.
     * @param symboles
     * @param lignes
     * @param plusGrandeLigne
     * @param unicode
     * @return
     */
    private static String tableau(ArrayList<String> symboles, ArrayList lignes,
                                  int plusGrandeLigne, String unicode){
        String html2 = null;
        symboles.ensureCapacity(symboles.size()*2);
        String htmlz = "";
        for(int k = 0; k < plusGrandeLigne; ++k){
            htmlz = htmlz+"<tr>";// k = nbr de syllabes par ligne en ordre 1-2-3-4
            for (int j = (int) lignes.size()-1; j >= 0; j--) {
                unicode = (String) symboles.get(j * plusGrandeLigne + k+j);
                System.out.println(unicode);
                htmlz = htmlz+unicode;// j = index de la phrase 4-3-2-1
                if(htmlz.contains("nouvelle ligne")){
                    htmlz = htmlz.replace("nouvelle ligne", "");
                }
            }
            htmlz = htmlz+"</tr>"+"<br>\n";
        }
        html2 = htmlz;
        return html2;
    }
}