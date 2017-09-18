package com.example.div;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    Button gen,submit;
    EditText et;
    public static final  int TIMES = 10;
    public static final  int MAX_GRADE = 100;
    private int currentIndex = 1;
    private int num1,num2;
    private int score ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        gen = (Button) findViewById(R.id.bt_gen);
        submit = (Button) findViewById(R.id.bt_submit);
        et = (EditText) findViewById(R.id.et);
        generate();
        String s = gen.getText().toString();
        gen.setText(s.replace("X",TIMES+""));
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = 1;
                generate();
                Toast.makeText(MainActivity.this," generate random problems success",Toast.LENGTH_LONG).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString();
                et.setText("");
                int num = Integer.parseInt(s);
                if(currentIndex< TIMES) {
                    if (num1 / num2 == num) {
                        score++;
                    }
                    currentIndex++;
                }else{
                    Toast.makeText(MainActivity.this,"Please generate random problems again",Toast.LENGTH_LONG).show();
                    return;
                }
                if(currentIndex==TIMES){
                    Toast.makeText(MainActivity.this,"Your Score is :"+(int)(score*1.0f/currentIndex*MAX_GRADE),Toast.LENGTH_LONG).show();
                    return;
                }else{
                    generate();
                }

            }
        });

    }
    private void generate(){
        Random random = new Random();
        while(true) {
            num1 = random.nextInt(100) + 1;
            num2 = random.nextInt(num1) + 1;
            if(num1%num2==0)break;
        }
        tv1.setText(num1+"");
        tv2.setText("÷"+num2);
    }
}
