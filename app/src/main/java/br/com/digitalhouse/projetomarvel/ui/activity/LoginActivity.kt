package br.com.digitalhouse.projetomarvel.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.projetomarvel.R
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.GOOGLE_ACCOUNT
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {

    private var googleSignInButton: SignInButton? = null
    private var googleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleSignInButton = findViewById(R.id.btn_login_google)
        loginWithGoogle()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                101 -> try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val conta = task.getResult(ApiException::class.java)
                    concluirLogin(conta)
                } catch (e: ApiException) {
                    Log.i("LOG", "Error: " + e.message)
                    Toast.makeText(applicationContext, "Erro", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun concluirLogin(conta: GoogleSignInAccount?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(GOOGLE_ACCOUNT, conta)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        val alreadyLoggedAccount = GoogleSignIn.getLastSignedInAccount(this)
        if (alreadyLoggedAccount != null) {
            Toast.makeText(this, getString(R.string.Logged), Toast.LENGTH_SHORT).show()
            concluirLogin(alreadyLoggedAccount)
        } else {
            Toast.makeText(this, getString(R.string.openWithSomething), Toast.LENGTH_SHORT).show()
        }
    }


    private fun loginWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInButton!!.setOnClickListener {
            val signInIntent = googleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, 101)
        }
    }
}







