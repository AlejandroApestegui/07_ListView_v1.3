package com.example.apesteguia.a07_listview_v13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad, etDocumento;
    private Button btnGrabar, btnEliminar;
    public static final int EDITAR = 0;
    public static final int ELIMINAR = 1;
    private String id;

    private View.OnClickListener btnGrabarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validar()) {
                Toast.makeText(Main2Activity.this, "Ingresar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("apellido", etApellido.getText().toString());
            intent.putExtra("documento", etDocumento.getText().toString());
            intent.putExtra("edad", Integer.parseInt(etEdad.getText().toString()));
            intent.putExtra("subAccion", EDITAR);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    private View.OnClickListener btnEliminarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("subAccion", ELIMINAR);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etDocumento = (EditText) findViewById(R.id.etDocumento);
        btnGrabar = (Button) findViewById(R.id.btnGrabar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnGrabar.setOnClickListener(btnGrabarOnClickListener);
        btnEliminar.setOnClickListener(btnEliminarOnClickListener);

        Intent intent = getIntent();

        etNombre.setText(intent.getStringExtra("nombre"));
        etApellido.setText(intent.getStringExtra("apellido"));
        etDocumento.setText(intent.getStringExtra("documento"));
        id = intent.getStringExtra("id");
        int edad = intent.getIntExtra("edad", 0);

        if (id == null) {
            btnEliminar.setVisibility(View.INVISIBLE);
            etEdad.setText("");
        } else {
            etEdad.setText("" + edad);
        }

    }

    private boolean validar() {

        boolean valida = true;

        if (TextUtils.isEmpty(etNombre.getText())) {
            valida = false;
        }
        if (TextUtils.isEmpty(etApellido.getText())) {
            valida = false;
        }
        if (TextUtils.isEmpty(etEdad.getText())) {
            valida = false;
        }
        if (TextUtils.isEmpty(etDocumento.getText())) {
            valida = false;
        }

        return valida;

    }
}
