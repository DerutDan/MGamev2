package MClient.GameCards;

public abstract class GameCard {
    public String name;
    public abstract void deserialize(String params);
}
