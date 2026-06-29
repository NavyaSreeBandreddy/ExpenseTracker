import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {

    // List to store all expenses
    private ArrayList<Expense> list = new ArrayList<>();

    // Add Expense
    public void addExpense(Expense e) {
        list.add(e);
    }

    // Remove Expense
    public void removeExpense(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            System.out.println("Invalid index!");
        }
    }

    // Calculate Total Expense
    public double getTotal() {
        double total = 0;

        for (Expense e : list) {
            total += e.getAmount();   // Using Getter
        }

        return total;
    }

    // Category-wise Total (Used for Chart)
    public Map<String, Double> getCategoryTotals() {

        Map<String, Double> categoryMap = new HashMap<>();

        for (Expense e : list) {

            String category = e.getCategory();   // Using Getter
            double amount = e.getAmount();       // Using Getter

            if (categoryMap.containsKey(category)) {
                categoryMap.put(category,
                        categoryMap.get(category) + amount);
            } else {
                categoryMap.put(category, amount);
            }
        }

        return categoryMap;
    }

    // Get All Expenses
    public ArrayList<Expense> getAllExpenses() {
        return list;
    }

    // Clear All Expenses
    public void clearAll() {
        list.clear();
    }
}