package com.ndevaki.rxjavasamples.examples;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ComspoitDisposableExample {
    private static  String displayString="Fist Message";
    private static Observable<String> observable= Observable.just(displayString);
    private static DisposableObserver observer1;
    private static DisposableObserver observer2;
    private static CompositeDisposable compositeDisposable;

    public static void main(String[] args){
        observer1=new DisposableObserver() {
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
        compositeDisposable.add(observer1);
        observable.subscribe(observer1);
        displayString="Second Message";

        observer2=new DisposableObserver() {
            public void onNext(Object o) {
                System.out.println("onNext called "+displayString);
                System.out.println("It's different implementations");
            }

            public void onError(Throwable throwable) {
                System.out.println("onError called");
            }

            public void onComplete() {
                System.out.println("complete called");
            }
        };
        compositeDisposable.add(observer2);
        observable.subscribe(observer2);

    }

    public static void stopMainThread(){
        //stops/kills/interrupts subscribers
       // disposable.dispose();
        compositeDisposable.clear();
        Thread.currentThread().interrupt();
    }
}
