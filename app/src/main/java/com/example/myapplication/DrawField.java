package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class DrawField extends View {
    private int height;
    private int width;
    Paint paint = new Paint();

    public DrawField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawField(Context context){
        this(context, null);
    }

    int[] getCellSize(int x, int y){
        int tmp_x = x;
        int tmp_y = y;
        // Нахождение НОД(x, y)
        while (tmp_y != 0){
            int tmp = tmp_x % tmp_y;
            tmp_x = tmp_y;
            tmp_y = tmp;
        }
        // Подгонка размера клетки под поле макс 8*10 клеток
        int[] cellSize = {x / (x / tmp_x), y / (y / tmp_x)};
        while (x / cellSize[0] > 9 || y / cellSize[1] > 11){
            cellSize[0] += 10;
            cellSize[1] += 10;
        }
        return cellSize;
    }

    @Override
    protected void onMeasure(int widthSpecId, int heightSpecId)
    {
        this.height = View.MeasureSpec.getSize(heightSpecId);
        this.width = View.MeasureSpec.getSize(widthSpecId);

        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        int[] cellSize;

        // Получение размера клетки
        if (width % 2 != 0) {
            cellSize = getCellSize(width - 1, height);
        }
        else if (height % 2 != 0){
            cellSize = getCellSize(width, height - 1);
        }
        else{
            cellSize = getCellSize(width, height);
        }

        int fieldWidth = cellSize[0] * (width / cellSize[0]);
        int fieldHeight = cellSize[1] * (height / cellSize[1]);
        // Отступы по горизонтали и вертикали (padding)
        int edgeX = (width - fieldWidth) / 2;
        int edgeY = (height - fieldHeight) / 2;
        // Отрисовка горизонтальных линий
        for(int h = edgeY; h <= fieldHeight + edgeY; h += cellSize[1]){
            canvas.drawLine(edgeX, h, fieldWidth + edgeX, h, paint);
        }
        // Отрисовка вертикальных линий
        for(int w = edgeX; w <= fieldWidth + edgeX; w += cellSize[0]){
            canvas.drawLine(w, edgeY, w, fieldHeight + edgeY, paint);
        }
    }
}
