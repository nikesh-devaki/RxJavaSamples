package com.ndevaki.rxjavasamples.examples.operators.createObservable;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class JustOperator {
    static String[] message={"Example 1","Example 2","Example 3"};
    static Observable<String[]> observable=Observable.just(message);
    static DisposableObserver observer;
    public static void main(String[] args){
        observer=new DisposableObserver() {
            public void onNext(Object o) {
                String[] temp=(String[])o;
                for(int i=0;i<temp.length;i++) {
                    System.out.println((String) temp[i]);
                }
            }

            public void onError(Throwable throwable) {

            }

            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
