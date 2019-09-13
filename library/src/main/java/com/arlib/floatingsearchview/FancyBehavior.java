package com.arlib.floatingsearchview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FancyBehavior extends CoordinatorLayout.Behavior<FloatingSearchView> {

    public FancyBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingSearchView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingSearchView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        float translation = child.getTranslationY();
        float height = child.findViewById(R.id.search_query_section).getHeight() + 5f;

        if (dyConsumed > 0) {
            if (translation > -height) {

                child.setTranslationY(-dyConsumed + translation);
            } else {
                child.setTranslationY(-height);
            }

        } else if (dyConsumed < 0 && translation < 0) {


            if (-dyConsumed + translation > 0){
                child.setTranslationY(0);
            } else {
                child.setTranslationY(-dyConsumed + translation);
            }

        }
    }
}
