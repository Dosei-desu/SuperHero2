package ui;

import domain.Controller;
import domain.Superhero;
import domain.comparators.*;

import java.util.*;

public class Menu {
    Scanner scanner;
    Controller controller = new Controller();

    final int CREATE_CHOICE = 1;
    final int PRINT_CHOICE = 2;
    final int PRINT_SORT_CHOICE = 3;
    final int SEARCH_CHOICE = 4;
    final int EDIT_CHOICE = 5;
    final int REMOVE_CHOICE = 6;
    final int EXIT_CHOICE = 9;
    final int DEFAULT_CHOICE = 0;

    public Menu() {
    }

    public void start() {
        scanner = new Scanner(System.in);
        int choice;

        //here begins the menu choice loop
        do {
            welcome();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(Colour.RED + "Must input a number!" + Colour.RESET);
                choice = DEFAULT_CHOICE;
            }
            switch (choice) {
                case CREATE_CHOICE -> createSuperhero();
                case PRINT_CHOICE -> printDatabase();
                case PRINT_SORT_CHOICE -> printSortDatabase();
                case SEARCH_CHOICE -> searchDatabase();
                case EDIT_CHOICE -> editHero();
                case REMOVE_CHOICE -> removeHero();
                case EXIT_CHOICE -> exitProgram();
            }
        } while (choice != EXIT_CHOICE);
    }

    public void welcome() {
        System.out.printf("""
                        \nWelcome to the Superhero Database Program!
                        Please pick one of the below options:
                        ------------------------------------------
                        1---%sCreate Superhero%s----------------------
                        2---%sView Database%s-------------------------
                        3---%sView Sorted Database%s------------------
                        4---%sSearch Database%s-----------------------
                        5---%sEdit Hero%s-----------------------------
                        6---%sRemove Hero%s---------------------------
                        9---%sExit Program%s--------------------------
                        ------------------------------------------
                        """, Colour.GREEN, Colour.RESET, Colour.GREEN, Colour.RESET, Colour.GREEN, Colour.RESET,
                Colour.GREEN, Colour.RESET, Colour.GREEN, Colour.RESET, Colour.GREEN, Colour.RESET, Colour.RED, Colour.RESET);
    }

    public void createSuperhero() {
        scanner = new Scanner(System.in);
        System.out.printf("""
                ------------------------------------------
                You have chosen "Create Superhero".
                %sBlank entries will be set to a default%s
                Please enter the following
                info about the hero:
                ------------------------------------------
                """, Colour.RED, Colour.RESET);
        System.out.print("Hero name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = "Unknown";
        }
        System.out.print("Real name: ");
        String realName = scanner.nextLine();
        if (realName.isEmpty()) {
            realName = "Unknown";
        }
        System.out.print("Hero power: ");
        String superPower = scanner.nextLine();
        if (superPower.isEmpty()) {
            superPower = "Unknown";
        }
        System.out.print("Year created: ");
        String year = scanner.nextLine();
        if (year.isEmpty()) {
            year = "0";
        }
        int yearCreated;
        try {
            yearCreated = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            System.out.println(Colour.RED + "Must input a number!\n" +
                    "Defaulted to year 0." + Colour.RESET);
            yearCreated = 0;
        }
        System.out.print("Is the hero human? ");
        String isHuman = scanner.nextLine();
        if (isHuman.isEmpty()) {
            isHuman = "Yes";
        }
        System.out.print("Strength value: ");
        String strengthValue = scanner.nextLine();
        if (strengthValue.isEmpty()) {
            strengthValue = "1";
        }
        double strength;
        try {
            strength = Double.parseDouble(strengthValue);
        } catch (NumberFormatException e) {
            System.out.println(Colour.RED + "Must input a number!\n" +
                    "Defaulted to strength value of 1." + Colour.RESET);
            strength = 1;
        }
        //using values to make a new superhero
        Superhero newHero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
        //adds the hero to the database
        controller.addSuperhero(newHero);
        //printing out the hero info
        System.out.printf("""
                ------------------------------------------
                New hero added to Database:
                %s
                ------------------------------------------""", Colour.GREEN + newHero + Colour.RESET);
    }

    public void printDatabase() {
        if (controller.getSuperheroes().isEmpty()) {
            System.out.println(Colour.RED + "Database is empty!" + Colour.RESET);
            return;
        }

        System.out.print("""
                ------------------------------------------
                You have chosen "View Database".
                Printing Database of Heroes...
                ------------------------------------------
                """);
        Collections.sort(controller.getSuperheroes(), new HeroNameComparator());
        System.out.print(controller);
    }

    public void printSortDatabase() {
        if (controller.getSuperheroes().isEmpty()) {
            System.out.println(Colour.RED + "Database is empty!" + Colour.RESET);
            return;
        }
        ArrayList<Superhero> printDatabase = new ArrayList<>(controller.getSuperheroes());
        System.out.print("""
                ------------------------------------------
                You have chosen "Print Sorted Database".
                Select sort criteria:
                ------------------------------------------
                0 --- No sort criteria
                1 --- Hero Name (alphabetical)
                2 --- Real Name (alphabetical)
                3 --- Superpowers (alphabetical)
                4 --- Year Created (chronological)
                5 --- Is Human (humans first)
                6 --- Strength (descending)
                7 --- Sort by two criteria
                ------------------------------------------
                """);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> Collections.sort(printDatabase, new HeroNameComparator());
            case 2 -> Collections.sort(printDatabase, new RealNameComparator());
            case 3 -> Collections.sort(printDatabase, new SuperpowerComparator());
            case 4 -> Collections.sort(printDatabase, new YearCreatedComparator());
            case 5 -> Collections.sort(printDatabase, new IsHumanComparator().reversed());
            case 6 -> Collections.sort(printDatabase, new StrengthComparator().reversed());
            case 7 -> {
                System.out.print("""
                        Sort Criteria Options:
                        You cannot pick the same twice!
                        ------------------------------------------
                        1 --- Hero Name (alphabetical)
                        2 --- Real Name (alphabetical)
                        3 --- Superpowers (alphabetical)
                        4 --- Year Created (chronological)
                        5 --- Is Human (humans first)
                        6 --- Strength (descending)
                        ------------------------------------------
                        """);

                System.out.print("Criteria 1: ");
                int choice1 = Integer.parseInt(scanner.nextLine());
                System.out.print("Criteria 2: ");
                int choice2 = Integer.parseInt(scanner.nextLine());

                if(choice1 != choice2 && comparatorPicker(choice1) != null && comparatorPicker(choice2) != null){
                    Collections.sort(printDatabase,comparatorPicker(choice1).
                            thenComparing(comparatorPicker(choice2)));
                }else{
                    System.out.printf("%sYou cannot sort by the same criteria twice!%s\n",
                            Colour.RED,Colour.RESET);
                    return;
                }
            }
            default -> {
                printDatabase();
                return;
            }

        }
        System.out.print("""
                ------------------------------------------
                Printing Database of Heroes according to
                sort criteria...
                ------------------------------------------
                """);
        System.out.print(printSuperheroes(printDatabase));
    }

    private Comparator<Superhero> comparatorPicker(int choice){
        switch (choice){
            case 1 -> {return new HeroNameComparator();}
            case 2 -> {return new RealNameComparator();}
            case 3 -> {return new SuperpowerComparator();}
            case 4 -> {return new YearCreatedComparator();}
            case 5 -> {return new IsHumanComparator().reversed();}
            case 6 -> {return new StrengthComparator().reversed();}
        }
        return null;
    }

    public void searchDatabase() {
        if (controller.getSuperheroes().isEmpty()) {
            System.out.println(Colour.RED + "Database is empty!" + Colour.RESET);
            return;
        }
        scanner = new Scanner(System.in);
        System.out.print("""
                ------------------------------------------
                You have chosen "Search Database".
                Please enter the name of the Hero
                you would like to find:
                ------------------------------------------
                """);
        String name = scanner.nextLine();
        ArrayList<Superhero> searchResult = controller.searchHeroByName(name);
        if (!searchResult.isEmpty()) {
            for (int i = 0; i < searchResult.size(); i++) {
                System.out.println((i + 1) + "---" + searchResult.get(i));
            }
        } else {
            System.out.println(Colour.RED + "Here not found!" + Colour.RESET);
        }
        System.out.print("------------------------------------------");
    }

    public void editHero() {
        if (controller.getSuperheroes().isEmpty()) {
            System.out.println(Colour.RED + "Database is empty!" + Colour.RESET);
            return;
        }
        scanner = new Scanner(System.in);
        ArrayList<Superhero> heroList = controller.getSuperheroes();
        System.out.print("""
                ------------------------------------------
                You have chosen "Edit Hero".
                Please enter the number of the hero that
                you would like to change:
                ------------------------------------------
                """);
        for (int i = 0; i < heroList.size(); i++) {
            System.out.println((i + 1) + "---" + heroList.get(i));
        }
        System.out.print("------------------------------------------\n");
        int selection;
        try {
            selection = scanner.nextInt();
            scanner.nextLine(); //flush
            if (selection <= 0 || selection > heroList.size()) {
                System.out.println(Colour.RED + "Inserted number is not an option!" + Colour.RESET);
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println(Colour.RED + "Must input a number!" + Colour.RESET);
            return;
        }
        Superhero selectedHero = heroList.get(selection - 1); //removing 1 from the number to match index

        System.out.printf("""
                ------------------------------------------
                Selected hero:
                %s
                ------------------------------------------
                %sPress enter to skip edit, otherwise fill
                in an edit and press enter%s
                ------------------------------------------
                """, selectedHero, Colour.RED, Colour.RESET);
        System.out.print("Hero name: " + selectedHero.getName() + "\nNew name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = selectedHero.getName();
        }
        System.out.print("\nReal name: " + selectedHero.getRealName() + "\nNew real name: ");
        String realName = scanner.nextLine();
        if (realName.isEmpty()) {
            realName = selectedHero.getRealName();
        }
        System.out.print("\nHero power: " + selectedHero.getSuperPower() + "\nNew hero power: ");
        String superPower = scanner.nextLine();
        if (superPower.isEmpty()) {
            superPower = selectedHero.getSuperPower();
        }
        System.out.print("\nYear created: " + selectedHero.getYearCreated() + "\nNew year created: ");
        String newYear = scanner.nextLine();
        if (newYear.isEmpty()) {
            newYear = "" + selectedHero.getYearCreated();
        }
        int yearCreated;
        try {
            yearCreated = Integer.parseInt(newYear);
        } catch (NumberFormatException e) {
            System.out.println(Colour.RED + "Must input a number!\n" +
                    "Field left unchanged." + Colour.RESET);
            yearCreated = selectedHero.getYearCreated();
        }
        System.out.print("\nIs the hero human? " + selectedHero.getIsHuman() + "\nStill human? ");
        String isHuman = scanner.nextLine();
        if (isHuman.isEmpty()) {
            isHuman = selectedHero.getIsHuman();
        }
        System.out.print("\nStrength value: " + selectedHero.getStrength() + "\nNew strength value: ");
        String newStrength = scanner.nextLine();
        if (newStrength.isEmpty()) {
            newStrength = "" + selectedHero.getStrength();
        }
        double strength;
        try {
            strength = Double.parseDouble(newStrength);
        } catch (NumberFormatException e) {
            System.out.println(Colour.RED + "Must input a number!\n" +
                    "Field left unchanged." + Colour.RESET);
            strength = selectedHero.getStrength();
        }
        //using values to make a new superhero
        Superhero newHero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
        //adds the hero to the database on the same spot as the hero that was selected
        heroList.set((selection - 1), newHero);
        controller.setSuperheroes(heroList);
        System.out.printf("""
                ------------------------------------------
                %sEdit complete!%s
                %s
                ------------------------------------------
                """, Colour.GREEN, Colour.RESET, newHero);
    }

    public void removeHero() {
        if (controller.getSuperheroes().isEmpty()) {
            System.out.println(Colour.RED + "Database is empty!" + Colour.RESET);
            return;
        }
        scanner = new Scanner(System.in);
        System.out.printf("""
                ------------------------------------------
                You have chosen "Remove Hero".
                Please enter the number of the hero that
                you would like to remove:
                %sThis choice is final and cannot be undone!%s
                ------------------------------------------
                """, Colour.RED, Colour.RESET);
        for (int i = 0; i < controller.getSuperheroes().size(); i++) {
            System.out.println((i + 1) + "---" + controller.getSuperheroes().get(i));
        }
        System.out.print("------------------------------------------\n");
        int selection;
        try {
            selection = scanner.nextInt();
            scanner.nextLine(); //flush
            if (selection <= 0 || selection > controller.getSuperheroes().size()) {
                System.out.println(Colour.RED + "Inserted number is not an option!" + Colour.RESET);
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println(Colour.RED + "Must input a number!" + Colour.RESET);
            return;
        }
        Superhero deletedHero = controller.getSuperheroes().get(selection - 1);
        controller.removeSuperhero(deletedHero);
        System.out.print("------------------------------------------\n");
        System.out.println(Colour.RED + "Removed!" + Colour.RESET);
        System.out.print("\n------------------------------------------");
    }

    public String printSuperheroes(ArrayList<Superhero> heroList) {
        StringBuilder heroes = new StringBuilder();
        for (int i = 0; i < heroList.size(); i++) {
            heroes.append(i + 1).append("---").append(heroList.get(i)).append("\n");
        }
        heroes.append("------------------------------------------");
        return heroes.toString();
    }

    public void exitProgram() {
        System.out.print("""
                ------------------------------------------
                Shutting Down...
                Goodbye.
                ------------------------------------------
                """);
        System.exit(0);
    }
}