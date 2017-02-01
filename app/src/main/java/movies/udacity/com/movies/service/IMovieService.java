package movies.udacity.com.movies.service;

import movies.udacity.com.movies.constants.Constants;
import movies.udacity.com.movies.pojo.PopularMovies;
import retrofit2.Call;
import retrofit2.http.GET;


public interface IMovieService {


    @GET(Constants.GET_MOVIES_END_POINT + Constants.API_KEY + Constants.API_KEY_VALUE)
    Call<PopularMovies> listMovies();


}
