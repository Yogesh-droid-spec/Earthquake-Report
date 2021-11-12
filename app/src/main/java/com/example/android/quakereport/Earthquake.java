package com.example.android.quakereport;

public class Earthquake {
    private double mmag;
    private String mlocation;
    private long mTimeinmillisec;
    private String mUrl;

    public Earthquake(double mag,String location,long Timeinmillisec,String Url )
    {
       mmag=mag;
       mlocation=location;
       mTimeinmillisec=Timeinmillisec;
       mUrl=Url;
    }

    public double getMag() { return mmag;}

    public String getMlocation() { return mlocation; }

    public long getmTimeinmillisec() { return mTimeinmillisec; }

    public String getmUrl(){return mUrl;}
}
