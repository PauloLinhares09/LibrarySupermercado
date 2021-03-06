package br.com.packapps.librarypackappsombr.apis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.packapps.librarypackappsombr.models.BannerTypes;
import br.com.packapps.librarypackappsombr.utils.Strings;

/**
 * Created by vaibhav on 11/9/15.
 */
public class HomeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("checksum")
    @Expose
    private String checksum;

    @SerializedName("banner_types")
    @Expose
    private ArrayList<BannerTypes> bannerTypes;

    public ArrayList<BannerTypes> getBannerTypes() {
        return bannerTypes;
    }

    public void setBannerTypes(ArrayList<BannerTypes> bannerTypes) {
        this.bannerTypes = bannerTypes;
    }

    public String getChecksum() {
        return Strings.nullSafeString(checksum);
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}


