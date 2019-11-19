package br.com.digitalhouse.projetomarvel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.projetomarvel.R;
import br.com.digitalhouse.projetomarvel.pojo.Result;
import br.com.digitalhouse.projetomarvel.view.Interface.OnClickDetails;
import br.com.digitalhouse.projetomarvel.view.adapter.ComicsAdapter;
import br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel;

import static br.com.digitalhouse.projetomarvel.view.activity.LoginActivity.GOOGLE_ACCOUNT;

public class MainActivity extends AppCompatActivity implements OnClickDetails {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ComicsAdapter adapter;
    private List<Result> resultList = new ArrayList<>();
    private ComicsViewModel viewModel;
    private GoogleSignInClient googleSignInClient;
    private ImageView imgProfile;
    private TextView nameProfile;
    private Button btnLogout;

    public static final String RESULT_KEY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        pegaOsDados();

        btnLogout.setOnClickListener(v ->
                googleSignInClient.signOut().addOnCompleteListener(task -> {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }));


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
        imgProfile = findViewById(R.id.imagemlogogoogle);
        nameProfile = findViewById(R.id.logingoogle);
        btnLogout = findViewById(R.id.buttonSair);

    }

    @Override
    public void Onclick(Result result) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_KEY, result);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void pegaOsDados() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).into(imgProfile);
        nameProfile.setText(googleSignInAccount.getDisplayName());
    }

}
