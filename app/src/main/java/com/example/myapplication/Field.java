package com.example.myapplication;

public class Field {
    boolean[][] content;

    int cellWidth;
    int cellHeight;
    
    int fieldWidth;
    int fieldHeight;
    
    int edgeX;
    int edgeY;

    int width;
    int height;


    public Field(int width, int height) {
        this.width = width;
        this.height = height;

        getCellSize();
        getFieldResolution();
        getEdges();

        this.content = new boolean[fieldWidth / cellWidth][fieldHeight / cellHeight];
    }

    private void getCellSize(){
        int width = this.width;
        int height = this.height;
        if(width % 2 != 0){
            width--;
        }
        else if(height % 2 != 0){
            height--;
        }
        int tmpWidth = width;
        int tmpHeight = height;
        
        int cellWidth;
        int cellHeight;

        if (tmpWidth % tmpHeight == 0 || tmpHeight % tmpWidth == 0){
            cellWidth = width / (width / 8);
            cellHeight = height / (height / 8);
        }
        else {
            // Нахождение НОД(width, height)
            while (tmpHeight != 0){
                int tmp = tmpWidth % tmpHeight;
                tmpWidth = tmpHeight;
                tmpHeight = tmp;
            }
            cellWidth = width / (width / tmpWidth);
            cellHeight = height / (height / tmpWidth);
        }
        // Подгонка размера клетки под поле макс 8*10 клеток
        while (width / cellWidth > 9 || height / cellHeight > 11){
            cellWidth += 10;
            cellHeight += 10;
        }

        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    private void getFieldResolution(){
        fieldWidth = cellWidth * (width / cellWidth);
        fieldHeight = cellHeight * (height / cellHeight);
    }
    
    private void getEdges(){
        edgeX = (width - fieldWidth) / 2;
        edgeY = (height - fieldHeight) / 2;
    }
}
