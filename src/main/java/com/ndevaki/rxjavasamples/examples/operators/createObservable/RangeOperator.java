package com.ndevaki.rxjavasamples.examples.operators.createObservable;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class RangeOperator {
    static Observable<Integer> observable=Observable.range(1,20);
    static DisposableObserver observer;
    public static void main(String[] args){
        observer=new DisposableObserver() {
            public void onNext(Object o) {
                System.out.println(o.toString());
            }

            public void onError(Throwable throwable) {

            }

            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}

