package com.example.smnotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

public class NoteADD extends DialogFragment {
    private NoteViewModel mNoteViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_notes,container,false);
        Button btn = view.findViewById(R.id.buttonadd);
        EditText name = view.findViewById(R.id.nameadd);
        EditText topic = view.findViewById(R.id.topicadd);
        EditText nnote = view.findViewById(R.id.noteadd);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        btn.setOnClickListener(view1 -> {
            Notes note = new Notes(name.getText().toString(), topic.getText().toString(), nnote.getText().toString());
            mNoteViewModel.insert(note);
            Objects.requireNonNull(getDialog()).dismiss();
        });
        return view;
    }
}
