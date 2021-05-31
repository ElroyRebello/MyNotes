package com.example.ytnotes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytnotes.Activity.MainActivity;
import com.example.ytnotes.Activity.UpdateActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allnotes;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
         this.mainActivity=mainActivity;
        this.notes=notes;
        this.allnotes=notes;
    }

    public void searhNotes(List<Notes> filternotes)
    {
        this.notes=filternotes;
        notifyDataSetChanged();
    }

    @Override
    public notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.sample,parent,false));
    }

    @Override
    public void onBindViewHolder( notesViewHolder holder, int position) {

        if(notes.get(position).notesPriority.equals("1"))
        {
           holder.priority.setBackgroundResource(R.drawable.green);
        }
       if(notes.get(position).notesPriority.equals("2"))
        {
            holder.priority.setBackgroundResource(R.drawable.yelow);
        }
       if(notes.get(position).notesPriority.equals("3"))
        {
            holder.priority.setBackgroundResource(R.drawable.redshape);
        }


        holder.title.setText(notes.get(position).notesTitle);
        holder.sub.setText(notes.get(position).notesSubTitle);
        holder.date.setText(notes.get(position).notesDate);

        holder.cv.setOnClickListener(v -> {

            Intent i = new Intent(mainActivity, UpdateActivity.class);

            i.putExtra("id",notes.get(position).id);
            i.putExtra("title",notes.get(position).notesTitle);
            i.putExtra("sub",notes.get(position).notesSubTitle);
            i.putExtra("date",notes.get(position).notesDate);
            i.putExtra("notes",notes.get(position).notes);
            i.putExtra("prio",notes.get(position).notesPriority);
            mainActivity.startActivity(i);


        });



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder{

        TextView title,sub,date;
        View priority;
        CardView cv;

        public notesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titlettt);
            sub=itemView.findViewById(R.id.subt);
            date=itemView.findViewById(R.id.date);
            priority=itemView.findViewById(R.id.priorityimg);
            cv=itemView.findViewById(R.id.sample);

        }
    }
}
