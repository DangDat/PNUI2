package com.example.pnui.assignment2_u3091855_part3;

/**
 * Created by PNUI on 5/11/2014.
 */
import java.util.ArrayList;

import android.graphics.*;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    //	private Paint myPaint;
//	private boolean IsDown=false;
//	private float X0=0;
//	private float Y0=0;
//	private float X=0;
//	private float Y=0;
    private static final int MAX_POINTERS = 10;
    private static final float TOUCH_TOLERANCE = 4;
    private static final int[] TOUCH_COLORS = {Color.YELLOW,
            Color.GREEN, Color.CYAN, Color.MAGENTA, Color.RED,
            Color.BLUE, Color.WHITE, Color.GRAY, Color.LTGRAY,
            Color.DKGRAY };
    private PointF[] starts = new PointF[MAX_POINTERS];
    private Paint[] myPaints = new Paint[MAX_POINTERS];
    private MotionEvent lastEvent;
    private PointF viewOffset = new PointF();

    ArrayList <Path> d_Path_list =  new ArrayList<Path>();
    ArrayList <Float> dX_list =  new ArrayList<Float>();
    ArrayList <Float> dY_list =  new ArrayList<Float>();
    ArrayList <Integer> dActivePointerId_list =  new ArrayList<Integer>();


    ArrayList<Pair<Path, Paint>> dArrayListPaths = new ArrayList<Pair<Path, Paint>>();
//    private ArrayList<PointF> collection = new ArrayList<PointF>();

    String ColorName;
    public static String pictureDetail = " ";

    public void init()
    {
        starts = new PointF[MAX_POINTERS];
        myPaints = new Paint[MAX_POINTERS];
        viewOffset = new PointF();
        d_Path_list =  new ArrayList<Path>();
        dX_list =  new ArrayList<Float>();
        dY_list =  new ArrayList<Float>();
        dActivePointerId_list =  new ArrayList<Integer>();


        dArrayListPaths = new ArrayList<Pair<Path, Paint>>();
        for (int i = 0; i < MAX_POINTERS; i++) {
            myPaints[i] = new Paint();
            myPaints[i].setColor(TOUCH_COLORS[i]);
            myPaints[i].setAntiAlias(true);
            myPaints[i].setStrokeWidth(2);
            myPaints[i].setStyle(Style.STROKE);
            myPaints[i].setTextSize(32);
            myPaints[i].setTypeface(Typeface.SANS_SERIF);
            myPaints[i].setTextAlign(Align.CENTER);
        }
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
//		myPaint = new Paint();
//		myPaint.setColor(Color.RED);
//		myPaint.setAntiAlias(true);
//		myPaint.setStrokeWidth(2);
//		myPaint.setStyle(Style.STROKE);
//		for (int i = 0; i < MAX_POINTERS; i++) {
//			myPaints[i] = new Paint();
//			myPaints[i].setColor(TOUCH_COLORS[i]);
//			myPaints[i].setAntiAlias(true);
//			myPaints[i].setStrokeWidth(2);
//			myPaints[i].setStyle(Style.STROKE);
//			myPaints[i].setTextSize(32);
//			myPaints[i].setTypeface(Typeface.SANS_SERIF);
//			myPaints[i].setTextAlign(Align.CENTER);
//		}
    }

    public boolean onTouchEvent(MotionEvent event) {
//		Log.v("MyView", describeEvent(event));
//		int action = event.getAction();
//		switch (action) {
//		case MotionEvent.ACTION_DOWN:
//			IsDown = true;
//			X0 = event.getX();
//			Y0 = event.getY();
//			X = event.getX();
//			Y = event.getY();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			X = event.getX();
//			Y = event.getY();
//			break;
//		case MotionEvent.ACTION_UP:
//		case MotionEvent.ACTION_CANCEL:
//			X = event.getX();
//			Y = event.getY();
//			IsDown = false;
//			break;
//		}
//		invalidate();
//		return true;
        Log.v("MyView", describeEvent(event));
        lastEvent = event;
        int action = event.getAction();
        int ptrIdx, ptrId;
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                init();
                ptrIdx = 0;
                ptrId = 0;
                viewOffset.x = event.getRawX() - event.getX();
                viewOffset.y = event.getRawY() - event.getY();
                starts[ptrId] = new PointF(event.getX(), event.getY());
                touch_start(ptrId);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ptrIdx = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                ptrId = event.getPointerId(ptrIdx);
                starts[ptrId] = new PointF(event.getX(ptrIdx), event.getY(ptrIdx));
                touch_start(ptrId);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < d_Path_list.size(); i++) {

                    if ( i == 0) {
                        ColorName = "Yellow";
                        pictureDetail = "Number of touches: 1 "  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 1) {
                        ColorName = "Green";
                        pictureDetail = "Number of touches: 2"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 2) {
                        ColorName = "Cyan";
                        pictureDetail = "Number of touches: 3"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 3) {
                        ColorName = "Magenta";
                        pictureDetail = "Number of touches: 4"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 4) {
                        ColorName = "Red";
                        pictureDetail = "Number of touches: 5"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 5) {
                        ColorName = "Blue";
                        pictureDetail = "Number of touches: 6"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 6) {
                        ColorName = "White";
                        pictureDetail = "Number of touches: 7"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 7) {
                        ColorName = "Gray";
                        pictureDetail = "Number of touches: 8" + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 8) {
                        ColorName = "Light Gray";
                        pictureDetail = "Number of touches: 9"  + "\nThe last Color: " + ColorName;
                    }
                    if ( i == 9) {
                        ColorName = "Dark Gray";
                        pictureDetail = "Number of touches: 10"  + "\nThe last Color: " + ColorName;
                    }

                }

            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        invalidate();
        return true;

    }
    //protected static String describeEvent(MotionEvent event) {
