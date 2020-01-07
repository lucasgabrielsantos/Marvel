package br.com.digitalhouse.projetomarvel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.digitalhouse.projetomarvel.R;
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

            carregaImagens(result);

            SetAsViews(result);

            formatDateSetText(result);

            cliqueImagem(result);
        }
    }

    private void SetAsViews(Result result) {
        titleview.setText(result.getTitle());
        descview.setText(result.getDescription());
        priceView.setText(result.getPrices().get(0).getPrice());
        pagesview.setText(result.getPageCount());
    }

    private void carregaImagens(Result result) {
        Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imgDetails);
        Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(img2Details);

    }

    private void formatDateSetText(Result result) {
        try {
            Locale local = new Locale("pt", "BR");
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("MMMM ',' dd ',' yyyy", local);
            String inputText = result.getDates().get(0).getDate();
            Date date = inputFormat.parse(inputText);
            String outputText = outputFormat.format(date);
            publishedview.setText(outputText);

        } catch (Exception ex) {

            Log.i("Exception", "------------> " + ex.getMessage());
        }
    }

    private void cliqueImagem(Result result) {

        imgDetails.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, DetailsImageActivity.class);
            intent.putExtra(DETAILSIMAGE_KEY, result);
            startActivity(intent);

        });
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
