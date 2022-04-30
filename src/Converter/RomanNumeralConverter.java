package Converter;

import java.util.Scanner;

public class RomanNumeralConverter {

    public static void main(String[] args){

        //Welcome greeting for the program
        System.out.println("Welcome to the Roman Numeral Converter!\n");
        System.out.println("""
                    Type RD to convert from Roman Numeral to Decimal.
                    Type DR to convert from Decimal to Roman Numeral.
                    Type Q to exit the program.""");

        //Scanner to allow user input
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        //Calls an instance of the NumeralConverter class
        NumeralConverter converter = new NumeralConverter();

        //Program will run until the user chooses to quit
        while(!userInput.equalsIgnoreCase("Q")){

            // Makes sure the user puts in correct input
            while(!userInput.equalsIgnoreCase("RD") && !userInput.equalsIgnoreCase("DR") && !userInput.equalsIgnoreCase("Q")){
                System.out.println("Invalid input\n");
                System.out.println("""
                    Type RD to convert from Roman Numeral to Decimal.
                    Type DR to convert from Decimal to Roman Numeral.
                    Type Q to exit the program.""");
                userInput = sc.next();
            }

            //Calls for the RomanNumeral to Decimal conversion
            if(userInput.equalsIgnoreCase("RD")){
                System.out.println("What is the numeral you would like to convert?");
                String numeralToConvert = sc.next();

                int decimalValue = converter.numeralToDecimal(numeralToConvert);

                //Makes sure the numeral value is valid
                while(decimalValue == -1){
                    System.out.println("The Numeral You entered is not valid. Please Try again");
                    numeralToConvert = sc.next();
                    decimalValue = converter.numeralToDecimal(numeralToConvert);
                }

                System.out.println("The Decimal Value of " + numeralToConvert + " is " + decimalValue + "\n");
            }

            //Calls for teh Decimal to Roman Numeral conversion
            else {
                System.out.println("What is the decimal number you would like to convert?");
                String userNumber = sc.next();

                //Makes sure the user inputs a valid decimal
                while(!userNumber.matches("[0-9]+")){
                    System.out.println("The Number you entered is not valid. Please try again");
                    userNumber = sc.next();
                }

                int decimalToConvert = Integer.parseInt(userNumber);
                String numeralValue = converter.decimalToNumeral(decimalToConvert);

                System.out.println("The Numeral Value of " + decimalToConvert + " is " + numeralValue + "\n");

            }

            //Called when conversion finishes, if user wants to do another conversion
            System.out.println("""
                Type RD to convert from Roman Numeral to Decimal.
                Type DR to convert from Decimal to Roman Numeral.
                Type Q to exit the program.""");
            userInput = sc.next();
        }

        //Called when user quits the program
        System.out.println("Thanks for using our program!");
    }
}
