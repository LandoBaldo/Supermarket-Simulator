/**
 * Represents a table storage unit with 1 tier and 4 capacity per tier.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see StorageUnit
 */
package Base;
public class Table extends StorageUnit {
    
    /**
     * Constructs a new table storage unit.
     * Initializes with 1 tier and 4 capacity per tier.
     */
    public Table() {
        super(1, 4);
    }
}