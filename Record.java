/**
 * The Record class represents a single record entry with various attributes.
 * Each record has an ID, title, time, length, coordinates (x, y), cost, a list
 * of keywords, and a description.
 */
public class Record {

    // Instance variables for the various attributes of the record
    private int id;
    private String title, time, length, x, y, cost, list, des;

    /**
     * Constructor to initialize a new Record object with given parameters.
     *
     * @param id
     *            Unique identifier for the record.
     * @param title
     *            Title of the record.
     * @param time
     *            Time associated with the record.
     * @param length
     *            Duration or length of the record.
     * @param x
     *            X-coordinate or other relevant data.
     * @param y
     *            Y-coordinate or other relevant data.
     * @param cost
     *            Cost associated with the record.
     * @param list
     *            A list of keywords or other data.
     * @param des
     *            Description of the record.
     */
    public Record(
        int id,
        String title,
        String time,
        String length,
        String x,
        String y,
        String cost,
        String list,
        String des) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.length = length;
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.list = list;
        this.des = des;
    }


    /**
     * Gets the handle (unique identifier) for this record.
     *
     * @return Handle object containing the ID of the record.
     */
    public Handle getHandle() {
        return new Handle(id);
    }


    /**
     * Provides a string representation of the record.
     * 
     * @return A string detailing all attributes of the record.
     */
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Time: " + time
            + ", Length: " + length + ", X: " + x + ", Y: " + y + ", Cost: "
            + cost + ", List: " + list + ", Description: " + des;
    }
}
