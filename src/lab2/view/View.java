package lab2.view;

import lab2.exeptions.Goingabroad;
import lab2.model.GamingField;

public interface View {
    void showGameField(GamingField gamingField) throws Goingabroad;
}
