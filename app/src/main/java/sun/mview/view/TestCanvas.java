package sun.mview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yaozong on 2017/3/14.
 */

public class TestCanvas extends View {
    public TestCanvas(Context context) {
        this(context, null);
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
