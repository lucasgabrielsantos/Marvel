package br.com.digitalhouse.projetomarvel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.projetomarvel.R;
import br.com.digitalhouse.projetomarvel.pojo.Date;
import br.com.digitalhouse.projetomarvel.pojo.Price;
import br.com.digitalhouse.projetomarvel.pojo.Result;
import br.com.digitalhouse.projetomarvel.view.Interface.OnClickImageDetails;

import static br.com.digitalhouse.projetomarvel.view.activity.MainActivity.RESULT_KEY;

public class DetailsActivity extends AppCompatActivity implements OnClickImageDetails {

    private ImageView imgDetails, img2Details;
    private TextView titleview, descview, publishedview, priceView, pagesview;

    public static final String DETAILSIMAGE_KEY = "imagedetails";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();

        if (getIntent() != null && getIntent().getExtras() != null) {

            Result result = getIntent().getExtras().getParcelable(RESULT_KEY);

            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imgDetails);
            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(img2Details);

            titleview.setText(result.getTitle());
            descview.setText(result.getDescription());


            //TODO: Tentar converter date to string//

            publishedview.setText(result.getDates().get(0).getDate());


            priceView.setText(result.getPrices().get(0).getPrice());
            pagesview.setText(result.getPageCount());


            imgDetails.setOnClickListener(v -> {
                Intent intent = new Intent(DetailsActivity.this, DetailsImageActivity.class);
                intent.putExtra(DETAILSIMAGE_KEY, result);
                startActivity(intent);

            });

        }
    }


    public void initViews() {

        imgDetails = findViewById(R.id.imageDetails);
        img2Details = findViewById(R.id.imagem2Details);
        titleview = findViewById(R.id.titleDetails);
        descview = findViewById(R.id.descDetails);
        publishedview = findViewById(R.id.publishedetails);
        priceView = findViewById(R.id.priceDetails);
        pagesview = findViewById(R.id.pagesDetails);


    }

    @Override
    public void Onclick(Result result) {
        Intent intent = new Intent(this, DetailsImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_KEY, result);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
