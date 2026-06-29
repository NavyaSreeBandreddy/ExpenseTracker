public class Expense {

    private String title;
    private double amount;
    private String category;
    private String date;

    // Constructor
    public Expense(String title, double amount, String category, String date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    // Optional: Setters (if you want edit feature later)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Display (useful for debugging)
    @Override
    public String toString() {
        return title + " | ₹" + amount + " | " + category + " | " + date;
    }
}
