package com.ndevaki.rxjavasamples.examples;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DisposableExample {
    private static  String displayString="Fist Message";
    private static Observable<String> observable= Observable.just(displayString);
    private static Observer observer;
    private static Disposable disposable;

    public static void main(String[] args){
        observer=new Observer() {
            public void onSubscribe(io.reactivex.disposables.Disposable d) {
                System.out.println("OnSubscribe called");
                disposable=d;
            }

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
