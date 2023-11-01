package domain;

import datasauce.FileWrangler;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Superhero> superheroes;
    private FileWrangler fileWrangler;

    public Controller() {
        superheroes = new ArrayList<>();
        fileWrangler = new FileWrangler();
        populateDatabase();
    }

    private void populateDatabase() {
       superheroes = fileWrangler.load();
    }

    public void addSuperhero(Superhero hero) {
        superheroes.add(hero);
        fileWrangler.save(hero);
    }

    public ArrayList<Superhero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(ArrayList<Superhero> superheroes) {
        this.superheroes = superheroes;
    }

    public void removeSuperhero(Superhero superhero) {
        superheroes.remove(superhero);
    }

    public ArrayList<Superhero> searchHeroByName(String name) {
        ArrayList<Superhero> heroList = new ArrayList<>();
        for (Superhero hero : superheroes) {
            if (hero.getName().toLowerCase().contains(name.toLowerCase()) ||
                    hero.getRealName().toLowerCase().contains(name.toLowerCase())) {
                heroList.add(hero);
            }
        }
        return heroList;
    }

    public String toString() {
        String heroes = "";
        for (int i = 0; i < superheroes.size(); i++) {
            heroes += (i + 1) + "---" + superheroes.get(i) + "\n";
        }
        heroes += "------------------------------------------";
        return heroes;
    }
}