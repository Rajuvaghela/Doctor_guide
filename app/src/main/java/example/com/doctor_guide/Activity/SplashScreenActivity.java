package example.com.doctor_guide.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import example.com.homepagefordoctor_guide.R;

/**
 * Created by Khodal on 03/08/2017.
 */

public class SplashScreenActivity extends Activity {

    /**
     * The thread to process splash screen events
     */
    private Thread mSplashThread;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);




                // Splash screen view
        setContentView(R.layout.splash_activity);
        ImageView imageview = (ImageView)findViewById(R.id.Imageview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final SplashScreenActivity sPlashScreen = this;

        // The thread to wait for splash screen events
        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        wait(5000);
                    }
                } catch (InterruptedException ex) {
                }

                finish();

                // Run next activity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen,MainActivity.class);
                startActivity(intent);

            }
        };

        mSplashThread.start();


        Animation animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.rotate_move_down);
        imageview.startAnimation(animation);

        /**
         * Processes splash screen touch events
         */
    }

}
