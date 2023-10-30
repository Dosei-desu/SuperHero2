package domain;

public class Superhero {
    private String name;
    private String realName;
    private String superPower;
    private int yearCreated;
    private String isHuman;
    private double strength;

    public Superhero(String name, String realName, String superPower,
                     int yearCreated, String isHuman, double strength) {
        //hero name (e.g. "Superman")
        this.name = name;
        //real name (e.g. "Clark Kent")
        this.realName = realName;
        this.superPower = superPower;
        this.yearCreated = yearCreated;
        this.isHuman = isHuman;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getSuperPower() {
        return superPower;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public String getIsHuman() {
        return isHuman;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return  "\"\u001B[32m" + name + "\u001B[0m\"---" +
                "\"" + realName + "\"---" +
                "\'\u001B[31m" + superPower + "\u001B[0m\'---" +
                "Created in " + yearCreated +
                "---Human: \"" + isHuman +
                "\"---Strength: " + strength;
    }
}