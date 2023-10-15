package Demo;

public class Test {
    public static void test(Table table) {
        System.out.println("getTableNO: " + table.getTableNO());
        System.out.println("getNumber: " + table.getNumber());
        System.out.println("getElf: " + table.getElf());
        System.out.println("getHuman: " + table.getHuman());
        System.out.println("getDwarf: " + table.getDwarf());
        System.out.println("getHobbit: " + table.getHobbit());
        System.out.println();
    }

    public static void main(String[] args) {
        Table root = new Table(1);
        TableTree tree = new TableTree(root);
        tree.start_new_table();
        tree.start_new_table();
        for (int i = 0; i < 12; i++) {
            tree.add_elf();

        }
        for (int i = 0; i < 5; i++) {
            tree.add_dwarf();
        }
        test(root);
        test(root.getLeft());
        test(root.getRight());
        System.out.println(tree.get_least_crowded_table().getTableNO());

        tree.add_hobbit();
        test(root);
        test(root.getLeft());
        test(root.getRight());
        System.out.println(tree.get_least_crowded_table().getTableNO());

        tree.add_elf();
        test(root);
        test(root.getLeft());
        test(root.getRight());
        test(root.getLeft().getLeft());
        System.out.println(tree.get_least_crowded_table().getTableNO());
    }
}

