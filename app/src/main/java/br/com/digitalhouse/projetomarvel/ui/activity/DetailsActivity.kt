package br.com.digitalhouse.projetomarvel.ui.activity

//import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
//import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.projetomarvel.Interfaces.OnClickImageDetails
import br.com.digitalhouse.projetomarvel.R
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.DETAILSIMAGE_KEY
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.RESULT_KEY
import br.com.digitalhouse.projetomarvel.pojo.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
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

//            getPortugueseTitle()

            SetAsViews(result)

            formatDateSetText(result)

            cliqueImagem(result)
        }
    }


    private fun SetAsViews(result: Result?) {

        titleDetails!!.text = result!!.title

        descdetails!!.text = result.description
        priceDetails!!.text = result.prices[0].price
        pagesDetails!!.text = result.pageCount
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

    private fun cliqueImagem(result: Result?) {
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

//    fun getPortugueseTitle() {
//        val options = FirebaseTranslatorOptions.Builder().setSourceLanguage(FirebaseTranslateLanguage.EN).setTargetLanguage(FirebaseTranslateLanguage.PT).build()
//        val englishPortugueseTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)
//        val conditions = FirebaseModelDownloadConditions.Builder().requireWifi().build()
//        englishPortugueseTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener {
//            englishPortugueseTranslator.translate(descdetails.text.toString()).addOnSuccessListener { translatedText -> descdetails.text = translatedText }.addOnFailureListener {}
//        }

}