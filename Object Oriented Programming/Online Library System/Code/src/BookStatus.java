package Knowles6701620;

public enum BookStatus {
    //Holds the book status. Either active or removed

    Active("Active", 1), Removed("Removed", 2);

    private String name;
    private int code;

    BookStatus (String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("%s, %d", name, code);
    }
}
