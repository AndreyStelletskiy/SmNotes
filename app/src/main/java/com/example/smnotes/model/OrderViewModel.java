package com.example.smnotes.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {

    private MutableLiveData<String> _notename = new MutableLiveData<>("");// для Model
    public LiveData<String> notename  = _notename; // для View

    private MutableLiveData<String> _topic = new MutableLiveData<>("");// для Model
    public LiveData<String> topic  = _topic; // для View

    public void set_name(String _notename) {
        this._notename.setValue(_notename);
    }

    public void set_topic(String _topic) {
        this._topic.setValue(_topic);
    }
}
