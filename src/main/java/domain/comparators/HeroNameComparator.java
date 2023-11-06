package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class HeroNameComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero sh1, Superhero sh2) {
        return String.CASE_INSENSITIVE_ORDER.compare(sh1.getName(), sh2.getName());
    }
}
