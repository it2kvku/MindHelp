package com.astra.mindhelp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen() {
    val pastelBlue = Color(0xFFE3F2FD)
    val pastelMint = Color(0xFFE0F7FA)
    val pastelSurface = MaterialTheme.colorScheme.surface
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(pastelBlue, pastelMint, pastelSurface)
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(Modifier.height(12.dp))
                // Logo or Lottie animation placeholder
                Box(
                    Modifier
                        .size(120.dp)
                        .background(Color(0xFFB3E5FC), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🌊", fontSize = 48.sp) // Replace with Lottie if available
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Welcome back",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.7f),
                        focusedContainerColor = Color.White.copy(alpha = 0.7f),
                        focusedIndicatorColor = Color(0xFF81D4FA),
                        unfocusedIndicatorColor = Color(0xFFB3E5FC)
                    )
                )
                Spacer(Modifier.height(12.dp))
                // Password field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val desc = if (passwordVisible) "Hide password" else "Show password"
                        Icon(
                            image,
                            desc,
                            modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.7f),
                        focusedContainerColor = Color.White.copy(alpha = 0.7f),
                        focusedIndicatorColor = Color(0xFF81D4FA),
                        unfocusedIndicatorColor = Color(0xFFB3E5FC)
                    )
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { /* TODO: Forgot password */ }) {
                        Text("Forgot password?", color = Color(0xFF0288D1))
                    }
                }
                Spacer(Modifier.height(8.dp))
                // Login button
                Button(
                    onClick = { /* TODO: Login */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81D4FA))
                ) {
                    Text("Login", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Spacer(Modifier.height(8.dp))
                // Google sign-in button
                Button(
                    onClick = { /* TODO: Google sign-in */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Google", tint = Color(0xFFEA4335))
                    Spacer(Modifier.width(8.dp))
                    Text("Sign in with Google", color = Color(0xFF757575), fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(16.dp))
                // Bottom prompt
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Don’t have an account? ", color = Color(0xFF757575))
                    Text(
                        "Sign up",
                        color = Color(0xFF0288D1),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* TODO: Sign up */ }
                    )
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

