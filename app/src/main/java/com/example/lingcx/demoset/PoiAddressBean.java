package com.example.lingcx.demoset;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.services.core.LatLonPoint;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/30 下午10:36
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class PoiAddressBean implements Parcelable {

    private LatLonPoint latLonPoint;
    /**
     * 信息内容
     */
    private String text;
    /**
     * 详细地址 (搜索的关键字)
     */
    private String detailAddress;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String district;

    public PoiAddressBean(String text, String detailAddress) {
        this.text = text;
        this.detailAddress = detailAddress;
    }

    public PoiAddressBean(LatLonPoint latLonPoint, String text, String detailAddress) {
        this.latLonPoint = latLonPoint;
        this.text = text;
        this.detailAddress = detailAddress;
    }

    public PoiAddressBean(LatLonPoint latLonPoint, String text, String detailAddress, String province, String city, String district) {
        this.latLonPoint = latLonPoint;
        this.text = text;
        this.detailAddress = detailAddress;
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public LatLonPoint getLatLonPoint() {
        return latLonPoint;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.latLonPoint = latLonPoint;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public PoiAddressBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.latLonPoint, flags);
        dest.writeString(this.text);
        dest.writeString(this.detailAddress);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
    }

    protected PoiAddressBean(Parcel in) {
        this.latLonPoint = in.readParcelable(LatLonPoint.class.getClassLoader());
        this.text = in.readString();
        this.detailAddress = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
    }

    public static final Creator<PoiAddressBean> CREATOR = new Creator<PoiAddressBean>() {
        @Override
        public PoiAddressBean createFromParcel(Parcel source) {
            return new PoiAddressBean(source);
        }

        @Override
        public PoiAddressBean[] newArray(int size) {
            return new PoiAddressBean[size];
        }
    };
}
