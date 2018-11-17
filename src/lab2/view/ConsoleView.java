package lab2.view;

import lab2.model.GamingField;


public class ConsoleView implements View {
    @Override
    public void showGameField(GamingField gamingField){
        gamingField.showFieldForPlayer();
    }
}
