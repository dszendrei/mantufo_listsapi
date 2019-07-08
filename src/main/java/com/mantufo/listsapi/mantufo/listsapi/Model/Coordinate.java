package com.mantufo.listsapi.mantufo.listsapi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.el.stream.Stream;

import java.io.InputStream;

@Getter
@AllArgsConstructor
public class Coordinate {
    private final int x;
    private final int y;

    public static String calculateStringXCoordinate(int Xvalue) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String returnValue = "";
        int decimalLetter = 0;
        while (letters.length() < Xvalue - (decimalLetter * letters.length())) {
            decimalLetter++;
        }
        if (decimalLetter > 0) {
            returnValue += calculateStringXCoordinate(decimalLetter);
        }
        returnValue += letters.charAt(Xvalue - 1 - (decimalLetter * letters.length()));
        return returnValue;
    }

    public String getStringXCoordinate() {
        return calculateStringXCoordinate(x);
    }

    public String getSheetsFormattedCoordinate(){
        return getStringXCoordinate()+y;
    }

    public static int calculateIntXCoordinate(String Xvalue) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (Xvalue.chars().anyMatch(c -> !letters.contains(String.valueOf((char) c)))) {
            throw new IllegalArgumentException("Use only capital abc letters!");
        } else if (Xvalue.length() < 1) {
            throw new IllegalArgumentException();
        }
        int returnValue = 0;

        for (int i = 0; i < Xvalue.length() - 1; i++) {
            returnValue += (letters.indexOf(Xvalue.charAt(i)) + 1) * Math.pow(letters.length(), (Xvalue.length() - 1 - i));
        }

        returnValue += letters.indexOf(Xvalue.charAt(Xvalue.length() - 1)) + 1;
        return returnValue;
    }
}


