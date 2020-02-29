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
        String output = "";
        output = ("Estatística: \n"
                + "Quantidade de caracteres (incluindo espaço): " + countCharacters(input) + " \n"
                + "Quantidade de caracteres (sem considerar o espaço): " + countCharactersWithNoSpace(input) + "\n"
                + "Quantidade de palavras : " + countWords(input) + " \n"
                + "Quantidade de identificadores : \n"
                + "Quantidade de números (inteiros e reais): " + countNumbers(input) + "\n"
                + "Quantidade de operadores (relacionais e aritméticos): \n"
                + "Quantidade de linhas: " + countLines(input) + " \n"
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

            if ((Character.isLetter(characters[i]) || Character.isDigit(characters[i]) || characters[i] == '–') && i != endOfLine) {
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
                    if( (firstIndex == 0 || !Character.isLetter(s.charAt(firstIndex - 1)  ))  && (secondIndex == s.length() || !Character.isLetter(s.charAt(secondIndex)))){
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
