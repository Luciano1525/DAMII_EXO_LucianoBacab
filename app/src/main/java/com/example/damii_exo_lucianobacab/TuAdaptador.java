package com.example.damii_exo_lucianobacab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TuAdaptador extends BaseAdapter {
    Context context1;
    List<DatosTabla> lst1;

    public TuAdaptador(Context context1, List<DatosTabla> lst1) {
        this.context1 = context1;
        this.lst1 = lst1;
    }

    @Override
    public int getCount(){
        return lst1.size();
    }

    @Override
    public Object getItem(int i){
        return i;
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup){
        ImageView ivProduc;
        TextView tvNombre;
        TextView tvPreci;
        TextView tvCant;
        DatosTabla c1 = lst1.get(i);

        if(view1==null){
            view1 = LayoutInflater.from(context1).inflate(R.layout.listview, null);
            ivProduc = view1.findViewById(R.id.ivProduc);
            tvNombre = view1.findViewById(R.id.tvNombre);
            tvPreci = view1.findViewById(R.id.tvPreci);
            tvCant = view1.findViewById(R.id.tvCant);

            ivProduc.setImageResource(c1.ivProduc);
            tvNombre.setText(c1.tvNombre);
            tvPreci.setText(c1.tvPreci);
            tvCant.setText(c1.tvCant);
        }

        return view1;

    }

}
