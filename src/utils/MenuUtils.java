package utils;

import java.util.Scanner;

public class MenuUtils {

    public int checkCorrect() {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstRun = false;
        int category = -1;
        while (category > 6 || category < 0) {
            if (isFirstRun) {
                System.out.println("Something went wrong ... Try again. ");
            }
            isFirstRun = true;

            System.out.println("");
            System.out.println("Select category:");
            System.out.println("0 - exit program");
            System.out.println("1 - Objects course");
            System.out.println("2 - Objects lecture");
            System.out.println("3 - Creating course");
            System.out.println("4 - Creating lecture");
            System.out.println("5 - Creating teacher");
            System.out.println("6 - Creating student");

            if (scanner.hasNextInt()) {
                category = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        return category;
    }

    public String inputString() {
        return new Scanner(System.in).next();
    }

    public int inputDigit() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter digit ");
                scanner.next();
            }
        }
    }


}
