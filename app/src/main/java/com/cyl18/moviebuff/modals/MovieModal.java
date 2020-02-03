package com.cyl18.moviebuff.modals;

public class MovieModal {

    public MovieModal(String id, String movietitle, String movieposter, String movierating, String moviereleaseddate, String movieoverview, String moviereview) {
        this.movietitle = movietitle;
        this.movieposter = movieposter;
        this.id = id;
        this.movierating = movierating;
        this.moviereleaseddate = moviereleaseddate;
        this.movieoverview = movieoverview;
        this.moviereview = moviereview;
    }

    private String movietitle, movieposter, movierating, moviereleaseddate, movieoverview, moviereview;
    private String id;


    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public String getMovieposter() {
        return movieposter;
    }

    public void setMovieposter(String movieposter) {
        this.movieposter = movieposter;
    }

    public String getMovierating() {
        return movierating;
    }

    public void setMovierating(String movierating) {
        this.movierating = movierating;
    }

    public String getMoviereleaseddate() {
        return moviereleaseddate;
    }

    public void setMoviereleaseddate(String moviereleaseddate) {
        this.moviereleaseddate = moviereleaseddate;
    }

    public String getMovieoverview() {
        return movieoverview;
    }

    public void setMovieoverview(String movieoverview) {
        this.movieoverview = movieoverview;
    }

    public String getMoviereview() {
        return moviereview;
    }

    public void setMoviereview(String moviereview) {
        this.moviereview = moviereview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
