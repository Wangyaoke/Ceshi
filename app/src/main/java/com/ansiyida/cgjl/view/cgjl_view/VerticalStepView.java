package com.ansiyida.cgjl.view.cgjl_view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.ansiyida.cgjl.R;

import java.util.List;

public class VerticalStepView extends View {
    private ItemDrawable[] itemDrawables;
    int textSize = 15;
    int descriptionTextSize = 12;
    int drawableSize = 30;
    int drawableMarginText = 10;
    int spacing = 30;
    private int mTextColor = Color.BLACK;
    private int mDescriptionTextColor = Color.GRAY;
    private int mProgressLineColor = Color.GRAY;
    private TextPaint mPaint;
    private List<Item> contentItems;

    public VerticalStepView(Context context) {
        super(context);
    }

    public VerticalStepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VerticalStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerticalStepView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.VerticalStepView_textSize) {
                textSize = a.getDimensionPixelSize(attr, textSize);
            } else if (attr == R.styleable.VerticalStepView_drawableSize) {
                drawableSize = a.getDimensionPixelSize(attr, drawableSize);
            } else if (attr == R.styleable.VerticalStepView_spacing) {
                spacing = a.getDimensionPixelSize(attr, spacing);
            } else if (attr == R.styleable.VerticalStepView_drawableMarginText) {
                drawableMarginText = a.getDimensionPixelSize(attr, drawableMarginText);
            } else if (attr == R.styleable.VerticalStepView_textColor) {
                mTextColor = a.getColor(attr, mTextColor);
            } else if (attr == R.styleable.VerticalStepView_progressLineColor) {
                mProgressLineColor = a.getColor(attr, mProgressLineColor);
            } else if (attr == R.styleable.VerticalStepView_descriptionTextColor) {
                mDescriptionTextColor = a.getColor(attr, mDescriptionTextColor);
            } else if (attr == R.styleable.VerticalStepView_descriptionTextSize) {
                descriptionTextSize = a.getDimensionPixelSize(attr, descriptionTextSize);
            }
        }
        a.recycle();
        final Resources res = getResources();
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.density = res.getDisplayMetrics().density;
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        layoutItem();
        if (itemDrawables != null) {
            int height = getWantHeight();
            if (height > getMeasuredHeight()) {
                requestLayout();
                invalidate();
                return;
            }
            for (int i = 0; i < itemDrawables.length; i++) {
                ItemDrawable textDrawable = itemDrawables[i];
                textDrawable.draw(canvas);
                if (i > 0) {
                    textDrawable.getBounds();
                    drawConnectLine(canvas, textDrawable.getBounds().left + drawableSize / 2, itemDrawables[i - 1].getBounds().top + drawableSize + drawableSize / 10, textDrawable.getBounds().top - drawableSize / 10);
                }
            }
        }
    }

    private int getWantHeight() {
        int height = 0;
        if (itemDrawables != null) {
            for (ItemDrawable itemDrawable : itemDrawables) {
                height += itemDrawable.getIntrinsicHeight();
                height += spacing;
            }
            height -= spacing;
            height += getPaddingTop() + getPaddingBottom();
        }
        return height;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//获取宽度的测试模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//获取宽度的测试值
        int width;
        //如果宽度的测试模式等于EXACTLY，就直接赋值
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 800;//使用我们自己在代码中定义的宽度
            //如果宽度的测试模式等于AT_MOST，取测量值和计算值的最小值
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//获取高度的测试模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//获取高度的测试值
        int height = 0;
        //如果高度的测试模式等于EXACTLY，就直接赋值
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            //计算出整个View的高度
            if (itemDrawables != null) {
                height = getWantHeight();
            } else {
                height = 200;
            }
            //如果高度的测试模式等于AT_MOST，取测量值和计算值的最小值
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }
        setMeasuredDimension(width, height);//来存储测量的宽，高值
    }

    private void drawConnectLine(Canvas canvas, int startX, int startY, int endY) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(mProgressLineColor);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3.0f);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10, 10}, 0);
        p.setPathEffect(dashPathEffect);

        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(startX, endY);
        canvas.drawPath(path, p);
    }

    private void layoutItem() {
        if (contentItems == null || contentItems.isEmpty()) {
            return;
        }
        int left = getPaddingLeft(), top = getPaddingTop();
        itemDrawables = new ItemDrawable[contentItems.size()];
        for (int i = 0; i < contentItems.size(); i++) {
            ItemDrawable itemDrawable = new ItemDrawable();
            itemDrawables[i] = itemDrawable;
            itemDrawable.setmTextColor(mTextColor);
            itemDrawable.setmDescriptionTextColor(mDescriptionTextColor);
            itemDrawable.setContent(contentItems.get(i).getIcon(),
                    contentItems.get(i).getContent(), contentItems.get(i).getDescription(), mPaint, textSize, descriptionTextSize,
                    getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                    drawableSize,
                    drawableMarginText
            );
            itemDrawable.setBounds(new Rect(left, top, left + itemDrawable.getIntrinsicWidth(), top + itemDrawable.getIntrinsicHeight()));
            top += itemDrawable.getIntrinsicHeight();
            top += getSpacing();
        }
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getDrawableSize() {
        return drawableSize;
    }

    public void setDrawableSize(int drawableSize) {
        this.drawableSize = drawableSize;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public List<Item> getContentItems() {
        return contentItems;
    }

    public void setContentItems(List<Item> contentItems) {
        this.contentItems = contentItems;
        invalidate();
    }

    private class ItemDrawable {
        private TextPaint mPaint;
        private String content = "";
        private String[] arrContent;
        private String description = "";
        private String[] arrDescription;
        private Rect bounds = new Rect();
        private Drawable icon;
        private int intrinsicWidth;
        private int intrinsicHeight;
        private int iconSize;
        private int iconMarginTextWidth;
        private int fontHeight;
        private int descriptionFontHeight;
        private int textSize;
        private int descriptionTextSize;
        private int mTextColor = Color.BLACK;
        private int mDescriptionTextColor = Color.GRAY;

        public int getmTextColor() {
            return mTextColor;
        }

        public void setmTextColor(int mTextColor) {
            this.mTextColor = mTextColor;
        }

        public int getmDescriptionTextColor() {
            return mDescriptionTextColor;
        }

        public void setmDescriptionTextColor(int mDescriptionTextColor) {
            this.mDescriptionTextColor = mDescriptionTextColor;
        }

        public String getContent() {
            return content;
        }

        /**
         * @param icon                左侧的图标
         * @param content             内容
         * @param width               总宽度
         * @param iconMarginTextWidth 文本与icon的距离
         * @param iconSize            icon宽度
         * @param p                   画笔
         */
        public void setContent(Drawable icon, String content, String description, TextPaint p, int textSize, int descriptionTextSize, int width, int iconSize, int iconMarginTextWidth) {
            this.mPaint = p;
            this.icon = icon;
            this.content = content;
            this.description = description;
            this.textSize = textSize;
            this.descriptionTextSize = descriptionTextSize;
            this.iconSize = iconSize;
            this.iconMarginTextWidth = iconMarginTextWidth;

            intrinsicWidth = width;
            intrinsicHeight = 0;

            Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
            if (!TextUtils.isEmpty(description)) {
                p.setTextSize(descriptionTextSize);
                arrDescription = autoSplit(description, p, width - iconMarginTextWidth - iconSize);
                descriptionFontHeight = fontMetrics.bottom - fontMetrics.top;
                intrinsicHeight += arrDescription.length * descriptionFontHeight;
            }
            p.setTextSize(textSize);
            arrContent = autoSplit(content, p, width - iconMarginTextWidth - iconSize);
            fontMetrics = p.getFontMetricsInt();
            fontHeight = fontMetrics.bottom - fontMetrics.top;
            intrinsicHeight += arrContent.length * fontHeight;
            intrinsicHeight = Math.max(intrinsicHeight, iconSize);
        }

        public Rect getBounds() {
            return bounds;
        }

        public void setBounds(Rect bounds) {
            this.bounds = bounds;
            int top;
            top = fontHeight > iconSize ? bounds.top + (fontHeight - iconSize) / 2 : bounds.top;
            icon.setBounds(bounds.left,
                    top,
                    bounds.left + iconSize,
                    top + iconSize);
        }

        public void draw(Canvas canvas) {
            int left, top;
            left = bounds.left;
            //绘制Icon
            icon.draw(canvas);

            //绘制文字
            left += iconSize + iconMarginTextWidth;
            top = fontHeight > iconSize ? bounds.top : bounds.top + (iconSize - fontHeight) / 2;
            mPaint.setTextSize(textSize);
            mPaint.setColor(mTextColor);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            Rect targetRect = new Rect(bounds.left, top, bounds.right, top + fontHeight);
            int baseline = targetRect.top + (targetRect.bottom - targetRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            for (String line : arrContent) {
                if (!TextUtils.isEmpty(line)) {
                    canvas.drawText(line, left, baseline, mPaint);
                    baseline += fontHeight;
                }
            }
            mPaint.setTextSize(descriptionTextSize);
            mPaint.setColor(mDescriptionTextColor);
            baseline -= fontHeight;
            baseline += descriptionFontHeight;
            if (arrDescription != null)
                for (String line : arrDescription) {
                    if (!TextUtils.isEmpty(line)) {
                        canvas.drawText(line, left, baseline, mPaint);
                        baseline += descriptionFontHeight;
                    }
                }
        }


        /**
         * 自动分割文本
         *
         * @param content 需要分割的文本
         * @param p       画笔，用来根据字体测量文本的宽度
         * @param width   最大的可显示像素（一般为控件的宽度）
         * @return 一个字符串数组，保存每行的文本
         */
        public String[] autoSplit(String content, TextPaint p, float width) {

            if (width <= 0) {
                return null;
            }
            int length = content.length();
            Rect bounds = new Rect();
            p.getTextBounds(content, 0, content.length(), bounds);
            float textWidth = bounds.width();
            if (textWidth <= width) {
                return new String[]{content};
            }

            int start = 0, end = 1, i = 0;
            int lines = (int) Math.ceil(textWidth / width); //计算行数
            String[] lineTexts = new String[lines];
            while (start < length && end < length) {
                if (p.measureText(content, start, end) >= width) {
                    lineTexts[i++] = content.substring(start, end - 1);
                    start = end - 1;
                    end--;
                }
                end++;
            }
            if (i < lineTexts.length)
                lineTexts[i++] = content.substring(start, end);
            return lineTexts;
        }

        public int getIntrinsicWidth() {
            return intrinsicWidth;
        }

        public void setIntrinsicWidth(int intrinsicWidth) {
            this.intrinsicWidth = intrinsicWidth;
        }

        public int getIntrinsicHeight() {
            return intrinsicHeight;
        }

        public void setIntrinsicHeight(int intrinsicHeight) {
            this.intrinsicHeight = intrinsicHeight;
        }

        public int getTextWidth(Paint paint, String str) {
            int w = 0;
            if (str != null && str.length() > 0) {
                int len = str.length();
                float[] widths = new float[len];
                paint.getTextWidths(str, widths);
                for (int j = 0; j < len; j++) {
                    w += (int) Math.ceil(widths[j]);
                }
            }
            return w;
        }
    }


    /**
     * 每一步对应的内容
     */
    public static class Item {
        /**
         * 内容
         */
        private String content;
        /**
         * 描述
         */
        private String description;
        /**
         * 左侧的图标
         */
        private Drawable icon;

        /**
         * 内容
         */
        public String getContent() {
            return content;
        }

        /**
         * 内容
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * 描述
         */
        public String getDescription() {
            return description;
        }

        /**
         * 描述
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * 左侧的图标
         */
        public Drawable getIcon() {
            return icon;
        }

        /**
         * 左侧的图标
         */
        public void setIcon(Drawable icon) {
            this.icon = icon;
        }
    }
}
