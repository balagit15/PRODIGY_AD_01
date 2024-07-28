package com.example.sample_1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView sol, res;
    MaterialButton buttonC, buttonOpen, buttonClose;
    MaterialButton buttondiv, buttonadd, buttonsub, buttonmul, buttonequal;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sol = findViewById(R.id.solutionTv);
        res = findViewById(R.id.result);

        buttonC = findViewById(R.id.button_c);
        buttonOpen = findViewById(R.id.button_p1);
        buttonClose = findViewById(R.id.button_p2);
        buttondiv = findViewById(R.id.button_div);
        buttonmul = findViewById(R.id.button_mul);
        buttonsub = findViewById(R.id.button_sub);
        buttonadd = findViewById(R.id.button_add);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttondot = findViewById(R.id.button_dot);
        buttonAC = findViewById(R.id.button_ac);
        buttonequal = findViewById(R.id.button_equal);

        // Set onClickListeners
        buttonC.setOnClickListener(this);
        buttonOpen.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttondiv.setOnClickListener(this);
        buttonmul.setOnClickListener(this);
        buttonsub.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttondot.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonequal.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = sol.getText().toString();

        if (buttonText.equals("AC")) {
            sol.setText("");
            res.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            sol.setText(res.getText());
            return;
        }
        if (buttonText.equals("c")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        sol.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("err"))
        {
            res.setText(finalResult);
        }
    }
    String getResult(String data)
    {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0"," ");
            }
            return finalResult;
        }
        catch (Exception e)
        {
            return "err";
        }

    }



}
