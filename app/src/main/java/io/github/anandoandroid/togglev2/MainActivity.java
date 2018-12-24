package io.github.anandoandroid.togglev2;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    public WifiManager wifimanager; // Used to communicate with wifimodule and wifi on the phone
    public BluetoothAdapter bluetoothmanager;
    int num1 = 0; // time in seconds variable
    Button wifibutton,exitbutton,bluetoothbutton,aboutbutton;
    Handler handler;
    Runnable runnable;
    EditText input; //textbox for time input variable

    private void time() { //External function created to hold the input values
        input = (EditText) findViewById(R.id.editText2); // finds the value of input and saves it as an EditText
        num1 = 60000 *(Integer.parseInt(input.getText().toString())); //Converts the EditText value to a String and then parses it to an Integer multiplied by 60000 to make it a minute
        Toast.makeText(getApplicationContext(), "Your time delay is " + input.getText().toString() + " minutes",
                Toast.LENGTH_SHORT).show();
    }

    public void Exit()  //Used to end the program and go to Homescreen of device
    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME); //Intent is used to go to main page of homescreen
        startActivity(startMain);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) { //main where all the functions run. similar to Void loop of Arduino C
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //refers to the XML file for main activity

        wifibutton = (Button) findViewById(R.id.WIFI); // sets the button with ID WIFI as a variable called wifibutton
        exitbutton = (Button) findViewById(R.id.EXIT);
        bluetoothbutton = (Button) findViewById(R.id.BLUETOOTH);
        aboutbutton = (Button) findViewById(R.id.aboutbutton);

        handler = new Handler(); //schedule messages and runnables to be executed at some point in the future


            wifibutton.setOnClickListener(new View.OnClickListener() { //View.OnClickListener detects if wifibutton has been pressed
                @Override
                public void onClick(View v) { //Function that runs when button is clicked
                    time();

                    runnable = new Runnable() { //basically runs the function belowin the future once the time delay input has been reached. Could also use threads with try and catch method
                        @Override
                        public void run() {
                            wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE); //casts or puts an object into another object type.Connects manager of wifi to system wifi
                            wifimanager.setWifiEnabled(false);
                        }
                    };
                    handler.postDelayed(runnable, num1); // acts similar to a while statement where it runs the runnable functions for time num1.
                }
            });


            bluetoothbutton.setOnClickListener(new View.OnClickListener() { //View.OnClickListener detects if bluetoothbutton has been pressed
                @Override
                public void onClick(View v) { //Function that runs when button is clicked
                    time();

                    runnable = new Runnable() { //basically runs the function below when the time delay input has been reached.
                        @Override
                        public void run() {
                            bluetoothmanager = BluetoothAdapter.getDefaultAdapter(); //Used to set up Bluetooth module to mobile bluetooth software //casts or puts an object into another object type.Connects manager of wifi to system wifi
                            bluetoothmanager.disable(); // disables bluetooth
                        }
                    };
                    handler.postDelayed(runnable, num1); //Runs the run function for the given time inputted, num1.
                    //If handler postDelayed put on the top instead, it does a present delay. Runnable is the context to which it works while num1 is the time to reach
                }
            });


            exitbutton.setOnClickListener(new View.OnClickListener() { //View.OnClickListener detects if exitbutton has been pressed
                @Override
                public void onClick(View v) { //Function that runs when button is clicked
                    Exit();


                }
            });


        aboutbutton.setOnClickListener(new View.OnClickListener() { //View.OnClickListener detects if button has been pressed
            @SuppressLint("PrivateApi")
            @Override
            public void onClick(View v) { //Function that runs when button is clicked. Runs the intent which redirects to another website
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://anando304.github.io/")); // intents are used to go from one page to another.
                startActivity(viewIntent); // starts the intent activity and redirects to that page. Always create "new intent"
               // Intent newpage = new Intent(this, Splashscreen.class), This can be used to go from one page to the other.
                // The context is this page all the objects are here while the final destination is Splashscreen class.
                // Starr the activity by doing startActivity(newpage);


            }
        });



        }










    }




