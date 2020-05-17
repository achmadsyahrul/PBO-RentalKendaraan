package rental.controller;

import rental.view.AddMenuView;
import rental.view.MenuView;

public class MenuController {

    public void mainMenu(){
        new MenuView();
    }

    public void add(){
        new AddMenuView();
    }
}
