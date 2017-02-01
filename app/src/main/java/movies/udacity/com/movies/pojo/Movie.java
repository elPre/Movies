package movies.udacity.com.movies.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class Movie implements Parcelable {

    private String original_title;
    private String poster_path;
    private String overview;
    private Double vote_average;
    private String release_date;


    public Movie(){}

    public Movie(Parcel in) {
        this.original_title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.vote_average = in.readDouble();
        this.release_date = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.original_title);
        out.writeString(this.poster_path);
        out.writeString(this.overview);
        out.writeDouble(this.vote_average);
        out.writeString(this.release_date);
    }

    public static final Parcelable.Creator<Movie> CREATOR =
            new Parcelable.Creator<Movie>(){

                @Override
                public Movie createFromParcel(Parcel in){
                  return new Movie(in);
                }
                @Override
                public Movie[] newArray(int size){
                    return new Movie[size];
                }

            };


    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public static class Builder{

        private String original_title;
        private String poster_path;
        private String overview;
        private Double vote_average;
        private String release_date;

        public Builder originalTitle(final String original_title){
            this.original_title = original_title;
            return this;
        }
        public Builder posterPath(final String poster_path){
            this.poster_path = poster_path;
            return this;
        }
        public Builder overview(final String overview){
            this.overview = overview;
            return this;
        }
        public Builder voteAverage(final Double vote_average){
            this.vote_average = vote_average;
            return this;
        }
        public Builder releaseDate(final String release_date){
            this.release_date = release_date;
            return this;
        }

        public Movie build(){
            return new Movie(this);
        }

    }

    private Movie(Builder builder){
        this.original_title = builder.original_title;
        this.poster_path = builder.poster_path;
        this.overview = builder.overview;
        this.vote_average = builder.vote_average;
        this.release_date = builder.release_date;
    }

    @Override
    public String toString(){
        return getOriginal_title()+" ";
    }


}
