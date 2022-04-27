package com.example.bus_e_no.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_e_no.R;
import com.example.bus_e_no.model.Model;

import java.util.Arrays;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Model> userList;
    Context context;
    List<String> contactList = Arrays.asList("9834784738","6436483822","8974897384","8736139870","8074730740","7646238947",
                                            "7893784733","8959868590","8745875096","6895865555","7087570444","7580705809",
                                            "7490470909","9897908960","8998989898");

    public Adapter(Context context,List<Model> userList){
        this.context = context;
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

        holder.setData(busNo,driverName,image,position);
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

        public void makeCall(int index){
            String number = ("tel:+91"+contactList.get(index));
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(number));
            context.startActivity(callIntent);
        }

        public void setData(String busNo, String driverName, int image,int index) {
            text1.setText(busNo);
            text2.setText(driverName);
//            imageView.setImageResource(image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeCall(index);
                }
            });
        }
    }
}
