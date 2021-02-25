package com.blaze.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    CheckBox whippedCreamCheckBox, chocolateCheckBox;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        whippedCreamCheckBox = findViewById(R.id.whipped_cream_cb);
        chocolateCheckBox = findViewById(R.id.chocolate_cb);
        EditText nameField = findViewById(R.id.name_et);
        name = nameField.getText().toString(); // to store the name of the customer

        Intent intent = new Intent(Intent.ACTION_SENDTO); // intent to send a email of the order summary
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method Calculates the price of the order and @returns total price.
     */
    private int calculatePrice() {
        int basePrice = 5;
        if (whippedCreamCheckBox.isChecked()) {
            basePrice += 1;
        }
        if (chocolateCheckBox.isChecked()) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the order summary on the screen.
     */
    public String createOrderSummary() {
        return ("Name: " + name + "\nAdd Whipped Cream? " + whippedCreamCheckBox.isChecked() + "\nAdd Chocolate? "
                + chocolateCheckBox.isChecked() + "\nQuantity: " + quantity + "\nTotal: ₹" + calculatePrice() + "\nThank you!");
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
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 Coffees", Toast.LENGTH_LONG).show();
            return;
        }
        displayQuantity(++quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 Coffee", Toast.LENGTH_LONG).show();
            return;
        }
        displayQuantity(--quantity);
    }
}