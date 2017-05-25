package br.com.packapps.librarypackappsombr.returns;

import java.util.List;

import br.com.packapps.librarypackappsombr.models.Categories;

/**
 * Created by paulolinhares on 25/05/17.
 */

public class CategoriesReturn extends ReturnMain {
    private List<Categories> data;

    public List<Categories> getData() {
        return data;
    }

    public void setData(List<Categories> data) {
        this.data = data;
    }
}
