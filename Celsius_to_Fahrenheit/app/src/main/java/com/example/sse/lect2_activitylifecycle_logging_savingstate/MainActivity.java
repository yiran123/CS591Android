/* (Problem 2)
File: MainActivity
==================
Function : Temperature Converter from C to F and vice-versa.
 */

package com.example.sse.lect2_activitylifecycle_logging_savingstate;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "LECT2_FLAG";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;


    private Button btnCtoF;
    private Button btnFtoC;
    private EditText txtInput;
    private EditText txtOutput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onCreate State Transition");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//Let's find our view references

        btnCtoF = (Button) findViewById(R.id.btnCtoF);
        btnFtoC = (Button) findViewById(R.id.btnFtoC);
        txtInput = (EditText) findViewById(R.id.txtInput);
        txtOutput = (EditText) findViewById(R.id.txtOutput);


        //MARK: Formula is correct
        btnCtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String I, O;
                Double DegI, DegO;
                I = txtInput.getText().toString();
                DegI = Double.parseDouble(I);
                DegO = DegI*9.0/5.0 + 32;   //todo, dblcheck formula.
                O = DegO.toString();
                txtOutput.setText(O);
                Toast.makeText(getApplicationContext()," C -> F !!!",Toast.LENGTH_SHORT).show();
            }
        });

        //MARK: Added rounded floating number when converting from F -> C
        btnFtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String I, O;
                Double DegI, DegO;
                I = txtInput.getText().toString();
                DegI = Double.parseDouble(I);
                DegO = (DegI-32) * 5.0/9.0;   //todo, dblcheck formula.
                Double roundedNumber = roundingNumber(DegO);

                Log.d(MyFlag,String.format("%f",roundedNumber));

                O = roundedNumber.toString();
                txtOutput.setText(O);
                Toast.makeText(getApplicationContext()," F -> C !!!",Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Useful Notes:
        // ctrl-O is a shortcut to override base methods
        // Alt-Ins is a shortcut to overriding base methods and more.

    @Override
    protected void onPause() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onPause State Transition");
        super.onPause();
    }


    @Override
    protected void onStart() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onStart State Transition");
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onSaveInstanceState State Transition");
        Log.i(MyFlag, "Bundling State of our views before they get destroyed");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        Log.i(MyFlag, "Retrieving our saved state from before... ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        super.onResume();
    }

// MARK: Rounding method

    public double roundingNumber(double input){

        int precision = 100000;

        return (Math.floor(input * precision + .5)/precision);

    }



//Handy Helpers...
    public String intToStr(Integer i)
    {
        return i.toString();
    }

    public int strToInt(String S)
    {
       return Integer.parseInt(S);
    }


}

