package com.example.smnotes;

import android.database.SQLException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smnotes.noteadd.NoteViewModel;
import com.example.smnotes.noteadd.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeNoteFragment extends Fragment {
    //изменение заметки
    private EditText name ;
    private EditText topic ;
    private EditText сnote;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int prc =0;
    private Notes Snote;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChangeNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangeNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangeNoteFragment newInstance(String param1, String param2) {
        ChangeNoteFragment fragment = new ChangeNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public String[]  Note;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        String result = getArguments().getString("0");
        Note = result.split("/ /");
        Snote = new Notes(Note[0],Note[1],Note[2]);



    }
    private NoteViewModel mNoteViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_note, container, false);
         name = view.findViewById(R.id.Change_Name);
         topic = view.findViewById(R.id.Change_Topic);
         сnote = view.findViewById(R.id.Change_Note);
        TextView show = view.findViewById(R.id.Show);
        Button change = view.findViewById(R.id.ChangeNote);
        Button dell = view.findViewById(R.id.ChDell);
        TextView noyeschdell = view.findViewById(R.id.noyeschdell);
        //вывод текущей заметки
        name.setText(Note[0]);
        topic.setText(Note[1]);
        сnote.setText(Note[2]);
        show.setText(getResources().getString(R.string.cnote)+" "+Note[0]);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((name.getText().toString().equals(Note[0])) != true || (topic.getText().toString().equals(Note[1])) != true || (сnote.getText().toString().equals(Note[2])) != true) && name.getText().toString().length() != 0 && topic.getText().toString().length() != 0 && сnote.getText().toString().length() != 0) {
                    Notes Nnote = new Notes(name.getText().toString(), topic.getText().toString(), сnote.getText().toString());
                    mNoteViewModel.deleteByname(Note[0], Note[1], Note[2]);

                    Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                    try {
                        prc = 1;
                        mNoteViewModel.insert(Nnote);
                    } catch (SQLException e) {
                        Log.d("RRR", "SQLException");
                    }

                }
                else{
                    int ccc=0;
                    if(name.getText().toString().length()==0 && topic.getText().toString().length()!=0 &&сnote.getText().toString().length()!=0){
                        ccc=1;
                        Toast.makeText(getActivity(), getResources().getString(R.string.addname), Toast.LENGTH_SHORT).show();}
                    if(name.getText().toString().length()!=0 && topic.getText().toString().length()==0 &&сnote.getText().toString().length()!=0){
                        ccc=2;
                        Toast.makeText(getActivity(), getResources().getString(R.string.addtopic), Toast.LENGTH_SHORT).show();}
                    if(name.getText().toString().length()!=0 && topic.getText().toString().length()!=0 &&сnote.getText().toString().length()==0){
                        ccc=3;
                        Toast.makeText(getActivity(), getResources().getString(R.string.addnote), Toast.LENGTH_SHORT).show();}
                    if(ccc==0) {Toast.makeText(getActivity(), getResources().getString(R.string.addall), Toast.LENGTH_SHORT).show();}
                }
            }
        });
        //метод удаления с подтверждением(удалять или нет)
        dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button yesch = view.findViewById(R.id.yesch);
                Button noch = view.findViewById(R.id.noch);
                TextView noyeschdell = view.findViewById(R.id.noyeschdell);

                yesch.setVisibility(View.VISIBLE);
                noch.setVisibility(View.VISIBLE);
                noyeschdell.setVisibility(View.VISIBLE);
                name.setVisibility(View.INVISIBLE);
                topic.setVisibility(View.INVISIBLE);
                сnote.setVisibility(View.INVISIBLE);
                show.setVisibility(View.INVISIBLE);
                change.setVisibility(View.INVISIBLE);
                dell.setVisibility(View.INVISIBLE);
                noyeschdell.setText(getResources().getString(R.string.ct));

                yesch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mNoteViewModel.deleteByname(Note[0],Note[1],Note[2]);

                        Toast.makeText(getActivity(), getResources().getString(R.string.cndell), Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                    }
                });

                noch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                    }
                });

            }
        });


        FloatingActionButton backch = view.findViewById(R.id.backch);
        Button yesch = view.findViewById(R.id.yesch);
        Button noch = view.findViewById(R.id.noch);
        TextView noyesch = view.findViewById(R.id.noyesch);
        noyeschdell.setVisibility(View.INVISIBLE);
        yesch.setVisibility(View.INVISIBLE);
        noch.setVisibility(View.INVISIBLE);
        noyesch.setVisibility(View.INVISIBLE);


        //метод возрашение назад во фрагменте с подтверждением(применять изменения или нет)
        backch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if ((name.getText().toString().equals(Note[0]))==true && (topic.getText().toString().equals(Note[1]))==true && (сnote.getText().toString().equals(Note[2]))==true || (name.getText().toString().length()==0 || topic.getText().toString().length()==0 || сnote.getText().toString().length()==0)){
                    Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);}
                else{

                    yesch.setVisibility(View.VISIBLE);
                    noch.setVisibility(View.VISIBLE);
                    noyesch.setVisibility(View.VISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    topic.setVisibility(View.INVISIBLE);
                    сnote.setVisibility(View.INVISIBLE);
                    show.setVisibility(View.INVISIBLE);
                    change.setVisibility(View.INVISIBLE);
                    dell.setVisibility(View.INVISIBLE);

                    yesch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Notes Nnote = new Notes(name.getText().toString().trim(), topic.getText().toString().trim(), сnote.getText().toString().trim());
                            mNoteViewModel.deleteByname(Note[0],Note[1], Note[2]);

                            Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                            try {
                                    prc =1;
                                mNoteViewModel.insert(Nnote);
                            } catch (SQLException e){
                                Toast.makeText(getActivity(), "Такая заметка уже есть", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    noch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prc =1;
                            Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                        }
                    });

                }

            }
        });



        // Inflate the layout for this fragment
        return view;
    }


    //метод вызывыющийся при закртытии фрагмента
    public void onDestroyView () {

        if (((name.getText().toString().equals(Note[0]))!=true || (topic.getText().toString().equals(Note[1]))!=true || (сnote.getText().toString().equals(Note[2]))!=true) && name.getText().toString().length()!=0 && topic.getText().toString().length()!=0 && сnote.getText().toString().length()!=0){
            Notes note = new Notes(name.getText().toString().trim(), topic.getText().toString().trim(), сnote.getText().toString().trim());
            try {
                if (prc==0){
                mNoteViewModel.insert(note);}
            } catch (SQLException e){
                Toast.makeText(getActivity(), "Такая заметка уже есть", Toast.LENGTH_SHORT).show();
            }
        }
        super.onDestroyView();
    }

}