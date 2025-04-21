import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println("Bienvenue dans le programme de création de classes Java");

        Scanner sc = new Scanner(System.in);

        System.out.println("Saisir le nom de la nouvelle Java");
        String nomFichier = sc.nextLine();
        Path p = Paths.get("./"+nomFichier+".java");

        String imports = "import java.io.*;\n" +
                "import java.util.*;\n";

        String entreeParam = "Vide";
        String param = "";
        System.out.println("Entrer un parametre du constructeur de la classe "+ nomFichier + " <type> <nom_param>");
        System.out.println("Entrer sur espace pour ne rentrer aucun parametre");

        ArrayList<String> listParam = new ArrayList<>();
        while (true) {
            entreeParam = sc.nextLine();
            if (entreeParam.isEmpty()) {
                break;
            }
            if (!param.isEmpty()) {
                param += ", ";
            }
            listParam.add(sc.next());
            listParam.add(sc.next());
            param += entreeParam;
        }


        String s = "public class "+ nomFichier + " {\n" +
                "   public "+ nomFichier+ "("+ param +"){\n";

        if(param != ""){
            for(int i=0; i<listParam.size(); i+=2){
                s += "this."+listParam.get(i+1)+ ";\n";
            }
            s+= "}\n";
        }



        byte data[] = s.getBytes();


        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }


    }
}