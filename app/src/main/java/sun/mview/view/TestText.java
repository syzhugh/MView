package sun.mview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yaozong on 2017/3/14.
 */

public class TestText extends View {
    public TestText(Context context) {
        this(context, null);
    }

    public TestText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
