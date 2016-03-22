package com.jomolangma.app.algorithm;

/**
 * Created by ZhangLijun on 3/21/16.
 */
public class Sort {
    public static void bubbleSort(int [] array){
        int temp;

        for (int i=0; i<array.length-1;i++){
            for (int j=1;j<array.length-i;j++){
                if(array[j-1]>array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args){
        int[] array = {7,2,8,4,6,9,1,3,0,5};

        bubbleSort(array);

        for (int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
    }
}
