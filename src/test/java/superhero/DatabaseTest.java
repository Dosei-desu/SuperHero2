package superhero;

import domain.Controller;
import domain.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Superhero testman;
    Superhero hackerman;
    Controller controller;

    @BeforeEach
    void setup(){
        controller = new Controller();
        testman = new Superhero("Testman","Test Testerson",
                "Testing",2023,"No",10);
        hackerman = new Superhero("Hackerman","Hack Hackerson",
                "Hacking",2023,"Yes",9999);
        controller.addSuperhero(testman);
        controller.addSuperhero(hackerman);
    }

    @Test
    void sizeTest(){
        int expectedSize = 4;
        assertEquals(expectedSize, controller.getSuperheroes().size());
    }

    @Test
    void searchHeroByNameTest() {
        ArrayList<Superhero> testerListExpected = new ArrayList<>(Arrays.asList(testman));
        String queryName = "Testman";
        ArrayList<Superhero> testerListReturned = controller.searchHeroByName(queryName);
        assertEquals(testerListExpected,testerListReturned);
    }

    @Test
    void searchHeroByRealNameTest() {
        ArrayList<Superhero> testerListExpected = new ArrayList<>(Arrays.asList(hackerman));
        String queryName = "Hackerson";
        ArrayList<Superhero> testerListReturned = controller.searchHeroByName(queryName);
        assertEquals(testerListExpected,testerListReturned);
    }

    @Test
    void removeHeroTest(){
        controller.removeSuperhero(testman);
        int expectedSize = 3;
        assertEquals(expectedSize, controller.getSuperheroes().size());
    }

    @Test
    void toStringTest(){
        String expectedString = "";
        for (int i = 0; i < controller.getSuperheroes().size(); i++) {
            expectedString += (i+1)+"---"+ controller.getSuperheroes().get(i)+"\n";
        }
        expectedString += "------------------------------------------";
        String actualString = controller.toString();
        assertEquals(expectedString,actualString);
    }
}