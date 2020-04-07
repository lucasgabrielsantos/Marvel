import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions

object getPortugueseTitle {
    private val options = FirebaseTranslatorOptions.Builder().setSourceLanguage(FirebaseTranslateLanguage.EN).setTargetLanguage(FirebaseTranslateLanguage.PT).build()
    val englishPortugueseTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)
    val conditions = FirebaseModelDownloadConditions.Builder().requireWifi().build()

}
