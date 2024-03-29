package com.lindroy.materialdialog.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.lindroid.anddialog.R;
import com.lindroy.materialdialog.util.UtilKt;

/**
 * @author Lin
 * @date 2019/8/27
 * @function
 * @Description
 */
class MDButtonBarLayout extends LinearLayout {
    /**
     * Amount of the second button to "peek" above the fold when stacked.
     */
    private static final int PEEK_BUTTON_DP = 16;

    /**
     * Whether the current configuration allows stacking.
     */
    private boolean mAllowStacking = true;

    private int mLastWidthSize = -1;

    private int mMinimumHeight = UtilKt.getResPx(R.dimen.md_dialog_button_panel_height);

    public MDButtonBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonBarLayout);
//        mAllowStacking = ta.getBoolean(R.styleable.ButtonBarLayout_allowStacking, true);
//        ta.recycle();
    }

    public void setAllowStacking(boolean allowStacking) {
        if (mAllowStacking != allowStacking) {
            mAllowStacking = allowStacking;
            if (!mAllowStacking && getOrientation() == LinearLayout.VERTICAL) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (mAllowStacking) {
            if (widthSize > mLastWidthSize && isStacked()) {
                // We're being measured wider this time, try un-stacking.
                setStacked(false);
            }

            mLastWidthSize = widthSize;
        }

        boolean needsRemeasure = false;

        // If we're not stacked, make sure the measure spec is AT_MOST rather
        // than EXACTLY. This ensures that we'll still get TOO_SMALL so that we
        // know to stack the buttons.
        final int initialWidthMeasureSpec;
        if (!isStacked() && MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            initialWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST);

            // We'll need to remeasure again to fill excess space.
            needsRemeasure = true;
        } else {
            initialWidthMeasureSpec = widthMeasureSpec;
        }

        super.onMeasure(initialWidthMeasureSpec, heightMeasureSpec);

        if (mAllowStacking && !isStacked()) {
            final int measuredWidth = getMeasuredWidthAndState();
            final int measuredWidthState = measuredWidth & MEASURED_STATE_MASK;
            if (measuredWidthState == MEASURED_STATE_TOO_SMALL) {
                setStacked(true);

                // Measure again in the new orientation.
                needsRemeasure = true;
            }
        }

        if (needsRemeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        // Compute minimum height such that, when stacked, some portion of the
        // second button is visible.
        int minHeight = 0;
        final int firstVisible = getNextVisibleChildIndex(0);
        if (firstVisible >= 0) {
            final View firstButton = getChildAt(firstVisible);
            final LayoutParams firstParams = (LayoutParams) firstButton.getLayoutParams();
            minHeight += getPaddingTop() + firstButton.getMeasuredHeight()
                    + firstParams.topMargin + firstParams.bottomMargin;
            if (isStacked()) {
                final int secondVisible = getNextVisibleChildIndex(firstVisible + 1);
                if (secondVisible >= 0) {
                    minHeight += getChildAt(secondVisible).getPaddingTop()
                            + PEEK_BUTTON_DP * getResources().getDisplayMetrics().density;
                }
            } else {
                minHeight += getPaddingBottom();
            }
        }

        if (getMinimumHeight() != minHeight) {
            setMinimumHeight(minHeight);
        }
    }

    private int getNextVisibleChildIndex(int index) {
        for (int i = index, count = getChildCount(); i < count; i++) {
            if (getChildAt(i).getVisibility() == View.VISIBLE) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getMinimumHeight() {
        return Math.max(mMinimumHeight, super.getMinimumHeight());
    }

    private void setStacked(boolean stacked) {
        setOrientation(stacked ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
        setGravity(stacked ? Gravity.END : Gravity.BOTTOM);

        final View spacer = findViewById(R.id.spacer);
        if (spacer != null) {
            spacer.setVisibility(stacked ? View.GONE : View.INVISIBLE);
        }

        // Reverse the child order. This is specific to the Material button
        // bar's layout XML and will probably not generalize.
        final int childCount = getChildCount();
        for (int i = childCount - 2; i >= 0; i--) {
            bringChildToFront(getChildAt(i));
        }
    }

    private boolean isStacked() {
        return getOrientation() == LinearLayout.VERTICAL;
    }
}
