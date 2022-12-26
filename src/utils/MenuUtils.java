package utils;

import ModelEnum.ResourceType;
import exceptions.ValidationException;

import java.util.Scanner;

public class MenuUtils {

    public int checkCorrect() {

        int menuItemStart = 0;
        int menuItemFinish = 12;

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
            System.out.println("8 - Creating addMaterials");
            System.out.println("9 - Get lecture by ID");
            System.out.println("10 - Sort Course by name");
            System.out.println("11 - Sort Teacher and Student by last name");
            System.out.println("12 - Sort add materials...");

            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= menuItemStart && Integer.parseInt(item) <= menuItemFinish) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException();
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

    public ResourceType resourceType() throws ValidationException {
        switch (resourceTypeMenu()) {
            case 1:
                return ResourceType.URL;

            case 2:
                return ResourceType.VIDEO;

            case 3:
                return ResourceType.BOOK;

        }
        throw new ValidationException("id object not found");
    }


    public int resourceTypeMenu() {
        int menuItemStart = 1;
        int menuItemFinish = 3;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("Select resource type:");

            System.out.println("1 - URL");
            System.out.println("2 - VIDEO");
            System.out.println("3 - BOOK");

            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= menuItemStart && Integer.parseInt(item) <= menuItemFinish) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException();
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Something went wrong ... Try again. ");
            }
        }
    }

    public int resourceTypeMenuSorting() {
        int menuSortingItemStart = 1;
        int menuSortingItemFinish = 3;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");

            System.out.println("Sorting add materials by: ");
            System.out.println("1 - ID");
            System.out.println("2 - ID Lecture");
            System.out.println("3 - type of add materials");

            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= menuSortingItemStart && Integer.parseInt(item) <= menuSortingItemFinish) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException();
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Something went wrong ... Try again. ");
            }
        }
    }

}
