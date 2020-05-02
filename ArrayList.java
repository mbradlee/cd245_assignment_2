import java.lang.reflect.Array;

public class ArrayList<T> {

    protected Class<T> type;
    protected T[] array;
    protected int items;

    /**
     * Generic constructor with no parameters
     * Creates an array of type T[] of size 10
     * Initializes num of items to 0
     */

    public ArrayList(){
        items = 0;
        array = (T[])new Object[5];
    }

    /**
     * Secondary constructor for ArrayList
     * Will create an ArrayList of given size and type
     * @param typeInit initial type given
     * @param sizeInit initial size given
     */

    public ArrayList(Class<T> typeInit, int sizeInit){
        type = typeInit;
        items = sizeInit;
        array = (T[])Array.newInstance(type, items);
    }

    /**
     * Add fucntion will add a given item to the next available spot
     * in the ArrayList (array[items]). If there is no room in the
     * ArrayList (items == array.length) the array size will be doubled
     * @param data given item T to be added
     * @return void
     */

    public void add(T data){
        if(items == array.length){
            double_array();
        }

        for(int i = array.length - 1; i > items; i--){
            array[i] = array[i-1];
        }
        array[items] = data;
        items++;
    }

    public void add(int pos, T data){
        if(pos < 0 || pos > items){
            throw new IndexOutOfBoundsException();
        }

        if(items == array.length){
            double_array();
        }

        for(int i = array.length - 1; i > pos; i--){
            array[i] = array[i-1];
        }
        array[pos] = data;
        items++;

    }

    public T get(int pos){
        if(pos < 0 || pos > items){
            throw new IndexOutOfBoundsException();
        }

        return array[pos];
    }

    public void set(int pos, T item){
        if(pos < 0 || pos > items){
            throw new IndexOutOfBoundsException();
        }

        array[pos] = item;
    }

    public T remove(int pos){

        T ret = array[pos];

        if(pos < 0 || pos > items){
            throw new IndexOutOfBoundsException();
        }

        if(items == pos){
            items--;
        }else{
            for(int i = array.length - 1; i > pos; i--){
                array[i] = array[i-1];
            }
            items--;
        }
        return ret;
    }

    public int size(){
        return items;
    }

    protected void double_array(){
        T[] tempArray;
        tempArray = (T[])new Object[array.length*2];

        for(int i = 0; i < array.length; i++){
            tempArray[i] = array[i];
        }

        array = tempArray;
    }

    //BINARY SEARCH FUNCTION

    public int find(String targetName, ArrayList<CSVStruct> a){
        int index = find(targetName, a, 0, a.size());
        return index;
    }

    protected int find(String targetName, ArrayList<CSVStruct> a, int min, int max){

        int mid = (min + max) / 2;

        if(min > max || a.get(mid) == null){
            return -1;
        }

        if(a.get(mid) == null)
            System.out.println("null pointer");


        if(a.get(mid).fullName.compareTo(targetName) == 0 || a.get(mid).shortName.compareTo(targetName) == 0){
            return mid;
        }else if(a.get(mid).fullName.compareTo(targetName) < 0){
            return find(targetName, a, mid+1, max);
        }else{
            return find(targetName, a, min, mid-1);
        }
    }

    protected void clear(){
        for(int i = 0; i < items; i++){
            remove(i);
        }
    }

}
