package movies.udacity.com.movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import movies.udacity.com.movies.R;
import movies.udacity.com.movies.activity.DetailActivity;
import movies.udacity.com.movies.pojo.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final String TAG_LOG = MovieAdapter.class.getSimpleName();

    // Store the context for easy access
    private Context mContext;
    protected List<Movie> mMovieList = new ArrayList<>();

    public MovieAdapter(List<Movie> movieList, Context context) {
        mMovieList = movieList;
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }


    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_item, parent, false);

        Log.d(TAG_LOG, "getting the onCreateViewHolder");

        return new ViewHolder(view, mContext);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(holder.mImageView.getContext())
                .load("http://image.tmdb.org/t/p/w185/" + mMovieList.get(position).getPoster_path())
                .into(holder.mImageView);

        Log.d(TAG_LOG, "getting the onBindViewHolder " + position);
    }


    @Override
    public int getItemCount() {
        return mMovieList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mImageView;
        private Context mContext;

        public ViewHolder(final View itemView, Context context) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.view_holder_item_poster_id);
            mContext = context;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();//get items position
            if (position != RecyclerView.NO_POSITION) {// Check if an item was deleted, but the user clicked it before the UI removed it
                Movie movie = mMovieList.get(position);
                Log.d("ontouch", "movie  ----> " + movie.toString());
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie",movie);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        }
    }


}
