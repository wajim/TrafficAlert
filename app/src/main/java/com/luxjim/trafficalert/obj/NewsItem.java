package com.luxjim.trafficalert.obj;

/**
 * Created by Kanku on 05/05/2015.
 */
public class NewsItem {

    private String camid;
    private String destination;
    private String date;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getcamid() {
        return camid;
    }

    public void setcamid(String camid) {
        this.camid = camid;
    }

    public String getdestination() {
        return destination;
    }

    public void setdestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[ camid=" + camid + ", destination Name=" + destination + " , date=" + date + "]";
    }
}

