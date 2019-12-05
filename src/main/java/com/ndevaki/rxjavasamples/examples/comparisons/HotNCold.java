package com.ndevaki.rxjavasamples.examples.comparisons;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotNCold {

    public static void main(String[] args){
        ArrayList<Integer> coldList=new ArrayList<Integer>();
        ArrayList<Integer> hotList=new ArrayList<Integer>();
        emitColdObservable().subscribe(System.out::println);
      //  print(coldList);
        emitHotObservable().subscribe(System.out::println);
      //  print(hotList);
    }

    public static void print(List<Integer> list){
        for(Integer item:list){
            System.out.println(item.toString());
        }
    }

    public static Observable emitHotObservable(){
       return  new Observable<Integer>(){
           @Override
           protected void subscribeActual(Observer<? super Integer> observer) {
               observer.onNext(1);
               observer.onNext(2);
               observer.onNext(3);
               observer.onNext(4);
               observer.onNext(5);
               observer.onComplete();
           }
       };
    }

    public static Observable<Integer> emitColdObservable(){
        return Observable.fromIterable(Arrays.asList(1,2,3,4,5,6,7));
    }
}
