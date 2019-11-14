package br.com.digitalhouse.projetomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.projetomarvel.R;
import br.com.digitalhouse.projetomarvel.pojo.Result;

import static br.com.digitalhouse.projetomarvel.view.activity.DetailsActivity.DETAILSIMAGE_KEY;
import static br.com.digitalhouse.projetomarvel.view.activity.MainActivity.RESULT_KEY;

public class DetailsImageActivity extends AppCompatActivity {

    private ImageView imageViewDetailsClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_image);

        initViews();

        if (getIntent() != null && getIntent().getExtras() != null) {

            Result result = getIntent().getExtras().getParcelable(DETAILSIMAGE_KEY);

            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imageViewDetailsClick);

        }
    }

    public void initViews(){
        imageViewDetailsClick = findViewById(R.id.imageDetailsClick);

    }
}
