package domain;

import java.util.ArrayList;

//TODO rename to something not 'Database'

public class Database {
    private ArrayList<Superhero> superheroes;

    public Database() {
        superheroes = new ArrayList<>();
        populateDatabase();
    }

    private void populateDatabase(){
        Superhero superman = new Superhero("Superman","Clark Kent",
                "Super strength, flight, xray vision",
                1938,"No",9999);
        Superhero spiderman = new Superhero("Spider-Man","Peter Parker",
                "Wall-climbing, web-shooting, danger sense",
                1962,"Yes",629);
        superheroes.add(superman);
        superheroes.add(spiderman);
    }

    public void addSuperhero(Superhero hero){
        superheroes.add(hero);
    }

    public ArrayList<Superhero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(ArrayList<Superhero> superheroes) {
        this.superheroes = superheroes;
    }

    public void removeSuperhero(Superhero superhero){
        superheroes.remove(superhero);
    }

    public ArrayList<Superhero> searchHeroByName(String name){
        ArrayList<Superhero> heroList = new ArrayList<>();
        for (Superhero hero: superheroes) {
            if(hero.getName().toLowerCase().contains(name.toLowerCase()) ||
                    hero.getRealName().toLowerCase().contains(name.toLowerCase())){
                heroList.add(hero);
            }
        }
        return heroList;
    }

    public String toString(){
        String heroes = "";
        for (int i = 0; i < superheroes.size(); i++) {
            heroes += (i+1)+"---"+superheroes.get(i)+"\n";
        }
        heroes += "------------------------------------------";
        return heroes;
    }
}