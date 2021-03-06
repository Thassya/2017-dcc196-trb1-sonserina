package com.sonserina.ufjf.slytherinpride.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.sonserina.ufjf.slytherinpride.R;
import com.sonserina.ufjf.slytherinpride.models.Participante;
import java.util.Comparator;
import java.util.List;

/**
 * Created by thassya on 21/10/17.
 */

public class ParticipanteAdapter extends ArrayAdapter<Participante> {

    private static final Comparator<Participante> comparatorParticipante = new Comparator<Participante>() {
        public int compare(Participante p1, Participante p2) {
            return p1.getNome().toLowerCase().compareTo(p2.getNome().toLowerCase());
        }
    };

    public ParticipanteAdapter(Context context, List<Participante> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Participante participante = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_view, parent,false);
        }
        TextView txt_lista = convertView.findViewById(R.id.txt_lista);
        txt_lista.setText(participante.getNome());
        //sem email..
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        this.setNotifyOnChange(false);
        this.sort(comparatorParticipante);
        super.notifyDataSetChanged();
        this.setNotifyOnChange(true);
    }
}
