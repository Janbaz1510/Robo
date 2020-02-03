package com.cyl18.moviebuff.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cyl18.moviebuff.R;
import com.cyl18.moviebuff.activities.InfoActivity;
import com.cyl18.moviebuff.activities.MainActivity;
import com.cyl18.moviebuff.modals.MovieModal;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Movieview> {

    List<MovieModal> list;
    private Context context;
    private OnItemClickListner clickListner;


    public MovieAdapter(List<MovieModal> list, Context context) {
        this.list = list;
        this.context = context;
        this.clickListner = clickListner;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Movieview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        return new MovieAdapter.Movieview(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Movieview holder, int position) {

        final MovieModal movieModal = list.get(position);

        holder.mainmovietitle.setText(movieModal.getMovietitle());

        Glide.with(context)
                .load(movieModal.getMovieposter())
                .into(holder.mainmovieposter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Movieview extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView mainmovietitle;
        ImageView mainmovieposter;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public Movieview(@NonNull View itemView) {
            super(itemView);

        mainmovietitle = itemView.findViewById(R.id.main_movie_title);
        mainmovieposter = itemView.findViewById(R.id.main_movie_poster);
        linearLayout = itemView.findViewById(R.id.movielayout);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    MovieModal clickedDetails = list.get(pos);
                    Intent in = new Intent(context, InfoActivity.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<ImageView, Object>(mainmovieposter, "imagetransition");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);

                    in.putExtra("movietitle", list.get(pos).getMovietitle());
                    in.putExtra("movieposter", list.get(pos).getMovieposter());
                    in.putExtra("moviereleaseddate", list.get(pos).getMoviereleaseddate());
                    in.putExtra("movierating", list.get(pos).getMovierating());
                    in.putExtra("movieoverview", list.get(pos).getMovieoverview());
                    in.putExtra("moviereview", list.get(pos).getMoviereview());
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in, options.toBundle());
                    Toast.makeText(view.getContext(), ""+ clickedDetails.getMovietitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }







    public interface OnItemClickListner{
        void onItemClick(MovieModal modalClass);
    }


}
