package com.example.bus_e_no.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_e_no.R;
import com.example.bus_e_no.model.Model;

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

        String busNo = userList.get(position).getTextView1();
        String driverName = userList.get(position).getTextView2();
        int image = userList.get(position).getImageView();

        holder.setData(busNo,driverName,image);

        Bundle bundle = new Bundle();
        bundle.putString("Call","Call Driver");
        Utils.logEvent(this,"Call_Bus_Driver",bundle);

        String number = ("tel:+91"+);
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(number));
        start

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text1;
        private TextView text2;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.item_title);
            text2 = itemView.findViewById(R.id.item_subTitle);
            imageView = itemView.findViewById(R.id.cardImage);
        }

        public void setData(String busNo, String driverName, int image) {
            text1.setText(busNo);
            text2.setText(driverName);
            imageView.setImageResource(image);
        }
    }
}
