package movies.udacity.com.movies.service;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import movies.udacity.com.movies.constants.Constants;
import movies.udacity.com.movies.fragment.MainFragment;
import movies.udacity.com.movies.pojo.Movie;
import movies.udacity.com.movies.pojo.PopularMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceMovieImpl {

    private static final String TAG_LOG = ServiceMovieImpl.class.getSimpleName();

    List<Movie> mListist = new ArrayList<>();
    ISendData mSendData;

    public ServiceMovieImpl(MainFragment fragment) {
        mSendData = fragment;
    }

    public ServiceMovieImpl() {
    }


    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build();
    public IMovieService service = retrofit.create(IMovieService.class);


    public void getPopularMovies() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Moves the current Thread into the background
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                ServiceMovieImpl serviceMovie = new ServiceMovieImpl();
                Call<PopularMovies> listCall = serviceMovie.service.listMovies();
                Log.d(TAG_LOG, "doing the call");
                listCall.enqueue(new Callback<PopularMovies>() {
                    @Override
                    public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {

                        PopularMovies popularMovies = response.body();
                        Log.d(TAG_LOG, "we already got throughout on success " + popularMovies);
                        Log.d(TAG_LOG, "got popularMovies");
                        mListist = popularMovies.getResults();
                        //sending data thru the callback interface
                        mSendData.passData(mListist);


                    }

                    @Override
                    public void onFailure(Call<PopularMovies> call, Throwable t) {
                        Log.d(TAG_LOG, "something went wrong");
                        t.printStackTrace();
                    }
                });
            }
        }).start();

    }

    public interface ISendData {
        void passData(List<Movie> list);
    }


}
