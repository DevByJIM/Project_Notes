package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.room.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolderNotes> {

    ArrayList<Note> notes;
    Context context;
    ListFragment fragment;

    public NotesAdapter(ArrayList<Note> notes,Context context,ListFragment listFragment) {
        this.notes = notes;
        this.context = context;
        fragment = listFragment;
    }


    @NonNull
    @Override
    public NotesAdapter.ViewHolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list,parent,false);

        ((ConstraintLayout)view.findViewById(R.id.constraintLayout)).setBackgroundColor(context.getResources().getColor(R.color.primary_200));

        return new ViewHolderNotes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolderNotes holder, int position) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");


        holder.title.setText(notes.get(position).getTitle());
        holder.content.setText(notes.get(position).getContent());

        holder.creationDate.setText(formatDate.format(notes.get(position).getCreationDate()));
        holder.updateDate.setText(formatDate.format(notes.get(position).getUpdateDate()));

        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolderNotes extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView title, content, creationDate, updateDate;
        int position;

        public ViewHolderNotes(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.tvTitle);
            content = (TextView)itemView.findViewById(R.id.tvContent);
            creationDate = (TextView)itemView.findViewById(R.id.tvCreateDate);
            updateDate = (TextView)itemView.findViewById(R.id.tvUpdateDate);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            final String[] options = {
                    "SI","NO"
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("¿Eliminar recuerdo?");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == 0){
                        fragment.notesLab.deleteNote(notes.get(position));
                        Toast.makeText(context,"Borrado con exito",Toast.LENGTH_SHORT).show();
                        fragment.updateList();
                    }else
                    {

                    }
                }
            });

            builder.show();





            return false;
        }




    }
}
