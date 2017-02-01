package movies.udacity.com.movies.service;


import android.util.Log;

import java.util.List;

import movies.udacity.com.movies.pojo.Movie;
import movies.udacity.com.movies.pojo.PopularMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallService {

    private static final String TAG_LOG = CallService.class.getSimpleName();

    private List<Movie> listMovie;
    ServiceMovieImpl serviceMovie = new ServiceMovieImpl();

    private static CallService callService = new CallService();

    public static CallService getInstance(){
        if(callService == null){
            callService = new CallService();
        }
        return callService;
    }

    public List<Movie> getPopularMovies(){

        Call<PopularMovies> listCall = serviceMovie.service.listMovies();
        Log.d(TAG_LOG, "doing the call");
        listCall.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                PopularMovies popularMovies = response.body();
                Log.d(TAG_LOG, "we already got throughout on success " + popularMovies);
                listMovie = popularMovies.getResults();
                Log.d(TAG_LOG, "popularMovies");
                for (Movie movie : listMovie) {
                    Log.d(TAG_LOG,movie.toString());
                }
            }
            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {
                Log.d(TAG_LOG, "something went wrong");
                t.printStackTrace();
            }
        });

        return listMovie;

    }


}