//		StringBuilder result = new StringBuilder(300);
//		result.append("Action: ").append(event.getAction()).append("\n");
//		result.append("Location: ").append(event.getX()).append(" x ").append(event.getY()).append("\n");
//		result.append("Edge flags: ").append(event.getEdgeFlags()).append("\n");
//		result.append("Pressure: ").append(event.getPressure()).append("   ");
//		result.append("Size: ").append(event.getSize()).append("\n");
//		result.append("Down time: ").append(event.getDownTime()).append("ms\n");
//		result.append("Event time: ").append(event.getEventTime()).append("ms");
//		result.append("  Elapsed: ").append(event.getEventTime()-event.getDownTime());
//		result.append(" ms\n");
//		return result.toString();
    private String describeEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT);
            sb.append(")");
        }
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i).append(":id")
                    .append(event.getPointerId(i));
            sb.append(" (").append(event.getX(i)).append(",")
                    .append(event.getY(i)).append(")");
            sb.append(", ").append(event.getPressure(i));
            sb.append(", ").append(event.getSize(i)).append(";");
        }
        return sb.toString();
    }


    public void draw(Canvas canvas) {
        super.draw(canvas);
        // RectF oval3 = new RectF(250, 50, 350, 400);
        //canvas.drawOval(oval3,myPaints[1] );
//		if(IsDown)
//			canvas.drawLine(X0, Y0, X, Y, myPaint);
        MotionEvent event = lastEvent;
        if (lastEvent != null) {
            int numPointers =0;
            int action = event.getAction();
            if (action == MotionEvent.ACTION_MOVE) {
                numPointers = event.getPointerCount();
                int ptrIdx = 0;
                while (ptrIdx < numPointers) {
                    int ptrId = event.getPointerId(ptrIdx);
                    if (ptrId >= 0 && ptrId < MAX_POINTERS) {
                        if (starts[ptrId] != null) {
//							float X0 = starts[ptrId].x;
//							float Y0 = starts[ptrId].y;
                            float X = event.getX(ptrIdx) - viewOffset.x;
                            float Y = event.getY(ptrIdx) - viewOffset.y;
//							float R = 80;
                            touch_move((X),(Y),ptrId);

//							String str = ptrIdx + ":id" + ptrId;
//
//							canvas.drawLine(X0, Y0, X, Y, myPaints[ptrId]);
//							canvas.save();
//							canvas.translate(X, Y);
//							canvas.drawText(str, -R / 1.4f, -R / 1.4f, myPaints[ptrId]);
//							canvas.restore();
                            //canvas.drawPath(d_Path_list.get(ptrId), myPaints[ptrId]);
//							canvas.save();
//							canvas.restore();
                            // RectF oval = new RectF(X,Y,150,100);

                            try{
                                //canvas.drawPath(d_Path_list.get(ptrId), myPaints[ptrId]);
                                myPaints[ptrId].setStyle(Paint.Style.FILL);
                                canvas.drawOval(new RectF(dX_list.get(ptrId)-15*(ptrId+1),dY_list.get(ptrId) -5*(ptrId+1),dX_list.get(ptrId)+15*(ptrId+1),dY_list.get(ptrId) +5*(ptrId + 1)), myPaints[ptrId]);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    ptrIdx++;
                }
            }
            for (int i = 0; i < d_Path_list.size(); i++  )
            {
                try{
                    //canvas.drawPath(d_Path_list.get(i), myPaints[i]);
                    myPaints[i].setStyle(Paint.Style.FILL);
                    canvas.drawOval(new RectF(dX_list.get(i)-15*(i+1),dY_list.get(i) -5*(i+1),dX_list.get(i)+15*(i+1),dY_list.get(i) +5*(i + 1)), myPaints[i]);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    private void touch_start(int ptrId)
    {
        //    undonePaths.clear();
        Path  d_Path=new Path();

        float X = starts[ptrId].x;
        float Y = starts[ptrId].y;
        d_Path_list.add(ptrId,d_Path);

        d_Path_list.get(ptrId).reset();

        d_Path_list.get(ptrId).moveTo(X,Y);

        dX_list.add(ptrId,X);
        dY_list.add(ptrId,Y);

    }


    private void touch_move(float x, float y,int ptrId)
    {
        float dx = Math.abs(x - dX_list.get(ptrId));
        float dy = Math.abs(y - dY_list.get(ptrId));
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE)
        {
            d_Path_list.get(ptrId).quadTo(dX_list.get(ptrId), dY_list.get(ptrId), (x + dX_list.get(ptrId))/2, (y + dY_list.get(ptrId))/2);
            try{

                dX_list.remove(ptrId);
                dY_list.remove(ptrId);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            dX_list.add(ptrId,x);
            dY_list.add(ptrId,y);
        }
    }
}
