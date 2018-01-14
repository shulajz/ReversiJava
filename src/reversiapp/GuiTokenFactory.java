package reversiapp;

import boardgame.Token;
import boardgame.TokenFactory;

public class GuiTokenFactory implements TokenFactory {
    public Token Create(){

        return new GuiToken();
    }
}
