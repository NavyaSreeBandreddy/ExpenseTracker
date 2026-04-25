import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {

    private ArrayList<Expense> list = new ArrayList<>();

    //  Add Expense
    public void addExpense(Expense e) {
        list.add(e);
    }

    //  Remove Expense
    public void removeExpense(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            System.out.println("Invalid index!");
        }
    }

    //  Total Expense
    public double getTotal() {
        double total = 0;
        for (Expense e : list) {
            total += e.amount;
        }
        return total;
    }

    //  Category-wise Total (IMPORTANT for charts)
    public Map<String, Double> getCategoryTotals() {
        Map<String, Double> categoryMap = new HashMap<>();

        for (Expense e : list) {
            String category = e.category;

            // if category already exists → add amount
            if (categoryMap.containsKey(category)) {
                categoryMap.put(category,
                        categoryMap.get(category) + e.amount);
            } else {
                categoryMap.put(category, e.amount);
            }
        }

        return categoryMap;
    }

    // Get All Expenses
    public ArrayList<Expense> getAllExpenses() {
        return list;
    }

    //  Clear All Data (optional feature)
    public void clearAll() {
        list.clear();
    }
}