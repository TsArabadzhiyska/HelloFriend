package com.example.hellofriend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    ArrayList<String> contact_id, name, phone, hint;

    CustomRecyclerViewAdapter(Activity activity, Context context, ArrayList contact_id, ArrayList name,
                              ArrayList phone, ArrayList hint){
        this.activity = activity;
        this.context = context;
        this.contact_id = contact_id;
        this.name = name;
        this.phone = phone;
        this.hint = hint;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_single_record_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.contact_id_txt.setText(String.valueOf(contact_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.phone_txt.setText(String.valueOf(phone.get(position)));
        holder.hint_txt.setText(String.valueOf(hint.get(position)));

        //Recyclerview onClickListener евент
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateContactActivity.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("phone", String.valueOf(phone.get(position)));
                intent.putExtra("hint", String.valueOf(hint.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contact_id_txt, name_txt, phone_txt, hint_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_id_txt = itemView.findViewById(R.id.contact_id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            phone_txt = itemView.findViewById(R.id.phone_txt);
            hint_txt = itemView.findViewById(R.id.hint_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
