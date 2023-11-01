package datasauce;

import domain.Superhero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWrangler {
    private final File file = new File("supertest.csv");
    private Scanner fileReader;
    private PrintStream fileSaver;

    public FileWrangler(){
        try {
            fileReader = new Scanner(file);
            fileSaver = new PrintStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Superhero> load(){
        ArrayList<Superhero> loadedHeroes = new ArrayList<>();
        while(fileReader.hasNext()){
            String[] values = fileReader.nextLine().split(",");
            loadedHeroes.add(new Superhero(values[0],values[1],
                    values[2],Integer.parseInt(values[3]),
                    values[4],Double.parseDouble(values[5])));
        }

        return loadedHeroes;
    }

    public void save(Superhero hero){
        //hero = name, real name, powers, year, human?, strength value
        StringBuilder string = new StringBuilder();
        string.append(hero.getName()).append(",");
        string.append(hero.getRealName()).append(",");
        string.append(hero.getSuperPower()).append(",");
        string.append(hero.getYearCreated()).append(",");
        string.append(hero.getIsHuman()).append(",");
        string.append(hero.getStrength());
        fileSaver.println(string);
    }

}
