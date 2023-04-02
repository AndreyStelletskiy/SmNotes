package com.example.smnotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.smnotes.noteadd.NoteViewModel;
import com.example.smnotes.noteadd.Notes;


public class NoteADD extends Fragment {
    private NoteViewModel mNoteViewModel;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_add, container, false);
        btn = view.findViewById(R.id.ADDNote);
        EditText name = view.findViewById(R.id.New_Name);
        EditText topic = view.findViewById(R.id.New_Topic);
        EditText nnote = view.findViewById(R.id.New_Note);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notes note = new Notes(name.getText().toString(), topic.getText().toString(), nnote.getText().toString());
                if(name.getText().toString().length()!=0 && topic.getText().toString().length()!=0 &&nnote.getText().toString().length()!=0) {
                    mNoteViewModel.insert(note);
                    Toast.makeText(getActivity(), "Заметка добавлена", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);
                }
                else{
                    if(name.getText().toString().length()==0 && topic.getText().toString().length()!=0 &&nnote.getText().toString().length()!=0){Toast.makeText(getActivity(), "Введите имя", Toast.LENGTH_SHORT).show();}
                    if(name.getText().toString().length()!=0 && topic.getText().toString().length()==0 &&nnote.getText().toString().length()!=0){Toast.makeText(getActivity(), "Введите тему", Toast.LENGTH_SHORT).show();}
                    if(name.getText().toString().length()!=0 && topic.getText().toString().length()!=0 &&nnote.getText().toString().length()==0){Toast.makeText(getActivity(), "Введите заметку", Toast.LENGTH_SHORT).show();}
                    else {Toast.makeText(getActivity(), "Заполните все поля", Toast.LENGTH_SHORT).show();}
                }


            }
        });


        return view;

    }

    
}