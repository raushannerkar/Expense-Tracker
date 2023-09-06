// ExpenseEntryActivity.java
package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExpenseEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_entry);

        EditText etExpenseAmount = findViewById(R.id.etAmount);
        Button btnSubmit = findViewById(R.id.btnDone);

        btnSubmit.setOnClickListener(v -> {
            String amountText = etExpenseAmount.getText().toString();
            int expenseAmount = Integer.parseInt(amountText);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("expenseAmount", expenseAmount);
            resultIntent.putExtra("category", getIntent().getStringExtra("category")); // Add this line
            setResult(RESULT_OK, resultIntent);

            // Debug log statements
            Log.d("ExpenseTracker", "Entered expense amount: " + expenseAmount);
            Log.d("ExpenseTracker", "Expense category: " + getIntent().getStringExtra("category"));

            finish();
        });
    }
}
