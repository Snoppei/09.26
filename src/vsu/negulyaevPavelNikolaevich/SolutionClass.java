package vsu.negulyaevPavelNikolaevich;
import util.ArrayUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static util.ArrayUtils.toIntArray2;

public class SolutionClass {
    public static List<Integer> createNewList(List<Integer> list1, List<Integer> list2){
        int countOfRepeats = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < list1.size(); i++){
            if(list1.get(i)%2==0){ // четное число из 1 списка
                for(int j = 0; j < list2.size(); j++){ // проверка на то есть ли это число во 2 списке
                    if(equals(list1.get(i), list2.get(j))){ // если есть, оно нам не подходит
                        countOfRepeats++;
                    }
                }
                if(countOfRepeats==0){
                    list.add(list1.get(i));
                    countOfRepeats = 0;
                }
                if(countOfRepeats!=0){
                    countOfRepeats=0;
                }
            }
        }
        countOfRepeats = 0;
        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i)%2!=0){
                for(int j = 0; j < list1.size(); j++){
                    if(equals(list2.get(i), list1.get(j))){
                        countOfRepeats++;
                    }
                }
                if(countOfRepeats==0){
                    list.add(list2.get(i));
                    countOfRepeats = 0;
                }
                if(countOfRepeats!=0){
                    countOfRepeats = 0;
                }
            }
        }
        sort(list);
        return list;
    }

    public static List<Integer> createNewList(List<Integer> list1, List<Integer> list2, Storage storage){ // перегрузка
        int[] arr1 = new int[list1.size()];
        int[] arr2 = new int[list2.size()];
        for(int i = 0; i < list1.size(); i++){
            arr1[i] = (int)list1.get(i);
        }
        for(int j = 0; j < list2.size(); j++){
            arr2[j] = (int)list2.get(j);
        }
        storage.arr1 = arr1;
        storage.arr2 = arr2;

        storage.list = createNewList(list1, list2);
        int[] sortedArr = new int[storage.list.size()];
        for(int i = 0; i < storage.list.size(); i++){
            sortedArr[i] = (int)storage.list.get(i);
        }
        storage.sortedArray = sortedArr;

        int[][] matrix = listsToMatrix(list1, list2);
        storage.listsInMatrix = matrix;

        matrixToLists(matrix, storage);

        return storage.list;
    }
    public static Storage createNewListForGUI(List<Integer> list1, List<Integer> list2){
        Storage storage = new Storage();
        int[] arr1 = new int[list1.size()];
        int[] arr2 = new int[list2.size()];
        for(int i = 0; i < list1.size(); i++){
            arr1[i] = (int)list1.get(i);
        }
        for(int j = 0; j < list2.size(); j++){
            arr2[j] = (int)list2.get(j);
        }
        storage.arr1 = arr1;
        storage.arr2 = arr2;

        storage.list = createNewList(list1, list2);
        int[] sortedArr = new int[storage.list.size()];
        for(int i = 0; i < storage.list.size(); i++){
            sortedArr[i] = (int)storage.list.get(i);
        }
        storage.sortedArray = sortedArr;

        int[][] matrix = listsToMatrix(list1, list2);
        storage.listsInMatrix = matrix;

        matrixToLists(matrix, storage);

        return storage;
    }

    public static void sort(List<Integer> list){ // сортировка "пузырьком" для списка по возрастанию
        for(int i = list.size()-1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(list.get(j) > list.get(j+1)){
                    Integer temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
    }

    public static boolean equals(Integer a, Integer b){
        if((int)a == (int)b){
            return true;
        }
        else{
            return false;
        }
    }

    public static int indexOf(List<Integer> list, int value){
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if((int)list.get(i) == value){
                index = i;
            }
        }
        return index;
    }

    public static int[][] listsToMatrix(List list1, List list2){
        int[] arr1 = new int[list1.size()];
        int[] arr2 = new int[list2.size()];
        int[][] matrix = new int[2][];
        for(int i = 0; i < list1.size(); i++){
            arr1[i] = (int)list1.get(i);
        }
        for(int j = 0; j < list2.size(); j++){
            arr2[j] = (int)list2.get(j);
        }
        matrix[0]=arr1;
        matrix[1]=arr2;
        return matrix;
    }

    public static int[][] readIntArray2FromFile(String fileName) {
        try {
            return toIntArray2(ArrayUtils.readLinesFromFile(fileName));
        }
        catch(FileNotFoundException e) {
            return null;
        }
    }
    public static void writeIntArrayToConsole(int[][] IntArray){ // debugged
        for(int rows = 0; rows < IntArray.length; rows++){
            for(int cols = 0; cols < IntArray[0].length; cols++){
                System.out.print(IntArray[rows][cols] + " ");
                if(cols == IntArray[0].length-1){
                    System.out.println();
                }
            }
        }
    }

    public static void matrixToLists(int[][] matrix, Storage storage){
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<>();
        for(int i = 0; i < matrix[0].length; i++){
            list1.add(matrix[0][i]);
        }
        for(int j = 0; j < matrix[1].length; j++){
            list2.add(matrix[1][j]);
        }
        storage.list1 = list1;
        storage.list2 = list2;
    }

    public static <T> void printCollection(Collection<T> collection) {
        boolean first = true;
        for (T item : collection) {
            System.out.print((first ? "" : ", ") + item);
            first = false;
        }
        System.out.println();
    }

}
