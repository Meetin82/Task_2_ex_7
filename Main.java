package com.company;

import java.util.Scanner;
import java.util.Locale;
public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        double m = readNumber("the amount of water in liters: ");
        checkMassPositive(m);

        double t = readNumber("the initial water temperature: ");
        checkAbsoluteZeroTempPositive(t);
        checkTempPositive(t);

        double k = readNumber("the amount of energy transferred to the water: ");
        checkEnergyPositive(k);

        double[] tempAndMass = calculateTemperatureAndMass(t, k, m);

        printTempAndMass(tempAndMass);
    }

    private static double[] calculateTemperatureAndMass(double t, double k, double m){
        double[] tempAndMass = new double[2];

        if (k > 4200 * m * (100 - t) + 2300000 * m){
            System.out.println("All the water has evaporated.");
        } else if (k > 4200 * m * (100 - t) ){
            double massSteam = (k - 4200 * m * (100 - t)) / 2300000;
            double finalMassWater = m - massSteam;
            tempAndMass[0] = 100;
            tempAndMass[1] = finalMassWater;
        } else {
            tempAndMass[0] = k / (4200 * m) + t;
            tempAndMass[1] = m;
        }

        return tempAndMass;
    }

    private static void printTempAndMass(double[] tempAndMass){
        System.out.printf("Water temperature: %1$.2f. Water Mass : %2$.2f.", tempAndMass[0], tempAndMass[1]);
    }

    private static void checkMassPositive(double num){
        if (num < 0){
            System.out.println("The mass cannot be below zero.");
            System.exit(0);
        }
    }

    private static void checkEnergyPositive(double num){
        if (num < 0){
            System.out.println("The energy cannot be below zero.");
            System.exit(0);
        }
    }

    private static void checkAbsoluteZeroTempPositive(double num){
        if (num < -273){
            System.out.println("The temperature cannot be less than absolute zero..");
            System.exit(0);
        }
    }

    private static void checkTempPositive(double num){
        if (num < 0){
            System.out.println("Does not fit the conditions. If the temperature is below zero, then it will be ice, not water.");
            System.exit(0);
        }
    }

    private static double readNumber(String value){
        System.out.print("Enter " + value);
        Scanner scn = new Scanner(System.in);
        return scn.nextDouble();
    }
}
