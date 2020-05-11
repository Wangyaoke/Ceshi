package com.ansiyida.cgjl.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
import com.ansiyida.cgjl.R;

public class Textview_lean  extends TextView {
    public Textview_lean(Context context) {
super(context);
 }

 public Textview_lean(Context context, AttributeSet attrs) {
super(context, attrs);
 }

 @Override
 protected void onDraw(Canvas canvas) {
//倾斜度45,上下左右居中
canvas.rotate(45, getMeasuredWidth()/3, getMeasuredHeight()/3);
super.onDraw(canvas);
 }

}