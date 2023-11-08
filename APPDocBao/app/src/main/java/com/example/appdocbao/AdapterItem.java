package com.example.appdocbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterItem extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Item> arrList;

    public AdapterItem(Context context, int layout, List<Item> arrList) {
        this.context = context;
        this.layout = layout;
        this.arrList = arrList;
    }

    @Override
    public int getCount() {
        return arrList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        Item item = arrList.get(i);

        //anh xa
        TextView tieude = view.findViewById(R.id.tieude);
        TextView mota = view.findViewById(R.id.mota);
        TextView ngaydang = view.findViewById(R.id.ngaydang);

        tieude.setText(item.getTieude());
        mota.setText(item.getMota());
        ngaydang.setText(item.getNgaydang());

        return view;
    }
}
