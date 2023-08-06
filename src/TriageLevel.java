package src;

/**
 * This enumeration defines the set of the predefined constants to determine the triage level of a
 * patient in an urgent priority care. The level RED is less than, meaning it has a higher priority,
 * than YELLOW, and YELLOW is less than GREEN.<BR/>
 * <BR/>
 * src.TriageLevel constants are comparable. <BR/>
 * References of type src.TriageLevel can be compared using the Comparable.compareTo() method. For
 * instance:<BR/>
 * If we consider the following references. <BR/>
 * src.TriageLevel red = src.TriageLevel.RED; <BR/>
 * src.TriageLevel yellow = src.TriageLevel.YELLOW; <BR/>
 * src.TriageLevel green = src.TriageLevel.GREEN; <BR/>
 * The following statements are TRUE: <BR/>
 * red.compareTo(yellow) &lt; 0 <BR/>
 * red.compareTo(green) &lt; 0 <BR/>
 * green.compareTo(yellow) &gt; 0
 *
 */
public enum TriageLevel {
    /**
     * RED triage level
     */
    RED,
    /**
     * YELLOW triage level
     */
    YELLOW,
    /**
     * GREEN triage level
     */
    GREEN;
}

