package com.example.gabri.temperodochef;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gabri.temperodochef.model.Client;
import com.example.gabri.temperodochef.model.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderList extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> orderList;

    public OrderList(Activity context, List<Order> orderList) {

        super(context, R.layout.order_list_layout, orderList);
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.order_list_layout, null, true);

        TextView textViewData = (TextView) listViewItem.findViewById(R.id.txtvwData);
        TextView textViewOpcao = (TextView) listViewItem.findViewById(R.id.txtvwOpcao);
        TextView textViewTamanho = (TextView) listViewItem.findViewById(R.id.txtvwTamanho);
        TextView textViewPgto = (TextView) listViewItem.findViewById(R.id.txtvwPgto);
        TextView textViewTroco = (TextView) listViewItem.findViewById(R.id.txtvwTroco);

        Order order = orderList.get(position);


        textViewData.setText("Data da compra: ");
        textViewOpcao.setText(order.getOpcao());
        textViewTamanho.setText((order.getTamanho()));
        textViewPgto.setText(order.getFormaPgto());
        textViewTroco.setText( order.getTroco());


        return listViewItem;
    }
}
