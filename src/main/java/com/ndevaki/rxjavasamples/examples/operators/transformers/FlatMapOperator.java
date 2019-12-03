package com.ndevaki.rxjavasamples.examples.operators.transformers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

import java.util.ArrayList;

public class FlatMapOperator {
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

        observable= Observable.create(new ObservableOnSubscribe<Student>() {
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
            //            Not all create observables operators added
//            voice is not clear. many things i dont understand even after listening repeatedly.
//            All topics are very brief. not in depth.
            public void onComplete() {

            }
        };
        observable
                .flatMap(new Function<Object, Observable<Student>>(){

                    public Observable<Student> apply(Object o) throws Exception {
                        return Observable.just((Student)o);
                    }

                })
                .map(new Function<Student, Student>() {
                    public Student apply(Student student) throws Exception {
                        student.name=student.name.toUpperCase();
                        return student;
                    }
                })
                .subscribe(observer);
    }
    public static ArrayList<Student> getStudents(){
        ArrayList<Student> students=new ArrayList<Student>();
        students.add(new Student(1,"nikesh"));
        students.add(new Student(2,"name2"));
        students.add(new Student(3,"name3"));

        students.add(new Student(4,"name3"));
        students.add(new Student(5,"name3"));
        students.add(new Student(6,"name3"));
        students.add(new Student(7,"name3"));
        return students;
    }
}
