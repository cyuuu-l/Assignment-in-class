package Demo;

public class TableTree {
    private Table root;
    private int numberTables;//Number of tables currently.
    private int numberDiners;//Number of diners in this inn currently.
    private int numberElves;//Number of elves dining in this currently.
    private int numberDwarves;//Number of dwarves dining in this currently.
    private int numberHumans;//Number of humans dining in this currently.
    private int numberHobbits;//Number of hobbits dining in this currently.
    private Table leastCrowdedTable;//Record the least crowded table.

    //Constructor, create and initialise a new tree.
    public TableTree(Table root) {
        this.root = root;
        this.numberTables = 1;
        this.numberDiners = root.getNumber();
        this.numberElves = root.getElf();
        this.numberDwarves = root.getDwarf();
        this.numberHumans = root.getHuman();
        this.numberHobbits = root.getHobbit();
        this.leastCrowdedTable = root;
    }

    //Getter and Setter.
    public Table getRoot() {
        return root;
    }

    public void setRoot(Table root) {
        this.root = root;
    }

    public int getNumberTables() {
        return numberTables;
    }

    public int getNumberDiners() {
        return numberDiners;
    }

    public int getNumberElves() {
        return numberElves;
    }

    public int getNumberDwarves() {
        return numberDwarves;
    }

    public int getNumberHumans() {
        return numberHumans;
    }

    public int getNumberHobbits() {
        return numberHobbits;
    }

    public Table getLeastCrowdedTable() {
        return leastCrowdedTable;
    }

/*I plan to store the Table node in this tree:
     Table 1 in depth 1;
     Table 2, 3 in depth 2;
     Table 4, 5, 6, 7 in depth 3;
     Table 8, 9, 10, 11, 12, 13, 14, 15 in depth 4......*/

    /*Use relevant binary bit algorithm to find the position of the node in the tree by the tableNO.
    For example: Input: 4 ---> Output: root.left.left
    Input: 5 ---> Output: root.left.right
    Input: 10 ---> Output: root.left.right.left
     */
    public Table findNode(int tableNO) {
        int l = 0;
        Table table = root;
        for (; Math.pow(2, l) - 1 < tableNO; l++) {
        }//Find out the depth of the node, which is stored in variable l.
        for (int i = l - 2; i >= 0; i--) {
            int bit = (tableNO >> i) & 1;
            if (bit == 0) {
                table = table.getLeft();
            } else {
                table = table.getRight();
            }
        }//if the following bit is "0", then visit its left child; if "1",then right child.
        return table;
    }

    /*Find parent of the table with given tableNO.
    For example: Input: 4 ---> Output: root.left
    Input: 5 ---> Output: root.left
    Input: 10 ---> Output: root.left.right*/
    public Table findParent(int tableNO) {
        int l = 0;
        Table table = root;
        for (; Math.pow(2, l) - 1 < tableNO; l++) {
        }
        for (int i = l - 2; i >= 1; i--) {
            int bit = (tableNO >> i) & 1;
            if (bit == 0) {
                table = table.getLeft();
            } else {
                table = table.getRight();
            }
        }//The difference with findNode() is in this algorithm, I set i>=1 rather than i>=0 in the for loop.
        return table;
    }

    //Implement start_new_table().
    //Add a new empty table in this tree.
    public void start_new_table() {
        Table table = root;
        int j = 1;
        while (table != null) {
            j++;
            table = findNode(j);
        }
        Table parent = findParent(j);
        Table newTable = new Table(j);
        if (j % 2 == 0) {
            parent.setLeft(newTable);
        } else {
            parent.setRight(newTable);
        }
        this.numberTables++;
        findLeastCrowdedTable();
    }

    static int i = 1;
    //I think define a static variable can decrease time when do "add-" operations.
    //For example, when make a "add_elf" operation, and in line "return add_elf();", it don't need to run the whole for loop from i=1 as what it did at the first time.

    //Implement add_elf().
    //Return the tableNO of which the new elf chooses to dine in.
    public int add_elf() {
        for (; i <= numberTables; i++) {
            Table table = findNode(i);
            if (table != null && table.add_elf() == table) {
                this.numberElves++;
                this.numberDiners++;
                i = 1;
                findLeastCrowdedTable();
                return table.getTableNO();
            }
        }
        start_new_table();
        return add_elf();
    }

    //Implement add_human().
    //Return the tableNO of which the new human chooses to dine in.
    public int add_human() {
        for (; i <= numberTables; i++) {
            Table table = findNode(i);
            if (table != null && table.add_human() == table) {
                this.numberHumans++;
                this.numberDiners++;
                i = 1;
                findLeastCrowdedTable();
                return table.getTableNO();
            }
        }
        start_new_table();
        return add_human();
    }

    //Find the closest table(with empty seats) with fewest elves
    //Return the closest table(with empty seats) with fewest elves.
    //Return null means all the tables are full.
    public Table findFewestElvesTable() {
        Table table = root;
        for (int j = numberTables; j >= 1; j--) {
            if (findNode(j).getElf() <= table.getElf()) {
                table = findNode(j);
            }
        }
        if (table.getNumber() == 7) {
            return null;
        } else return table;
    }

    //Implement add_dwarf().
    //Return the tableNO of which the new dwarf chooses to dine in.
    public int add_dwarf() {
        Table table = findFewestElvesTable();
        if (table != null && table.add_dwarf() == table) {
            this.numberDwarves++;
            this.numberDiners++;
            i = 1;
            findLeastCrowdedTable();
            return table.getTableNO();
        }
        start_new_table();
        return add_dwarf();
    }

    //Find the least crowded table.
    //Return the least crowded table.
    //Return null means all the tables are full.
    public Table findLeastCrowdedTable() {
        for (int j = this.numberTables; j >= 1; j--) {
            if (findNode(j).getNumber() <= this.leastCrowdedTable.getNumber()) {
                this.leastCrowdedTable = findNode(j);
            }
        }
        if (this.leastCrowdedTable.getNumber() == 7) {
            return null;
        } else return this.leastCrowdedTable;
    }

    //Implement add_hobbit().
    //Return the tableNO of which the new hobbit chooses to dine in.
    public int add_hobbit() {
        Table table = findLeastCrowdedTable();
        if (table != null && table.add_hobbit() == table) {
            numberHobbits++;
            numberDiners++;
            i = 1;
            findLeastCrowdedTable();
            return table.getTableNO();
        }
        start_new_table();
        return add_hobbit();
    }

    //Implement get_least_crowded_table().
    //Return the least crowded table, in case of ties, return the closest from the door.
    //Time complexity is O(1).
    public Table get_least_crowded_table() {
        return this.leastCrowdedTable;
    }

    //Implement get_number_tables().
    //Return current number of tables.
    //Time complexity is O(1).
    public int get_number_tables() {
        return this.numberTables;
    }

    //Implement get_number_diners().
    //Return current number of diners.
    //Time complexity is O(1).
    public int get_number_diners() {
        return this.numberDiners;
    }

    //Implement get_number_elves().
    //Return current number of elves.
    //Time complexity is O(1).
    public int get_number_elves() {
        return this.numberElves;
    }

    //Implement get_number_humans().
    //Return current number of humans.
    //Time complexity is O(1).
    public int get_number_humans() {
        return this.numberHumans;
    }

    //Implement get_number_dwarves().
    //Return current number of dwarves.
    //Time complexity is O(1).
    public int get_number_dwarves() {
        return this.numberDwarves;
    }

    //Implement  get_number_hobbits().
    //Return current number of hobbits.
    //Time complexity is O(1).
    public int get_number_hobbits() {
        return this.numberHobbits;
    }
}

