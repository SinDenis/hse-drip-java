import cow.MyCopyOnWriteArrayList;

public class Main {

    public static void main(String[] args) {
        MyCopyOnWriteArrayList<String> list = new MyCopyOnWriteArrayList<>();
        list.add("alice");
        list.add("bob");
        list.add("carol");

        System.out.println("size = " + list.size());
        for (String name : list) {
            System.out.println(" - " + name);
        }

        list.set(1, "BOB");
        list.remove("alice");
        System.out.println("after edits:");
        for (String name : list) {
            System.out.println(" - " + name);
        }
    }
}
