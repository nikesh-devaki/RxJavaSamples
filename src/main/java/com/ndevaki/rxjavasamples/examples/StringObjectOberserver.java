package com.ndevaki.rxjavasamples.examples;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StringObjectOberserver {
    private static  String displayString="Fist Message";
    private static Observable<String> observable= Observable.just(displayString);
    private static Observer observer;

    public static void main(String[] args){


        observer=new Observer() {
            public void onSubscribe(Disposable disposable) {
                System.out.println("OnSubscribe called");
            }

            public void onNext(Object o) {
                System.out.println("onNext called"+displayString);
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
}
