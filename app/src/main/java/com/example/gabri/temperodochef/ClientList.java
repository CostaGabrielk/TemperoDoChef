package com.example.gabri.temperodochef;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gabri.temperodochef.R;
import com.example.gabri.temperodochef.model.Client;

import java.util.List;



public class ClientList extends ArrayAdapter<Client> {

    private Activity context;
    private List<Client> clientList;

    public ClientList(Activity context, List<Client> clientList){
        super(context, R.layout.client_list_layout, clientList);
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.client_list_layout,null,true  );

        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.txtvwNome);
        TextView textViewFone = (TextView) listViewItem.findViewById(R.id.txtvwFone);
        TextView textViewEndereco = (TextView) listViewItem.findViewById(R.id.txtvwEndereco);

        Client client = clientList.get(position);

        textViewNome.setText(client.getClienteNome());
        textViewFone.setText(client.getClienteFone());
        textViewEndereco.setText(client.getClienteEndereco());

        return listViewItem;

    }
}
