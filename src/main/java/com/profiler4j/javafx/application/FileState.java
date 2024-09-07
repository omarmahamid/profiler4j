package com.profiler4j.javafx.application;

import java.util.ArrayList;
import java.util.List;

public class FileState {

    private String filePath;
    private final List<FilePathObserver> observers = new ArrayList<>();

    public void setFilePath(String filePath){
        this.filePath = filePath;
        notifyObservers();
    }

    public String getFilePath(){
        return filePath;
    }

    public void registerObserver(FilePathObserver observer){
        observers.add(observer);
    }


    private void notifyObservers() {
        for (FilePathObserver observer : observers) {
            observer.update(filePath);
        }
    }

}
