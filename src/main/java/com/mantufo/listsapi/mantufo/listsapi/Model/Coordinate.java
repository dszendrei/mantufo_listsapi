package com.mantufo.listsapi.mantufo.listsapi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coordinate {
    static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int x;
    private final int y;

    public static String calculateStringXCoordinate(int Xvalue) {
        String returnValue = "";
        int decimalLetter = 0;
        while (LETTERS.length() < Xvalue - (decimalLetter * LETTERS.length())) {
            decimalLetter++;
        }
        if (decimalLetter > 0) {
            returnValue += calculateStringXCoordinate(decimalLetter);
        }
        returnValue += LETTERS.charAt(Xvalue - 1 - (decimalLetter * LETTERS.length()));
        return returnValue;
    }

    public static int calculateIntXCoordinate(String Xvalue) {
        if (Xvalue.chars().anyMatch(c -> !LETTERS.contains(String.valueOf((char) c)))) {
            throw new IllegalArgumentException("Use only capital abc letters!");
        } else if (Xvalue.length() < 1) {
            throw new IllegalArgumentException();
        }
        int returnValue = 0;

        for (int i = 0; i < Xvalue.length() - 1; i++) {
            returnValue += (LETTERS.indexOf(Xvalue.charAt(i)) + 1) * Math.pow(LETTERS.length(), (Xvalue.length() - 1 - i));
        }

        returnValue += LETTERS.indexOf(Xvalue.charAt(Xvalue.length() - 1)) + 1;
        return returnValue;
    }

    String getStringXCoordinate() {
        return calculateStringXCoordinate(x);
    }

    public String getSheetsFormattedCoordinate(){
        return getStringXCoordinate()+y;
    }

}


