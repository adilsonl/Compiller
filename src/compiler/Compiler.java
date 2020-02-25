/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

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
        String output  = "";
        output = ("Estatística: \n"
                + "Quantidade de caracteres (incluindo espaço): "+ countCharacters(input) + " \n"
                + "Quantidade de caracteres (sem considerar o espaço): "+ countCharactersWithNoSpace(input) + "\n"
                + "Quantidade de palavras : "+ countWords(input) + " \n"
                + "Quantidade de identificadores : \n"
                + "Quantidade de números (inteiros e reais): \n"
                + "Quantidade de operadores (relacionais e aritméticos): \n"
                + "Quantidade de linhas: "+ countLines(input) + " \n"
                + "Índice Alfabético:");
        
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

            if ((Character.isLetter(characters[i]) || Character.isDigit(characters[i]) || characters[i] == '–' ) && i != endOfLine) {
                isWord = true;


            } else if (!(Character.isLetter(characters[i]) || Character.isDigit(characters[i]) || characters[i] == '–') && isWord) {
                wordCount++;
                isWord = false;


            } else if (Character.isLetter(characters[i]) && i == endOfLine) {
                wordCount++;
            }
        }

        return wordCount;
    }
        public static int countLines(String input) {
        int count = 0;

        //Counts each character except space    
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                count++;
            }

        }
        return count;
    }
    
}
