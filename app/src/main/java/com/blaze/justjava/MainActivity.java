package com.blaze.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        createOrderSummary(price);
    }

    /**
     * This method Calculates the price of the order and @returns total price.
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method displays the order summary on the screen.
     */
    private void createOrderSummary(int price) {
        displayMessage("Name: Sam \nQuantity: " + quantity + "\nTotal: ₹" + price + "\nThank you!");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_tv);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        displayQuantity(++quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        displayQuantity(--quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_tv);
        orderSummaryTextView.setText(message);
    }
}