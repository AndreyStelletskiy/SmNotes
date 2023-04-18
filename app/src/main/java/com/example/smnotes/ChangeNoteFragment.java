package com.example.smnotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
        EditText name = view.findViewById(R.id.Change_Name);
        EditText topic = view.findViewById(R.id.Change_Topic);
        EditText note = view.findViewById(R.id.Change_Note);
        TextView show = view.findViewById(R.id.Show);
        Button change = view.findViewById(R.id.ChangeNote);
        Button dell = view.findViewById(R.id.ChDell);
        TextView noyeschdell = view.findViewById(R.id.noyeschdell);
        name.setText(Note[0]);
        topic.setText(Note[1]);
        note.setText(Note[2]);
        show.setText(getResources().getString(R.string.cnote)+Note[0]);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notes Nnote = new Notes(name.getText().toString(), topic.getText().toString(), note.getText().toString());
                mNoteViewModel.deleteByname(Note[0],Note[1], Note[2]);
                if ((name.getText().toString().equals(Note[0]))!=true || (topic.getText().toString().equals(Note[1]))!=true || (note.getText().toString().equals(Note[2]))!=true){
                    Toast.makeText(getActivity(), getResources().getString(R.string.cnotes), Toast.LENGTH_SHORT).show();
                }

                Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                mNoteViewModel.insert(Nnote);

            }
        });
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
                note.setVisibility(View.INVISIBLE);
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

        backch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((name.getText().toString().equals(Note[0]))==true && (topic.getText().toString().equals(Note[1]))==true && (note.getText().toString().equals(Note[2]))==true){
                    Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);}
                else{

                    yesch.setVisibility(View.VISIBLE);
                    noch.setVisibility(View.VISIBLE);
                    noyesch.setVisibility(View.VISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    topic.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                    show.setVisibility(View.INVISIBLE);
                    change.setVisibility(View.INVISIBLE);
                    dell.setVisibility(View.INVISIBLE);

                    yesch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Notes Nnote = new Notes(name.getText().toString().trim(), topic.getText().toString().trim(), note.getText().toString().trim());
                            Toast.makeText(getActivity(), getResources().getString(R.string.cnotes), Toast.LENGTH_SHORT).show();
                            mNoteViewModel.deleteByname(Note[0],Note[1], Note[2]);

                            Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                            mNoteViewModel.insert(Nnote);
                        }
                    });

                    noch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Navigation.findNavController(requireView()).navigate(R.id.action_changeNoteFragment_to_homes);
                        }
                    });

                }

            }
        });



        // Inflate the layout for this fragment
        return view;
    }

}