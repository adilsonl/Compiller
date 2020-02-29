/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.Collections;
import view.JF;

/**
 *
 * @author Adilson
 */
public class Compiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JF jf = new JF();
        jf.setVisible(true);
    }

    public static String Run(String input) {
        String output = "";
        output = ("Estatística: \n"
                + "Quantidade de caracteres (incluindo espaço): " + countCharacters(input) + " \n"
                + "Quantidade de caracteres (sem considerar o espaço): " + countCharactersWithNoSpace(input) + "\n"
                + "Quantidade de palavras : " + countWords(input) + " \n"
                + "Quantidade de identificadores: " + countIdentifiers(input) + " \n"
                + "Quantidade de números (inteiros e reais): " + countNumbers(input) + "\n"
                + "Quantidade de operadores (relacionais e aritméticos): " + countOperators(input) + "\n"
                + "Quantidade de linhas: " + countLines(input) + " \n"
                + "Índice Alfabético: \n"
                + printIdentifiers(input));

        
        return output;

    }

    public static int countCharactersWithNoSpace(String input) {
        int count = 0;

        //Counts each character except space    
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                count++;
            }

        }
        return count;
    }

    public static int countCharacters(String input) {
        int count = 0;

        //Counts each character except space    
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '\n') {
                count++;
            }

        }
        return count;
    }

    public static int countWords(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }

        int wordCount = 0;

        boolean isWord = false;
        int endOfLine = word.length() - 1;
        char[] characters = word.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            if ((Character.isLetter(characters[i]) || Character.isDigit(characters[i]) || characters[i] == '–') && i != endOfLine) {
                isWord = true;

            } else if (!(Character.isLetter(characters[i]) || Character.isDigit(characters[i]) || characters[i] == '–') && isWord) {
                wordCount++;
                isWord = false;

            } else if ((Character.isLetter(characters[i]) || Character.isDigit(characters[i]) || characters[i] == '–') && i == endOfLine) {
                wordCount++;
            }
        }

        return wordCount;
    }

    public static int countNumbers(String input) {
        int firstIndex = 0;
        int secondIndex = 0;
        int counter = 0;
        boolean isValid = false;

        String[] lines = input.split("\\n");

        for (String s : lines) {

            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    firstIndex = i;
                    while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                        i++;
                    }
                    secondIndex = i;
                    if ((firstIndex == 0 || !Character.isLetter(s.charAt(firstIndex - 1))) && (secondIndex == s.length() || !Character.isLetter(s.charAt(secondIndex)))) {
                        isValid = AutomatonDigits(s.substring(firstIndex, secondIndex));
                        if (isValid) {
                            counter++;
                        }
                    }

                }
            }

        }
        return counter;
    }

    public static boolean AutomatonDigits(String s) {
        char actualChar;
        boolean error;
        int actualIndex;
        int actualState;

        actualState = 0;
        actualIndex = 0;
        error = false;

        while (actualIndex < s.length() && !error) {
            actualChar = s.charAt(actualIndex);

            switch (actualState) {
                case 0:
                    if (Character.isDigit(actualChar)) {
                        actualState = 1;
                    } else {
                        error = true;
                    }
                    break;
                case 1:
                    if (Character.isDigit(actualChar)) {
                        actualState = 1;
                    } else if (actualChar == '.') {
                        actualState = 2;
                    } else {
                        error = true;
                    }
                    break;
                case 2:
                    if (Character.isDigit(actualChar)) {
                        actualState = 3;
                    } else {
                        error = true;
                    }
                    break;

                case 3:
                    if (Character.isDigit(actualChar)) {
                        actualState = 3;
                    } else {
                        error = true;
                    }
                    break;
            }
            actualIndex++;
            //System.out.println("Char: " + actualChar + " State: " + actualState);

            //System.out.println("Erro: " + error + " State: " + actualState);
        }
        if ((actualState == 1 || actualState == 3) && !error) {
            return true;

        }
        return false;

    }

    public static int countOperators(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*' || input.charAt(i) == '/' || input.charAt(i) == '+' || input.charAt(i) == '-') {
                count++;
            }
            if (input.charAt(i) == '=') {
                count++;
            }

            if (input.charAt(i) == '>') {
                if (i != (input.length() - 1) && input.charAt(i + 1) == '=') {
                    count++;
                    i++;

                } else {
                    count++;
                }

            }
            if (input.charAt(i) == '<') {
                if (i != (input.length() - 1) && input.charAt(i + 1) == '=') {
                    count++;
                    i++;

                } else if (i != (input.length() - 1) && input.charAt(i + 1) == '>') {
                    count++;
                    i++;

                } else {
                    count++;

                }

            }

        }

        return count;
    }

    //OLD WAY TO COUNT INDENTIFIERS
    /*public static int countIdentifiers(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int idCount = 0;

        boolean isIdentifier = false;
        int endOfLine = input.length() - 1;
        char[] characters = input.toCharArray();
        ArrayList<String> identifiers = new ArrayList<>();
        String newId;

        for (int i = 0; i < characters.length; i++) {

            if (Character.isLetter(characters[i]) && i != endOfLine) {
                isIdentifier = true;

            } else if (!(Character.isLetter(characters[i]) || Character.isDigit(characters[i])) && isIdentifier) {
                idCount++;
                isIdentifier = false;

            } else if ((Character.isLetter(characters[i])) && i == endOfLine) {
                idCount++;
            }

        }

        return idCount;
    }*/

    public static ArrayList<Identifier> createArrayIdentifiers(String input) {

        int firstIndex = 0;
        int secondIndex = 0;
        int counter = 0;
        boolean hasLine = false;
        boolean hasId = false;
        ArrayList<Identifier> identifiers = new ArrayList<>();
        Identifier newId;
        CountRepLine countRepLine;
        String[] lines = input.split("\\n");
        int line = 1;

        for (String s : lines) {

            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i))) {
                    firstIndex = i;
                    while (i < s.length() && (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)))) {
                        i++;
                    }
                    secondIndex = i;
                    newId = new Identifier();
                    newId.setIdentifier(s.substring(firstIndex, secondIndex));
                    for (Identifier id : identifiers) {
                        if (newId.getIdentifier().equalsIgnoreCase(id.getIdentifier())) {
                            hasId = true;
                            id.addTotalRepetitions();
                            for (CountRepLine cl : id.getRepLines()) {
                                if (cl.getLine() == line) {
                                    hasLine = true;
                                    cl.addRep(i);

                                }
                            }
                            if (!hasLine) {
                                countRepLine = new CountRepLine(1, line);
                                id.addRepLine(countRepLine);
                            }
                        }
                    }
                    if (!hasId) {
                        countRepLine = new CountRepLine(1, line);
                        newId.addRepLine(countRepLine);
                        identifiers.add(newId);
                        
                    }
                }
                hasId = false;
                hasLine = false;
            }
            line++;
        }
        IdentifierSorter identifierSort = new IdentifierSorter(identifiers);
        ArrayList<Identifier> sortedIdentifier = identifierSort.getIdentifierSorter();
        return sortedIdentifier;
      
  

    }
    
    public static int countIdentifiers(String input){
        ArrayList<Identifier> sortedIdentifier = createArrayIdentifiers(input);
        
        return sortedIdentifier.size();
    }
    
    public static String printIdentifiers(String input){
        ArrayList<Identifier> sortedIdentifier = createArrayIdentifiers(input);
        String output = "";
        
        char lastFirstLetter = sortedIdentifier.get(0).getIdentifier().toUpperCase().charAt(0);
        int index = 0;
        for (Identifier id : sortedIdentifier) {
              
            if((lastFirstLetter != id.getIdentifier().toUpperCase().charAt(0)) ||  index == 0 ){
                index = 1;
                output +=  " \n" + id.getIdentifier().toUpperCase().charAt(0) + "\n";
                
            }
              
             output += " \n" +id.getIdentifier().substring(0, 1).toUpperCase() + id.getIdentifier().substring(1) +
                    "  (" + id.getTotalRepetitions() + ") : " + id.printArrayLines();
             
             lastFirstLetter = id.getIdentifier().toUpperCase().charAt(0);
            /*if (id.getIdentifier().equalsIgnoreCase("n")) {
                
                String output = " ";
                System.out.println(id.getTotalRepetitions());
                for (CountRepLine cl : id.getRepLines()) {
                    output += " " + cl.getLine() + "(" + cl.getRep() + ")";
                }
                System.out.println(output);
            }*/

        }
        return output;
        
    }

    public static int countLines(String input) {
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                count++;
            }

        }
        return count;
    }

}
