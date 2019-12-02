package com.ndevaki.rxjavasamples.examples;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class DisposableOberserverExample {
    private static  String displayString="Fist Message";
    private static Observable<String> observable= Observable.just(displayString);
    private static DisposableObserver observer;
    private static Disposable disposable;

    public static void main(String[] args){
        observer=new DisposableObserver() {
            public void onNext(Object o) {
                System.out.println("onNext called "+displayString);
            }

            public void onError(Throwable throwable) {
                System.out.println("onError called");
            }

            public void onComplete() {
                System.out.println("complete called");
            }
        };
        observable.subscribe(observer);
        displayString="Second Message";
    }

    public static void stopMainThread(){
        //stops/kills/interrupts subscribers
        disposable.dispose();
        Thread.currentThread().interrupt();
    }
}
