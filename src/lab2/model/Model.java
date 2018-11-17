package lab2.model;

public class Model {
    public GamingField getField(int size, int mines, int x, int y){
        return new GamingField(size,mines,x,y);
    }
}
