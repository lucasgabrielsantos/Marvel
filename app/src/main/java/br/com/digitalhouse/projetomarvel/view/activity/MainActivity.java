package br.com.digitalhouse.projetomarvel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.projetomarvel.R;
import br.com.digitalhouse.projetomarvel.pojo.Result;
import br.com.digitalhouse.projetomarvel.view.Interface.OnClickDetails;
import br.com.digitalhouse.projetomarvel.view.adapter.ComicsAdapter;
import br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel;

public class MainActivity extends AppCompatActivity implements OnClickDetails {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ComicsAdapter adapter;
    private List<Result> resultList = new ArrayList<>();
    private ComicsViewModel viewModel;

    public static final String RESULT_KEY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        viewModel.getComics();

        viewModel.getListaComics().observe(this, resultaLista -> {
            adapter.atualizalista(resultaLista);
        });

        viewModel.loading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void initViews() {
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);
        adapter = new ComicsAdapter(resultList, this);
        viewModel = ViewModelProviders.of(this).get(ComicsViewModel.class);

    }

    @Override
    public void Onclick(Result result) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_KEY, result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
