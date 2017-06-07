package com.rafsanjani.address.dao;

import com.rafsanjani.address.entity.Web;
import javafx.collections.ObservableList;

public interface WebDao {
 
    public ObservableList<Web> getWebs();
    public Web getWeb(int id);
    public void saveWeb(Web web);
    public void updateWeb(Web web);
    public void deleteWeb(Web web);
}
