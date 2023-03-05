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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

public class NoteADD extends Fragment{
    private NoteViewModel mNoteViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_create,container,false);
        Button btn = view.findViewById(R.id.ADDNote);
        EditText name = view.findViewById(R.id.New_Name);
        EditText topic = view.findViewById(R.id.New_Topic);
        EditText nnote = view.findViewById(R.id.New_Note);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        btn.setOnClickListener(view1 -> {
            Notes note = new Notes(name.getText().toString(), topic.getText().toString(), nnote.getText().toString());
            mNoteViewModel.insert(note);
        });
        return view;
    }
}
