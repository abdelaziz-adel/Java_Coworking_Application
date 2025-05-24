package JasonOOP.DataBase;

public class users {
    private String name;
    private String password;
    private int id;
    private String type;
    private double penalty;
    private int freeHours = 0;
    private static int maxId = 0;

    public users(String name, String password, int id, String type, double penalty, int freeHours) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.type = type;
        this.penalty = penalty;
        this.freeHours = freeHours;
        maxId = Math.max(maxId, id);
    }

    public String getType() {
        return type;
    }

    public double getPenalty() {
        return penalty;
    }

    public String getPassword() {
        return password;
    }

    public users(String name, String password, String type, double penalty) {
        this.name = name;
        this.password = password;
        this.id = ++maxId;
        this.penalty = penalty;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + "," + password + "," + id + "," + type + "," + penalty + "," + freeHours;
    }

    public static int getMaxId() {
        return maxId;
    }

    public void addPenalty() {
        penalty += 0.5;
    }

    public int getId() {
        return id;
    }

    public static void setMaxId(int id) {
        maxId = id;
    }

    public String getName() {
        return name;
    }

    public int getFreeHours() {
        return freeHours;
    }

    public void addFreeHours() {
        this.freeHours += 1;
    }

    public void removeFreeHours() {
        if (this.freeHours > 0)
            this.freeHours -= 1;
    }

    public void setFreeHours(int freeHours) {
        this.freeHours = freeHours;
    }
}

