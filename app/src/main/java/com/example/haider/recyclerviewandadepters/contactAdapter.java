package com.example.haider.recyclerviewandadepters;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.viewHolder>{
    Context context;
    ArrayList<dataSetter> data;

    public contactAdapter(Context context, ArrayList<dataSetter> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater=LayoutInflater.from(context).inflate(R.layout.recyler_layout,parent,false);
        return new viewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView1.setImageResource(data.get(position).img);
        holder.name1.setText(data.get(position).name);
        holder.number1.setText(data.get(position).number);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_update_activity);
                dialog.show();
                EditText na=dialog.findViewById(R.id.txtName);
                EditText nu=dialog.findViewById(R.id.txtNumber);

                na.setText(data.get(position).name);
                nu.setText(data.get(position).number);

                dialog.findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String n1=na.getText().toString();
                        String n2=nu.getText().toString();

                        data.set(position,new dataSetter(data.get(position).img , n1,n2));
                        notifyItemChanged(position);
                        dialog.dismiss();

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView name1;
        TextView number1;
        ImageView imageView1;
        LinearLayout layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name1=itemView.findViewById(R.id.txtName);
            number1=itemView.findViewById(R.id.txtNumber);
            imageView1=itemView.findViewById(R.id.imgProfile);
            layout=itemView.findViewById(R.id.linearLayout);
        }
    }

}
