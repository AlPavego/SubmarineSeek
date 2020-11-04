package com.example.myapplication;

public class Field {
    boolean[][] content;
    int width;
    int height;

    int[] cellSize;

    int fieldWidth;
    int fieldHeight;

    // Отступы по горизонтали и вертикали
    int edgeX;
    int edgeY;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.cellSize = getCellSize(width, height);
        this.fieldWidth = cellSize[0] * (width / cellSize[0]);
        this.fieldHeight = cellSize[1] * (height / cellSize[1]);
        this.edgeX = (width - fieldWidth) / 2;
        this.edgeY = (height - fieldHeight) / 2;
        this.content = new boolean[fieldWidth / cellSize[0]][fieldHeight / cellSize[1]];
        this.content[(int)(Math.random() * content.length)][(int)(Math.random() * content[0].length)] = true;
    }

    int[] getCellSize(int x, int y){
        if(x % 2 != 0){
            x--;
        }
        else if(y % 2 != 0){
            y--;
        }
        int tmp_x = x;
        int tmp_y = y;
        int[] cellSize;

        if (tmp_x % tmp_y == 0 || tmp_y % tmp_x == 0){
            cellSize = new int[]{x / (x / 8), y / (y / 8)};
        }
        else {
            // Нахождение НОД(x, y)
            while (tmp_y != 0){
                int tmp = tmp_x % tmp_y;
                tmp_x = tmp_y;
                tmp_y = tmp;
            }
            cellSize = new int[]{x / (x / tmp_x), y / (y / tmp_x)};
        }
        // Подгонка размера клетки под поле макс 8*10 клеток
        while (x / cellSize[0] > 9 || y / cellSize[1] > 11){
            cellSize[0] += 10;
            cellSize[1] += 10;
        }
        return cellSize;
    }
}
