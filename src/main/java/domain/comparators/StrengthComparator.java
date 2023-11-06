package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero sh1, Superhero sh2) {
        return Double.compare(sh1.getStrength(),sh2.getStrength());
    }
}
