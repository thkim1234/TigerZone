class TileTable {

    private int counter;
    private TableEntry table = new TableEntry();
    Hashtable<Integer,TableEntry> tileTable = new Hashtable<Integer,TableEntry>();

    public TileTable () {
        counter = 0;
        table = null;
    }

    public void setSides (char top, char bottom, char left, char right) {
        table.setTop(top);
        table.setBottom(bottom);
        table.setLeft(left);
        table.setRight(right);
    }

    public void setOffset(int LeftRightOffset, int UpDownOffset) {
        table.setLeftRightOffset(LeftRightOffset);
        table.setUpDownOffset(UpDownOffset);
    }

    public void setTileId(int tileID) {
        table.setTileId;
    }

    public void setTableEntry(table) {
        tileTable.put(counter,table);
        counter++;
        table.reset();
    }
    public TableEntry getTableEntry(int counter) {
        TableEntry returnedTableEntry = tileTable.get(counter);
        return returnedTableEntry;
    }

    public int tableSize () {
        return tileTable.size();
    }

    private class TableEntry {

        char top;
        char bottom;
        char left;
        char right;
        int tileID;
        int LeftRightOffset;
        int UpDownOffset;

        public TableEntry () {

            reset();

        }

        // Getter functions
        public char getBottom() {
            return bottom;
        }

        public char getLeft() {
            return left;
        }

        public char getRight() {
            return right;
        }

        public char getTop() {
            return top;
        }

        public int getLeftRightOffset() {
            return LeftRightOffset;
        }

        public int getTileID() {
            return tileID;
        }

        public int getUpDownOffset() {
            return UpDownOffset;
        }

        // Setter Functions

        public void setBottom(char bottom) {
            this.bottom = bottom;
        }

        public void setLeft(char left) {
            this.left = left;
        }

        public void setRight(char right) {
            this.right = right;
        }

        public void setTop(char top) {
            this.top = top;
        }

        public void setLeftRightOffset(int leftRightOffset) {
            LeftRightOffset = leftRightOffset;
        }

        public void setTileID(int tileID) {
            this.tileID = tileID;
        }

        public void setUpDownOffset(int upDownOffset) {
            UpDownOffset = upDownOffset;
        }

        public void reset() {
            top = '';
            bottom = '';
            left = '';
            right = '';
            tileID = 0;
            LeftRightOffset = 0;
            UpDownOffset = 0;
        }

    }
}