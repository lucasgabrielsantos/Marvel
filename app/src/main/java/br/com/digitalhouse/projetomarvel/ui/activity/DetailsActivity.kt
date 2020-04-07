package br.com.digitalhouse.projetomarvel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.projetomarvel.Interfaces.OnClickImageDetails
import br.com.digitalhouse.projetomarvel.R
import br.com.digitalhouse.projetomarvel.api.Result
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.DETAILSIMAGE_KEY
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.RESULT_KEY
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import com.squareup.picasso.Picasso
import getPortugueseTitle.conditions
import getPortugueseTitle.englishPortugueseTranslator
import kotlinx.android.synthetic.main.activity_details.*
import org.w3c.dom.Text
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity(), OnClickImageDetails {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent != null && intent.extras != null) {
            val result: Result? = intent.extras!!.getParcelable(RESULT_KEY)

            carregaImagens(result)
            SetAsViews(result)
            formatDateSetText(result)

            onClickImage(result)
        }
    }


    private fun SetAsViews(result: Result?) {
        titleDetails!!.text = result!!.title
        descdetails!!.text = result.description
        priceDetails!!.text = result.prices[0].price
        pagesDetails!!.text = result.pageCount
        setPortugueseTitle()
    }

    private fun carregaImagens(result: Result?) {
        Picasso.get().load(result!!.thumbnail.path + ".jpg").into(imageDetails)
        Picasso.get().load(result.thumbnail.path + ".jpg").into(imagem2Details)
    }

    private fun formatDateSetText(result: Result?) {
        try {
            val local = Locale("pt", "BR")
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat: DateFormat = SimpleDateFormat("MMMM ',' dd ',' yyyy", local)
            val inputText = result!!.dates[0].date
            val date = inputFormat.parse(inputText)
            val outputText = outputFormat.format(date!!)
            publishedetails!!.text = outputText
        } catch (ex: Exception) {
            Log.i("Exception", "------------> " + ex.message)
        }
    }

    private fun onClickImage(result: Result?) {
        imageDetails!!.setOnClickListener {
            val intent = Intent(this@DetailsActivity, DetailsImageActivity::class.java)
            intent.putExtra(DETAILSIMAGE_KEY, result)
            startActivity(intent)
        }
    }

    override fun Onclick(result: Result?) {
        val intent = Intent(this, DetailsImageActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(RESULT_KEY, result)
        intent.putExtras(bundle)
    }

    private fun setPortugueseTitle() {
        englishPortugueseTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener {
            englishPortugueseTranslator.translate(descdetails.text.toString())
                    .addOnSuccessListener { translatedText ->
                        descdetails.text = translatedText
                    }.addOnFailureListener {
                       lateinit var errortranslate : String
                        Log.i("ExceptionTranslate", "------------> $errortranslate")
                    }
        }

    }
}