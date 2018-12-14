package com.example.gabri.temperodochef;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RetrieveAdapter extends RecyclerView.Adapter<RetrieveAdapter.RetrieveViewHolder> {

    private Context retrieveContext;
    private List<OrderR> retrieveList;

    public RetrieveAdapter(Context retrieveContext, List<OrderR> retrieveList) {
        this.retrieveContext = retrieveContext;
        this.retrieveList = retrieveList;
    }

    @NonNull
    @Override
    public RetrieveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(retrieveContext);
        View view = layoutInflater.inflate(R.layout.retrievelist_layout, null);
        return new RetrieveViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RetrieveViewHolder retrieveViewHolder, int i) {

        OrderR orderR = retrieveList.get(i);

        retrieveViewHolder.textViewOption.setText(orderR.getOpcao());
        retrieveViewHolder.textViewSize.setText(orderR.getTamanho());
        retrieveViewHolder.textViewPayment.setText(orderR.getFormaPgto());
        retrieveViewHolder.textViewChange.setText(orderR.getTroco());


    }

    @Override
    public int getItemCount() {
        return retrieveList.size();
    }

    class RetrieveViewHolder extends RecyclerView.ViewHolder{

        TextView textViewOption, textViewSize, textViewPayment, textViewChange;

        public RetrieveViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewOption = itemView.findViewById(R.id.tvtOption);
            textViewSize = itemView.findViewById(R.id.tvtSize);
            textViewPayment = itemView.findViewById(R.id.tvtPayment);
            textViewChange = itemView.findViewById(R.id.tvtChange);
        }
    }
}
