package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class SuperpowerComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero sh1, Superhero sh2) {
        return String.CASE_INSENSITIVE_ORDER.compare(sh1.getSuperPower(), sh2.getSuperPower());
    }
}
