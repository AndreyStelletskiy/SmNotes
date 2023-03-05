package com.example.smnotes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class NotesViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private NotesViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.N_Name);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static NotesViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new NotesViewHolder(view);
    }
}
