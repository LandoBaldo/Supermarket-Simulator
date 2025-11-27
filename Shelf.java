/**
 * Represents a shelf storage unit with 2 tiers and 4 capacity per tier.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see StorageUnit
 */
package Base;
public class Shelf extends StorageUnit {
    
    /**
     * Constructs a new shelf storage unit.
     * Initializes with 2 tiers and 4 capacity per tier.
     */
    public Shelf() {
        super(2, 4);
    }
}