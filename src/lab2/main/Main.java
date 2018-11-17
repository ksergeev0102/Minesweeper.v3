package lab2.main;

import lab2.controller.Controller;
import lab2.exeptions.Goingabroad;


public class Main {
    public static void main(String[] args) throws Goingabroad {
        Controller controller =  new Controller();
        controller.Move();
    }
}
