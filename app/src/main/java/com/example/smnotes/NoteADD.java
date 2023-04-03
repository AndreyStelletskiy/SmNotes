package com.example.smnotes;

import static android.view.View.GONE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.smnotes.noteadd.NoteViewModel;
import com.example.smnotes.noteadd.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NoteADD extends Fragment {
    private NoteViewModel mNoteViewModel;
    private FloatingActionButton btn;
    private FloatingActionButton back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_add, container, false);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        TextView ch = view.findViewById(R.id.noyes);
        yes.setVisibility(View.INVISIBLE);
        no.setVisibility(View.INVISIBLE);
        ch.setVisibility(View.INVISIBLE);


        btn = view.findViewById(R.id.ADDNOTE);
        back = view.findViewById(R.id.back);
        EditText name = view.findViewById(R.id.New_Name);
        EditText topic = view.findViewById(R.id.New_Topic);
        EditText nnote = view.findViewById(R.id.New_Note);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               NotesADD(name.getText().toString(), topic.getText().toString(), nnote.getText().toString());


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((name.getText().toString().length()==0) || (topic.getText().toString().length()==0) || (nnote.getText().toString().length()==0)) {
                    Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);}
                else{

                    yes.setVisibility(View.VISIBLE);
                    no.setVisibility(View.VISIBLE);
                    ch.setVisibility(View.VISIBLE);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NotesADD(name.getText().toString(), topic.getText().toString(), nnote.getText().toString());
                            //gsoto();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gsoto();
                        }
                    });

                }

            }
        });


        return view;

    }

    public void gsoto() {
        Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);

    }

    public void NotesADD(String name, String topic, String nnote){
        Notes note = new Notes(name, topic, nnote);
        if(name.length()!=0 && topic.length()!=0 &&nnote.length()!=0) {
            mNoteViewModel.insert(note);
            Toast.makeText(getActivity(), "Заметка добавлена", Toast.LENGTH_SHORT).show();

            Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);
        }
        else{
            if(name.length()==0 && topic.length()!=0 &&nnote.length()!=0){Toast.makeText(getActivity(), "Введите имя", Toast.LENGTH_SHORT).show();}
            if(name.length()!=0 && topic.length()==0 &&nnote.length()!=0){Toast.makeText(getActivity(), "Введите тему", Toast.LENGTH_SHORT).show();}
            if(name.length()!=0 && topic.length()!=0 &&nnote.length()==0){Toast.makeText(getActivity(), "Введите заметку", Toast.LENGTH_SHORT).show();}
            else {Toast.makeText(getActivity(), "Заполните все поля", Toast.LENGTH_SHORT).show();}
        }


    }

}