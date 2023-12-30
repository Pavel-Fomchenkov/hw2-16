package pro.sky.javacoursepart2.intList;

import pro.sky.javacoursepart2.exceptions.*;

public class IntListImpl implements IntList {
    private int capacity = 10; // storage value (default value is 10)
    private int[] storage = new int[capacity];
    private int size = 0; // actual quantity of elements in storage

    public IntListImpl() {
    }

    public IntListImpl(int capacity) {
        if (capacity < 1) {
            throw new IllegalCapacityException("Capacity " + capacity + " cannot be lower than 1");
        }
        this.capacity = capacity;
    }

    @Override
    public int add(Integer item) {
        checkItem(item);
        checkCapacity(size);
        storage[size++] = item;
        return item;
    }


    @Override
    public int add(int index, Integer item) {
        checkIndex(index);
        checkItem(item);
        checkCapacity(size);
        if (index >= size) {
            storage[size++] = item;
        } else {
            size++;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = item;
        }
        return item;
    }

    @Override
    public int set(int index, Integer item) {
        checkIndex(index);
        checkItem(item);
        if (index >= size) {
            throw new IllegalIndexException("There is no element in storage with index " + index + ". Use \"add\" method to add new element.");
        }
        storage[index] = item;
        return item;
    }

    @Override
    public int remove(int index) {
        checkIndex(index);
        int removed = storage[index];
        if (index < size - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
        size--;
        return removed;
    }

//    @Override
//    public boolean contains(Integer item) {
//        checkItem(item);
//        for (int i = 0; i < size; i++) {
//            if (item.equals(storage[i])) return true;
//        }
//        return false;
//    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        int[] storageCopy = toArray();
        sortInsertion(storageCopy);
        return searchBinary(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(storage[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(storage[i])) return i;
        }
        return -1;
    }

    @Override
    public int get(int index) {
        checkIndex(index);
        if (index >= size) throw new ItemNotFoundException("There is no element in storage with index " + index);
        return storage[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (this.size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (storage[i] != otherList.get(i)) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size != 0) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int[] toArray() {
        int[] newArr = new int[size];
        System.arraycopy(storage, 0, newArr, 0, size);
        return newArr;
    }

    private void checkIndex(int index) {
        if (index < 0) throw new IllegalIndexException(index + " negative index is not allowed");
    }

    private void checkItem(Integer item) {
        if (item == null) throw new IllegalArgumentException("Item argument: " + item + " cannot be null");
    }

    private void checkCapacity(int index) {
        if (index >= capacity)
            throw new InsufficientCapacityException("There is no space for index " + index + " item");
    }

    private void sortInsertion(int[] storageCopy) {
        for (int i = 1; i < size; i++) {
            int temp = storageCopy[i];
            int j = i;
            while (j > 0 && storageCopy[j - 1] >= temp) {
                storageCopy[j] = storageCopy[j - 1];
                j--;
            }
            storageCopy[j] = temp;
        }
    }

    private boolean searchBinary(int[] storageCopy, int item) {
        int minIdx = 0;
        int maxIdx = size - 1;
        while (minIdx <= maxIdx) {
            int midIdx = (minIdx + maxIdx) / 2;

            if (item == storageCopy[midIdx]) {
                return true;
            }

            if (item < storageCopy[midIdx]) {
                maxIdx = midIdx - 1;
            } else {
                minIdx = midIdx + 1;
            }
        }
        return false;
    }
}