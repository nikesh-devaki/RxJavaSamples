package com.ndevaki.rxjavasamples.examples.operators.transformers;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

import java.util.List;

public class MapFilter {

    /**
     * Returns an observable that processes the input words, by outputting only the ones with an even number of
     * characters.
     * @param words list of words to process.
     * @return observable that outputs only those words from the input, that have an even numbers of characters.
     */
    public Observable<String> getEvenCharWords(List<String> words) {
        return Observable.fromIterable(words)
                .flatMap(word->Observable.just(word))
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length()%2==0;
                    }
                });
    }

    /**
     * Returns an observable that computes the square of each number in the input list, and outputs only those
     * numbers greater than the supplied limit.
     * @param numbers input list of numbers to process.
     * @param limit value limit for numbers included in the output.
     * @return observable of integers.
     */
    public Observable<Integer> getSquareNumbersBiggerThan(List<Integer> numbers, int limit) {
        return Observable.fromIterable(numbers)
                .flatMap(number->Observable.just(number*number))
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer sqr) throws Exception {
                        return sqr>limit;
                    }
                });
    }

    /**
     * Returns an observable that transforms each input word to upper case. If one of the input words is "boom",
     * the observable calls onError on the subscriber with a RuntimeException.
     * @param words input list of words.
     * @return observable that may call onError on subscribers.
     */
    public Observable<String> toUppercase(List<String> words) {
        return Observable.fromIterable(words)
                .flatMap(word->Observable.just(word.toUpperCase()));
    }
}
