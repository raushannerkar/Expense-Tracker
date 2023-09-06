package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int totalExpense = 0;
    private int foodExpense = 0;
    private int transportExpense = 0;
    private int shoppingExpense = 0;
    private int taxExpense=0;

    private TextView txtTotalExpense;
    private TextView txtFoodExpense;
    private TextView txtTransportExpense;
    private TextView txtShoppingExpense;
    private TextView txtTaxExpense;

    private final int EXPENSE_ENTRY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the TextView elements
        txtTotalExpense = findViewById(R.id.txtTotalExpense);
        txtFoodExpense = findViewById(R.id.txtFoodExpense);
        txtTransportExpense = findViewById(R.id.txtTransportExpense);
        txtShoppingExpense = findViewById(R.id.txtShoppingExpense);
        txtTaxExpense=findViewById(R.id.txtTaxExpense);

        // Set initial total expense amount and individual expense amounts
        updateExpenseView();

        // Food Button click listener
        Button btnFood = findViewById(R.id.btnFood);
        btnFood.setOnClickListener(v -> openExpenseEntryPage("Food"));

        // Transport Button click listener
        Button btnTransport = findViewById(R.id.btnTransport);
        btnTransport.setOnClickListener(v -> openExpenseEntryPage("Transport"));

        // Shopping Button click listener
        Button btnShopping = findViewById(R.id.btnShopping);
        btnShopping.setOnClickListener(v -> openExpenseEntryPage("Shopping"));

        Button btnTax = findViewById(R.id.btnTax);
        btnTax.setOnClickListener(v -> openExpenseEntryPage("Tax"));


        // Reset Button click listener
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> resetExpenses());
    }

    // Method to open the Expense Entry page
    private void openExpenseEntryPage(String category) {
        Intent intent = new Intent(MainActivity.this, ExpenseEntryActivity.class);
        intent.putExtra("category", category);

        startActivityForResult(intent, EXPENSE_ENTRY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXPENSE_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Retrieve the entered expense amount and category from the Expense Entry page
            int expenseAmount = data.getIntExtra("expenseAmount", 0);
            String category = data.getStringExtra("category");

            // Debug log statements
            Log.d("ExpenseTracker", "Expense amount: " + expenseAmount);
            Log.d("ExpenseTracker", "Expense category: " + category);

            // Update the total expense amount
            totalExpense += expenseAmount;

            // Update the individual expense amounts
            updateExpenseAmounts(category, expenseAmount);

            // Update the Total Expense and individual expense TextViews
            updateExpenseView();
        }
    }

    // Method to update the expense amounts
    private void updateExpenseAmounts(String category, int expenseAmount) {
        if (category != null) {
            switch (category) {
                case "Food":
                    foodExpense += expenseAmount;
                    break;
                case "Transport":
                    transportExpense += expenseAmount;
                    break;
                case "Shopping":
                    shoppingExpense += expenseAmount;
                    break;
                case "Tax":
                    taxExpense+=expenseAmount;
                    break;
            }
        }
    }

    // Method to update the expense view
    private void updateExpenseView() {
        txtTotalExpense.setText(String.valueOf(totalExpense));
        txtFoodExpense.setText(String.valueOf(foodExpense));
        txtTransportExpense.setText(String.valueOf(transportExpense));
        txtShoppingExpense.setText(String.valueOf(shoppingExpense));
        txtTaxExpense.setText(String.valueOf(taxExpense));
    }

    // Method to reset the expense values to zero
    private void resetExpenses() {
        totalExpense = 0;
        foodExpense = 0;
        transportExpense = 0;
        shoppingExpense = 0;
        taxExpense=0;
        updateExpenseView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the expense view when returning to the MainActivity
        updateExpenseView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the expense amounts in the bundle
        outState.putInt("totalExpense", totalExpense);
        outState.putInt("foodExpense", foodExpense);
        outState.putInt("transportExpense", transportExpense);
        outState.putInt("shoppingExpense", shoppingExpense);
        outState.putInt("taxExpense",taxExpense);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the expense amounts from the bundle
        totalExpense = savedInstanceState.getInt("totalExpense");
        foodExpense = savedInstanceState.getInt("foodExpense");
        transportExpense = savedInstanceState.getInt("transportExpense");
        shoppingExpense = savedInstanceState.getInt("shoppingExpense");
        taxExpense=savedInstanceState.getInt("taxExpense");
    }
}
