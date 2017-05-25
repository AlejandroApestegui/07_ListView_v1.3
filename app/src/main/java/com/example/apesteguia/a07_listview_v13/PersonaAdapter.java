package com.example.apesteguia.a07_listview_v13;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by apesteguia on 25/05/2017.
 */

public class PersonaAdapter extends ArrayAdapter<Persona> {

    public PersonaAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<Persona>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView tvNombre, tvEdad, tvDocumento;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.persona_item, parent, false);
        }
        tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
        tvDocumento = (TextView) convertView.findViewById(R.id.tvDocumento);
        tvEdad = (TextView) convertView.findViewById(R.id.tvEdad);

        Persona persona = getItem(position);
        tvNombre.setText(persona.getNombre() + " / " + persona.getApellido());
        tvDocumento.setText(persona.getDocumento());
        tvEdad.setText("" + persona.getEdad());
        return convertView;
    }
}
