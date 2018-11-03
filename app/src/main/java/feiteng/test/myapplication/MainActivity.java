package feiteng.test.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import feiteng.test.myapplication.adapters.MovieAdapter;
import feiteng.test.myapplication.modules.ViewModule;
import feiteng.test.myapplication.persenters.MovieInterface;
import feiteng.test.myapplication.persenters.MoviePresenter;

public class MainActivity extends AppCompatActivity implements MovieInterface.ViewInterface {

    @Inject
    MoviePresenter presenter;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent();
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.movie_recyclerview);
        // use a linear layout manager
        RecyclerView.LayoutManager  layoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter(this, presenter);
        recyclerView.setAdapter(adapter);
        presenter.sendRequest();
    }

    private void setupComponent() {
        DaggerMainComponent.builder().viewModule(new ViewModule(this)).build().inject(this);
    }

    @Override
    public void updateDataSet() {
        adapter.notifyDataSetChanged();
    }
}
