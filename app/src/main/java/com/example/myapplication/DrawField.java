package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class DrawField extends View {
    Field field = null;
    Paint paint = new Paint();
    Bitmap submarine;
    
    boolean alive = true;
    Cell submarineCell;
    Cell emptyCell;
    int distance;

    public DrawField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawField(Context context){
        this(context, null);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        paint.setTextSize(96);

        if(field != null){
            drawHorizontalLines(canvas);
            drawVerticalLines(canvas);

            if (emptyCell != null) {
                drawDistance(canvas);
            }
            if (!alive){
                drawSubmarine(canvas);
            }
            drawTest(canvas);
        }
    }
    
    void performClick(float x, float y){
        if (field.edgeX <= x && x <= field.width - field.edgeX && field.edgeY <= y && y <= field.height - field.edgeY){
            Cell cell = new Cell(x, y, field);

            if (field.content[cell.x][cell.y]) {
                alive = false;
                emptyCell = null;
            }
            else {
                emptyCell = cell;
                distance = Math.abs(emptyCell.x - submarineCell.x) + Math.abs(emptyCell.y - submarineCell.y);
            }
        }
        else {
            emptyCell = null;
            distance = 0;
        }
    }

    void setField(Field field){
        this.field = field;
        loadSubmarine();
    }

    void loadSubmarine(){
        int x = (int)(Math.random() * field.content.length);
        int y = (int)(Math.random() * field.content[0].length);
        submarineCell = new Cell(x, y);

        submarine = BitmapFactory.decodeResource(getResources(), R.drawable.submarine);
        submarine = Bitmap.createScaledBitmap(submarine, (int)(field.cellWidth * 0.9), (int)(field.cellHeight * 0.9), false);

        field.content[submarineCell.x][submarineCell.y] = true;
    }

    private int getDistanceDrawX(int distance){
        if (distance > 9){
            return field.cellWidth * emptyCell.x + field.edgeX + (int)(field.cellWidth * 0.19);
        }
        return field.cellWidth * emptyCell.x + field.edgeX + (int)(field.cellWidth * 0.35);
    }

    private int getDistanceDrawY(){
        return field.cellHeight * emptyCell.y + field.edgeY + (int) (field.cellHeight * 0.67);
    }

    private int getSubmarineDrawX(){
        return field.cellWidth * submarineCell.x + field.edgeX + (int)(field.cellWidth * 0.1 / 2);
    }

    private int getSubmarineDrawY(){
        return field.cellHeight * submarineCell.y + field.edgeY + (int)(field.cellHeight * 0.1 / 2);
    }

    private void drawHorizontalLines(Canvas canvas){
        for (int h = field.edgeY; h <= field.fieldHeight + field.edgeY; h += field.cellHeight){
            canvas.drawLine(field.edgeX, h, field.fieldWidth + field.edgeX, h, paint);
        }
    }
    private void drawVerticalLines(Canvas canvas){
        for (int w = field.edgeX; w <= field.fieldWidth + field.edgeX; w += field.cellWidth){
            canvas.drawLine(w, field.edgeY, w, field.fieldHeight + field.edgeY, paint);
        }
    }

    private void drawDistance(Canvas canvas){
        canvas.drawText(String.valueOf(distance), getDistanceDrawX(distance), getDistanceDrawY(), paint);
    }

    private void drawSubmarine(Canvas canvas){
        canvas.drawBitmap(submarine, getSubmarineDrawX(), getSubmarineDrawY(), paint);
    }

    private void drawTest(Canvas canvas){
        paint.setTextSize(44);
        for (int h = 0; h < field.content.length; h++){
            for (int w = 0; w < field.content[h].length; w++){
                if (field.content[h][w]){
                    paint.setColor(Color.GREEN);
                }
                canvas.drawText(String.valueOf(field.content[h][w]), h * field.cellHeight + field.edgeX + (int)(field.cellWidth / 4), w * field.cellWidth + field.edgeY + (int)(field.cellHeight / 2), paint);
                paint.setColor(Color.BLACK);
            }
        }
    }
}
