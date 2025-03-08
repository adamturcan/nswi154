package cz.cuni.mff.turcana.util;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Arrays {
    private static final int Prepinac = 32;

    public static void paraMergeSort(int[] arr){
        ForkJoinPool pool = new ForkJoinPool();
        int[] sortedarr = pool.invoke(new ParallelMergeSort(arr));

        System.arraycopy(sortedarr,0,arr,0,sortedarr.length);
        System.out.println("aa");
    }



    private static class ParallelMergeSort extends RecursiveTask<int[]> {




        private final int[] numbers;
        public ParallelMergeSort(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        protected int[] compute() {


            if (numbers.length <= Prepinac) {

                int[] sorted = numbers.clone();
                java.util.Arrays.sort(sorted);
                return sorted;
            }


            int len = numbers.length;

            int [] arr1 = java.util.Arrays.copyOfRange(numbers,0,len/2);
            int [] arr2 = java.util.Arrays.copyOfRange(numbers,len/2,len);


            ParallelMergeSort left = new ParallelMergeSort(arr1);
            ParallelMergeSort right = new ParallelMergeSort(arr2);


            left.fork();
            right.fork();

            int[] leftResult = left.join();
            int[] rightResult = right.join();


            return merge(leftResult, rightResult);




        }


        private int[] merge(int[] arr1, int[] arr2) {

            int [] result = new int[arr1.length + arr2.length];

            int arr1Index = 0;
            int arr2Index = 0;
            int index = 0;


            while (arr1Index < arr1.length && arr2Index < arr2.length ) {

                result[index++]  = arr1[arr1Index] < arr2[arr2Index] ? arr1[arr1Index++] : arr2[arr2Index++];

            }


            while (arr1Index < arr1.length) {
                result[index++] = arr1[arr1Index++];
            }
            while (arr2Index < arr2.length) {
                result[index++] = arr2[arr2Index++];
            }


            return result;


        }

    }




}
