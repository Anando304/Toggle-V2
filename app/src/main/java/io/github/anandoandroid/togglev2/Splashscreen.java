package io.github.anandoandroid.togglev2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //main where all the functions run. similar to Void loop of Arduino C
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread thread = new Thread(){ //splashscreen using Threads
            public void run(){
                try{
                    sleep(1500); // Keep the splashscreen for 3 seonds
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class); // AFter splashscreen, it'll go from splashscreen to MainActivity UI
                            startActivity(intent); // Starts the Intent to go to main page.
                    finish(); //After completing the splashscreen, destroys the program so it doesn't re-run
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
            thread.start();
    }

    /*Method 2 Splashscreen using Handlers, more optimized
    Intent intent = new Intent(this, MainActivity.class); //"this" refers to the current java file just like how getApplicationContext()
    refers to the same thing in handlers

   new Handler().postDelayed(new Runnable(){ if handler at the end with runnable, then future delay. If in the beginning like here, then present delay
        @Override
        public void run() {
            startActivity(intent);
            finish();
        }
    }, delaytime);
} */
}
