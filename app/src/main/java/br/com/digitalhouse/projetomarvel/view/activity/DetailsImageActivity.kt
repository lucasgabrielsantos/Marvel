package br.com.digitalhouse.projetomarvel.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.projetomarvel.R
import br.com.digitalhouse.projetomarvel.pojo.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_image.*


class DetailsImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_image)

        PicassoActionClickImage()

    }

    private fun PicassoActionClickImage() {
        if (intent != null && intent.extras != null) {
            val result: Result? = intent.extras!!.getParcelable(DetailsActivity.DETAILSIMAGE_KEY)
            Picasso.get().load(result!!.thumbnail.path + ".jpg").into(imageDetailsClick)
        }

    }
}
