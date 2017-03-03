package com.benny.reader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Marvin on 2017-03-02.
 */

public class IconView extends TextView {
    public IconView(Context context) {
        super(context);
        init();
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setTypeface(MainActivity.getInstance().getIconTypeface());
    }
}
