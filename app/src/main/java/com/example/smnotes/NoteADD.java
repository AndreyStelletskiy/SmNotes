package com.example.smnotes;



import android.database.SQLException;

import android.os.Bundle;
import android.util.Log;
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
    //Добавление заметки
    private NoteViewModel mNoteViewModel;
    private FloatingActionButton btn;
    private FloatingActionButton back;
    private EditText name ;
    private EditText topic ;
    private EditText nnote;
    private int bb = 0 ;


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
         name = view.findViewById(R.id.New_Name);
         topic = view.findViewById(R.id.New_Topic);
         nnote = view.findViewById(R.id.New_Note);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        //добавление заметки, подтверждение добавления
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               NotesADD(name.getText().toString().trim(), topic.getText().toString().trim(), nnote.getText().toString().trim(),name.getText().toString().trim()+topic.getText().toString().trim()+nnote.getText().toString().trim());


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
                    name.setVisibility(View.INVISIBLE);
                    topic.setVisibility(View.INVISIBLE);
                    nnote.setVisibility(View.INVISIBLE);
                    view.findViewById(R.id.Show).setVisibility(View.INVISIBLE);
                    btn.setVisibility(View.INVISIBLE);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NotesADD( name.getText().toString().trim(), topic.getText().toString().trim(), nnote.getText().toString().trim(),  name.getText().toString().trim()+topic.getText().toString().trim()+nnote.getText().toString().trim());
                            //gsoto();
                            bb=1;
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gsoto();
                            bb=1;
                        }
                    });

                }

            }
        });


        return view;

    }

    //переход  в главный фрагмент
    public void gsoto() {
        Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);

    }


    //метод, отвечающий за добавление заметки
    public void NotesADD(String name, String topic, String nnote, String ndiff){
        Notes note = new Notes(name, topic, nnote, name+topic+nnote);
        if(name.length()!=0 && topic.length()!=0 &&nnote.length()!=0) {
            bb=1;

            try {

                mNoteViewModel.insert(note);
                String text = getResources().getString(R.string.noteadd);
                //Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                Toast.makeText(getActivity(), "Такая заметка уже есть", Toast.LENGTH_SHORT).show();
                Log.d("rrr" , "vmvbrivnvnrnvr");
            }

            Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);
        }
        else{
            int ccc=0;
            if(name.length()==0 && topic.length()!=0 &&nnote.length()!=0){
                ccc=1;
                Toast.makeText(getActivity(), getResources().getString(R.string.addname), Toast.LENGTH_SHORT).show();}
            if(name.length()!=0 && topic.length()==0 &&nnote.length()!=0){
                ccc=2;
                Toast.makeText(getActivity(), getResources().getString(R.string.addtopic), Toast.LENGTH_SHORT).show();}
            if(name.length()!=0 && topic.length()!=0 &&nnote.length()==0){
                ccc=3;
                Toast.makeText(getActivity(), getResources().getString(R.string.addnote), Toast.LENGTH_SHORT).show();}
            if(ccc==0) {Navigation.findNavController(requireView()).navigate(R.id.action_noteADD_to_homes);}
        }


    }


    //метод, вызывающийся при закрытии фрагмента
    public void onDestroyView () {
        if(name.length()!=0 && topic.length()!=0 &&nnote.length()!=0) {
            Notes note = new Notes(name.getText().toString().trim(), topic.getText().toString().trim(), nnote.getText().toString().trim(), name.getText().toString().trim()+topic.getText().toString().trim()+nnote.getText().toString().trim());
            if (bb == 0) {
                try {

                    mNoteViewModel.insert(note);
                    String text = getResources().getString(R.string.noteadd);
                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                } catch (SQLException e){
                    Toast.makeText(getActivity(), "Такая заметка уже есть", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onDestroyView();
    }

}