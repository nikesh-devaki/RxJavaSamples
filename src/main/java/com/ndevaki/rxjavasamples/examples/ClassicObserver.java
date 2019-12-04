package com.ndevaki.rxjavasamples.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassicObserver {

public static void main(String[] args){
    Observable1 observable =new Observable1();
    observable.registerObserver(new Observer1());
    observable.registerObserver(new Observer1());
    observable.notifyAllObserver();
}

public interface Observer{
    public void onEvent(Object obj);
}

public static class Observer1 implements Observer{

    public void onEvent(Object obj) {
        System.out.println(Thread.currentThread().toString()+" "+obj.toString());
    }
}

public interface Observable{
    public void registerObserver(Observer observer);
    public void unregisterObserver(Observer observer);
    void notifyAllObserver();
}


public static class Observable1 implements Observable{
    List<Observer> observerCollection=new ArrayList<Observer>();
    public void registerObserver(Observer observer) {
        observerCollection.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observerCollection.remove(observer);
    }

    public void notifyAllObserver() {
        for(Observer observer:observerCollection){
            observer.onEvent("Hello world");
        }
    }
}
}
