package com.example.myapplication;

public class Cell {
    Field field;
    int x;
    int y;

    public Cell(float x, float y, Field field){
        this.field = field;
        getCell(x, y);
    }

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    private void getCell(float x, float y){
        int cX = (int)((x - field.edgeX) / field.cellWidth);
        int cY = (int)((y - field.edgeY) / field.cellHeight);

        this.x = cX;
        this.y = cY;
    }
}
