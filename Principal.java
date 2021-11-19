/**
 * @authors Stefan Voicu, Loqmane Kerdid
 * Code permanent : VOIS04079600 KERL07129513
 * Courriels : voicu.stefan@courrier.uqam.ca kerdid.loqmane@courrier.uqam.ca
 * Cours : INF2120-30
 * @version 18/11/2021
 */
import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;
public class Principal {
    /**
     * Lit le fichier. L'utilisateur est sollicite a entrer
     * le nom du fichier en question "syllabes.txt". S'il y a une erreure d'entree de la part
     * de l'utilisateur, le programme s'arrete. Lorsque le nom du fichier est entre
     * le programme est redirige vers la methode separerSyllabes.
     */
    public static void lecture() {
        System.out.println("Entrez le nom du fichier : \n");
        Scanner sc = new Scanner(System.in);
        String syllabes = sc.nextLine();
        ArrayList lignesOccidentaux = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(syllabes))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                lignesOccidentaux.add(sCurrentLine);
                System.out.println(sCurrentLine);
            }
            separerSyllabes(lignesOccidentaux);
            //nous lance vers notre methode separerSyllabes
        } catch (IOException | InvalidPathException e) {
            e.printStackTrace();
            System.out.println("Mauvaise entree");
            System.exit(-1);
        }
    }
    /**
     * Separer les syllabes dans le texte données en entrée ligne par ligne.
     * Les syllabes et un ArrayList contenant le nombre de syllabes par ligne
     * sera envoyé à la méthode définir grandeur.
     * @param lignesOccidentaux
     * @return
     */
    public static ArrayList<String> separerSyllabes(ArrayList lignesOccidentaux){
        ArrayList<String> syllabes = new ArrayList<>();
        ArrayList<Integer> lignes = new ArrayList();
        //nombre de syllabes par ligne
        String phrase;
        String consonne = "";
        int nbrSyllabesLigne = 0;
        //iterateur pour nombre de syllabes par ligne
        phrase = lignesOccidentaux.toString();
        if(phrase.contains("[") || phrase.contains("]") || phrase.contains(",")){
            phrase = phrase.replace("[", "");
            phrase = phrase.replace("]", "");
        }
        for(int i = 0; i < phrase.length(); ++i){
            if(phrase.charAt(i) == ' '){
                ++i;
            } else if((phrase.charAt(i) + " ").equals(", ")){
                syllabes.add(" ");
                ++i;
                lignes.add(nbrSyllabesLigne);
                nbrSyllabesLigne = 0;
            }
            //  k, K, n, N, h, H, m, M, r, R, g, G, b, B, p, P
            consonne = consonne + phrase.charAt(i);
            consonne = consonne.replace(" ", "");
            if(consonne.contains("a") || consonne.contains("e") || consonne.contains("i")
                    || consonne.contains("o") || consonne.contains("u") || consonne.contains("A")
                    || consonne.contains("E") || consonne.contains("I") || consonne.contains("O")
                    || consonne.contains("U")){
                if(!consonne.contains("kya") && !consonne.contains("nya") && !consonne.contains("hya")
                        && !consonne.contains("mya") && !consonne.contains("rya")
                        && !consonne.contains("gya") && !consonne.contains("bya")
                        && !consonne.contains("pya") && !consonne.contains("n'") ){
                    syllabes.add(consonne);
                    ++nbrSyllabesLigne;
                } else {
                    consonne = Character.toString(consonne.charAt(0));
                    syllabes.add(consonne + "i");
                    syllabes.add("ya");
                    nbrSyllabesLigne+=2;
                }
                consonne = "";
            }
        }
          definirGrandeur(syllabes, lignes);
        return syllabes;
    }
    /**
     * Trouver la ligne avec le plus de syllabes.
     * Envoyer les syllabes et les lignes à la méthode
     * transformerUnicodes.
     * @param syllabes
     * @param lignes
     */
    public static void definirGrandeur(ArrayList syllabes, ArrayList lignes){
        int plusGrandeLigne = 0;
        int ligneTemporaire;
        for(int i = 0; i < lignes.size(); ++i){
            ligneTemporaire = (int) lignes.get(i);
            if(ligneTemporaire > plusGrandeLigne){
                plusGrandeLigne = ligneTemporaire;
            }
        }
        System.out.println(plusGrandeLigne);
        transformerUnicodes(syllabes, lignes, plusGrandeLigne);
        //nous lance vers notre methode transformerUnicodes
    }
    /**
     * Prend en paramètre les syllabes et les changent en symboles
     * qui contiendront les unicodes qui correspondent.
     * Les symboles, les lignes et la plusGrandeLigne sont envoyés à
     * la méthode creerHtml.
     * @param syllabes
     * @param lignes
     * @param plusGrandeLigne
     */
    public static void transformerUnicodes(ArrayList syllabes, ArrayList lignes, int plusGrandeLigne){
        ArrayList<String> hiragana = new ArrayList();
        ArrayList<String> katagana = new ArrayList();
        ArrayList<String> unicodesHiragana = new ArrayList();
        ArrayList<String> unicodesKatagana = new ArrayList();
        String unicode = null;
        String chePo;
        int index;
        ArrayList<String> symboles = new ArrayList<>();
        char a;
        for(int z = 0; z < syllabes.size(); ++z){
            chePo = syllabes.get(z).toString();
            a = chePo.charAt(0);
            if(Character.isUpperCase(a)){
                chePo = chePo.toLowerCase();
                Character.toUpperCase(chePo.charAt(0));
                syllabes.set(z, chePo);
            } else {
                chePo = chePo.toLowerCase();
                syllabes.set(z, chePo);
            }
        }
        hiragana.add(0, "a"); hiragana.add(1, "i");
        hiragana.add(2, "u"); hiragana.add(3, "e");
        hiragana.add(4, "o"); hiragana.add(5, "ka");
        hiragana.add(6, "ki"); hiragana.add(7, "ku");
        hiragana.add(8, "ke"); hiragana.add(9, "ko");
        hiragana.add(10, "sa"); hiragana.add(11, "shi");
        hiragana.add(12, "su"); hiragana.add(13, "se");
        hiragana.add(14, "so"); hiragana.add(15, "ta");
        hiragana.add(16, "chi"); hiragana.add(17, "tsu");
        hiragana.add(18, "te"); hiragana.add(19, "to");
        hiragana.add(20, "na"); hiragana.add(21, "ni");
        hiragana.add(22, "nu"); hiragana.add(23, "ne");
        hiragana.add(24, "no"); hiragana.add(25, "ha");
        hiragana.add(26, "hi"); hiragana.add(27, "fu");
        hiragana.add(28, "he"); hiragana.add(29, "ho");
        hiragana.add(30, "ma"); hiragana.add(31, "mi");
        hiragana.add(32, "mu"); hiragana.add(33, "me");
        hiragana.add(34, "mo"); hiragana.add(35, "ya");
        hiragana.add(36, "yu"); hiragana.add(37, "yo");
        hiragana.add(38, "ra"); hiragana.add(39, "ri");
        hiragana.add(40, "ru"); hiragana.add(41, "re");
        hiragana.add(42, "ro"); hiragana.add(43, "wa");
        hiragana.add(44, "wi"); hiragana.add(45, "we");
        hiragana.add(46, "wo"); hiragana.add(47, "ga");
        hiragana.add(48, "gi"); hiragana.add(49, "gu");
        hiragana.add(50, "ge"); hiragana.add(51, "go");
        hiragana.add(52, "za"); hiragana.add(53, "ji");
        hiragana.add(54, "zu"); hiragana.add(55, "ze");
        hiragana.add(56, "zo"); hiragana.add(57, "da");
        hiragana.add(58, "dji"); hiragana.add(59, "dzu");
        hiragana.add(60, "de"); hiragana.add(61, "do");
        hiragana.add(62, "ba"); hiragana.add(63, "bi");
        hiragana.add(64, "bu"); hiragana.add(65, "be");
        hiragana.add(66, "bo"); hiragana.add(67, "pa");
        hiragana.add(68, "pi"); hiragana.add(69, "pu");
        hiragana.add(70, "pe"); hiragana.add(71, "po");
        hiragana.add(72, "n'");
        //nouveau tableau
        katagana.add(0, "A"); katagana.add(1, "I");
        katagana.add(2, "U"); katagana.add(3, "E");
        katagana.add(4, "O"); katagana.add(5, "Ka");
        katagana.add(6, "Ki"); katagana.add(7, "Ku");
        katagana.add(8, "Ke"); katagana.add(9, "Ko");
        katagana.add(10, "Sa"); katagana.add(11, "Shi");
        katagana.add(12, "Su"); katagana.add(13, "Se");
        katagana.add(14, "So"); katagana.add(15, "Ta");
        katagana.add(16, "Chi"); katagana.add(17, "Tsu");
        katagana.add(18, "Te"); katagana.add(19, "To");
        katagana.add(20, "Na"); katagana.add(21, "Ni");
        katagana.add(22, "Nu"); katagana.add(23, "Ne");
        katagana.add(24, "No"); katagana.add(25, "Ha");
        katagana.add(26, "Hi"); katagana.add(27, "Fu");
        katagana.add(28, "He"); katagana.add(29, "Ho");
        katagana.add(30, "Ma"); katagana.add(31, "Mi");
        katagana.add(32, "Mu"); katagana.add(33, "Me");
        katagana.add(34, "Mo"); katagana.add(35, "Ya");
        katagana.add(36, "Yu"); katagana.add(37, "Yo");
        katagana.add(38, "Ra"); katagana.add(39, "Ri");
        katagana.add(40, "Ru"); katagana.add(41, "Re");
        katagana.add(42, "Ro"); katagana.add(43, "Wa");
        katagana.add(44, "Wi"); katagana.add(45, "We");
        katagana.add(46, "Wo"); katagana.add(47, "Ga");
        katagana.add(48, "Gi"); katagana.add(49, "Gu");
        katagana.add(50, "Ge"); katagana.add(51, "Go");
        katagana.add(52, "Za"); katagana.add(53, "Ji");
        katagana.add(54, "Zu"); katagana.add(55, "Ze");
        katagana.add(56, "Zo"); katagana.add(57, "Da");
        katagana.add(58, "Dji"); katagana.add(59, "Dzu");
        katagana.add(60, "De"); katagana.add(61, "Do");
        katagana.add(62, "Ba"); katagana.add(63, "Bi");
        katagana.add(64, "Bu"); katagana.add(65, "Be");
        katagana.add(66, "Bo"); katagana.add(67, "Pa");
        katagana.add(68, "Pi"); katagana.add(69, "Pu");
        katagana.add(70, "Pe"); katagana.add(71, "Po");
        katagana.add(72, "N'");
        //nouveau tableau
        unicodesHiragana.add(0, "&#12354"); unicodesHiragana.add(1, "&#12356");
        unicodesHiragana.add(2, "&#12358"); unicodesHiragana.add(3, "&#12360");
        unicodesHiragana.add(4, "&#12362"); unicodesHiragana.add(5, "&#12363");
        unicodesHiragana.add(6, "&#12365"); unicodesHiragana.add(7, "&#12367");
        unicodesHiragana.add(8, "&#12369"); unicodesHiragana.add(9, "&#12371");
        unicodesHiragana.add(10, "&#12373"); unicodesHiragana.add(11, "&#12375");
        unicodesHiragana.add(12, "&#12377"); unicodesHiragana.add(13, "&#12379");
        unicodesHiragana.add(14, "&#12381"); unicodesHiragana.add(15, "&#12383");
        unicodesHiragana.add(16, "&#12385"); unicodesHiragana.add(17, "&#12388");
        unicodesHiragana.add(18, "&#12390"); unicodesHiragana.add(19, "&#12392");
        unicodesHiragana.add(20, "&#12394"); unicodesHiragana.add(21, "&#12395");
        unicodesHiragana.add(22, "&#12396"); unicodesHiragana.add(23, "&#12397");
        unicodesHiragana.add(24, "&#12398"); unicodesHiragana.add(25, "&#12399");
        unicodesHiragana.add(26, "&#12402"); unicodesHiragana.add(27, "&#12405");
        unicodesHiragana.add(28, "&#12408"); unicodesHiragana.add(29, "&#12411");
        unicodesHiragana.add(30, "&#12414"); unicodesHiragana.add(31, "&#12415");
        unicodesHiragana.add(32, "&#12416"); unicodesHiragana.add(33, "&#12417");
        unicodesHiragana.add(34, "&#12418"); unicodesHiragana.add(35, "&#12420");
        unicodesHiragana.add(36, "&#12422"); unicodesHiragana.add(37, "&#12424");
        unicodesHiragana.add(38, "&#12425"); unicodesHiragana.add(39, "&#12426");
        unicodesHiragana.add(40, "&#12427"); unicodesHiragana.add(41, "&#12428");
        unicodesHiragana.add(42, "&#12429"); unicodesHiragana.add(43, "&#12431");
        unicodesHiragana.add(44, "&#12432"); unicodesHiragana.add(45, "&#12433");
        unicodesHiragana.add(46, "&#12434"); unicodesHiragana.add(47, "&#12364");
        unicodesHiragana.add(48, "&#12366"); unicodesHiragana.add(49, "&#12368");
        unicodesHiragana.add(50, "&#12370"); unicodesHiragana.add(51, "&#12372");
        unicodesHiragana.add(52, "&#12374"); unicodesHiragana.add(53, "&#12376");
        unicodesHiragana.add(54, "&#12378"); unicodesHiragana.add(55, "&#12380");
        unicodesHiragana.add(56, "&#12382"); unicodesHiragana.add(57, "&#12384");
        unicodesHiragana.add(58, "&#12386"); unicodesHiragana.add(59, "&#12389");
        unicodesHiragana.add(60, "&#12391"); unicodesHiragana.add(61, "&#12393");
        unicodesHiragana.add(62, "&#12400"); unicodesHiragana.add(63, "&#12403");
        unicodesHiragana.add(64, "&#12406"); unicodesHiragana.add(65, "&#12409");
        unicodesHiragana.add(66, "&#12412"); unicodesHiragana.add(67, "&#12401");
        unicodesHiragana.add(68, "&#12404"); unicodesHiragana.add(69, "&#12407");
        unicodesHiragana.add(70, "&#12410"); unicodesHiragana.add(71, "&#12413");
        unicodesHiragana.add(72, "&#12435");
        //nouveau tableau
        unicodesKatagana.add(0, "&#12450"); unicodesKatagana.add(1, "&#12452");
        unicodesKatagana.add(2, "&#12454"); unicodesKatagana.add(3, "&#12456");
        unicodesKatagana.add(4, "&#12458"); unicodesKatagana.add(5, "&#12459");
        unicodesKatagana.add(6, "&#12461"); unicodesKatagana.add(7, "&#12463");
        unicodesKatagana.add(8, "&#12465"); unicodesKatagana.add(9, "&#12467");
        unicodesKatagana.add(10, "&#12469"); unicodesKatagana.add(11, "&#12471");
        unicodesKatagana.add(12, "&#12473"); unicodesKatagana.add(13, "&#12475");
        unicodesKatagana.add(14, "&#12477"); unicodesKatagana.add(15, "&#12479");
        unicodesKatagana.add(16, "&#12481"); unicodesKatagana.add(17, "&#12484");
        unicodesKatagana.add(18, "&#12486"); unicodesKatagana.add(19, "&#12488");
        unicodesKatagana.add(20, "&#12490"); unicodesKatagana.add(21, "&#12491");
        unicodesKatagana.add(22, "&#12492"); unicodesKatagana.add(23, "&#12493");
        unicodesKatagana.add(24, "&#12494"); unicodesKatagana.add(25, "&#12495");
        unicodesKatagana.add(26, "&#12498"); unicodesKatagana.add(27, "&#12501");
        unicodesKatagana.add(28, "&#12504"); unicodesKatagana.add(29, "&#12507");
        unicodesKatagana.add(30, "&#12510"); unicodesKatagana.add(31, "&#12511");
        unicodesKatagana.add(32, "&#12512"); unicodesKatagana.add(33, "&#12513");
        unicodesKatagana.add(34, "&#12514"); unicodesKatagana.add(35, "&#12516");
        unicodesKatagana.add(36, "&#12518"); unicodesKatagana.add(37, "&#12520");
        unicodesKatagana.add(38, "&#12521"); unicodesKatagana.add(39, "&#12522");
        unicodesKatagana.add(40, "&#12523"); unicodesKatagana.add(41, "&#12524");
        unicodesKatagana.add(42, "&#12525"); unicodesKatagana.add(43, "&#12527");
        unicodesKatagana.add(44, "&#12528"); unicodesKatagana.add(45, "&#12529");
        unicodesKatagana.add(46, "&#12530"); unicodesKatagana.add(47, "&#12460");
        unicodesKatagana.add(48, "&#12462"); unicodesKatagana.add(49, "&#12464");
        unicodesKatagana.add(50, "&#12466"); unicodesKatagana.add(51, "&#12468");
        unicodesKatagana.add(52, "&#12470"); unicodesKatagana.add(53, "&#12472");
        unicodesKatagana.add(54, "&#12474"); unicodesKatagana.add(55, "&#12476");
        unicodesKatagana.add(56, "&#12478"); unicodesKatagana.add(57, "&#12480");
        unicodesKatagana.add(58, "&#12482"); unicodesKatagana.add(59, "&#12485");
        unicodesKatagana.add(60, "&#12487"); unicodesKatagana.add(61, "&#12489");
        unicodesKatagana.add(62, "&#12496"); unicodesKatagana.add(63, "&#12499");
        unicodesKatagana.add(64, "&#12502"); unicodesKatagana.add(65, "&#12505");
        unicodesKatagana.add(66, "&#12508"); unicodesKatagana.add(67, "&#12497");
        unicodesKatagana.add(68, "&#12500"); unicodesKatagana.add(69, "&#12503");
        unicodesKatagana.add(70, "&#12506"); unicodesKatagana.add(71, "&#12509");
        unicodesKatagana.add(72, "&#12531");
        for(int i = 0; i < syllabes.size(); ++i){
            if(hiragana.contains(syllabes.get(i)) ){
                index = hiragana.indexOf(syllabes.get(i));
                unicode = unicodesHiragana.get(index);
                symboles.add(unicode);
            } else if (katagana.contains(syllabes.get(i))){
                index = katagana.indexOf(syllabes.get(i));
                unicode = unicodesKatagana.get(index);
                symboles.add(unicode);
            } else if(syllabes.get(i).equals(" ")){
                unicode = "";
                symboles.add(unicode);
            }
            else {
                System.out.println("Mauvaise entree. Roomaji est invalide.");
                System.exit(1);
            }
        }
        creerHtml(symboles, lignes, plusGrandeLigne);
        //nous lance vers notre methode creerHtml
    }
    /**
     * À travers le méthode bw.write() il y a appel à la classe Traduction
     * qui va créer un String html. Un nouveau fichier nommé traduction.html
     * sera écrit dans un répertoire choisi dans le disque dur qui contiendra
     * ce Sting html.
     * @param symboles
     * @param lignes
     * @param plusGrandeLigne
     */
    public static void creerHtml(ArrayList<String> symboles, ArrayList lignes, int plusGrandeLigne){
       // String html = "<div><h1>Helllo</h1></div>";
        File traduction = new File("C:\\Users\\stefa\\Desktop\\INF2120-programmation2\\BRUNO\\TP\\traduction.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(traduction));
            bw.write(Traduction.ecrire(symboles, lignes, plusGrandeLigne));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        lecture();
    }
}