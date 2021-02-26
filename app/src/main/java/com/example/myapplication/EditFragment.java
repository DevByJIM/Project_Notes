package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.room.Note;
import com.example.myapplication.room.NotesLab;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Note temporalNote;

    private EditText title;
    private EditText content;

    private NotesLab notesLab;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        notesLab = NotesLab.get(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        title = (EditText)view.findViewById(R.id.editTitle);
        content = (EditText)view.findViewById(R.id.editContent);



        ((Button)view.findViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrUpdateNote(v);
            }
        });

        return view;
    }


    public void addOrUpdateNote(View view)
    {
        if(title.getText().toString().isEmpty()||content.getText().toString().isEmpty())
        {
            Snackbar.make(view, "Tiene que rellenar todos los campos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else
        {
            if(temporalNote==null)
            {
                temporalNote = new Note(title.getText().toString(),content.getText().toString());

                notesLab.insertNote(temporalNote);

                Snackbar.make(view, "Nota añadida con exito", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else
            {
                temporalNote.setTitle(title.getText().toString());
                temporalNote.setContent(content.getText().toString());

                notesLab.updateNote(temporalNote);

                Snackbar.make(view, "Nota modificada con exito", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            temporalNote = null;

            title.getText().clear();
            content.getText().clear();


        }

    }




}