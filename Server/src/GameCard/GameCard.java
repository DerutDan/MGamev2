package GameCard;

/**
 * Created by Danila on 11.02.17.
 */
public abstract class GameCard implements Cloneable{
    protected String  name;
    protected String type;
    public  String getName() {
        return name;
    }
    public String getT()
    {
      return type;
    }
    public abstract void setT();
    public abstract void setName(String name);
    public abstract String serialize();
}
