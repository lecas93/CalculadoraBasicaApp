package com.example.angel.calculadorabasica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText num1, num2;
    Button suma, resta, mult, div;
    TextView txtResultado;

    int n1, n2, res;

    //manera de obtener un string desde resources
    //getResources().getString(R.string.btn_suma)

    private String getStringResource(int id) {
        return getResources().getString(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        suma = findViewById(R.id.btnSumar);
        resta = findViewById(R.id.btnRestar);
        mult = findViewById(R.id.btnMultiplicar);
        div = findViewById(R.id.btnDividir);
        txtResultado = findViewById(R.id.txtResultado);

        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);

        if (savedInstanceState != null) {
            txtResultado.setText(savedInstanceState.getString("valor"));
        }
    }

    @Override
    public void onClick(View view) {
        try {
            n1 = Integer.parseInt(num1.getText().toString());
            n2 = Integer.parseInt(num2.getText().toString());
        } catch (NumberFormatException nfe) {
            Toast.makeText(getApplicationContext(), getStringResource(R.string.error_campoVacio), Toast.LENGTH_LONG).show();
            return;
        }
        res = 0;

        switch (view.getId()) {
            case R.id.btnSumar:
                res = n1 + n2;
                break;
            case R.id.btnRestar:
                res = n1 - n2;
                break;
            case R.id.btnMultiplicar:
                res = n1 * n2;
                break;
            case R.id.btnDividir:
                if (n2 == 0) {
                    Toast.makeText(getApplicationContext(), getStringResource(R.string.error_divCero), Toast.LENGTH_SHORT).show();
                    return;
                }
                res = n1 / n2;
                break;
        }

        txtResultado.setText("" + res);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("valor", txtResultado.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
