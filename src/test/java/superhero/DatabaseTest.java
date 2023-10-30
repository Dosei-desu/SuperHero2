package superhero;

import domain.Database;
import domain.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Superhero testman;
    Superhero hackerman;
    Database database;

    @BeforeEach
    void setup(){
        database = new Database();
        testman = new Superhero("Testman","Test Testerson",
                "Testing",2023,"No",10);
        hackerman = new Superhero("Hackerman","Hack Hackerson",
                "Hacking",2023,"Yes",9999);
        database.addSuperhero(testman);
        database.addSuperhero(hackerman);
    }

    @Test
    void sizeTest(){
        int expectedSize = 4;
        assertEquals(expectedSize,database.getSuperheroes().size());
    }

    @Test
    void searchHeroByNameTest() {
        ArrayList<Superhero> testerListExpected = new ArrayList<>(Arrays.asList(testman));
        String queryName = "Testman";
        ArrayList<Superhero> testerListReturned = database.searchHeroByName(queryName);
        assertEquals(testerListExpected,testerListReturned);
    }

    @Test
    void searchHeroByRealNameTest() {
        ArrayList<Superhero> testerListExpected = new ArrayList<>(Arrays.asList(hackerman));
        String queryName = "Hackerson";
        ArrayList<Superhero> testerListReturned = database.searchHeroByName(queryName);
        assertEquals(testerListExpected,testerListReturned);
    }

    @Test
    void removeHeroTest(){
        database.removeSuperhero(testman);
        int expectedSize = 3;
        assertEquals(expectedSize,database.getSuperheroes().size());
    }

    @Test
    void toStringTest(){
        String expectedString = "";
        for (int i = 0; i < database.getSuperheroes().size(); i++) {
            expectedString += (i+1)+"---"+database.getSuperheroes().get(i)+"\n";
        }
        expectedString += "------------------------------------------";
        String actualString = database.toString();
        assertEquals(expectedString,actualString);
    }
}