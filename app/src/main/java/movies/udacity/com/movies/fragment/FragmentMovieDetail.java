package movies.udacity.com.movies.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import movies.udacity.com.movies.R;
import movies.udacity.com.movies.pojo.Movie;


public class FragmentMovieDetail extends Fragment {

    //Define the tag for debug
    private final static String TAG_LOG = FragmentMovieDetail.class.getSimpleName();
    private Context mContext;


    private ImageView mImageViewPoster;
    private TextView mTextViewTitle;
    private TextView mOverview;
    private TextView mReleaseDate;
    private TextView mVoteAverage;


    public FragmentMovieDetail(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        Movie movie = bundle.getParcelable("movie");
        Log.d(TAG_LOG,"getting the info from the intent "+movie.toString());

        mImageViewPoster = (ImageView) view.findViewById(R.id.posterId);
        mTextViewTitle = (TextView) view.findViewById(R.id.tvContentDetailTitle);

        mOverview = (TextView)view.findViewById(R.id.tvContentDetailOverview);

        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w185/" + movie.getPoster_path())
                .into(mImageViewPoster);
        mTextViewTitle.setText(movie.getOriginal_title());
        mOverview.setText(movie.getOverview());


        return view;
    }




}
