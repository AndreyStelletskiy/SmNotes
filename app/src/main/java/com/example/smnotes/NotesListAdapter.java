package com.example.smnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NotesListAdapter extends ListAdapter<Notes, NotesListAdapter.NotesViewHolder> {

    static class NotesViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemView;
        private final TextView nameItemView;

        private final TextView topicItemView;

        private NotesViewHolder(View itemView) {
            super(itemView);
            nameItemView= itemView.findViewById(R.id.N_Name);
            topicItemView = itemView.findViewById(R.id.N_Topic);
            noteItemView = itemView.findViewById(R.id.N_Note);
        }

        public void bind(String name, String topic, String note) {
            noteItemView.setText(note);
            nameItemView.setText(name);
            topicItemView.setText(topic);
        }

        static NotesViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new NotesViewHolder(view);
        }
    }


    public NotesListAdapter(@NonNull DiffUtil.ItemCallback<Notes> diffCallback) {
        super(diffCallback);
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NotesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Notes current = getItem(position);
        holder.bind(current.getName(), current.getTopic(), current.getNote());
    }

    static class NotesDiff extends DiffUtil.ItemCallback<Notes> {

        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getNote().equals(newItem.getNote());
        }
    }
}
