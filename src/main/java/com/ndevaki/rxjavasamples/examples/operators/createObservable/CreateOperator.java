package com.ndevaki.rxjavasamples.examples.operators.createObservable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

import java.util.ArrayList;

public class CreateOperator {
    static class Student{
        int id;
        String name;
        public Student(int id,String name){
            this.id=id;
            this.name=name;
        }
        public String toString(){
            return id+" "+name;
        }
    }
    static Observable<Student> observable;
    static DisposableObserver observer;
    public static void main(String[] args){

       observable=Observable.create(new ObservableOnSubscribe<Student>() {
           public  void subscribe(ObservableEmitter<Student> emitter){
               ArrayList<Student> students=getStudents();
               for(Student student:students){
                   emitter.onNext(student);
               }
               emitter.onComplete();
           }
       });

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

    public static ArrayList<Student> getStudents(){
        ArrayList<Student> students=new ArrayList<Student>();
        students.add(new Student(1,"nikesh"));
        students.add(new Student(2,"name2"));
        students.add(new Student(3,"name3"));
        return students;
    }
}
