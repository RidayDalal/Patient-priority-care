package src;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the src.PatientRecord at the root should be the smallest src.PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the src.PatientRecord.compareTo() method to compare
 * PatientRecords.
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
    private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
    private int size; // size of this priority queue


    /**
     * Creates a new empty src.PriorityCareAdmissions queue with the given capacity
     *
     * @param capacity Capacity of this src.PriorityCareAdmissions queue
     * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
     *                                  positive integer
     */
    public PriorityCareAdmissions(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity");
        } else {
            this.size = 0;
            queue = new PatientRecord[capacity];
        }
    }

    /**
     * Checks whether this src.PriorityCareAdmissions queue is empty
     *
     * @return {@code true} if this src.PriorityCareAdmissions queue is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the size of this src.PriorityCareAdmissions queue
     *
     * @return the total number of PatientRecords stored in this src.PriorityCareAdmissions queue
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the capacity of this src.PriorityCareAdmissions queue
     *
     * @return the capacity of this src.PriorityCareAdmissions queue
     */
    public int capacity() {
        return queue.length;
    }


    /**
     * Removes all the elements from this src.PriorityCareAdmissions queue
     */
    public void clear() {
        Arrays.fill(queue, null);
        this.size = 0;
    }

    /**
     * Returns the src.PatientRecord at the root of this src.PriorityCareAdmissions queue, i.e. the
     * src.PatientRecord having the highest priority.
     *
     * @return the src.PatientRecord at the root of this src.PriorityCareAdmissions queue
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
     *                                if this src.PriorityCareAdmissions queue is empty
     */
    public PatientRecord peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return queue[0];
        }
    }


    /**
     * Adds the given src.PatientRecord to this src.PriorityCareAdmissions queue at the correct position based
     * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
     * src.PatientRecord at each index is less than or equal to than the PatientRecords in its child
     * nodes. PatientRecords should be compared using the src.PatientRecord.compareTo() method.
     *
     * @param p src.PatientRecord to add to this src.PriorityCareAdmissions queue
     * @throws NullPointerException  if the given src.PatientRecord is null
     * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
     *                               if this src.PriorityCareAdmissions queue is full
     */
    public void addPatient(PatientRecord p) {

        if (p == null) {
            throw new NullPointerException();
        }
        if (size == this.capacity()) {
            throw new IllegalStateException("Warning: Full Admissions Queue!");
        } else {
            queue[size] = p;
            size++;
            percolateUp(size - 1);
        }
    }

    /**
     * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
     * priority queue by percolating a leaf up the heap. If the element at the given index does not
     * violate the min-heap invariant (it is greater than its parent), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
     * continue percolating the element up the heap.
     *
     * @param i index of the element in the heap to percolate upwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateUp(int i) {

        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            if (i > 0) {
                PatientRecord temp;
                int parentPosition = (i - 1) / 2;
                if (queue[parentPosition].compareTo(queue[i]) > 0) {
                    temp = queue[parentPosition];
                    queue[parentPosition] = queue[i];
                    queue[i] = temp;
                    percolateUp(parentPosition);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     * Removes and returns the src.PatientRecord at the root of this src.PriorityCareAdmissions queue, i.e.
     * the src.PatientRecord having the highest priority (the minimum one).
     *
     * @return the src.PatientRecord in this src.PriorityCareAdmissions queue at the root of this priority
     *         queue.
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
     *                                if this src.PriorityCareAdmissions queue is empty
     */
    public PatientRecord removeBestRecord() {

        if (isEmpty()) {
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");
        } else {
            PatientRecord recordToBeRemoved = queue[0];
            queue[0] = queue[size - 1];
            queue[size - 1] = null;
            size--;
            percolateDown(0);
            return recordToBeRemoved;
        }
    }


    /**
     * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
     * by percolating its root down the tree. If the element at the given index does not violate the
     * min-heap ordering property (it is smaller than its smallest child), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
     * correct child and continue percolating the element down the heap.
     *
     * @param i index of the element in the heap to percolate downwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateDown(int i) {

        if (i > size || i < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            PatientRecord temp;
            int leftChildPosition = (2 * i) + 1;
            int rightChildPosition = (2 * i) + 2;
            int positionToSwapWith;

            if (leftChildPosition > size - 1) {
                return;
            }
            else {
                if (rightChildPosition > size - 1) {
                    positionToSwapWith = leftChildPosition;
                } else {
                    if (queue[leftChildPosition].compareTo(queue[rightChildPosition]) <= 0) {
                        positionToSwapWith = leftChildPosition;
                    } else {
                        positionToSwapWith = rightChildPosition;
                    }
                }
            }

            if (queue[i].compareTo(queue[positionToSwapWith]) > 0) {
                temp = queue[positionToSwapWith];
                queue[positionToSwapWith] = queue[i];
                queue[i] = temp;
                percolateDown(positionToSwapWith);
            }
        }
    }


    /**
     * Returns a deep copy of this src.PriorityCareAdmissions queue containing all of its elements in the
     * same order. This method does not return the deepest copy, meaning that you do not need to
     * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
     * be duplicated.
     *
     * @return a deep copy of this src.PriorityCareAdmissions queue. The returned new priority care
     *         admissions queue has the same length and size as this queue.
     */
    public PriorityCareAdmissions deepCopy() {
        PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
        deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
        deepCopy.size = this.size;
        return deepCopy;
    }

    /**
     * Returns a deep copy of the array-heap of this src.PriorityCareAdmissions queue <BR/>
     *
     * This method can be used for testing purposes.
     *
     * @return a deep copy of the array-heap storing the ParientRecords in this queue
     */
    protected PatientRecord[] arrayHeapCopy() {
        return Arrays.copyOf(this.queue, this.queue.length);

    }
    /**
     * Returns a String representing this src.PriorityCareAdmissions queue, where each element
     * (src.PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
     *
     * @return a String representing this src.PriorityCareAdmissions queue, and an empty String "" if this
     *         queue is empty.
     */
    public String toString() {
        String patientList = "";

        if (isEmpty()) {
            return "";
        }
        PriorityCareAdmissions deepCopy = this.deepCopy();

        for (int i = 0; i < size; i++) {
            patientList = patientList + deepCopy.removeBestRecord().toString() + "\n";
        }
        return patientList;

    }


}
