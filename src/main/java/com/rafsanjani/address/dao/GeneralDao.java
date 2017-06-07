package com.rafsanjani.address.dao;

import com.rafsanjani.address.entity.General;
import javafx.collections.ObservableList;

public interface GeneralDao {
    
    public ObservableList<General> getGenerals();
    public General getGeneral(int id);
    public void saveGeneral(General general);
    public void updateGeneral(General general);
    public void deleteGeneral(General general);
}
