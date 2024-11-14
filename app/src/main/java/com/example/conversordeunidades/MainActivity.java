package com.example.conversordeunidades;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner initialSpinner = (Spinner) findViewById(R.id.input_convert_spinner);
        Spinner finalSpinner = (Spinner) findViewById(R.id.output_convert_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.units_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        initialSpinner.setAdapter(adapter);
        finalSpinner.setAdapter(adapter);

        initialSpinner.setSelection(1);
        finalSpinner.setSelection(3);

   }

   public void Converter(View view){
        Spinner inputSpinner, outputSpinner;
        TextView valueResult;
        EditText editInput;
        Double result;

        valueResult = findViewById(R.id.text_result);
        editInput = findViewById(R.id.edit_Input_value);

        double valueEditInput = Double.parseDouble(editInput.getText().toString());

        inputSpinner = findViewById(R.id.input_convert_spinner);
        outputSpinner = findViewById(R.id.output_convert_spinner);

         var valorInputSpinner = inputSpinner.getSelectedItem();
         var valorOutputSpinner = outputSpinner.getSelectedItem();

       valueResult.setText("U$: " + String.format("%.2f", valueEditInput));

        if (valorInputSpinner.equals("Metros")) {
            if (valorOutputSpinner.equals("Centimetros")) {
                result = valueEditInput * 100;
            } else if (valorOutputSpinner. equals("Quilometros")) {
                result = valueEditInput / 1000;
            } else if (valorOutputSpinner. equals("Milhas")) {
                result = valueEditInput / 1609.34;
            }
       }
    }
}