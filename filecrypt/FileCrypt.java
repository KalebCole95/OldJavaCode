// Kaleb Cole
// File Encryption - Project 4
// This program will take an input from a text document and an encryption key from another
// and it will encrypt the text file to another new file.
package filecrypt;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileCrypt {

    public static void main(String[] args) {
        String const1 = "0123456789\n123456789212345678931 !\"#$%&'()*+'-./0123456789:;<=>?@";
        String const2 = "[\\]^_`";
        String key = null, keyPart1 = null, keyPart2 = null, keyPart3 = null, inFile, outFile, keyFile, inText = null, outText = null, tempLine = null;
        Scanner input = new Scanner(System.in);
        BufferedReader Fin;
        BufferedWriter Fout;

        System.out.print("Key File : ");    //Start of the process for the key file
        keyFile = input.next();

        try {
            Fin = new BufferedReader(new FileReader(new File(keyFile)));
            keyPart1 = Fin.readLine();                                      //These are the inputs for the first three lines of the key file
            keyPart2 = Fin.readLine();
            keyPart3 = Fin.readLine();
            if (keyPart1 == null || keyPart2 == null || keyPart3 != null) {                         //This checks to make sure there are only 2 lines,
                System.out.println("The key file does not have the correct number of lines!");      //each with exactly 26 characters
                System.exit(0);
            } else {
                if (keyPart1.length() != 26 || keyPart2.length() != 26) {
                    System.out.println("The key file does not have the correct line length!");
                    System.exit(0);
                } else {
                    System.out.println("Successful Key File");
                }
            }
        } catch (FileNotFoundException e) {                                 //This catch is for a bad file name
            System.out.println(" ! " + keyFile + " does not Exist ! ");
            System.exit(0);
        } catch (Exception e) {                                             //This catch is for a unknown error
            System.out.println(" ! Something Went Wrong ! ");
            System.exit(0);
        }
        key = const1 + keyPart1 + const2 + keyPart2;

        System.out.print("Text File : ");       //Start of the process for the text file
        inFile = input.next();

        try {
            Fin = new BufferedReader(new FileReader(new File(inFile)));
            while ((tempLine = Fin.readLine()) != null) {           //This is where the text file is converted to one long string
                if (inText == null) {                               //it does several checks to remove nulls from being added to the string
                    if (tempLine != null) {
                        inText = tempLine;
                    }
                } else {
                    if (tempLine != null) {
                        inText = inText + " " + tempLine;
                    }
                }
            }
            System.out.println("Successful Text File");
        } catch (FileNotFoundException e) {                             //This catch is for a bad file name
            System.out.println(" ! " + inFile + " does not Exist ! ");
            System.exit(0);
        } catch (Exception e) {                                         //This catch is for a unknown error
            System.out.println(" ! Something Went Wrong ! ");
            System.exit(0);
        }

        if (inText != null) {                                           //This is where the text is ciphered
            for (int c = 0; c < inText.length(); c++) {
                if (outText == null) {                                  //it does several checks to remove nulls from being added to the string
                    outText = Character.toString(key.charAt(inText.charAt(c)));
                } else {
                    outText = outText + key.charAt(inText.charAt(c));
                }
            }
        }

        System.out.print("Output File : ");     //Start of the process for the outfile
        outFile = input.next();

        try {
            File file = new File(outFile);
            Fout = new BufferedWriter(new FileWriter(file));
            Fout.write(outText);
            Fout.close();
            System.out.println("Successful Output File");
        } catch (IOException e) {       //This is for when the output file cannot be created
            System.out.println(" ! Could not create the Output File ! ");
            System.exit(0);
        } catch (Exception e) {         //This is a catch for the unknown errors
            System.out.println(" ! Something Went Wrong ! ");
            System.exit(0);
        }
    }
}
