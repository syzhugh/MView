package sun.mview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Yaozong on 2017/3/3.
 */

public class TestViewGroup extends ViewGroup {
    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
