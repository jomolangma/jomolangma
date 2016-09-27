package com.jomolangma.app.algorithm;

/**
 * Created by ZhangLijun on 3/21/16.
 */
public class Sort {

    private int[] array;

    public Sort(int[] array) {
        this.array = array;
    }

    //按顺序打印数组中的元素
    public void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    //冒泡排序
    public void bubbleSort() {
        int temp;
        int len = array.length;

        for (int i = 0; i < len - 1; i++) {  //外层循环：每循环一次就确定了一个相对最大元素
            for (int j = 1; j < len - i; j++) {  //内层循环：有i个元素已经排好，根据i确定本次的比较次数
                if (array[j - 1] > array[j]) {  //如果前一位大于后一位，交换位置
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
            System.out.print("第" + (i + 1) + "轮排序结果：");
            display();
        }
    }


    //选择排序
    public void selectionSort() {
        int minPoint;  //存储最小元素的小标
        int len = array.length;
        int temp;
        int counter = 1;

        for (int i = 0; i < len - 1; i++) {

            minPoint = i;
            for (int j = i + 1; j <= len - 1; j++) {  //每完成一轮排序，就确定了一个相对最小元素，下一轮排序只对后面的元素排序
                if (array[j] < array[minPoint]) {  //如果待排数组中的某个元素比当前元素小，minPoint指向该元素的下标
                    minPoint = j;
                }
            }

            if (minPoint != i) {  //如果发现了更小的元素，交换位置
                temp = array[i];
                array[i] = array[minPoint];
                array[minPoint] = temp;
            }

            System.out.print("第" + counter + "轮排序结果：");
            display();
            counter++;
        }
    }

    //归并排序
    public void mergeSort() {
        int[] workSpace = new int[array.length]; //用于辅助排序的数组
        recursiveMergeSort(workSpace, 0, workSpace.length - 1);
    }

    /**
     * 递归的归并排序
     *
     * @param workSpace  辅助排序的数组
     * @param lowerBound 欲归并数组段的最小下标
     * @param upperBound 欲归并数组段的最大下标
     */
    private void recursiveMergeSort(int[] workSpace, int lowerBound, int upperBound) {

        if (lowerBound == upperBound) {  //该段只有一个元素，不用排序
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recursiveMergeSort(workSpace, lowerBound, mid);    //对低位段归并排序
            recursiveMergeSort(workSpace, mid + 1, upperBound);  //对高位段归并排序
            merge(workSpace, lowerBound, mid, upperBound);
            display();
        }
    }

    /**
     * 对数组array中的两段进行合并，lowerBound~mid为低位段，mid+1~upperBound为高位段
     *
     * @param workSpace  辅助归并的数组，容纳归并后的元素
     * @param lowerBound 合并段的起始下标
     * @param mid        合并段的中点下标
     * @param upperBound 合并段的结束下标
     */
    private void merge(int[] workSpace, int lowerBound, int mid, int upperBound) {

        int lowBegin = lowerBound;  //低位段的起始下标
        int lowEnd = mid;           //低位段的结束下标
        int highBegin = mid + 1;  //高位段的起始下标
        int highEnd = upperBound;  //高位段的结束下标
        int j = 0; //workSpace的下标指针
        int n = upperBound - lowerBound + 1;  //归并的元素总数

        while (lowBegin <= lowEnd && highBegin <= highEnd) {
            if (array[lowBegin] < array[highBegin]) {//将两者较小的那个放到workSpace中
                workSpace[j++] = array[lowBegin++];
            } else {
                workSpace[j++] = array[highBegin++];
            }
        }

        while (lowBegin <= lowEnd) {
            workSpace[j++] = array[lowBegin++];
        }

        while (highBegin <= highEnd) {
            workSpace[j++] = array[highBegin++];
        }

        for (j = 0; j < n; j++) {  //将归并好的元素复制到array中
            array[lowerBound++] = workSpace[j];
        }

    }

    //快速排序
    public void quikSort() {
        recursiveQuikSort(0, array.length - 1);
    }

    /**
     * 递归的快速排序
     *
     * @param low  数组的最小下标
     * @param high 数组的最大下标
     */
    private void recursiveQuikSort(int low, int high) {
        if (low >= high) {
            return;
        } else {
            int pivot = array[low];  //以第一个元素为基准
            int partition = partition(low, high, pivot);  //对数组进行划分，比pivot小的元素在低位段，比pivot大的元素在高位段

            display();

            recursiveQuikSort(low, partition - 1);  //对划分后的低位段进行快速排序
            recursiveQuikSort(partition + 1, high);  //对划分后的高位段进行快速排序
        }
    }

    /**
     * 以pivot为基准对下标low到high的数组进行划分
     *
     * @param low   数组段的最小下标
     * @param high  数组段的最大下标
     * @param pivot 划分的基准元素
     * @return 划分完成后基准元素所在位置的下标
     */
    private int partition(int low, int high, int pivot) {

        while (low < high) {

            while (low < high && array[high] >= pivot) {  //从右端开始扫描，定位到第一个比pivot小的元素
                high--;
            }
            swap(low, high);

            while (low < high && array[low] <= pivot) {  //从左端开始扫描，定位到第一个比pivot大的元素
                low++;
            }
            swap(low, high);

        }
        return low;

    }

    /**
     * 交换数组中两个元素的数据
     *
     * @param low  欲交换元素的低位下标
     * @param high 欲交换元素的高位下标
     */
    private void swap(int low, int high) {
        int temp = array[high];
        array[high] = array[low];
        array[low] = temp;
    }

    public static void main(String[] args) {
        int[] a = {57, 68, 59, 52, 72, 28, 96, 33, 24, 19};
        Sort sort = new Sort(a);
        System.out.print("未排序时的结果：");
        sort.display();
        sort.quikSort();
    }
}
