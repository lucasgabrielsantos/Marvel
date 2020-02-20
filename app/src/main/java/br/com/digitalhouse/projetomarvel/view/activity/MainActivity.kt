package br.com.digitalhouse.projetomarvel.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.digitalhouse.projetomarvel.R
import br.com.digitalhouse.projetomarvel.pojo.Result
import br.com.digitalhouse.projetomarvel.view.Interface.OnClickDetails
import br.com.digitalhouse.projetomarvel.view.adapter.ComicsAdapter
import br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), OnClickDetails {
    private var adapter: ComicsAdapter? = null
    private val resultList: List<Result> = ArrayList()
    private var viewModel: ComicsViewModel? = null
    private var googleSignInClient: GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVariables()
        InstanceLoginGoogleGSO()
        pegaOsDadosDoGoogle()
        InstanceRecyclerView()
        InstanceViewModel()
        btnLogout()
    }

    private fun btnLogout() {
        buttonSair!!.setOnClickListener { v: View? ->
            googleSignInClient!!.signOut().addOnCompleteListener {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        }
    }

    fun initVariables() {
//        recyclerView = findViewById(R.id.recyclerview)
        adapter = ComicsAdapter(resultList, this)
        viewModel = ViewModelProviders.of(this).get(ComicsViewModel::class.java)
    }

    private fun InstanceLoginGoogleGSO() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    private fun pegaOsDadosDoGoogle() {
        val googleSignInAccount: GoogleSignInAccount? = intent.getParcelableExtra(LoginActivity.GOOGLE_ACCOUNT)
        Picasso.get().load(googleSignInAccount!!.photoUrl).into(imagemlogogoogle)
        logingoogle!!.text = googleSignInAccount.displayName
    }

    private fun InstanceViewModel() {
        viewModel!!.comics
        viewModel!!.listaComics.observe(this, Observer { resultaLista: List<Result>? -> adapter!!.atualizalista(resultaLista!!) })
        viewModel!!.loading().observe(this, Observer { loading: Boolean ->
            if (loading) {
                progressBar!!.visibility = View.VISIBLE
            } else {
                progressBar!!.visibility = View.GONE
            }
        })

    }

    private fun InstanceRecyclerView() {
        recyclerview!!.adapter = adapter
        recyclerview!!.layoutManager = GridLayoutManager(this, 3)
    }

    override fun Onclick(result: Result?) {
        val intent = Intent(this, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(RESULT_KEY, result)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    companion object {
        const val RESULT_KEY = "result"
    }
}