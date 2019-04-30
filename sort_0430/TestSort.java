package exercise.sort_0430;

import java.util.Arrays;
import java.util.Stack;

public class TestSort {
    public static void main(String[] args) {
        int[] array = {12,5,9,34,6,8,33,56,89,0,7,4,22,55,77};
        //quickSort(array);
        //mergeSort(array,0,array.length-1);
        mergeSort1(array);
        System.out.println(Arrays.toString(array));
    }
    /*
    非递归快排
     */
    public static void quickSort(int[] array){
        Stack<Integer> stack = new Stack<>();
        int low = 0;
        int high = array.length-1;
        int par = partion(array,low,high);
        //左边有两个数据元素以上
        if(par > low+1){
            stack.push(low);
            stack.push(par-1);
        }
        //右边有两个元素以上
        if(par < high-1){
            stack.push(par+1);
            stack.push(high);
        }
        while(!stack.empty()){
            high = stack.pop();
            low = stack.pop();
            par= partion(array,low,high);
            if(par > low+1){
                stack.push(low);
                stack.push(par-1);
            }
            if(par < high-1){
                stack.push(par+1);
                stack.push(high);
            }
        }
    }
    public static int  partion(int[] array,int low,int high){
        int tmp = array[low];
        while(low < high){
            while((low < high) && array[high] >= tmp){
                high--;
            }
            if(low == high){
                break;
            }else{
                array[low] = array[high];
            }
            while((low < high) && array[low] <= tmp){
                low++;
            }
            if(low == high){
                break;
            }else{
                array[high] = array[low];
            }
        }
        array[low] = tmp;
        return low;
    }

    /*
    归并排序
     */
    public static void mergeSort(int[] array,int start,int end){
        if(start >= end){
            return;
        }
        int mid = (start+end)>>1;
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);
        merge(array,start,mid,end);
    }
    //合并
    public static void merge(int[] array,int start,int mid,int end){
       int[] tmpArr = new int[array.length];
       int tmpIndex = start;//指的是tmpArr的下标
        int start2 = mid+1;//第二个归并段的开始
        int i = start;
        while(start <= mid && start2<=end){
            if(array[start] <= array[start2]){
                tmpArr[tmpIndex++] = array[start++];
            }else{
                tmpArr[tmpIndex++] = array[start2++];
            }
        }
        if(start2<=end){
            System.arraycopy(array,start2,tmpArr,tmpIndex,end-start2+1);
        }else if(start <= mid){
            System.arraycopy(array,start,tmpArr,tmpIndex,mid-start+1);
        }
        System.arraycopy(tmpArr,i,array,i,end-i+1);
    }

    /*
    非递归
    归并排序
     */
    public static void mergeSort1(int[] array){
        for(int i=1; i<array.length; i*=2){
            merge1(array,i);
        }
    }
    /*
    gap:每组的个数
     */
    public static void merge1(int[] array,int gap){
        int[] tmpArr = new int[array.length];
        int i=0;
        int start1 = 0;
        int end1= start1+gap-1;
        int start2 =end1+1;
        int end2 = start2+gap-1 >array.length-1 ? array.length-1 : start2+gap-1;
        //保证有两个归并段
        while (start2 < array.length){
            while(start1<=end1 && start2<=end2){
                if(array[start1] <=array[start2]){
                    tmpArr[i++] = array[start1++];
                }else{
                    tmpArr[i++] = array[start2++];
                }
            }
            while (start1<=end1){
                tmpArr[i++] = array[start1++];
            }
            while (start2<=end2){
                tmpArr[i++] = array[start2++];
            }
            start1 = end2+1;
            end1 = start1+gap-1;
            start2 = end1+1;
            end2 = start2+gap-1 >array.length-1 ? array.length-1 : start2+gap-1;
        }
        while(start1 <= array.length-1){
            tmpArr[i++] = array[start1++];
        }
        System.arraycopy(tmpArr,0,array,0,array.length);
    }
}
