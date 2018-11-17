package lab2.model;

public class Model {
    private  GamingField gamingField;

    public  Model(int size, int mines, int x, int y){
        this.gamingField =  new GamingField(size,mines,x,y);
    }
    public GamingField getField(){
        return this.gamingField;
    }
}
