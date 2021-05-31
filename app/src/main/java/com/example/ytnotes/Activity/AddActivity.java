package com.example.ytnotes.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ytnotes.Notes;
import com.example.ytnotes.NotesViewModel;
import com.example.ytnotes.R;
import com.example.ytnotes.databinding.ActivityAddBinding;

import java.text.DateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;
    String title,subt,note;
    NotesViewModel notesViewModel;
    String priority = "1";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.greenp.setOnClickListener(v -> {

            binding.greenp.setImageResource(R.drawable.done_24sdcsddfdf);
            binding.redp.setImageResource(0);
            binding.yellowp.setImageResource(0);
            priority="1";
        });
        binding.yellowp.setOnClickListener(v -> {
            binding.yellowp.setImageResource(R.drawable.done_24sdcsddfdf);
            binding.redp.setImageResource(0);
            binding.greenp.setImageResource(0);
            priority="2";
        });
        binding.redp.setOnClickListener(v -> {
            binding.redp.setImageResource(R.drawable.done_24sdcsddfdf);
            binding.greenp.setImageResource(0);
            binding.yellowp.setImageResource(0);
            priority="3";
        });

        binding.floatingdonenotes.setOnClickListener(v -> {

            title=binding.addtitle.getText().toString();
            subt=binding.addsub.getText().toString();
            note=binding.addnote.getText().toString();

            createNotes(title,subt,note);



        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createNotes(String title, String subt, String note) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        Notes notes = new Notes();
        notes.notesTitle=title;
        notes.notesSubTitle=subt;
        notes.notes=note;
        notes.notesDate=currentDateandTime;
        notes.notesPriority=priority;

        notesViewModel.insertNote(notes);


        Toast.makeText(this,"added",Toast.LENGTH_SHORT).show();
        finish();


    }
}