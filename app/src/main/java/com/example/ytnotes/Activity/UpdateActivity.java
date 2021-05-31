package com.example.ytnotes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ytnotes.Notes;
import com.example.ytnotes.NotesViewModel;
import com.example.ytnotes.R;
import com.example.ytnotes.databinding.ActivityAddBinding;
import com.example.ytnotes.databinding.ActivityUpdateBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;
    String title,subt,note,date;
    NotesViewModel notesViewModel;
    String priority ;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        subt = getIntent().getStringExtra("sub");
        date = getIntent().getStringExtra("date");
        note = getIntent().getStringExtra("notes");
        priority = getIntent().getStringExtra("prio");

        binding.utitle.setText(title);
        binding.usub.setText(subt);
        binding.unote.setText(note);
        if(priority.equals("1"))
        {
            binding.greenpu.setImageResource(R.drawable.done_24sdcsddfdf);
        }
        if(priority.equals("2"))
        {
            binding.yellowpu.setImageResource(R.drawable.done_24sdcsddfdf);
        }
        if(priority.equals("3"))
        {
            binding.redpu.setImageResource(R.drawable.done_24sdcsddfdf);
        }




        binding.greenpu.setOnClickListener(v -> {

            binding.greenpu.setImageResource(R.drawable.done_24sdcsddfdf);
            binding.redpu.setImageResource(0);
            binding.yellowpu.setImageResource(0);
            priority="1";
        });
        binding.yellowpu.setOnClickListener(v -> {
            binding.yellowpu.setImageResource(R.drawable.done_24sdcsddfdf);
            binding.redpu.setImageResource(0);
            binding.greenpu.setImageResource(0);
            priority="2";
        });
        binding.redpu.setOnClickListener(v -> {
            binding.redpu.setImageResource(R.drawable.done_24sdcsddfdf);
            binding.greenpu.setImageResource(0);
            binding.yellowpu.setImageResource(0);
            priority="3";
        });

        binding.floatingupdatenotes.setOnClickListener(v -> {

            title=binding.utitle.getText().toString();
            subt=binding.usub.getText().toString();
            note=binding.unote.getText().toString();

            update(title,subt,note,date,priority,id);



        });

    }

    private void update(String title, String subt, String note, String date, String priority, int id) {

        Notes notes = new Notes();
        notes.id=id;
        notes.notesTitle=title;
        notes.notesSubTitle=subt;
        notes.notes=note;
        notes.notesDate=date;
        notes.notesPriority=priority;

        notesViewModel.updateNote(notes);


        Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.icdelete) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateActivity.this,R.style.bottomsheet);
             View view = LayoutInflater.from(UpdateActivity.this).inflate(R.layout.bottomsheet,
                    (ConstraintLayout)findViewById(R.id.bottomsheet));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
            TextView yes;
            yes=view.findViewById(R.id.yes);
            TextView no = view.findViewById(R.id.no);

            yes.setOnClickListener(v -> {

                notesViewModel.deleteNote(id);
                finish();
            });

            no.setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
            });

        }

        return true;
    }
}