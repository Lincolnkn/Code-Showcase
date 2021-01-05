public enum TicketType {
    Economy("Economy", 1), Business("Business", 2), First("First", 3);

    private String name;
    private int code;

    TicketType(String name, int i) {
        this.name = name;
        this.code = i;
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

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.code);
    }

    public static TicketType getTicketType(String ticketType) {
        for (TicketType ticket : TicketType.values()) {
            if (ticket.getName().equals(ticketType)) {
                return ticket;
            }
        }
        return null;
    }
}

