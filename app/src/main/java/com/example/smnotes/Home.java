package com.example.smnotes;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smnotes.noteadd.NoteViewModel;
import com.example.smnotes.noteadd.Notes;
import com.example.smnotes.noteadd.NotesListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    private NoteViewModel mNotesViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int c;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        c =0;
    }


    private LiveData<List<Notes>> notees;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final NotesListAdapter adapter = new NotesListAdapter(new NotesListAdapter.NotesDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        mNotesViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        if (c == 0) {
            mNotesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                adapter.submitList(notes);
            });}




        TextView show = view.findViewById(R.id.shownote);


        mNotesViewModel.getALLtopic().observe(getViewLifecycleOwner(), topic -> {
            Set<String> set=new LinkedHashSet<>(topic);
            show.setText(set.toString());
            // set[1,дз, дороботки]
            //надо сделать чтобы на 1 на дз на доработки можно нажать по отдельности.
            //сколько их - длина масива





        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //notees = mNotesViewModel.getNote("дз");
                mNotesViewModel.getNote("дз").observe(getViewLifecycleOwner(), topic -> {
                    adapter.submitList(topic);
                });


                Toast.makeText(getActivity(), "Заметка 2", Toast.LENGTH_SHORT).show();
            }
        });


        Button allshow = view.findViewById(R.id.all);
        allshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notees = mNotesViewModel.getAllNotes();
                adapter.submitList(notees.getValue());
                Toast.makeText(getActivity(), "Все заметки", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new NoteADD().show(getParentFragmentManager(), null);
                Navigation.findNavController(requireView()).navigate(R.id.action_homes_to_noteADD);

            }
        });


            return view;
        }


}