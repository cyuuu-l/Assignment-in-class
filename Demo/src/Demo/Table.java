package Demo;

public class Table {
    private int tableNO;//Number of this table.
    private int dwarf;//Number of dwarves at this table currently.
    private int hobbit;//Number of hobbits at this table currently.
    private int elf;//Number of elves at this table currently.
    private int human;//Number of humans at this table currently.
    private int number;//Number of customers at this table currently.
    private Table left;//Point at left child of this table.
    private Table right;//Point at right child of this table.

    //Constructor, create and initialise a new table.
    public Table(int tableNO) {
        this.tableNO = tableNO;
        this.dwarf = 0;
        this.hobbit = 0;
        this.elf = 0;
        this.human = 0;
        this.number = 0;
        this.left = null;
        this.right = null;
    }

    //Getter and Setter.
    public int getTableNO() {
        return tableNO;
    }

    public int getDwarf() {
        return dwarf;
    }

    public int getHobbit() {
        return hobbit;
    }

    public int getElf() {
        return elf;
    }

    public int getHuman() {
        return human;
    }

    public int getNumber() {
        return number;
    }

    public Table getLeft() {
        return left;
    }

    public void setLeft(Table left) {
        this.left = left;
    }

    public Table getRight() {
        return right;
    }

    public void setRight(Table right) {
        this.right = right;
    }

    //Implement is_table_full().
    //Return false stand for the table is not full, while true means it is full.
    public boolean is_table_full() {
        if (this.number < 7) {
            return false;
        }
        return true;
    }

    //Implement is_elves_only()
    //Return true means the customers at this table currently are all elves, false means they are not all elves.
    //If the table is empty, then return false.
    public boolean is_elves_only() {
        if (this.number > 0 && this.elf == this.number) {
            return true;
        }
        return false;
    }

    //Implement is_dhe_only().
    //Return true means the customers at this table currently are all dwarves or all hobbits or all elves, false means they are not.
    //If the table is empty, then return false.
    public boolean is_dhe_only() {
        if (this.number > 0 && (this.dwarf == this.number || this.hobbit == this.number || this.elf == this.number)) {
            return true;
        }
        return false;
    }

    //Implement get_total_diners().
    //Return the number of customers currently at this table.
    public int get_total_diners() {
        return this.number;
    }

    //Implement get_elves().
    //Return the number of elves currently at this table.
    public int get_elves() {
        return this.elf;
    }

    //Implement add_elf().
    //Return this means this table can accept one more elf currently, and this operation is already done.
    //Return null means the table is full or it cannot accept one more elf even it's not full(according to elf-rule).
    //Below isn't all logic when add a elf, remain part is in Class TableTree.
    public Table add_elf() {
        if (this.number < 7) {
            if (is_elves_only() || this.number == 0) {
                this.elf++;
                this.number++;
                return this;
            }
        }
        return null;
    }

    //Implement add_human().
    //Return this means this table can accept one more human currently, and this operation is already done.
    //Return null means he table is full or it cannot accept one more human even it's not full(according to human-rule).
    //Below isn't all logic when add a human, remain part is in Class TableTree.
    public Table add_human() {
        if (this.number < 7) {
            if (!is_dhe_only() || this.number == 0) {
                this.human++;
                this.number++;
                return this;
            }
        }
        return null;
    }

    //Implement add_dwarf().
    //Return this means this table can accept one more dwarf currently, and this operation is already done.
    //Return null means he table is full or it cannot accept one more dwarf even it's not full(according to dwarf-rule).
    //Below isn't all logic when add a dwarf, remain part is in Class TableTree.
    public Table add_dwarf() {
        if (this.number < 7) {
            this.dwarf++;
            this.number++;
            return this;
        }
        return null;
    }

    //Implement add_hobbit().
    //Return this means this table can accept one more hobbit currently, and this operation is already done.
    //Return null means he table is full or it cannot accept one more hobbit even it's not full(according to hobbit-rule).
    //Below isn't all logic when add a hobbit, remain part is in Class TableTree.
    public Table add_hobbit() {
        if (this.number < 7) {
            this.hobbit++;
            this.number++;
            return this;
        }
        return null;
    }

    //Implement get_distance().
    //tableNO equals to distance of the table, so I just let the algorithm return tableNO.
    public int get_distance() {
        return this.tableNO;
    }
}
