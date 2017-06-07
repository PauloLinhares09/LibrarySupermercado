package br.com.packapps.librarypackappsombr.apis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import br.com.packapps.librarypackappsombr.models.Product;

//@Parcel
public class ProductWrapper implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("product")
    @Expose
    private Product product;

    public ProductWrapper(){}

    /**
     *
     * @return
     *     The product
     */
    public Product getProduct() {
        return product == null ? new Product() : product;
    }
}
