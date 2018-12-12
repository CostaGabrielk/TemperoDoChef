package com.example.gabri.temperodochef;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> nomeClienteList;
    ArrayList<String> foneClienteList;
    ArrayList<String> enderecoClienteList;

    class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView nomeCliente, foneCliente, enderecoCliente;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeCliente = (TextView) itemView.findViewById(R.id.txtvwNome);
            foneCliente = (TextView) itemView.findViewById(R.id.txtvwFone);
            enderecoCliente = (TextView) itemView.findViewById(R.id.txtvwEndereco);

        }
    }

    public SearchAdapter(Context context, ArrayList<String> nomeClienteList, ArrayList<String> foneClienteList, ArrayList<String> enderecoClienteList) {
        this.context = context;
        this.nomeClienteList = nomeClienteList;
        this.foneClienteList = foneClienteList;
        this.enderecoClienteList = enderecoClienteList;
    }


    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.searchclient_list_layout, viewGroup, false);

        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

        searchViewHolder.nomeCliente.setText(nomeClienteList.get(i));
        searchViewHolder.foneCliente.setText(foneClienteList.get(i));
        searchViewHolder.enderecoCliente.setText(enderecoClienteList.get(i));



    }


    @Override
    public int getItemCount() {
        return nomeClienteList.size();
    }
}
