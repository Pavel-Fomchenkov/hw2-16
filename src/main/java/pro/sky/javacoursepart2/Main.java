package pro.sky.javacoursepart2;

import pro.sky.javacoursepart2.intList.IntList;
import pro.sky.javacoursepart2.intList.IntListImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntList list = new IntListImpl(8);
        int i = 0;
        for (int j = 0; j < 20; j++) {
            list.add(i++);
        }
        System.out.println(Arrays.toString(list.toArray()));

    }
}