package com.example.wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView txtViewResults;

    //Added another line from local on Feb 8 project

    //Add another lilne from remote at 10.30 am 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);
        actionBar.setTitle(R.string.txtTitle);

        txtViewResults = findViewById(R.id.txtViewResults);
        EditText editTextInputWt = findViewById(R.id.editTextInputWt);
        RadioGroup radGroupConv = findViewById(R.id.radGroupConv);
        Button btnConvertWt = findViewById(R.id.btnConvertWt);
        radGroupConv.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radBtnLbsToKgs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert Pounds to Kilos",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radBtnKgsToLbs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert kilos to pounds",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

       // radGroupConv.getCheckedRadioButtonId() --> -1 if nothing is onContextItemSelected()
        // --> R.id.radBtnLbsToKgs if that radio button is checked
        // --> R.id.radBtnKgsToLbs if the other radio button is checked

        //First, set up a button click listener for btnConvertWt
        //In that listener, do the following:
        //Display an error message if no radio button is checked (id is -1)

        btnConvertWt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In that listener, do the following:
                //Display an error message if editTextInputWt is empty
                if (editTextInputWt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Error: Enter an input for the weight", Toast.LENGTH_SHORT).show();
                } else if (radGroupConv.getCheckedRadioButtonId() == -1) {
                    //Display an error message if no radio button is checked (id is -1)
                    Toast.makeText(MainActivity.this,
                            "Error: No radio Button is selected", Toast.LENGTH_SHORT).show();
                } else {
                    double inputNumWt = 0;
                    double outputNumWt = 0;
                    try {
                        inputNumWt = Integer.parseInt(editTextInputWt.getText().toString());
                        //If Pounds to Kilos is checked and parsed inputWt is > 1000,
                        // display an error message saying input weight in pounds must be <= 1000
                        if (radGroupConv.getCheckedRadioButtonId() == R.id.radBtnLbsToKgs) {
                            if (inputNumWt > 1000) {
                                Toast.makeText(MainActivity.this,
                                        "Error: Input weight in pounds must be < = 1000", Toast.LENGTH_SHORT).show();
                            } else {
                                //Display the output weight in txtViewResults using correct
                                //unit as Kgs (if converting from pounds to kilos) and
                                outputNumWt = inputNumWt/ 2.2;
                                txtViewResults.setText(String.format("Converted weight is : %.2f", outputNumWt));
                            }
                            //If Kilos to Pounds is checked and parsed inputWt is > 500,
                            // display an error message saying input weight in pounds must be <= 500
                            //Otherwise compute outputWtInPounds = inputWt*2.2
                        } else if (radGroupConv.getCheckedRadioButtonId() == R.id.radBtnKgsToLbs) {
                            if (inputNumWt < 500) {
                                Toast.makeText(MainActivity.this,
                                        "Error: Input weight in kilos must be <5000", Toast.LENGTH_SHORT).show();
                            } else {
                                //Display the output weight in txtViewResults using correct
                                //Lbs (if converting kilos to pounds)
                                outputNumWt = inputNumWt* 2.2;
                                txtViewResults.setText(String.format("Converted weight is : %.2f",outputNumWt));
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(MainActivity.this, "Invalid inputs", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
