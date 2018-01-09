package boardgame;


import java.awt.*;

public class Cell {

    private int m_cellWidth;
    private int m_cellHeight;
    private Button m_button;
    private int m_row;
    private int m_col;


public Cell(int cellWidth, int cellHeight, Button button, int row, int col) {
    m_cellWidth = cellWidth;
    m_cellHeight = cellHeight;
    m_button = button;
    m_row = row;
    m_col = col;
}




}
