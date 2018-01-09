/*
 * ex3.c
 *
 *  Created on: Nov 25, 2017
 *      Author:  Shulamit & Or Sha'ashua
*/
package boardgame;
public abstract class Menu {

    public abstract char getSelection();
    public abstract void printWrongChoice();
    public abstract void printOptionsMenu();
    public Player choosePlayer(){
        Player  p;
        while (true) {
            char selection = getSelection();
            //H for Human Player
            if (selection == 'H') {
                p = new RealPlayer(TokenValue.White);
                return p;
                //A for AIPlayer
//            } else if (selection == 'A') {
//                p = new AIPlayer(TokenValue.White);
//                return p;
            } else {
                //illegal option
                printWrongChoice();
            }
        }
    }

}