package com.example.sqlite_app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final ItemClickListener recyclerClickListener;
    private final ArrayList<User> users;
    private final LayoutInflater inflater;

    public UserAdapter(Context context, ArrayList<User> arrayList, ItemClickListener listener) {
        users = arrayList;
        inflater = LayoutInflater.from(context);
        recyclerClickListener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_user_recycler_view, parent, false);
        return new ViewHolder(itemView, recyclerClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.id.setText(String.format("Id = %d", user.id));
        holder.name.setText(String.format("Name = %s", user.name));
        holder.phone.setText(String.format("Name = %s", user.phone));
        holder.birthdate.setText(String.format("Name = %s", user.birthdate));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView id, name, phone, birthdate;
        final ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView, ItemClickListener listener) {
            super(itemView);
            id = itemView.findViewById(R.id.item_id_tv);
            name = itemView.findViewById(R.id.item_name_tv);
            phone = itemView.findViewById(R.id.item_phone_tv);
            birthdate = itemView.findViewById(R.id.item_birthdate_tv);

            itemClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnUserClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void OnUserClick(int position);
    }
}


