package lab2.view;

import lab2.model.GamingField;
import lab2.model.Model;

public class ConsoleView implements View {
    private GamingField gamingField;

    public ConsoleView(Model model){
        this.gamingField = model.getField();
    }
    @Override
    public void showGameField(){
        this.gamingField.showFieldForPlayer();
    }
}
