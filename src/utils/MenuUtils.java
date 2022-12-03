package utils;

import java.util.Scanner;

public class MenuUtils {

    public int checkCorrect() {

        int menuItemStart = 0;
        int menuItemFinish = 8;

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("");
            System.out.println("Select category:");
            System.out.println("0 - exit program");
            System.out.println("1 - Objects course");
            System.out.println("2 - Objects lecture");
            System.out.println("3 - Creating course");
            System.out.println("4 - Creating lecture");
            System.out.println("5 - Creating teacher");
            System.out.println("6 - Creating student");
            System.out.println("7 - Creating homework");
            System.out.println("8 - Get lecture by ID");

            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= menuItemStart && Integer.parseInt(item) <= menuItemFinish) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException ();
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Something went wrong ... Try again. ");
            }
        }
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
