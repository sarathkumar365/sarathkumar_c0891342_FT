import java.util.ArrayList;
import java.util.List;

class Move {
    /* *************************************** */
    private List<Box> boxes;

    public Move(int capacity) {
        this.boxes = new ArrayList<>(capacity);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

//    public void print() {
//        for (Box box : boxes) {
//            System.out.println("Box " + box.getBox_number() + " contains:");
//            printContents(box.getItems(), 1);
//            System.out.println();
//        }
//    }

    public void print() {
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            printContents(box.getItems());
        }
    }

//    public int find(String itemName) {
//        for (int i = 0; i < boxes.size(); i++) {
//            if (findItemInBox(boxes.get(i), itemName)) {
//                return i + 1; // Box numbering starts from 1
//            }
//        }
//        return -1; // Item not found
//    }

    public int find(String itemName) {
        for (int i = 0; i < boxes.size(); i++) {
            if (findItemInBox(boxes.get(i), itemName)) {
                return boxes.get(i).getBox_number(); // Return the outermost box number
            }
        }
        return -1; // Item not found
    }


    private boolean findItemInBox(Box box, String itemName) {
        for (Object item : box.getItems()) {
            if (item instanceof SingleObject singleObject) {
                if (singleObject.getName().equals(itemName)) {
                    System.out.println(singleObject.getName());
                    return true;
                }
            } else if (item instanceof Box) {
                if (findItemInBox((Box) item, itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

private void printContents(List<Object> items) {
    for (Object item : items) {
        if (item instanceof SingleObject singleObject) {
            System.out.println(singleObject.getName());
        } else if (item instanceof Box) {
            printContents(((Box) item).getItems());
        }
    }
}
    /* *************************************** */

    public static void main(String[] args) {
        // We create a move that will hold 2 main boxes
        Move move = new Move(2);

        /*
         * We create and then fill 3 boxes
         * Arguments of the constructor of Box:
         * argument 1: number of items (simple items/objects or box) that the box can hold
         * argument 2: box number
         */

        // box 1 contains scissors
        Box box1 = new Box(1, 1);
        box1.addItem(new SingleObject("scissors"));

        // box 2 contains one book
        Box box2 = new Box(1, 2);
        box2.addItem(new SingleObject("book"));

        // box 3 contains one compass
        // and one box containing one scarf
        Box box3 = new Box(2, 3);
        box3.addItem(new SingleObject("compass"));
        Box box4 = new Box(1, 4);
        box4.addItem(new SingleObject("scarf"));
        box3.addItem(box4);

        // We add the three boxes to the first box of move - see below
        Box box5 = new Box(3, 5);
        box5.addItem(box1);
        box5.addItem(box2);
        box5.addItem(box3);

        // We add one box containing 3 objects to move
        Box box6 = new Box(3, 6);
        box6.addItem(new SingleObject("pencils"));
        box6.addItem(new SingleObject("pens"));
        box6.addItem(new SingleObject("rubber"));

        // We add the two most external boxes to the move
        move.addBox(box5);
        move.addBox(box6);

        // We print all the contents of the move
        move.print();

        // We print the number of the outermost cardboard containing the item "scarf"
        System.out.println("The sarf is in the cardboard number " + move.find("scarf"));
    }
}