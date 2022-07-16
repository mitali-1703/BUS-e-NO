package com.example.bus_e_noadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_e_noadmin.R;
import com.example.bus_e_noadmin.model.Model;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Model> userList;
    public Adapter(List<Model> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        String busNo = userList.get(position).getBusName();
        String driverName = userList.get(position).getBusRouteNumber();
        int image1 = userList.get(position).getEditIcon();
        int image2 = userList.get(position).getDeleteIcon();

        holder.setData(busNo,driverName,image1,image2);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text1;
        private TextView text2;
        private ImageView imageView1;
        private ImageView imageView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.item_title);
            text2 = itemView.findViewById(R.id.item_subTitle);
            imageView1 = itemView.findViewById(R.id.cardImage1);
            imageView2 = itemView.findViewById(R.id.cardImage2);
        }

        public void setData(String busNo, String driverName, int image1,int image2) {
            text1.setText(busNo);
            text2.setText(driverName);
            imageView1.setImageResource(image1);
            imageView2.setImageResource(image2);
        }
    }
}

