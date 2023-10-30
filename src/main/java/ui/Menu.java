package ui;

import domain.Database;
import domain.Superhero;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner scanner;
    Database database = new Database();

    final int CREATE_CHOICE = 1;
    final int PRINT_CHOICE = 2;
    final int SEARCH_CHOICE = 3;
    final int EDIT_CHOICE = 4;
    final int REMOVE_CHOICE = 5;
    final int EXIT_CHOICE = 9;
    final int DEFAULT_CHOICE = 0;

    public Menu() {
    }

    public void start(){
        scanner = new Scanner(System.in);
        int choice;

        //here begins the menu choice loop
        do {
            welcome();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e){
                System.out.print("\u001B[31mMust input a number!\u001B[0m");
                choice = DEFAULT_CHOICE;
            }
            switch (choice) {
                case CREATE_CHOICE -> createSuperhero();
                case PRINT_CHOICE -> printDatabase();
                case SEARCH_CHOICE -> searchDatabase();
                case EDIT_CHOICE -> editHero();
                case REMOVE_CHOICE -> removeHero();
                case EXIT_CHOICE -> exitProgram();
            }
        }while(choice != EXIT_CHOICE);
    }

    public void welcome(){
        System.out.print("""
                \nWelcome to the Superhero Database Program!
                Please pick one of the below options:
                ------------------------------------------
                1---\u001B[32mCreate Superhero\u001B[0m----------------------
                2---\u001B[32mPrint Database\u001B[0m------------------------
                3---\u001B[32mSearch Database\u001B[0m-----------------------
                4---\u001B[32mEdit Hero\u001B[0m-----------------------------
                5---\u001B[32mRemove Hero\u001B[0m---------------------------
                9---\u001B[31mExit Program\u001B[0m--------------------------
                ------------------------------------------
                """);
    }

    public void createSuperhero(){
        scanner = new Scanner(System.in);
        System.out.print("""
                ------------------------------------------
                You have chosen "Create Superhero".
                \u001B[31mBlank entries will be set to a default\u001B[0m
                Please enter the following
                info about the hero:
                ------------------------------------------
                """);
        System.out.print("Hero name: ");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            name = "Unknown";
        }
        System.out.print("Real name: ");
        String realName = scanner.nextLine();
        if(realName.isEmpty()){
            realName = "Unknown";
        }
        System.out.print("Hero power: ");
        String superPower = scanner.nextLine();
        if(superPower.isEmpty()){
            superPower = "Unknown";
        }
        System.out.print("Year created: ");
        String year = scanner.nextLine();
        if(year.isEmpty()){
            year = "0";
        }
        int yearCreated;
        try {
            yearCreated = Integer.parseInt(year);
        }
        catch(NumberFormatException e){
            System.out.println("\u001B[31mMust input a number!\u001B[0m");
            System.out.println("\u001B[31mDefaulted to year 0.\u001B[0m");
            yearCreated = 0;
        }
        System.out.print("Is the hero human? ");
        String isHuman = scanner.nextLine();
        if(isHuman.isEmpty()){
            isHuman = "Yes";
        }
        System.out.print("Strength value: ");
        String strengthValue = scanner.nextLine();
        if(strengthValue.isEmpty()){
            strengthValue = "1";
        }
        double strength;
        try {
            strength = Double.parseDouble(strengthValue);
        }
        catch(NumberFormatException e){
            System.out.println("\u001B[31mMust input a number!\u001B[0m");
            System.out.println("\u001B[31mDefaulted to strength value of 1.\u001B[0m");
            strength = 1;
        }
        //using values to make a new superhero
        Superhero newHero = new Superhero(name,realName,superPower,yearCreated,isHuman,strength);
        //adds the hero to the database
        database.addSuperhero(newHero);
        //printing out the hero info
        System.out.printf("""
                ------------------------------------------
                New hero added to Database:
                %s
                ------------------------------------------""",newHero);
    }

    public void printDatabase(){
        if(database.getSuperheroes().isEmpty()){
            System.out.println("\u001B[31mDatabase is empty!\u001B[0m");
            return;
        }
        System.out.print("""
                ------------------------------------------
                You have chosen "Print Database".
                Printing Database of Heroes...
                ------------------------------------------
                """);
        System.out.print(database);
    }

    public void searchDatabase(){
        if(database.getSuperheroes().isEmpty()){
            System.out.println("\u001B[31mDatabase is empty!\u001B[0m");
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
        ArrayList<Superhero> searchResult = database.searchHeroByName(name);
        if (!searchResult.isEmpty()){
            for (int i = 0; i < searchResult.size(); i++) {
                System.out.println((i+1)+"---"+searchResult.get(i));
            }
        }else{
            System.out.println("\u001B[31mHero not found!\u001B[0m");
        }
        System.out.print("------------------------------------------");
    }

    public void editHero(){
        if(database.getSuperheroes().isEmpty()){
            System.out.println("\u001B[31mDatabase is empty!\u001B[0m");
            return;
        }
        scanner = new Scanner(System.in);
        ArrayList<Superhero> heroList = database.getSuperheroes();
        System.out.print("""
                ------------------------------------------
                You have chosen "Edit Hero".
                Please enter the number of the hero that
                you would like to change:
                ------------------------------------------
                """);
        for (int i = 0; i < heroList.size(); i++) {
            System.out.println((i+1)+"---"+heroList.get(i));
        }
        System.out.print("------------------------------------------\n");
        int selection;
        try {
            selection = scanner.nextInt();
            scanner.nextLine(); //flush
            if(selection <= 0 || selection > heroList.size()){
                System.out.print("\u001B[31mInserted number is not an option!\u001B[0m");
                return;
            }
        }
        catch(InputMismatchException e){
            System.out.print("\u001B[31mMust input a number!\u001B[0m");
            return;
        }
        Superhero selectedHero = heroList.get(selection-1); //removing 1 from the number to match index

        System.out.printf("""
                ------------------------------------------
                Selected hero:
                %s
                ------------------------------------------
                \u001B[31mPress enter to skip edit, otherwise fill
                in an edit and press enter\u001B[0m
                ------------------------------------------
                """,selectedHero);
        System.out.print("Hero name: "+selectedHero.getName()+"\nNew name: ");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            name = selectedHero.getName();
        }
        System.out.print("\nReal name: "+selectedHero.getRealName()+"\nNew real name: ");
        String realName = scanner.nextLine();
        if(realName.isEmpty()){
            realName = selectedHero.getRealName();
        }
        System.out.print("\nHero power: "+selectedHero.getSuperPower()+"\nNew hero power: ");
        String superPower = scanner.nextLine();
        if(superPower.isEmpty()){
            superPower = selectedHero.getSuperPower();
        }
        System.out.print("\nYear created: "+selectedHero.getYearCreated()+"\nNew year created: ");
        String newYear = scanner.nextLine();
        if(newYear.isEmpty()){
            newYear = ""+selectedHero.getYearCreated();
        }
        int yearCreated;
        try {
            yearCreated = Integer.parseInt(newYear);
        }
        catch(NumberFormatException e){
            System.out.println("\u001B[31mMust input a number!\u001B[0m");
            System.out.println("\u001B[31mField left unchanged.\u001B[0m");
            yearCreated = selectedHero.getYearCreated();
        }
        System.out.print("\nIs the hero human? "+selectedHero.getIsHuman()+"\nStill human? ");
        String isHuman = scanner.nextLine();
        if(isHuman.isEmpty()){
            isHuman = selectedHero.getIsHuman();
        }
        System.out.print("\nStrength value: "+selectedHero.getStrength()+"\nNew strength value: ");
        String newStrength = scanner.nextLine();
        if(newStrength.isEmpty()){
            newStrength = ""+selectedHero.getStrength();
        }
        double strength;
        try {
            strength = Double.parseDouble(newStrength);
        }
        catch(NumberFormatException e){
            System.out.println("\u001B[31mMust input a number!\u001B[0m");
            System.out.println("\u001B[31mField left unchanged.\u001B[0m");
            strength = selectedHero.getStrength();
        }
        //using values to make a new superhero
        Superhero newHero = new Superhero(name,realName,superPower,yearCreated,isHuman,strength);
        //adds the hero to the database on the same spot as the hero that was selected
        heroList.set((selection-1),newHero);
        database.setSuperheroes(heroList);
        System.out.printf("""
                ------------------------------------------
                \u001B[32mEdit complete!\u001B[0m
                %s
                ------------------------------------------
                """,newHero);
    }

    public void removeHero(){
        if(database.getSuperheroes().isEmpty()){
            System.out.println("\u001B[31mDatabase is empty!\u001B[0m");
            return;
        }
        scanner = new Scanner(System.in);
        System.out.print("""
                ------------------------------------------
                You have chosen "Remove Hero".
                Please enter the number of the hero that
                you would like to remove:
                \u001B[31mThis choice is final and cannot be undone!\u001B[0m
                ------------------------------------------
                """);
        for (int i = 0; i < database.getSuperheroes().size(); i++) {
            System.out.println((i+1)+"---"+database.getSuperheroes().get(i));
        }
        System.out.print("------------------------------------------\n");
        int selection;
        try {
            selection = scanner.nextInt();
            scanner.nextLine(); //flush
            if(selection <= 0 || selection > database.getSuperheroes().size()){
                System.out.print("\u001B[31mInserted number is not an option!\u001B[0m");
                return;
            }
        }
        catch(InputMismatchException e){
            System.out.print("\u001B[31mMust input a number!\u001B[0m");
            return;
        }
        Superhero deletedHero = database.getSuperheroes().get(selection-1);
        database.removeSuperhero(deletedHero);
        System.out.print("------------------------------------------\n");
        System.out.println("\u001B[31mRemoved!\u001B[0m");
        System.out.print("\n------------------------------------------");
    }

    public void exitProgram(){
        System.out.print("""
                ------------------------------------------
                Shutting Down...
                Goodbye.
                ------------------------------------------
                """);
        System.exit(0);
    }
}