package br.com.digitalhouse.projetomarvel.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.projetomarvel.R;
import br.com.digitalhouse.projetomarvel.pojo.Result;
import br.com.digitalhouse.projetomarvel.view.Interface.OnClickDetails;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {

    private List<Result> listresult;
    private OnClickDetails listener;


    public ComicsAdapter(List<Result> listresult, OnClickDetails listener) {
        this.listresult = listresult;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ComicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsAdapter.ViewHolder holder, int position) {
        final Result resultlist = listresult.get(position);
        holder.onBind(resultlist);

        holder.itemView.setOnClickListener(v -> listener.Onclick(resultlist));
    }

    @Override
    public int getItemCount() {
        return listresult.size();
    }

    public void atualizalista(List<Result> resultList) {
        this.listresult.clear();
        this.listresult = resultList;
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.marvellogo);
            textViewTitle = itemView.findViewById(R.id.idComics);

        }

        public void onBind(Result result){

            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imageView);
            textViewTitle.setText(result.getTitle());
        }
    }
}
