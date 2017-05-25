package com.example.apesteguia.a07_listview_v13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ListView lvPersonas;
    private Button btnNuevo;
    private PersonaAdapter personaAdapter;
    private final int NUEVO = 0;
    private final int EDITAR = 1;

    private View.OnClickListener btnNuevoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(intent, NUEVO);
        }
    };
    private AdapterView.OnItemClickListener lvPersonasOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            Persona persona = personaAdapter.getItem(position);

            intent.putExtra("id", persona.getId());
            intent.putExtra("nombre", persona.getNombre());
            intent.putExtra("apellido", persona.getApellido());
            intent.putExtra("documento", persona.getDocumento());
            intent.putExtra("edad", persona.getEdad());

            startActivityForResult(intent, EDITAR);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPersonas = (ListView) findViewById(R.id.lvPersonas);
        btnNuevo = (Button) findViewById(R.id.btnNuevo);
        personaAdapter = new PersonaAdapter(MainActivity.this);

        btnNuevo.setOnClickListener(btnNuevoOnClickListener);
        lvPersonas.setOnItemClickListener(lvPersonasOnItemClickListener);
        lvPersonas.setAdapter(personaAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == NUEVO) {
            Persona persona = new Persona();
            persona.setId(UUID.randomUUID().toString());
            persona.setNombre(data.getStringExtra("nombre"));
            persona.setApellido(data.getStringExtra("apellido"));
            persona.setDocumento(data.getStringExtra("documento"));
            persona.setEdad(data.getIntExtra("edad", 0));
            personaAdapter.add(persona);
            personaAdapter.notifyDataSetChanged();
        } else if (requestCode == EDITAR) {
            int subAccion = data.getIntExtra("subAccion", Main2Activity.EDITAR);
            if (subAccion == Main2Activity.EDITAR) {
                for (int i = 0; i < personaAdapter.getCount(); i++) {
                    if (personaAdapter.getItem(i).getId().equals(data.getStringExtra("id"))) {
                        Persona persona = personaAdapter.getItem(i);
                        persona.setNombre(data.getStringExtra("nombre"));
                        persona.setApellido(data.getStringExtra("apellido"));
                        persona.setDocumento(data.getStringExtra("documento"));
                        persona.setEdad(data.getIntExtra("edad", 0));

                        personaAdapter.notifyDataSetChanged();
                    }
                }
            } else if (subAccion == Main2Activity.ELIMINAR) {
                for (int i = 0; i < personaAdapter.getCount(); i++) {
                    if (personaAdapter.getItem(i).getId().equals(data.getStringExtra("id"))) {
                        personaAdapter.remove(personaAdapter.getItem(i));
                        personaAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
