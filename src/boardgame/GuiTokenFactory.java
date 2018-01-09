package boardgame;

public class GuiTokenFactory implements TokenFactory {
    public Token Create(){

        return new GuiToken();
    }
}
