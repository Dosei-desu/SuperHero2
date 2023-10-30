package superhero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Superhero;

import static org.junit.jupiter.api.Assertions.*;

class SuperheroTest {
    Superhero testHero;

    @BeforeEach
    void setup() {
            testHero = new Superhero("Testman", "Test Testerson",
                    "Testing",2023, "No", 0);
    }

    @Test
    void constructSuperhero() {
        assertAll("Testing superhero construction:",
                () -> assertEquals("Testman", testHero.getName()),
                () -> assertEquals("Test Testerson", testHero.getRealName()),
                () -> assertEquals("Testing", testHero.getSuperPower()),
                () -> assertEquals(2023, testHero.getYearCreated()),
                () -> assertEquals("No", testHero.getIsHuman()),
                () -> assertEquals(0, testHero.getStrength())
        );
    }

    @Test
    void testToString() {
        String resultStringExpected = "\"\u001B[32m" + testHero.getName() + "\u001B[0m\"---" +
                "\"" + testHero.getRealName() + "\"---" +
                "\'\u001B[31m" + testHero.getSuperPower() + "\u001B[0m\'---" +
                "Created in " + testHero.getYearCreated() +
                "---Human: \"" + testHero.getIsHuman() +
                "\"---Strength: " + testHero.getStrength();
        assertEquals(resultStringExpected, testHero.toString());
    }
}