package com.royce.sample3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by johnroycepunay on 9/18/2016.
 */
public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);

        final ImageView imageview = (ImageView) findViewById(R.id.imageView);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        imageview.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // imageview.startAnimation(animation2);
                finish();
                Intent intentAndroid = new Intent(getBaseContext(), Login.class);
                startActivity(intentAndroid);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });







    }

}
