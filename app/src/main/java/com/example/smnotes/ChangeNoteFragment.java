package com.example.smnotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Note = result.split(" ");



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_note, container, false);
        EditText name = view.findViewById(R.id.Change_Name);
        EditText topic = view.findViewById(R.id.Change_Topic);
        EditText note = view.findViewById(R.id.Change_Note);
        TextView show = view.findViewById(R.id.Show);
        Button dell = view.findViewById(R.id.ChangeNote);
        name.setText(Note[0]);
        topic.setText(Note[1]);
        note.setText(Note[2]);
        show.setText("Изменение заметки "+Note[0]);

        dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обновление заметки
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}