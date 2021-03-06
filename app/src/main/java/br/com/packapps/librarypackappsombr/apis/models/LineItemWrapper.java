package br.com.packapps.librarypackappsombr.apis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

import br.com.packapps.librarypackappsombr.models.LineItem;

/**
 * Created by vaibhav on 12/28/15.
 */
public class LineItemWrapper implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("line_item")
    @Expose
    private LineItem lineItem;

    public LineItemWrapper(LineItem lineItem) {
        this.lineItem = lineItem;
    }

    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }
}
