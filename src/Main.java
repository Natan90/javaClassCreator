package src;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String[] typesTab = {"int", "boolean", "String", "double", "float", "char", "byte", "short", "long", "Object"};

        System.out.println("Bienvenue dans le programme de création de classes Java");

        Scanner sc = new Scanner(System.in);

        System.out.println("Saisir le nom de la nouvelle Java");
        String nomFichier = sc.nextLine();
        Path p = Paths.get("./out/"+nomFichier+".java");



        String param = "";
        ArrayList<String> typeParam = new ArrayList<>();
        ArrayList<String> nomParam = new ArrayList<>();

        System.out.println("Entrer un paramètre du constructeur de la classe " + nomFichier + " <type> <nom_param>");
        System.out.println("Appuyer sur Entrée sans rien écrire pour terminer.");

        while (true) {
            System.out.print("> ");
            String entreeParam = sc.nextLine().trim();

            if (entreeParam.isEmpty())
                break;

            String type = entreeParam.split(" ")[0];
            if( Arrays.stream(typesTab).anyMatch(e -> e.equals(type))){
                typeParam.add(type);
                nomParam.add(entreeParam.split(" ")[1]);

            }else{
                System.out.println("Type [ " + type+ " ] non valide, veuillez entrer un type valide.");
            }

        }

        String s =headerConstructor(nomFichier, nomParam, typeParam).toString() + classConstructor(nomFichier, nomParam, typeParam);






        byte data[] = s.getBytes();


        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }


    }

    public static StringBuilder headerConstructor(String nomFichier, ArrayList nomParam, ArrayList typeParam){

        StringBuilder headConstructor = new StringBuilder();
        headConstructor.append("import java.io.*;\n").append("import java.util.*;\n\n");

        headConstructor.append("public class ").append(nomFichier).append(" {\n\n");

        for(int i =0; i<nomParam.size(); i++){
            headConstructor.append("\t").append(typeParam.get(i)).append(" ").append(nomParam.get(i)).append(";\n");
        }
        headConstructor.append("\n");

        return headConstructor;
    }

    public static StringBuilder classConstructor(String nomFichier, ArrayList nomParam, ArrayList typeParam) {
        StringBuilder classConstructor = new StringBuilder();

        classConstructor.append("\tpublic ").append(nomFichier).append("(){\n}\n");


        classConstructor.append("\tpublic ").append(nomFichier).append("(");

        for (int i = 0; i < nomParam.size() - 1; i++) {
            classConstructor.append(typeParam.get(i)).append(" ").append(nomParam.get(i)).append(", ");
        }
        classConstructor.append(typeParam.getLast()).append(" ").append(nomParam.getLast());

        classConstructor.append("){\n");

        for (int i =0; i<nomParam.size(); i++){
            classConstructor.append("\t\tthis.").append(nomParam.get(i)).append(" = ").append(nomParam.get(i)).append(";\n");
        }
        classConstructor.append("\t}");

        return classConstructor;

    }
}