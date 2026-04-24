public class NPC {
    private String name;
    private char symbol;
    private int x, y;
    private int mapID;
    private String dialogue;

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setMapID(int id) {
        this.mapID = id;
    }

    public NPC(String name, char symbol, int mapID, int x, int y, String dialogue) {
        this.name = name;
        this.symbol = symbol;
        this.mapID = mapID;
        this.x = x;
        this.y = y;
        this.dialogue = dialogue;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public String getName() { return name; }
    public char getSymbol() { return symbol; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getMapID() { return mapID; }
    public String getDialogue() { return dialogue; }

    public void talk() {
        System.out.println(name + ": " + dialogue);
    }
}
