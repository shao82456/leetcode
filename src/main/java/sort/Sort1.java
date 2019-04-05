package sort;

import jdk.nashorn.internal.ir.IdentNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Sort1 {
    static interface Sorter{
        void sort(int[] arr);
    }
    static class BubbleSort implements Sorter{
        @Override
        public void sort(int[] arr) {
            if(arr==null||arr.length<2) return;

            int lastpos=0;//lastpos表示最后一个无序的位置
            //i表示要冒出来的元素的位置
            for(int i= arr.length-1;i>0;i=lastpos){
                lastpos=0;//每次默认数组已经有序
                for(int j=0;j<i;j++){
                    if(arr[j]>arr[j+1]){
                        int tmp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=tmp;
                        lastpos=j;
                    }
                }
            }
        }
    }
    static class SelectSort implements Sorter{
        @Override
        public void sort(int[] arr) {
            if(null==arr||arr.length<2)
                return;
            for(int i=0;i<arr.length-1;i++){
                int min=arr[i];
                int minpos=i;
                for(int j=i+1;j<arr.length;j++){
                    if(arr[j]<min){
                        min=arr[j];
                        minpos=j;
                    }
                }
                arr[minpos]=arr[i];
                arr[i]=min;
            }
        }
    }
    static class InsertSort implements Sorter{
        @Override
        public void sort(int[] arr) {
            if(null==arr||arr.length<2)
                return;
            for(int i=1;i<arr.length;i++){
                int key=arr[i];
                int j;
                for(j=i-1;j>=0&&key<arr[j];j--){
                    arr[j+1]=arr[j];
                }
                arr[j+1]=key;
            }
        }
    }
    static class MergeSort implements Sorter{
        @Override
        public void sort(int[] arr) {
            sort(arr,0,arr.length-1);
        }

        private void sort(int[] arr, int p, int r) {
            if(p<r){
                int q=(p+r)/2;
                sort(arr,p,q);
                sort(arr,q+1,r);
                merge(arr,p,q,r);
            }
        }

        private void merge(int[] arr, int p, int q, int r) {
            int[] arr1=new int[q-p+1];
            int[] arr2=new int[r-q];
            for(int i=0;i<arr1.length;i++)
                arr1[i]=arr[p+i];
            for(int i=0;i<arr2.length;i++)
                arr2[i]=arr[q+1+i];

            int i1=0,i2=0,i=p;
            while (i1<arr1.length&&i2<arr2.length){
                if(arr1[i1]<=arr2[i2])
                    arr[i++]=arr1[i1++];
                else
                    arr[i++]=arr2[i2++];
            }

            while (i1<arr1.length)
                    arr[i++]=arr1[i1++];
            while (i2<arr2.length)
                    arr[i++]=arr2[i2++];
        }
    }
    public static void main(String[] args) {
        testSort(new BubbleSort());
    }

    static void testSort(Sorter sorter){
        Random rd= new Random();
        for(int i=0;i<10;i++){
            int n=rd.nextInt(20);
            int[] input=new int[n];
            for(int j=0;j<n;j++) input[j]=rd.nextInt(10);
            TreeMap<Integer,Integer> ori=new TreeMap<>();
            for(int num:input)
                    ori.put(num,ori.getOrDefault(num,0)+1);
//            System.out.println(Arrays.toString(input));
            sorter.sort(input);
            TreeMap<Integer,Integer> sorted=new TreeMap<>();
            for(int num:input)
                sorted.put(num,sorted.getOrDefault(num,0)+1);
            for(Map.Entry<Integer, Integer> entry:sorted.entrySet()){
                if(entry.getValue()!=ori.get(entry.getKey())) {
                    System.out.println("wrong");
                    break;
                }
            }
            if(!checkSorted(input)) {
                System.out.println("wrong");
                break;
            }
//            System.out.println(Arrays.toString(input));
        }
        System.out.println("ok");
    }

    static boolean checkSorted(int[] arr){
        if(arr==null||arr.length==0) return true;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]>arr[i+1]) return false;
        }
        return true;
    }
}
