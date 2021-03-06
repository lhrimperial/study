package com.github.study.algorithm.sort;

import java.util.Arrays;

/**
 * @author longhairen
 * @create 2017-10-02 10:20
 * @description
 **/
public class SortAlgorithm {
    public static void main(String[] args) {
        int[] arr = {3,1,5,7,2,4,9,6,10,8};
        System.out.println(Arrays.toString(arr));
//        insertSort(arr);
//        System.out.println("插入排序："+Arrays.toString(arr));
//        shellInsertSort(arr);
//        System.out.println("希尔排序："+Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("冒泡排序："+Arrays.toString(arr));
        quitSort(arr, 0, arr.length - 1);
        System.out.println("快速排序："+Arrays.toString(arr));

    }

    /**
     * 快速排序
     * 选择一个基准元素,通常选择第一个元素或者最后一个元素,
     2）通过一趟排序讲待排序的记录分割成独立的两部分，其中一部分记录的元素值均比基准元素值小。另一部分记录的 元素值比基准值大。
     * @param array
     * @param low
     * @param high
     */
    public static void quitSort(int[] array, int low, int high){
        if (low < high) {
            int mid = getMiddle(array, low, high);
            quitSort(array, low, mid - 1);
            quitSort(array, mid + 1, high);
        }
    }

    public static int getMiddle(int[] arr, int low, int high){
        System.out.println("low="+low+" high="+high);
        System.out.println(Arrays.toString(arr));
        int key = arr[low];
        while (low < high) {
            while(low < high && key <= arr[high]){
                high--;
            }
            arr[low] = arr[high];
            while(low < high && key >= arr[low]){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = key;
        return low;
    }

    /**
     * 冒泡排序
     * 在要排序的一组数中，对当前还未排好序的范围内的全部数，
     * 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
     * @param array
     */
    public static void bubbleSort(int[] array){
        int temp;
        boolean swap;
        for (int i = 0, len = array.length; i < len; i++){
            swap = false;
            for (int j = 0; j < len - i - 1; j++){
                if (array[j+1] < array[j]){
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    swap = true;
                }
                System.out.println("i="+i+" j="+j);
                System.out.println(Arrays.toString(array));
            }
            if (!swap) {
                break;
            }
        }
    }

    /**
     * 希尔排序
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
     * 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     * @param array
     */
    public static void shellInsertSort(int[] array){
        int j, curr, dk = array.length / 2;
        while(dk >= 1){
            for (int i = dk, len = array.length; i < len; i++){
                curr = array[i];
                for (j = i - dk; j >= 0 && curr < array[j]; j = j - dk){
                    array[j + dk] = array[j];
                }
                array[j+dk] = curr;
            }

            dk = dk / 2;
        }
    }

    /**
     * 插入排序
     * 先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
     * @param array
     */
    public static void insertSort(int[] array){
        int index, curr;
        for (int i = 1, len = array.length; i < len; i++){
            curr = array[i];
            for (index = i -1; index >= 0 && curr < array[index]; index--){
                array[index+1] = array[index];
            }
            array[index+1] = curr;
        }
    }

}
