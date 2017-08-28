package stevenandjackson.tictactoe;

/**
 * An simple encapsulating java bean
 * @author zhaoy
 *
 */
public class Spot{
    int x, y, rating;

    public Spot(int x, int y, int rating){
        this.x = x;
        this.y = y;
        this.rating = rating;
    }

    public Spot(Spot template){
        this.x = template.x;
        this.y = template.y;
        this.rating = template.rating;
    }

}