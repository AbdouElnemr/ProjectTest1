package elnemr.com.project.Adapters;

import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by root on 3/28/16.
 */
public class Pojo implements Serializable {

    private String imageurl;
    private String year;
    private String vote_average;
    private String overview;
    private String title;


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}