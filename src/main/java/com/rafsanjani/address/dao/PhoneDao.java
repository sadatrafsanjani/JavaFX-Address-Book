package com.rafsanjani.address.dao;

import com.rafsanjani.address.entity.Phone;
import javafx.collections.ObservableList;

public interface PhoneDao {
    
    public ObservableList<Phone> getPhones();
    public Phone getPhone(int id);
    public void savePhone(Phone phone);
    public void updatePhone(Phone phone);
    public void deletePhone(Phone phone);
}
