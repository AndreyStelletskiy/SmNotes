package com.example.smnotes;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public infoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static infoFragment newInstance(String param1, String param2) {
        infoFragment fragment = new infoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        FloatingActionButton fab = view.findViewById(R.id.backinfo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new NoteADD().show(getParentFragmentManager(), null);
                Navigation.findNavController(requireView()).navigate(R.id.action_infoFragment_to_homes);

            }
        });
        TextView  info = view.findViewById(R.id.textinfo);
         /*info.setText("\n\n\n  Вы скачали приложение Умные заметки (Smart Notes)." +
                "\nДля создания заметки нужно заполнить 3 поля: название, тему и текст заметки." +
                "\n  Заметки отображаются общим списком или сгруппированными по теме или названию." +
                "\nПри удалении заметки она сразу удаляется (не попадает в карзину)." +
                "\n  Приятного использования. " +
                "\nПо вопросам и предложениям как улучшить пишите: a.stelletskiy@ya.ru" +
                "\n  designed by Andrey Stelletskiy"); /*/
        Linkify.addLinks(info, Linkify.EMAIL_ADDRESSES);
        Spannable text = new SpannableString("\n\n\n  Вы скачали приложение Умные заметки (Smart Notes)." +
                "\nДля создания заметки нужно заполнить 3 поля: название, тему и текст заметки." +
                "\n  Заметки отображаются общим списком или сгруппированными по теме или названию." +
                "\nПри удалении заметки она сразу удаляется (не попадает в карзину)." +
                "\n  Приятного использования. " +
                "\nПо вопросам и предложениям как улучшить пишите: a.stelletskiy@ya.ru" +
                "\n  designed by Andrey Stelletskiy");
        text.setSpan(new StyleSpan(Typeface.ITALIC), 27, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        info.setText(text);
        return view;

    }
}