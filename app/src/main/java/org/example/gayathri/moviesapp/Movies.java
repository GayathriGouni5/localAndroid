package org.example.gayathri.moviesapp;

/**
 * Created by gayathri on 4/16/17.
 */

class Movies {
    private String overView;
    private String releaseDate;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private Double popularity;
    private int voteAverage;
    private int voteCount;

//    public Movies(String overView, String releaseDate, String originalTitle, String originalLanguage, String title, Double popularity, int voteAverage, int voteCount) {
//        this.overView = overView;
//        this.releaseDate = releaseDate;
//        this.originalTitle = originalTitle;
//        this.originalLanguage = originalLanguage;
//        this.title = title;
//        this.popularity = popularity;
//        this.voteAverage = voteAverage;
//        this.voteCount = voteCount;
//    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public int getVoteAverage() {
        return voteAverage;
    }


    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "{" +
                "overView=\""  + overView + '"' +
                ", releaseDate=\"" + releaseDate + '\"' +
                ", originalTitle=\"" + originalTitle + '\"' +
                ", originalLanguage=\"" + originalLanguage + '\"' +
                ", title=\"" + title + '\"' +
                ", popularity=" + popularity +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
