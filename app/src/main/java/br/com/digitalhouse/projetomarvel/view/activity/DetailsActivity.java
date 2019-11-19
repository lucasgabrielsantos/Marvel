package br.com.digitalhouse.projetomarvel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.digitalhouse.projetomarvel.R;
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

            try {

                //Atribui objeto de acordo com a localização determinada
                Locale local = new Locale("pt", "BR");
                //Determina a formatação inicial da data ou seja pega o formato que vem a sua data
                // do o objeto e formata de acordo com o padrão estabelecido no objeto
                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                //Determina o formato de saida da data ou seja o resultado final da data já formatada
                DateFormat outputFormat = new SimpleDateFormat("MMMM ',' dd ',' yyyy", local);
                //Atribui a uma variavel o valor que está chegando da data
                String inputText = result.getDates().get(0).getDate();
                //Formata o valor atribuido a variavel de acordo com o padrão estabelecido no objeto
                // de inputFormat transformando-o para um tipo Date do java
                Date date = inputFormat.parse(inputText);
                //Formata para o padrão de saida estabelecido inicialmente no objeto outputFormat
                String outputText = outputFormat.format(date);
                //Seta no textView a data formatada de acordo com o padrão estabelecido
                publishedview.setText(outputText);

            }catch (Exception ex){

                Log.i("Exception", "------------> "+ex.getMessage());
            }


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
