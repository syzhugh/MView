package sun.mview.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import sun.mview.R;

public class AnimationAct extends AppCompatActivity {

    private static final String TAG = "AnimationAct";

    private View mView, mView2;
    private ScaleAnimation mScale;
    private AnimationSet mSet;
    private ValueAnimator mOfInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        init();
    }


    private void init() {
        //animation from xml
        mView = findViewById(R.id.animact_view1);
        mScale = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.animation_xml_scale);
        mSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.animation_xml_set);
        mScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        //animation from code
        Animation animation = new AlphaAnimation(0.3f, 1);


        //valueanimation
        mView2 = findViewById(R.id.animact_view2);

        mOfInt = ValueAnimator.ofInt(0, 200);
        mOfInt.setRepeatCount(ValueAnimator.INFINITE);
        mOfInt.setRepeatMode(ValueAnimator.REVERSE);
        mOfInt.setDuration(1000);
        mOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mView2.layout(value, mView2.getTop()
                        , value + mView2.getHeight(), mView2.getBottom());
            }
        });
        mOfInt.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "------AnimationAct------onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "------AnimationAct------onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "------AnimationAct------onAnimationRepeat");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.startAnimation(mScale);
        mOfInt.start();
    }
}
