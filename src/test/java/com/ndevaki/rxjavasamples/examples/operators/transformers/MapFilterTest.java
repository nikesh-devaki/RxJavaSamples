package com.ndevaki.rxjavasamples.examples.operators.transformers;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

    public class MapFilterTest {

        private MapFilter mapFilter;

        @Before
        public void setUp() {
            mapFilter = new MapFilter();
        }

        @Test
        public void evenCharWordsTest() {
            mapFilter.getEvenCharWords(Arrays.asList("java", "apple", "sun", "care"))
                    .test()
                    .assertValues("java", "care")
                    .assertNoErrors()
                    .assertComplete();
        }

        @Test
        public void squareGreaterThanTest() {
            mapFilter.getSquareNumbersBiggerThan(Arrays.asList(4, 7, 2, 9, 5), 25)
                    .test()
                    .assertValues(49, 81)
                    .assertNoErrors()
                    .assertComplete();
        }

        @Test
        public void toUpperBoomTest() {
            mapFilter.toUppercase(Arrays.asList("one", "two", "three", "boom", "four"))
                    .test()
                    .assertValues("ONE", "TWO", "THREE")
                    .assertError(RuntimeException.class)
                    .assertNotComplete();
        }
    }

