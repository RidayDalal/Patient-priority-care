package src;

import java.util.NoSuchElementException;

/**
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations
 */
public class PriorityCareTester {

    /**
     * Tests whether compareTo() method implemented in src.PatientRecord returns a positive integer when a
     * higher triage level is compared to a lower triage level, regardless of patient order of
     * arrival. Similarly, this method tests whether compareTo() method implemented in src.PatientRecord
     * returns a negative integer when a lower triage level is compared to a higher triage level,
     * regardless of patient order of arrival.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testPatientRecordCompareToDifferentTriage() {

        PatientRecord.resetCounter();
        PatientRecord firstPatient = new PatientRecord('M', 45, TriageLevel.RED);
        PatientRecord secondPatient = new PatientRecord('F', 23, TriageLevel.YELLOW);
        PatientRecord thirdPatient = new PatientRecord('X', 34, TriageLevel.GREEN);

        {
            // 1. When a patient has higher triage level than other patient
            try {
                int actual = thirdPatient.compareTo(secondPatient);

                if (actual <= 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        {
            // 2. When a patient has lower triage level than other patient
            try {
                int actual = firstPatient.compareTo(secondPatient);

                if (actual >= 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests whether patients in the same triage level are compared based on their order of arrival.
     * Patients of the same triage level with a lower arrival number compared to patients with a
     * higher arrival number should return a negative integer. The reverse situation should return a
     * positive integer.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {

        PatientRecord.resetCounter();
        PatientRecord firstPatient = new PatientRecord('M', 45, TriageLevel.RED);
        PatientRecord secondPatient = new PatientRecord('F', 23, TriageLevel.RED);
        PatientRecord thirdPatient = new PatientRecord('X', 34, TriageLevel.RED);

        {
            // 1. When a patient arrived before the other patient
            try {
                int actual = firstPatient.compareTo(secondPatient);

                if (actual >= 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        {
            // 2. When a patient arrived after the other patient
            try {
                int actual = thirdPatient.compareTo(firstPatient);

                if (actual <= 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests whether patients in the same triage level and with the same order of arrival are equal
     * (compareTo should return 0). Even though this case will not be possible in your priority queue,
     * it is required for testing the full functionality of the compareTo() method. Hint: you will
     * need to use the resetCounter() to create equivalent PatientRecords.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testPatientRecordCompareToSameTriageSameArrival() {

        PatientRecord.resetCounter();
        PatientRecord firstPatient = new PatientRecord('M', 45, TriageLevel.YELLOW);
        PatientRecord.resetCounter();
        PatientRecord secondPatient = new PatientRecord('F', 23, TriageLevel.YELLOW);
        PatientRecord thirdPatient = new PatientRecord('X', 34, TriageLevel.YELLOW);
        PatientRecord.resetCounter();
        PatientRecord fourthPatient = new PatientRecord('M', 54, TriageLevel.GREEN);

        {
            // 1. When both the patient records have same triage level and order of arrival
            try {
                int actual = firstPatient.compareTo(secondPatient);

                if (actual != 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        {
            // 2. When both patient records have different order of arrival but same triage level
            try {
                int actual = firstPatient.compareTo(thirdPatient);

                if (actual >= 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        {
            // 3. When both patient records have different triage level but same order of arrival
            try {
                int actual = secondPatient.compareTo(fourthPatient);

                if (actual >= 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    /**
     * Tests the functionality of the constructor for src.PriorityCareAdmissions Should implement at least
     * the following tests:
     *
     * - Calling the src.PriorityCareAdmissions with an invalid capacity should throw an
     * IllegalArgumentException
     * - Calling the src.PriorityCareAdmissions with a valid capacity should not throw any errors, and
     * should result in a new src.PriorityCareAdmissions which is empty, has size 0, a capacity equal to
     * the capacity that was passed as a parameter.
     *
     * @return true if the constructor of src.PriorityCareAdmissions functions properly, false otherwise
     */
    public static boolean testConstructor() {
        PatientRecord.resetCounter();

        {
            // 1. Calling the constructor with invalid capacity
            try {
                PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(-7);
                return false;
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                return false;
            }
        }

        {
            // 2. Calling the constructor with valid capacity
            try {
                PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(4);
                if (!(priorityCare.isEmpty() && priorityCare.size() == 0
                        && priorityCare.capacity() == 4)) {
                    return false;
                }

            } catch (IllegalArgumentException e) {
                return false;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }


    /**
     * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
     * throws a NoSuchElementException.
     *
     * @return true if src.PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
     */
    public static boolean testPeekEmpty() {
        PatientRecord.resetCounter();

        try {
            PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(4);
            priorityCare.peek();
            return false;
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
     * 1) returns the src.PatientRecord having the highest priority (the minimum) and 2) does not remove
     * the src.PatientRecord from the queue.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testPeekNonEmpty() {
        PatientRecord.resetCounter();

        try {
            PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(2);

            PatientRecord firstPatient = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord secondPatient = new PatientRecord('X', 45, TriageLevel.YELLOW);

            priorityCare.addPatient(firstPatient);
            priorityCare.addPatient(secondPatient);

            String actual = priorityCare.peek().toString();
            String expected = firstPatient.toString();

            if (!actual.equals(expected)) {
                return false;
            }

        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
     * ensuring the method 1) adds the src.PatientRecord and 2) increments the size.
     *
     * @return true if src.PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientEmpty() {

        PatientRecord.resetCounter();
        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);
        PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);

        try {
            priorityCare.addPatient(firstPatient);
            int actual = priorityCare.size();
            int expected = 1;

            String actualString = priorityCare.toString();
            String expectedString = "35402: 54X (GREEN) - not seen\n";

            if (actual != expected || !(actualString.equals(expectedString))) {
                return false;
            }

        } catch (IllegalStateException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
     * ensuring the method 1) adds the src.PatientRecord at the proper position and 2) increments the
     * size. Try add at least 5 PatientRecords.
     *
     * @return true if src.PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
     */
    public static boolean testAddPatientNonEmpty() {

        PatientRecord.resetCounter();
        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);
        PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);
        PatientRecord secondPatient = new PatientRecord('M', 23, TriageLevel.GREEN);
        PatientRecord thirdPatient = new PatientRecord('F', 67, TriageLevel.YELLOW);

        priorityCare.addPatient(firstPatient);
        priorityCare.addPatient(secondPatient);

        try {
            priorityCare.addPatient(thirdPatient);

            int actual = priorityCare.size();
            int expected = 3;

            String actualString = priorityCare.toString();
            String expectedString = "16704: 67F (YELLOW) - not seen\n" +
                    "35402: 54X (GREEN) - not seen\n" +
                    "22303: 23M (GREEN) - not seen\n";

            if (actual != expected || !actualString.equals(expectedString)) {
                return false;
            }
        } catch (IllegalStateException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
     * ensuring the method throws an IllegalStateException.
     *
     * @return true if src.PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientFull() {
        PatientRecord.resetCounter();

        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);
        PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);
        PatientRecord secondPatient = new PatientRecord('M', 23, TriageLevel.GREEN);
        PatientRecord thirdPatient = new PatientRecord('F', 67, TriageLevel.YELLOW);
        PatientRecord fourthPatient = new PatientRecord('X', 22, TriageLevel.YELLOW);

        priorityCare.addPatient(firstPatient);
        priorityCare.addPatient(secondPatient);
        priorityCare.addPatient(thirdPatient);

        try {
            priorityCare.addPatient(fourthPatient);
            return false;
        } catch (IllegalStateException e) {

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of addPatient() method by calling addPatient() with a null
     * src.PatientRecord and ensuring the method throws a NullPointerException.
     *
     * @return true if src.PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientNull() {
        PatientRecord.resetCounter();

        PriorityCareAdmissions priorityCare = null;
        PatientRecord somePatient = new PatientRecord('X', 22, TriageLevel.YELLOW);

        try {
            priorityCare.addPatient(somePatient);
            return false;
        } catch (NullPointerException e) {

        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
     * queue.
     *
     * @return true if src.PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
     *         false otherwise
     */
    public static boolean testRemoveBestRecordEmpty() {
        PatientRecord.resetCounter();

        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);

        try {
            priorityCare.removeBestRecord();
            return false;
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
     * of size one.
     *
     * @return true if src.PriorityCareAdmissions.removeBestRecord() returns the correct src.PatientRecord and
     *         size is 0
     */
    public static boolean testRemoveBestRecordSizeOne() {
        PatientRecord.resetCounter();

        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);
        PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);;

        priorityCare.addPatient(firstPatient);

        try {
            String actualPatient = priorityCare.removeBestRecord().toString();
            String expectedPatient = "35402: 54X (GREEN) - not seen";

            int actualSize = priorityCare.size();
            int expectedSize = 0;

            if (expectedSize != actualSize || !actualPatient.equals(expectedPatient)) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of removeBestRecord() methods.
     *
     * The removeBestRecord() method must remove, and return the patient record with the highest
     * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
     * method is successfully called.
     *
     * Remove the best record from a queue whose size is at least 6. Consider cases where
     * percolate-down recurses on left and right.
     *
     * @return true if src.PriorityCareAdmissions.removeBestRecord() returns the correct src.PatientRecord
     *         each time it is called and size is appropriately decremented, false otherwise
     */
    public static boolean testRemoveBestRecordNonEmpty() {

        PatientRecord.resetCounter();

        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(7);
        PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);
        firstPatient.seePatient();
        PatientRecord secondPatient = new PatientRecord('M', 23, TriageLevel.GREEN);
        PatientRecord thirdPatient = new PatientRecord('F', 67, TriageLevel.YELLOW);
        PatientRecord fourthPatient = new PatientRecord('X', 34, TriageLevel.RED);
        PatientRecord fifthPatient = new PatientRecord('M', 65, TriageLevel.GREEN);
        PatientRecord sixthPatient = new PatientRecord('F', 86, TriageLevel.RED);

        priorityCare.addPatient(firstPatient);
        priorityCare.addPatient(thirdPatient);
        priorityCare.addPatient(secondPatient);
        priorityCare.addPatient(fourthPatient);
        priorityCare.addPatient(sixthPatient);
        priorityCare.addPatient(fifthPatient);

        try {
            priorityCare.removeBestRecord();
            priorityCare.removeBestRecord();
            priorityCare.removeBestRecord();
            String actualPatient = priorityCare.removeBestRecord().toString();
            String expectedPatient = "35402: 54X (GREEN) - seen";

            String actualString = priorityCare.toString();
            String expectedString = "22303: 23M (GREEN) - not seen\n" +
                    "26506: 65M (GREEN) - not seen\n";

            int actualSize = priorityCare.size();
            int expectedSize = 2;

            if (actualSize != expectedSize || !actualPatient.equals(expectedPatient)
                    ||!actualString.equals(expectedString)) {
                return false;
            }

            ;    } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of the clear() method.
     * Should implement at least the following scenarios:
     * - clear can be called on an empty queue with no errors
     * - clear can be called on a non-empty queue with no errors
     * - After calling clear(), the queue should contain zero PatientRecords.
     * - After calling clear(), the size should be 0
     *
     * @return true if src.PriorityCareAdmissions.clear() functions properly
     */
    public static boolean testClear() {

        PatientRecord.resetCounter();

        PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(7);
        PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);
        PatientRecord secondPatient = new PatientRecord('M', 23, TriageLevel.GREEN);
        PatientRecord thirdPatient = new PatientRecord('F', 67, TriageLevel.YELLOW);
        PatientRecord fourthPatient = new PatientRecord('X', 34, TriageLevel.RED);
        PatientRecord fifthPatient = new PatientRecord('M', 65, TriageLevel.GREEN);
        PatientRecord sixthPatient = new PatientRecord('F', 86, TriageLevel.RED);

        priorityCare.addPatient(firstPatient);
        priorityCare.addPatient(thirdPatient);
        priorityCare.addPatient(secondPatient);
        priorityCare.addPatient(fourthPatient);
        priorityCare.addPatient(sixthPatient);
        priorityCare.addPatient(fifthPatient);

        try {
            priorityCare.clear();

            int actualSize = priorityCare.size();
            int expectedSize = 0;

            if (actualSize != expectedSize) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Tests toString() method of src.PriorityCareAdmissions class.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testToString() {

        {
            // 1. Testing on non-empty priority queue
            PatientRecord.resetCounter();

            PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);
            PatientRecord firstPatient = new PatientRecord('X', 54, TriageLevel.GREEN);
            PatientRecord secondPatient = new PatientRecord('M', 23, TriageLevel.GREEN);
            PatientRecord thirdPatient = new PatientRecord('F', 67, TriageLevel.YELLOW);

            priorityCare.addPatient(firstPatient);
            priorityCare.addPatient(thirdPatient);
            priorityCare.addPatient(secondPatient);

            try {
                String actualString = priorityCare.toString();
                String expectedString = "16704: 67F (YELLOW) - not seen\n" +
                        "35402: 54X (GREEN) - not seen\n" +
                        "22303: 23M (GREEN) - not seen\n";

                if (!actualString.equals(expectedString)) {
                    return false;
                }

            } catch (Exception e) {
                return false;
            }
        }

        { // 2. Testing on empty priority queue
            PatientRecord.resetCounter();

            PriorityCareAdmissions priorityCare = new PriorityCareAdmissions(3);

            try {
                String actualString = priorityCare.toString();
                String expectedString = "";

                if (!actualString.equals(expectedString)) {
                    return false;
                }

            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }


    /**
     * Runs all the tester methods defined in this class.
     *
     * @return true if no bugs are detected.
     */
    public static boolean runAllTests() {

        return testPatientRecordCompareToDifferentTriage()
                && testPatientRecordCompareToSameTriageDifferentArrival()
                && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
                && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
                && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
                && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
                && testToString();
    }

    /**
     * Main method to run this tester class.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToDifferentTriage: "
                + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
                + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
                + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
        System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
        System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
        System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
        System.out
                .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
        System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
        System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
    }

}
