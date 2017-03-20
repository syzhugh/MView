package sun.mview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sun.mview.R;
import sun.mview.view.TestBezier;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_paint)
    Button mMainPaint;
    @BindView(R.id.main_view)
    Button mMainView;
    @BindView(R.id.main_viewgroup)
    Button mMainViewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TestBezier bezier = (TestBezier) findViewById(R.id.main_bezier);
        bezier.startAnim();

        Intent it = new Intent(MainActivity.this, AnimationAct.class);
        startActivity(it);
    }

    @OnClick({R.id.main_paint, R.id.main_view, R.id.main_viewgroup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_paint:
                Intent it = new Intent(MainActivity.this, PaintAct.class);
                startActivity(it);
                break;
            case R.id.main_view:
                Intent it1 = new Intent(MainActivity.this, ViewAct.class);
                startActivity(it1);
                break;
            case R.id.main_viewgroup:
                Intent it2 = new Intent(MainActivity.this, ViewGroupAct.class);
                startActivity(it2);
                break;
        }
    }
}
