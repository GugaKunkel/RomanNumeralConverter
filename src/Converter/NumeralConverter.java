package Converter;

import java.util.Locale;

enum Numeral {
    I, V, X, L, C, D, M;

    public int getValue() {
        return switch (this) {
            case I -> 1;
            case V -> 5;
            case X -> 10;
            case L -> 50;
            case C -> 100;
            case D -> 500;
            case M -> 1000;
        };
    }
}

public class NumeralConverter {

    public int numeralToDecimal(String numeral) {

        //Makes sure the input is all upper case;
        numeral = numeral.toUpperCase(Locale.ROOT);

        //Makes sure the input from the user is a valid Roman Numeral
        boolean isNumeralValid = isNumeralValid(numeral);

        if(!isNumeralValid){
            return -1; //This value will cause main to tell the user to try again.
        }

        else{
            int endDecimalValue = 0;
            for (int i = 0; i < numeral.length(); i++) {
                // Gets value for Numeral at index i.
                char numeralLetter = numeral.charAt(i);
                int numeralLetterValue = Numeral.valueOf(String.valueOf(numeralLetter)).getValue();

                if (i < numeral.length() - 1) {
                    // Get value for Numeral at index i + 1. (if there is space left in the string)
                    char numeralNeighbor = numeral.charAt(i + 1);
                    int numeralNeighborValue = Numeral.valueOf(String.valueOf(numeralNeighbor)).getValue();

                    //if the value on the right is greater than on the left we will do numeral subtraction
                    if (numeralNeighborValue > numeralLetterValue) {
                        endDecimalValue += (numeralNeighborValue - numeralLetterValue);
                        i++; //We need to increment again because we did two letters
                    }
                    else {
                        //if no subtraction needed we add the value and increment by one
                        endDecimalValue += numeralLetterValue;
                    }
                }
                else {
                    //if no subtraction needed we add the value and increment by one(used for final letter)
                    endDecimalValue += numeralLetterValue;
                }
            }
            return endDecimalValue;
        }
    }

    /*
     Checks for specific cases where the user inputted numeral can be incorrect.
     Looks for certain letters that might appear behind certain numeral and numeral pairs.
     returns false if one of those letters is found.
     */
    private boolean isNumeralValid(String numeral) {
        if (numeral.contains("CM")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("CM"));
            if (substringToCheck.matches("[DCLXVI]")){
                return false;
            }
        }

        if (numeral.contains("CD")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("CD"));
            if (substringToCheck.matches("[CLXVI]")){
                return false;
            }
        }

        if (numeral.contains("XC")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("XC"));
            if (substringToCheck.matches("[LXVI]")){
                return false;
            }
        }

        if (numeral.contains("XL")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("XL"));
            if (substringToCheck.matches("[XVI]")){
                return false;
            }
        }

        if (numeral.contains("IX")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("IX"));
            if (substringToCheck.matches("[VI]")){
                return false;
            }
        }

        if (numeral.contains("IV")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("IV"));
            if (substringToCheck.matches("[I]")){
                return false;
            }
        }

        if (numeral.contains("M")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("M"));
            if (substringToCheck.matches("[DLXVI]+")){
                return false;
            }
        }

        if (numeral.contains("D")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("D"));
            if (substringToCheck.matches("[LXVI]+")){
                return false;
            }
        }

        if (numeral.contains("C")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("C"));
            if (substringToCheck.matches("[LVI]+")){
                return false;
            }
        }

        if (numeral.contains("L")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("L"));
            if (substringToCheck.matches("[VI]+")){
                return false;
            }
        }

        if (numeral.contains("X")) {
            String substringToCheck = numeral.substring(0, numeral.indexOf("X"));
            return !substringToCheck.matches("[V]+");
        }

        return true;
    }

    /*
    Similar to decimal to binary conversion.
    Goes from highest to lowest numeral values and sees if those values can fit in the decimal value.
    Repeats this process until the string of numerals is made and the number is decremented to zero
     */
   public String decimalToNumeral(int number){
       StringBuilder numeral = new StringBuilder();

       while(number > 0){
           if((number - Numeral.M.getValue()) >= 0){
               numeral.append("M");
               number -= 1000;
           }
           else if((number - (Numeral.M.getValue() - Numeral.C.getValue())) >= 0){
               numeral.append("CM");
               number -= 900;
           }
           else if((number - Numeral.D.getValue()) >= 0){
               numeral.append("D");
               number -= 500;
           }
           else if((number - (Numeral.D.getValue() - Numeral.C.getValue())) >= 0){
               numeral.append("CD");
               number -= 400;
           }
           else if((number - Numeral.C.getValue()) >= 0){
               numeral.append("C");
               number -= 100;
           }
           else if((number - (Numeral.C.getValue() - Numeral.X.getValue())) >= 0){
               numeral.append("XC");
               number -= 90;
           }
           else if((number - Numeral.L.getValue()) >= 0){
               numeral.append("L");
               number -= 50;
           }
           else if((number - (Numeral.L.getValue() - Numeral.X.getValue())) >= 0){
               numeral.append("XL");
               number -= 40;
           }
           else if((number - Numeral.X.getValue()) >= 0){
               numeral.append("X");
               number -= 10;
           }
           else if((number - (Numeral.X.getValue() - Numeral.I.getValue())) >= 0){
               numeral.append("IX");
               number -= 9;
           }
           else if((number - Numeral.V.getValue()) >= 0){
               numeral.append("V");
               number -= 5;
           }
           else if((number - (Numeral.V.getValue() - Numeral.I.getValue())) >= 0){
               numeral.append("IV");
               number -= 4;
           }
           else {
               numeral.append("I");
               number -= 1;
           }
       }

       return numeral.toString();
   }
}
