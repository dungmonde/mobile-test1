package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityQuestion2Binding
import java.text.NumberFormat
import java.util.Locale

class Question2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestion2Binding
    private var baseTotalAmount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBookTicket.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val phone = binding.etPhoneNumber.text.toString().trim()
            val numTicketsStr = binding.etNumTickets.text.toString().trim()
            val discountStr = binding.etDiscount.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || numTicketsStr.isEmpty()) {
                Toast.makeText(this, "Please enter Full Name, Phone Number, and Number of Tickets", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numTickets = numTicketsStr.toIntOrNull() ?: 0
            if (numTickets <= 0) {
                Toast.makeText(this, "Number of tickets must be greater than 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val discountPercent = discountStr.toDoubleOrNull() ?: 0.0

            // Base price based on selected ticket class
            val ticketPrice = when (binding.rgTicketType.checkedRadioButtonId) {
                R.id.rbFirstClass -> 1500000.0
                R.id.rbBusinessClass -> 1300000.0
                R.id.rbEconomyClass -> 1000000.0
                else -> 1000000.0
            }

            val subTotal = ticketPrice * numTickets
            val discountAmount = subTotal * (discountPercent / 100.0)
            baseTotalAmount = subTotal - discountAmount

            // Reset rating bar to 0 when booking a new ticket
            binding.ratingBar.rating = 0f

            // Update result fields
            binding.tvResultName.text = "Full Name: $name"
            binding.tvResultPhone.text = "Phone Number: $phone"
            updateTotalAmountDisplay()

            // Make result layout visible
            binding.layoutResult.visibility = View.VISIBLE
            Toast.makeText(this, "Ticket Booked Successfully!", Toast.LENGTH_SHORT).show()
        }

        // Listener for RatingBar to dynamically apply 5-star extra 5% discount
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            updateTotalAmountDisplay()
            if (rating == 5.0f) {
                Toast.makeText(this, "Thank you for the 5-star rating! Extra 5% discount applied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateTotalAmountDisplay() {
        var finalAmount = baseTotalAmount
        // If the user gives a 5-star rating, an additional 5% discount will be deducted from the total amount
        if (binding.ratingBar.rating == 5.0f) {
            finalAmount *= 0.95
        }

        // Format number beautifully as currency
        val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        val formattedAmount = formatter.format(finalAmount).replace("₫", "VND")
        binding.tvResultAmount.text = "Total Amount: $formattedAmount"
    }
}
