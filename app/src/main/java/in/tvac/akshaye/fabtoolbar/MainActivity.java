package in.tvac.akshaye.fabtoolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button mRevealBtn;
    private LinearLayout mPopupLayout;

    private Button mHideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Reveal Animation");

        mRevealBtn = (Button) findViewById(R.id.revealBtn);
        mPopupLayout = (LinearLayout) findViewById(R.id.popupLayout);

        mHideBtn = (Button) findViewById(R.id.hideBtn);

        mRevealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 21 && mPopupLayout.getVisibility() == View.INVISIBLE){

                        // get the center for the clipping circle
                        int cx = mPopupLayout.getWidth() / 2;
                        int cy = mPopupLayout.getHeight() / 2;

                        // get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

                        // create the animator for this view (the start radius is zero)
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(mPopupLayout, cx, cy, 0, finalRadius);

                        // make the view visible and start the animation
                        mPopupLayout.setVisibility(View.VISIBLE);
                        anim.start();


                    } else {

                        mPopupLayout.setVisibility(View.VISIBLE);

                }

            }
        });

        mHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 21){

                    // get the center for the clipping circle
                    int cx = mPopupLayout.getWidth() / 2;
                    int cy = mPopupLayout.getHeight() / 2;

                    // get the initial radius for the clipping circle
                    float initialRadius = (float) Math.hypot(cx, cy);

                    // create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(mPopupLayout, cx, cy, initialRadius, 0);

                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mPopupLayout.setVisibility(View.INVISIBLE);
                        }
                    });

                    // start the animation
                    anim.start();

                } else {

                    mPopupLayout.setVisibility(View.INVISIBLE);

                }

            }
        });

    }
}
