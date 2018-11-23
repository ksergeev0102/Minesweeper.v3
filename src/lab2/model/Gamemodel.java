package lab2.model;

import javax.swing.plaf.nimbus.State;
import java.awt.*;


public class Gamemodel {
    private int off_on;
    private  GamingField gamingField;

    public  Gamemodel(int size, int mines){
        this.gamingField =  new GamingField(size,mines);
    }

    public void setField(int mines, int x, int y){
        this.getField().inicialized(mines,x,y);
        this.off_on = 1;
    }

    public boolean getState(){
        if(this.off_on == 1){
            return true;
        }else{
            return false;
        }
    }

    public GamingField getField(){
        return this.gamingField;
    }
}
