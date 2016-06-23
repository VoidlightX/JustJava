package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity= 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(quantity);

    }

    private void displayPrice(int number) {
        int a = 1;
        int price = 0;
        ArrayList<String> outputArray= new ArrayList<>();
        outputArray.add("Total\n");
        outputArray.add( "\nThank you!");
        EditText nameText = (EditText) findViewById(R.id.nameText);
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_box);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_box);
        if (nameText.getText() != null)
        {
            outputArray.add(0,"Dear "+ nameText.getText() + "\n");
            a += 1 ;
        }
        if (whippedCream.isChecked())
        {
            outputArray.add(1,"With Whipped Cream\n");
            price += number;
            a += 1;
        }
        if(chocolate.isChecked())
        {
            outputArray.add(2,"With Chocolate\n");
            price += number*2;
            a += 1 ;
        }

        outputArray.add(a, NumberFormat.getCurrencyInstance().format(price + number*5));

        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < outputArray.size(); i++) {
           finalString.append(outputArray.get(i));
        }
        priceTextView.setText(finalString);
        onClickEmail(finalString);




    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantity = quantity + number;
        quantityTextView.setText(quantity);
    }

    public void onClickPluse(View view) {

        if (quantity<100)
        display(1);
        else Toast.makeText(this,"Only for less then 100 ",Toast.LENGTH_SHORT).show();
    }


    public void onClickReduce(View view){

        if (quantity>1)
            display(-1);
        else Toast.makeText(this, "Not for negative coffee", Toast.LENGTH_SHORT).show();
    }

    public void onClickEmail(StringBuffer email){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:apptestforx@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        emailIntent.putExtra(Intent.EXTRA_TEXT,(CharSequence) email);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
            Log.e("email","have used emailIntent");
        }
        else {Toast.makeText(this, "Without Email Apps", Toast.LENGTH_SHORT).show();
        Log.e("email","have not used emailIntent");
        }
    }


    }

