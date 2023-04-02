package com.example.smnotes.noteadd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smnotes.R;

public class NotesListAdapter extends ListAdapter<Notes, NotesListAdapter.NotesViewHolder> {


    static class NotesViewHolder extends RecyclerView.ViewHolder {
        private NoteDao mnoteDao;
        private final TextView noteItemView;
        private final TextView nameItemView;
        private final Button changeItemView;
        private final TextView topicItemView;

        private NotesViewHolder(View itemView) {
            super(itemView);
            nameItemView= itemView.findViewById(R.id.N_Name);
            topicItemView = itemView.findViewById(R.id.N_Topic);
            noteItemView = itemView.findViewById(R.id.N_Note);
            changeItemView = itemView.findViewById(R.id.buttonChange);
            changeItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Заметка изменена", Toast.LENGTH_SHORT).show();
                    Bundle rr = new Bundle();
                    String nname = nameItemView.getText().toString();
                    String ntopic = topicItemView.getText().toString();
                    String nnote = noteItemView.getText().toString();
                    rr.putString("0", (nname + "/ /"+ ntopic + "/ /"+ nnote));
                    Navigation.findNavController(v).navigate(R.id.action_homes_to_changeNoteFragment,rr);
                }
            });


        }

        public void bind(String name, String topic, String note) {
            noteItemView.setText(note);
            nameItemView.setText(name);
            topicItemView.setText(topic);
            Notes nnote = new Notes(name, topic, note);
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

    public static class NotesDiff extends DiffUtil.ItemCallback<Notes> {

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
