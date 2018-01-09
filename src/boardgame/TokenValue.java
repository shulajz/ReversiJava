//public enum TokenValue {
//    Black,
//    White,
//    Empty,
//}
package boardgame;

public enum TokenValue {
    Black(0), White(1), Empty(3);

    private final int value;

    private TokenValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}