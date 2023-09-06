
public class Record 
{
    private int id;
    private String title, time, length, x, y, cost, list, des;

    public Record(int id, String title, String time, String length, String x, String y, String cost, String list, String des) {
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

    public Handle getHandle() 
    {
        return new Handle(id);
    }

    @Override
    public String toString() 
    {
        return "ID: " + id + ", Title: " + title + ", Time: " + time + ", Length: " + length + ", X: " + x + ", Y: " + y + ", Cost: " + cost + ", List: " + list + ", Description: " + des;
    }
}
