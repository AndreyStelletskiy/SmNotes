package com.example.smnotes;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.smnotes.noteadd.NoteViewModel;
import com.example.smnotes.noteadd.Notes;
import com.example.smnotes.noteadd.NotesListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
    private  int colb;
    int sortedby=1;

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
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                try {
                    requireActivity().finish();
                } catch (Exception e) {
                }

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final NotesListAdapter adapter = new NotesListAdapter(new NotesListAdapter.NotesDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Button sorted = view.findViewById(R.id.sortedby);
        sorted.setText(getResources().getText(R.string.sortedbytema));
        sorted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortedby==0){
                    sortedby=1;
                    sorted.setText(getResources().getText(R.string.sortedbytema));
                    mNotesViewModel.getALLtopic().observe(getViewLifecycleOwner(), topic -> {
                        Set<String> set = new LinkedHashSet<>(topic);
                        List<String> topicnames = new ArrayList<>(set);
                        LinearLayout layout = (LinearLayout) view.findViewById(R.id.ltop);


                        Button[] btn = new Button[(topicnames.size() + 2)];
                        int im = topicnames.size() + 1;
                        System.out.println(im);
                        btn[0] = new Button(getContext());
                        btn[0].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        btn[0].setText(getResources().getText(R.string.all));
                        int id = View.generateViewId();
                        btn[0].setId(id);
                        layout.removeAllViews();
                        layout.addView(btn[0]);
                        btn[0].setTextColor(Color.WHITE);
                        btn[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mNotesViewModel.getAllNotes().observe(getViewLifecycleOwner(), topic -> {
                                    adapter.submitList(topic);
                                    Toast.makeText(getActivity(), getResources().getText(R.string.alln), Toast.LENGTH_SHORT).show();
                                    btn[colb].setClickable(true);
                                    btn[0].setClickable(false);
                                    btn[0].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                                    btn[colb].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                                    colb = 0;
                                });
                            }
                        });

                        btn[im] = new Button(getContext());
                        btn[im].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        btn[im].setText(getResources().getText(R.string.infot));
                        id = View.generateViewId();
                        btn[im].setId(id);
                        btn[im].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);

                        View devider = new View(getContext());
                        int dividerHeight = (int) (getResources().getDisplayMetrics().density * 5); // 1dp to pixels
                        devider.setLayoutParams(new LayoutParams(dividerHeight, LayoutParams.MATCH_PARENT));
                        devider.setBackgroundColor(Color.parseColor("#FE6D00"));

                        btn[im].setTextColor(Color.WHITE);
                        btn[im].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(requireView()).navigate(R.id.action_homes_to_infoFragment);
                            }
                        });

                        btn[0].setClickable(false);
                        btn[0].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                        for (int i = 0; i < topicnames.size(); i++) {
                            i++;
                            btn[i] = new Button(getContext());
                            btn[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                            btn[i].setText("" + topicnames.get(i - 1));
                            id = View.generateViewId();
                            btn[i].setId(id);
                            layout.addView(btn[i]);

                            btn[i].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                            btn[i].setTextColor(Color.WHITE);
                            int finalI = i - 1;
                            btn[i].setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                        mNotesViewModel.getNotet(topicnames.get(finalI)).observe(getViewLifecycleOwner(), topic -> {
                                            adapter.submitList(topic);
                                            Toast.makeText(getActivity(), "Заметки с темой: " + topicnames.get(finalI), Toast.LENGTH_SHORT).show();
                                            btn[colb].setClickable(true);
                                            btn[finalI + 1].setClickable(false);
                                            btn[finalI + 1].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                                            btn[colb].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                                            colb = finalI + 1;
                                        });

                                }
                            });
                            i--;

                            // слой, к которому кнопку хотите прикрепить
                        }
                        layout.addView(devider);

                        layout.addView(btn[im]);
                    });
                }
                else {sortedby=0;
                    sorted.setText(getResources().getText(R.string.sortedbyname));
                    mNotesViewModel.getALLnames().observe(getViewLifecycleOwner(), names -> {
                        Set<String> set=new LinkedHashSet<>(names);
                        List<String> topicnames = new ArrayList<>(set);
                        LinearLayout layout = (LinearLayout) view.findViewById(R.id.ltop);


                        Button[] btn = new Button[(topicnames.size()+2)];
                        int im = topicnames.size()+1;
                        System.out.println(im);
                        btn[0] = new Button(getContext());
                        btn[0].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        btn[0].setText(getResources().getText(R.string.all));
                        int id = View.generateViewId();
                        btn[0].setId(id);
                        layout.removeAllViews();
                        layout.addView(btn[0]);
                        btn[0].setTextColor(Color.WHITE);
                        btn[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mNotesViewModel.getAllNotes().observe(getViewLifecycleOwner(), topic -> {
                                    adapter.submitList(topic);
                                    Toast.makeText(getActivity(), getResources().getText(R.string.alln), Toast.LENGTH_SHORT).show();
                                    btn[colb].setClickable(true);
                                    btn[0].setClickable(false);
                                    btn[0].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                                    btn[colb].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                                    colb=0;
                                });
                            }
                        });

                        btn[im] = new Button(getContext());
                        btn[im].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        btn[im].setText(getResources().getText(R.string.infot));
                        id = View.generateViewId();
                        btn[im].setId(id);


                        View devider = new View(getContext());
                        int dividerHeight = (int) (getResources().getDisplayMetrics().density * 5); // 1dp to pixels
                        devider.setLayoutParams(new LayoutParams(dividerHeight, LayoutParams.MATCH_PARENT));
                        devider.setBackgroundColor(Color.parseColor("#FE6D00"));

                        btn[im].setTextColor(Color.WHITE);
                        btn[im].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                        btn[im].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(requireView()).navigate(R.id.action_homes_to_infoFragment);
                            }
                        });

                        btn[0].setClickable(false);
                        btn[0].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                        for (int i = 0; i < topicnames.size(); i++) {
                            i++;
                            btn[i] = new Button(getContext());
                            btn[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                            btn[i].setText("" + topicnames.get(i-1));
                            id = View.generateViewId();
                            btn[i].setId(id);
                            layout.addView(btn[i]);

                            btn[i].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                            btn[i].setTextColor(Color.WHITE);
                            int finalI = i-1;
                            btn[i].setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                        mNotesViewModel.getNoten(topicnames.get(finalI)).observe(getViewLifecycleOwner(), topic -> {
                                            adapter.submitList(topic);
                                            Toast.makeText(getActivity(), "Заметки с названием: " + topicnames.get(finalI), Toast.LENGTH_SHORT).show();
                                            btn[colb].setClickable(true);
                                            btn[finalI + 1].setClickable(false);
                                            btn[finalI + 1].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                                            btn[colb].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                                            colb = finalI + 1;
                                        });
                                }
                            });
                            i--;

                            // слой, к которому кнопку хотите прикрепить
                        }
                        layout.addView(devider);

                        layout.addView(btn[im]);


                    });}

                if (c == 0) {
                    mNotesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                        adapter.submitList(notes);
                    });}
                colb=0;
                view.invalidate();
            }
        });



        mNotesViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        if (c == 0) {
            mNotesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                adapter.submitList(notes);
            });}


        // нажатие на кнопку, чтобы создать заметку
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new NoteADD().show(getParentFragmentManager(), null);
                Navigation.findNavController(requireView()).navigate(R.id.action_homes_to_noteADD);

            }
        });

        sorted.setText(getResources().getText(R.string.sortedbytema));
        mNotesViewModel.getALLtopic().observe(getViewLifecycleOwner(), topic -> {
            Set<String> set = new LinkedHashSet<>(topic);
            List<String> topicnames = new ArrayList<>(set);
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.ltop);

            //генерация кнопок
            Button[] btn = new Button[(topicnames.size() + 2)];
            int im = topicnames.size() + 1;
            System.out.println(im);
            btn[0] = new Button(getContext());
            btn[0].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btn[0].setText(getResources().getText(R.string.all));
            int id = View.generateViewId();
            btn[0].setId(id);
            layout.removeAllViews();
            layout.addView(btn[0]);
            btn[0].setTextColor(Color.WHITE);
            btn[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNotesViewModel.getAllNotes().observe(getViewLifecycleOwner(), topic -> {
                        adapter.submitList(topic);
                        Toast.makeText(getActivity(), getResources().getText(R.string.alln), Toast.LENGTH_SHORT).show();
                        btn[colb].setClickable(true);
                        btn[0].setClickable(false);
                        btn[0].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                        btn[colb].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                        colb = 0;
                    });
                }
            });

            btn[im] = new Button(getContext());
            btn[im].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btn[im].setText(getResources().getText(R.string.infot));
            id = View.generateViewId();
            btn[im].setId(id);
            btn[im].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);

            View devider = new View(getContext());
            int dividerHeight = (int) (getResources().getDisplayMetrics().density * 5); // 1dp to pixels
            devider.setLayoutParams(new LayoutParams(dividerHeight, LayoutParams.MATCH_PARENT));
            devider.setBackgroundColor(Color.parseColor("#FE6D00"));

            btn[im].setTextColor(Color.WHITE);
            btn[im].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(requireView()).navigate(R.id.action_homes_to_infoFragment);
                }
            });

            btn[0].setClickable(false);
            btn[0].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
            for (int i = 0; i < topicnames.size(); i++) {
                i++;
                btn[i] = new Button(getContext());
                btn[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                btn[i].setText("" + topicnames.get(i - 1));
                id = View.generateViewId();
                btn[i].setId(id);
                layout.addView(btn[i]);

                btn[i].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                btn[i].setTextColor(Color.WHITE);
                int finalI = i - 1;
                btn[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        mNotesViewModel.getNotet(topicnames.get(finalI)).observe(getViewLifecycleOwner(), topic -> {
                            adapter.submitList(topic);
                            Toast.makeText(getActivity(), getResources().getText(R.string.notesit) + topicnames.get(finalI), Toast.LENGTH_SHORT).show();
                            btn[colb].setClickable(true);
                            btn[finalI + 1].setClickable(false);
                            btn[finalI + 1].getBackground().setColorFilter(Color.parseColor("#FF9A9A9A"), PorterDuff.Mode.MULTIPLY);
                            btn[colb].getBackground().setColorFilter(Color.parseColor("#FE6D00"), PorterDuff.Mode.MULTIPLY);
                            colb = finalI + 1;
                        });

                    }
                });
                i--;
            }
            layout.addView(devider);

            layout.addView(btn[im]);
        });


            return view;
        }
    }