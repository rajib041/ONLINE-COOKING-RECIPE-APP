package com.hello.online_cooking_recipe_app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        // Initialize Views
        val nameET = findViewById<EditText>(R.id.nameET)
        val phoneET = findViewById<EditText>(R.id.phET)
        val emailET = findViewById<EditText>(R.id.emailET)
        val passwordET = findViewById<EditText>(R.id.passwordET)
        val termsCB = findViewById<CheckBox>(R.id.termsCB)
        val signupBT = findViewById<Button>(R.id.signupBT)
        val textTV = findViewById<TextView>(R.id.textTV)

        // Add TextWatchers to clear errors when user types
        nameET.addTextChangedListener(createTextWatcher(nameET))
        phoneET.addTextChangedListener(createTextWatcher(phoneET))
        emailET.addTextChangedListener(createTextWatcher(emailET))
        passwordET.addTextChangedListener(createTextWatcher(passwordET))

        // Sign Up Button Click
        signupBT.setOnClickListener {
            val name = nameET.text.toString().trim()
            val phone = phoneET.text.toString().trim()
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            when {
                name.isEmpty() -> nameET.error = "Name is required"
                phone.isEmpty() -> phoneET.error = "Phone number is required"
                !Patterns.PHONE.matcher(phone).matches() -> phoneET.error = "Enter a valid phone number"
                email.isEmpty() -> emailET.error = "Email is required"
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> emailET.error = "Enter a valid email"
                password.isEmpty() -> passwordET.error = "Password is required"
                !termsCB.isChecked -> Toast.makeText(this, "You must accept the terms & conditions", Toast.LENGTH_SHORT).show()
                else -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        // Navigate to Sign In Page
        textTV.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
        }
    }

    // Function to create TextWatcher
    private fun createTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editText.error = null // Remove error when user starts typing
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }
}
