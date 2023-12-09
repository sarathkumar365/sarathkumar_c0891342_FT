import java.util.ArrayList;
import java.util.List;

class Box {
    private int capacity;
    private int box_number;
    private List<Object> items;

    public Box(int capacity, int box_number) {
        this.capacity = capacity;
        this.box_number = box_number;
        this.items = new ArrayList<>();
    }

    public void addItem(Object item) {
        if (items.size() < capacity) {
            items.add(item);
        } else {
            System.out.println("OOPS!! Box is full. Cannot add more items.");
        }
    }

    public List<Object> getItems() {
        return items;
    }

    public int getBox_number() {
        return box_number;
    }
}