package movies.udacity.com.movies.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import movies.udacity.com.movies.R;
import movies.udacity.com.movies.adapter.MovieAdapter;
import movies.udacity.com.movies.pojo.Movie;
import movies.udacity.com.movies.pojo.MovieParcelable;
import movies.udacity.com.movies.service.ServiceMovieImpl;


public class MainFragment extends Fragment implements ServiceMovieImpl.ISendData {

    private static final String TAG_LOG = MainFragment.class.getSimpleName();

    private List<Movie> mMovieList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private MovieParcelable mMoviesParcel = new MovieParcelable();
    private ServiceMovieImpl serviceMovie;

    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        serviceMovie = new ServiceMovieImpl(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            serviceMovie.getPopularMovies();
        } else {
            mMoviesParcel = savedInstanceState.getParcelable("listMovies");
            mMovieList = mMoviesParcel.getMovieList();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMoviesParcel.setMovieList(mMovieList);
        outState.putParcelable("listMovies", mMoviesParcel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_grid_view_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        //improve recycler view performance as long all view are the same height and width
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(mMovieList, mContext);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void passData(List<Movie> list) {
        mMovieList = list;
        mAdapter = new MovieAdapter(mMovieList, mContext);
        mRecyclerView.setAdapter(mAdapter);

    }


}