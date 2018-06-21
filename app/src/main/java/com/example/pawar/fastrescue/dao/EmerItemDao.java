package com.example.pawar.fastrescue.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawar on 04-Feb-17.
 */

public class EmerItemDao implements Parcelable {
    @SerializedName("accept_id")
    @Expose
    private String acceptId;
    @SerializedName("official_id")
    @Expose
    private String officialId;
    @SerializedName("noti_id")
    @Expose
    private String notiId;
    @SerializedName("official_time")
    @Expose
    private String officialTime;
    @SerializedName("accept_status")
    @Expose
    private String acceptStatus;
    @SerializedName("noti_latitude")
    @Expose
    private String notiLatitude;
    @SerializedName("noti_longitude")
    @Expose
    private String notiLongitude;
    @SerializedName("noti_event")
    @Expose
    private String notiEvent;
    @SerializedName("noti_detail")
    @Expose
    private String notiDetail;
    @SerializedName("noti_pstatus")
    @Expose
    private String notiPstatus;
    @SerializedName("noti_date")
    @Expose
    private String notiDate;
    @SerializedName("noti_user")
    @Expose
    private String notiUser;
    @SerializedName("noti_official")
    @Expose
    private String notiOfficial;
    @SerializedName("monitor_id")
    @Expose
    private String monitorId;
    @SerializedName("noti_status")
    @Expose
    private String notiStatus;
    @SerializedName("file_id")
    @Expose
    private String fileId;
    @SerializedName("noti_filename")
    @Expose
    private String notiFilename;

    protected EmerItemDao(Parcel in) {
        acceptId = in.readString();
        officialId = in.readString();
        notiId = in.readString();
        officialTime = in.readString();
        acceptStatus = in.readString();
        notiLatitude = in.readString();
        notiLongitude = in.readString();
        notiEvent = in.readString();
        notiDetail = in.readString();
        notiPstatus = in.readString();
        notiDate = in.readString();
        notiUser = in.readString();
        notiOfficial = in.readString();
        monitorId = in.readString();
        notiStatus = in.readString();
        fileId = in.readString();
        notiFilename = in.readString();
    }

    public static final Creator<EmerItemDao> CREATOR = new Creator<EmerItemDao>() {
        @Override
        public EmerItemDao createFromParcel(Parcel in) {
            return new EmerItemDao(in);
        }

        @Override
        public EmerItemDao[] newArray(int size) {
            return new EmerItemDao[size];
        }
    };

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public String getNotiId() {
        return notiId;
    }

    public void setNotiId(String notiId) {
        this.notiId = notiId;
    }

    public String getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(String officialTime) {
        this.officialTime = officialTime;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getNotiLatitude() {
        return notiLatitude;
    }

    public void setNotiLatitude(String notiLatitude) {
        this.notiLatitude = notiLatitude;
    }

    public String getNotiLongitude() {
        return notiLongitude;
    }

    public void setNotiLongitude(String notiLongitude) {
        this.notiLongitude = notiLongitude;
    }

    public String getNotiEvent() {
        return notiEvent;
    }

    public void setNotiEvent(String notiEvent) {
        this.notiEvent = notiEvent;
    }

    public String getNotiDetail() {
        return notiDetail;
    }

    public void setNotiDetail(String notiDetail) {
        this.notiDetail = notiDetail;
    }

    public String getNotiPstatus() {
        return notiPstatus;
    }

    public void setNotiPstatus(String notiPstatus) {
        this.notiPstatus = notiPstatus;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiUser() {
        return notiUser;
    }

    public void setNotiUser(String notiUser) {
        this.notiUser = notiUser;
    }

    public String getNotiOfficial() {
        return notiOfficial;
    }

    public void setNotiOfficial(String notiOfficial) {
        this.notiOfficial = notiOfficial;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public String getNotiStatus() {
        return notiStatus;
    }

    public void setNotiStatus(String notiStatus) {
        this.notiStatus = notiStatus;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getNotiFilename() {
        return notiFilename;
    }

    public void setNotiFilename(String notiFilename) {
        this.notiFilename = notiFilename;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(acceptId);
        dest.writeString(officialId);
        dest.writeString(notiId);
        dest.writeString(officialTime);
        dest.writeString(acceptStatus);
        dest.writeString(notiLatitude);
        dest.writeString(notiLongitude);
        dest.writeString(notiEvent);
        dest.writeString(notiDetail);
        dest.writeString(notiPstatus);
        dest.writeString(notiDate);
        dest.writeString(notiUser);
        dest.writeString(notiOfficial);
        dest.writeString(monitorId);
        dest.writeString(notiStatus);
        dest.writeString(fileId);
        dest.writeString(notiFilename);
    }
}

