package com.ndevaki.rxjavasamples.examples.comparisons;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RxVsStream {
    public static void main(String[] args) {
        List<String> list =  Arrays.asList("1", "2", "3", "4", "5");
        List<Integer> result=processWithRx(list);
        List<Integer> classicList=processWithStreams(list);
        for(Integer num:result){
            System.out.println(num);
        }
        for(Integer num:classicList){
            System.out.println(num);
        }
        List<Integer> alist =  Arrays.asList(1,2,3,4,5,6);
        List<Integer> asyncList=calculateSquareAsync(alist);
        for(Integer num:asyncList){
            System.out.println(num);
        }
    }

    private static List<Integer> processWithStreams(List<String> list) {

        return list.stream().map(new java.util.function.Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).map(new java.util.function.Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s*s;
            }
        }).collect(Collectors.toList());


    }

    private static ArrayList<Integer> processWithRx(List<String> list) {
        ArrayList<Integer> result=new ArrayList<Integer>();
        Observable.fromIterable(list)
                  .map(new Function<String, Integer>() {

                      public Integer apply(String s) throws Exception {
                          System.out.println("Map 1"+Thread.currentThread().getName());
                          return Integer.parseInt(s);
                      }
                  })
                  .map(new Function<Integer, Integer>() {

                      public Integer apply(Integer num) throws Exception {
                          System.out.println("Map 2"+Thread.currentThread().getName());
                          return num*num;
                      }
                  })
                .blockingSubscribe(result::add);
        System.out.println("main "+Thread.currentThread().getName());
                return result;
    }

    //asynchronous execution
    public static ArrayList<Integer> calculateSquareAsync(List<Integer> list){
        ArrayList<Integer> result=new ArrayList<Integer>();
        Observable.fromIterable(list)
                  .flatMap(integer->Observable.just(integer))
                  .map(integer->{
                          System.out.println("map "+integer+" "+Thread.currentThread().getName());
                          Thread.sleep(3000);
                          return integer*integer;
                      })
                  .subscribeOn(Schedulers.io())
                  .blockingSubscribe(result::add);
        return result;

        }
}
