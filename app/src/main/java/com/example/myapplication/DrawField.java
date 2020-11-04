package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class DrawField extends View {
    Field field = null;
    Paint paint = new Paint();

    public DrawField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawField(Context context){
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);

        if(field != null){
            // Отрисовка горизонтальных линий
            for(int h = field.edgeY; h <= field.fieldHeight + field.edgeY; h += field.cellSize[1]){
                canvas.drawLine(field.edgeX, h, field.fieldWidth + field.edgeX, h, paint);
            }
            // Отрисовка вертикальных линий
            for(int w = field.edgeX; w <= field.fieldWidth + field.edgeX; w += field.cellSize[0]){
                canvas.drawLine(w, field.edgeY, w, field.fieldHeight + field.edgeY, paint);
            }
            // Для тестов
            paint.setTextSize(44);
            for(int h = 0; h < field.content.length; h++){
                for(int w = 0; w < field.content[h].length; w++){
                    if(field.content[h][w]){
                        paint.setColor(Color.GREEN);
                    }
                    canvas.drawText(String.valueOf(field.content[h][w]), h * field.cellSize[1] + field.edgeX + (int)(field.cellSize[0] / 4), w * field.cellSize[0] + field.edgeY + (int)(field.cellSize[1] / 2), paint);
                    paint.setColor(Color.BLACK);
                }
            }
        }
    }
}
