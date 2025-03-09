package com.hello.online_cooking_recipe_app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        // Initialize Views
        val emailET = findViewById<EditText>(R.id.emailET)
        val passwordET = findViewById<EditText>(R.id.pasET)
        val signInBTN = findViewById<Button>(R.id.siginBTN)
        val createAccountTV = findViewById<TextView>(R.id.createAccountTV)
        val forgotPasswordTV = findViewById<TextView>(R.id.forgotPasswordTV)

        // Add TextWatchers to clear errors when user types
        emailET.addTextChangedListener(createTextWatcher(emailET))
        passwordET.addTextChangedListener(createTextWatcher(passwordET))

        // Sign In Button Click
        signInBTN.setOnClickListener {
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            when {
                email.isEmpty() -> emailET.error = "Email is required"
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> emailET.error = "Enter a valid email"
                password.isEmpty() -> passwordET.error = "Password is required"
                else -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        // Navigate to SignUp Page
        createAccountTV.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        // Forgot Password Click
        forgotPasswordTV.setOnClickListener {
            Toast.makeText(this, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show()
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
