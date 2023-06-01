package com.ifsc.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import com.ifsc.myapplication.model.Nota;

public class NotaAdapterListView extends ArrayAdapter<Nota> {
	Context mContext;
	int mResource;
	
	public NotaAdapterListView(Context context, int resource, ArrayList<Nota> objects) {
		super(context, resource, objects);
		mContext = context;
		mResource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		convertView = layoutInflater.inflate(mResource, parent, false);
		
		TextView id = convertView.findViewById(R.id.textID);
		TextView titulo = convertView.findViewById(R.id.listTextViewTitulo);
		TextView texto = convertView.findViewById(R.id.listTextViewTexto);
		Nota nota = getItem(position);
		
		id.setText("ID Nota: " + nota.getIdNota().toString());
		titulo.setText(nota.getTitulo());
		texto.setText(nota.getTexto());
		
		return convertView;
	}
}
