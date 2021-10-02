package com.example.adjustableimage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private int lastX, lastY;

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        int lastX = 0, lastY = 0;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                /*
                方法一：layout(getLeft()+offsetX, getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                */
                /*
                方法二：
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                 */
                //方法三：
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
                layoutParams.leftMargin = getLeft()+offsetX;
                layoutParams.topMargin = getTop()+offsetY;
                setLayoutParams(layoutParams);
                break;

        }
        return true;
    }
}
