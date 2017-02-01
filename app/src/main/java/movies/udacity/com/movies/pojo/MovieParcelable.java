package movies.udacity.com.movies.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class MovieParcelable implements Parcelable {

    protected List<Movie> movieList;

    public MovieParcelable(){}

    private MovieParcelable(Parcel in){
        movieList = in.readArrayList(Movie.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieParcelable> CREATOR =
            new Parcelable.Creator<MovieParcelable>(){

                @Override
                public MovieParcelable createFromParcel(Parcel in){
                    return new MovieParcelable(in);
                }
                @Override
                public MovieParcelable[] newArray(int size){
                    return new MovieParcelable[size];
                }

            };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(movieList);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
