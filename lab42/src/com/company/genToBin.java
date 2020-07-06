package com.company;
//zad 4.2
import java.io.*;
import java.util.*;
//import IOException;
import java.text.NumberFormat;
import org.junit.Assert;

import static java.lang.System.*;

public class genToBin {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(in);
        out.println("Podaj rozmiar probki: \n");
        String dimString = scan.nextLine();

        int dim = 0;
        try
        {
            dim = Integer.parseInt(dimString);
        }
        catch (NumberFormatException e)
        {
            out.println("NumberFormatException: " + e.getMessage());
        }
        catch (StackOverflowError e){
            out.println("wyszedleś poza zakres !");
        }


        out.println("Podaj mi: \n");
        String miString = scan.nextLine();
        double mi = 0;

        try {
            mi = Double.parseDouble(miString);
        }
        catch (Exception e) {
            out.println("NumberFormatException: " + e.getMessage());
        }
        catch (StackOverflowError e){
            out.println("wyszedleś poza zakres !");
        }

        out.println("Podaj sigma: \n");
        String sigmaString = scan.nextLine();
        double sigma=0;
        try {
            sigma = Double.parseDouble(sigmaString);
        }
        catch (Exception e) {
            out.println("NumberFormatException: " + e.getMessage());
        }
        catch (StackOverflowError e){
            out.println("wyszedleś poza zakres !");
        }

        scan.close();
        Assert.assertTrue(sigma > 0);

        Random generator = new Random();
        double gaussNumber;
        
        DataOutputStream GaussWejscie;
        GaussWejscie = null;

        try {
            GaussWejscie  = new DataOutputStream(new FileOutputStream("numbers.bin"));
        } catch (FileNotFoundException e) {
            out.println("Nie znaleziono takiego pliku ! \n");
        }

        int i=0;
        while( i < dim ){
            gaussNumber = mi + generator.nextGaussian() * sigma;
            assert GaussWejscie != null;
            GaussWejscie.writeDouble(gaussNumber);
        i++;
        }

        assert GaussWejscie != null;
        GaussWejscie.close();

        NumberFormat nF = NumberFormat.getInstance(new Locale("pl", "PL"));

        nF.setMinimumFractionDigits(4);
        nF.setMaximumFractionDigits(8);

        DataInputStream GaussWyjscie;
        GaussWyjscie =null;

        try {
            GaussWyjscie = new DataInputStream(new FileInputStream("numbers.bin"));
        } catch (FileNotFoundException e) {
            out.println("Nie znaleziono takiego pliku ! \n");
        }


        FileWriter fileTxt = new FileWriter("numbers.txt");

        i=0;
        while(i < dim) {
            assert GaussWyjscie != null;
            gaussNumber = GaussWyjscie.readDouble();
            fileTxt.write(nF.format(gaussNumber) + '\n');
            i++;
        }
        assert GaussWyjscie != null;
        GaussWyjscie.close();
        fileTxt.close();
    }
}
