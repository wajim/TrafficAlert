package com.luxjim.trafficalert.obj;

/**
 * Created by Kanku on 02/04/2015.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable {

    private int id;
    private String origine;
    //private String coord1;
    private String arrivee;
    //private String coord2;

    public Route() {
        super();
    }

    private Route(Parcel in) {
        super();
        this.id = in.readInt();
        this.origine = in.readString();
        //this.coord1 = in.readString();
        this.arrivee = in.readString();
        //this.coord2 = in.readString();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    //public String getCoord1() {
      //  return coord1;
    //}

    //public void setCoord1(String coord1) {
      //  this.coord1 = coord1;
    //}

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    //public String getCoord2() {
      //  return coord2;
    //}

    //public void setCoord2(String coord2) {
      //  this.coord2 = coord2;
    //}

    @Override
    public String toString() {
        return "Route [id=" + id + ", origine=" + origine + ", arrivee=" + arrivee + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Route other = (Route) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getOrigine());
        //parcel.writeString(getCoord1());
        parcel.writeString(getArrivee());
        //parcel.writeString(getCoord2());

    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

}