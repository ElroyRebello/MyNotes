package com.example.ytnotes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ytnotes.Notes;
import com.example.ytnotes.NotesAdapter;
import com.example.ytnotes.NotesViewModel;
import com.example.ytnotes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fb;
    RecyclerView recyclerView;
    NotesViewModel notesViewModel;
    TextView nofil,htol,ltohi;
    List<Notes> filterNameList;
    NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        fb=findViewById(R.id.floatingadd);
        nofil=findViewById(R.id.nofil);
        htol=findViewById(R.id.HightoLow);
        ltohi=findViewById(R.id.lowtohi);
        recyclerView=findViewById(R.id.recyclerView);


        nofil.setBackgroundResource(R.drawable.selectedfilter);


        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setadapter(notes);
                filterNameList=notes;
            }
        });
        nofil.setOnClickListener(v -> {

                loaddata(1);
            nofil.setBackgroundResource(R.drawable.selectedfilter);
            ltohi.setBackgroundResource(R.drawable.unselectedfilter);
            htol.setBackgroundResource(R.drawable.unselectedfilter);

        });
        ltohi.setOnClickListener(v -> {

            loaddata(3);
            nofil.setBackgroundResource(R.drawable.unselectedfilter);
            ltohi.setBackgroundResource(R.drawable.selectedfilter);
            htol.setBackgroundResource(R.drawable.unselectedfilter);

        });
        htol.setOnClickListener(v -> {
            nofil.setBackgroundResource(R.drawable.unselectedfilter);
            ltohi.setBackgroundResource(R.drawable.unselectedfilter);
            htol.setBackgroundResource(R.drawable.selectedfilter);
            loaddata(2);

        });


        fb.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this,AddActivity.class));
        });



    }
    public void loaddata(int i) {
        if (i == 1) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setadapter(notes);
                    filterNameList=notes;
                }
            });

        }
        if (i == 2) {
            notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setadapter(notes);
                    filterNameList=notes;
                }
            });

        }
        if (i == 3) {
            notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setadapter(notes);
                    filterNameList=notes;
                }
            });
        }
    }
    public void setadapter(List<Notes> notes)
    {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
         adapter = new NotesAdapter(MainActivity.this,notes);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesfilter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void notesfilter(String newText) {

        ArrayList<Notes> filterNames = new ArrayList<>();

        for (Notes notes: this.filterNameList)
        {
            if(notes.notesTitle.contains(newText)|| notes.notesSubTitle.contains(newText))
                filterNames.add(notes);
        }

        this.adapter.searhNotes(filterNames);
    }


}