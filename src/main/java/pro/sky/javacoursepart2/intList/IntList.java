package pro.sky.javacoursepart2.intList;

public interface IntList {
    int add(Integer item);
    int add(int index, Integer item);
    int set(int index, Integer item);
    int remove(int index);
    boolean contains(Integer item);
    int indexOf(Integer item);
    int lastIndexOf(Integer item);
    int get(int index);
    boolean equals(IntList otherList);
    int size();
    boolean isEmpty();
    void clear();
    int[] toArray();
}
