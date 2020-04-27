import java.util.*;

public class QuickSort {

    public void sort(ArrayList<CSVStruct> a){
        quickSort(a, 0, a.size()-1);
    }

    void quickSort(ArrayList<CSVStruct> a, int start, int end){
        if(start < end){
            //get the index of the pivot
            int pivot = partition(a, start, end);
            //sort the left
            quickSort(a, start, pivot-1);
            //sort the right
            quickSort(a, pivot+1, end);
        }
    }

    int partition(ArrayList<CSVStruct> a, int start, int end){
        Random r = new Random();
        int pivot = r.nextInt((end - start)+1)+start;
        int pIndex = start;
        swap(a, pivot, end);
        pivot = end;
        for(int i = start; i < end; i++){
            if(a.get(i).fullName.compareTo(a.get(pivot).fullName) < 0 || a.get(i).fullName.compareTo(a.get(pivot).fullName) == 0){
                swap(a, i, pIndex);
                ++pIndex;
            }
        }

        swap(a, pIndex, pivot);
        return pIndex;
    }

    void swap(ArrayList<CSVStruct> a, int x, int y){
        CSVStruct temp = a.get(x);
        a.set(x, a.get(y));
        a.set(y, temp);
    }

}
