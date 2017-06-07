package com.rafsanjani.address;

import com.rafsanjani.address.entity.General;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface PersonList {
    
    ObservableList<General> PERSONLIST = FXCollections.observableArrayList();
}
