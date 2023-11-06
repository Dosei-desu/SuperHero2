package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class YearCreatedComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero sh1, Superhero sh2) {
        return Integer.compare(sh1.getYearCreated(), sh2.getYearCreated());
    }
}
