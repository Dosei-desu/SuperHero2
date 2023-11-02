package datasauce;

import domain.Superhero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWrangler {
    private final String FILE_NAME = "superheroes.csv";

    public ArrayList<Superhero> load(){
        ArrayList<Superhero> loadedHeroes = new ArrayList<>();
        try (Scanner fileReader = new Scanner(new File(FILE_NAME))){
            while (fileReader.hasNextLine()) {
                String string = fileReader.nextLine();
                String[] values = string.split(",");
                if(values.length == 6) {
                    loadedHeroes.add(new Superhero(values[0], values[1],
                            values[2], Integer.parseInt(values[3]),
                            values[4], Double.parseDouble(values[5])));
                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return loadedHeroes;
    }

    public void save(ArrayList<Superhero> superheroes){
        try (PrintStream fileSaver = new PrintStream(FILE_NAME)) {
            for (Superhero hero: superheroes) {
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
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
