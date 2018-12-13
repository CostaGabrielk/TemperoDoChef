package com.example.gabri.temperodochef;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabri.temperodochef.model.Order;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    ArrayList<Order> orders;

    public OrderAdapter(Context c, ArrayList<Order> o){
        context = c;
        orders = o;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.retrieveorder_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {

        orderViewHolder.opcao.setText(orders.get(i).getOpcao());
        orderViewHolder.tamanho.setText(orders.get(i).getTamanho());
        orderViewHolder.formaPgto.setText(orders.get(i).getFormaPgto());
        orderViewHolder.troco.setText(orders.get(i).getTroco());


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView opcao, tamanho, formaPgto, troco;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);


            opcao = (TextView) itemView.findViewById(R.id.txtvwOpcao);
            tamanho = (TextView) itemView.findViewById(R.id.txtvwTamanho);
            formaPgto = (TextView) itemView.findViewById(R.id.txtvwPgto);
            troco = (TextView) itemView.findViewById(R.id.txtvwTroco);
        }
    }
}
