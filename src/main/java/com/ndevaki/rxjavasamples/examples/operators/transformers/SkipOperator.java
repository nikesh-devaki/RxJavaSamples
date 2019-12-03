package com.ndevaki.rxjavasamples.examples.operators.transformers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

import java.util.ArrayList;

public class SkipOperator {
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

        public int hashCode(){
            return this.id;
        }
        public boolean equals(Object o){
            SkipLastOperator.Student s=(SkipLastOperator.Student)o;
            return s.id==this.id;
        }
    }
    static Observable<SkipLastOperator.Student> observable;
    static DisposableObserver observer;
    public static void main(String[] args){

        observable= Observable.create(new ObservableOnSubscribe<SkipLastOperator.Student>() {
            public  void subscribe(ObservableEmitter<SkipLastOperator.Student> emitter){
                ArrayList<SkipLastOperator.Student> students=getStudents();
                for(SkipLastOperator.Student student:students){
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
                .distinct()
                .skip(1)
                .subscribe(observer);
    }
    public static ArrayList<SkipLastOperator.Student> getStudents(){
        ArrayList<SkipLastOperator.Student> students=new ArrayList<SkipLastOperator.Student>();
        students.add(new SkipLastOperator.Student(1,"nikesh"));
        students.add(new SkipLastOperator.Student(2,"name2"));
        students.add(new SkipLastOperator.Student(3,"name3"));

        students.add(new SkipLastOperator.Student(4,"name3"));
        students.add(new SkipLastOperator.Student(5,"name3"));
        students.add(new SkipLastOperator.Student(6,"name3"));
        students.add(new SkipLastOperator.Student(6,"name3"));
        return students;
    }
}
