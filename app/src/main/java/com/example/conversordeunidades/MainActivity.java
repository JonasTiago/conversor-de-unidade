package com.example.conversordeunidades;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        Spinner initialSpinner = findViewById(R.id.input_convert_spinner);
        Spinner finalSpinner = findViewById(R.id.output_convert_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.units_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        initialSpinner.setAdapter(adapter);
        finalSpinner.setAdapter(adapter);

        initialSpinner.setSelection(1);
        finalSpinner.setSelection(0);
    }

    public void Converter(View view) {
        EditText editInput = findViewById(R.id.edit_Input_value);
        TextView valueResult = findViewById(R.id.text_result);

        if (TextUtils.isEmpty(editInput.getText())) {
            editInput.setError("Valor não pode ser vazio");
            return;
        }

        double valueEditInput;
        try {
            valueEditInput = Double.parseDouble(editInput.getText().toString());
        } catch (NumberFormatException e) {
            editInput.setError("Digite um número válido");
            return;
        }

        Spinner inputSpinner = findViewById(R.id.input_convert_spinner);
        Spinner outputSpinner = findViewById(R.id.output_convert_spinner);

        String valorInputSpinner = inputSpinner.getSelectedItem().toString();
        String valorOutputSpinner = outputSpinner.getSelectedItem().toString();

        double result = realizarConversao(valueEditInput, valorInputSpinner, valorOutputSpinner);

        valueResult.setText(result + " " + valorOutputSpinner);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private double realizarConversao(double valor, String unidadeInicial, String unidadeFinal) {
        switch (unidadeInicial) {
            case "Centímetros":
                if (unidadeFinal.equals("Metros")) return valor / 100;
                if (unidadeFinal.equals("Quilômetros")) return valor / 100000;
                if (unidadeFinal.equals("Milhas")) return valor / 160900;
                break;
            case "Milhas":
                if (unidadeFinal.equals("Metros")) return valor * 1609;
                if (unidadeFinal.equals("Quilômetros")) return valor * 1.609;
                if (unidadeFinal.equals("Centímetros")) return valor * 160900;
                break;
            case "Quilômetros":
                if (unidadeFinal.equals("Metros")) return valor * 1000;
                if (unidadeFinal.equals("Milhas")) return valor / 1.609;
                if (unidadeFinal.equals("Centímetros")) return valor * 100000;
                break;
            case "Metros":
                if (unidadeFinal.equals("Centímetros")) return valor * 100;
                if (unidadeFinal.equals("Quilômetros")) return valor / 1000;
                if (unidadeFinal.equals("Milhas")) return valor / 1609;
                break;
        }
        return 0.0;
    }
}
