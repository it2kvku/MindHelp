package com.astra.mindhelp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignUpScreen() {
    val pastelBlue = Color(0xFFE3F2FD)
    val pastelMint = Color(0xFFE0F7FA)
    val pastelSurface = MaterialTheme.colorScheme.surface
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var agreed by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current
    val passwordStrength = getPasswordStrength(password)
    val passwordStrengthColor = when (passwordStrength) {
        PasswordStrength.WEAK -> Color(0xFFFFCDD2)
        PasswordStrength.MEDIUM -> Color(0xFFFFF9C4)
        PasswordStrength.STRONG -> Color(0xFFC8E6C9)
        else -> Color.Transparent
    }
    val passwordStrengthLabel = when (passwordStrength) {
        PasswordStrength.WEAK -> "Weak"
        PasswordStrength.MEDIUM -> "Medium"
        PasswordStrength.STRONG -> "Strong"
        else -> ""
    }
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
                // Icon or Lottie placeholder
                Box(
                    Modifier
                        .size(90.dp)
                        .background(Color(0xFFB3E5FC), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = Color(0xFF0288D1), modifier = Modifier.size(48.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Create your MindHelp account",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                // Full Name (optional)
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name (optional)") },
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
                Spacer(Modifier.height(8.dp))
                // Email
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
                Spacer(Modifier.height(8.dp))
                // Password
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
                // Password strength meter
                if (password.isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 2.dp)
                    ) {
                        Box(
                            Modifier
                                .height(6.dp)
                                .weight(1f)
                                .background(passwordStrengthColor, RoundedCornerShape(3.dp))
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(passwordStrengthLabel, fontSize = 12.sp, color = passwordStrengthColor, fontWeight = FontWeight.SemiBold)
                    }
                }
                Spacer(Modifier.height(8.dp))
                // Confirm Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val desc = if (confirmPasswordVisible) "Hide password" else "Show password"
                        Icon(
                            image,
                            desc,
                            modifier = Modifier.clickable { confirmPasswordVisible = !confirmPasswordVisible }
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.7f),
                        focusedContainerColor = Color.White.copy(alpha = 0.7f),
                        focusedIndicatorColor = Color(0xFF81D4FA),
                        unfocusedIndicatorColor = Color(0xFFB3E5FC)
                    )
                )
                Spacer(Modifier.height(8.dp))
                // Terms & Privacy
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = agreed,
                        onCheckedChange = { agreed = it },
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    val annotatedString = buildAnnotatedString {
                        append("I agree to the ")
                        pushStringAnnotation(tag = "terms", annotation = "https://your-terms-url.com")
                        pushStyle(SpanStyle(color = Color(0xFF0288D1), textDecoration = TextDecoration.Underline))
                        append("Terms")
                        pop()
                        pop()
                        append(" & ")
                        pushStringAnnotation(tag = "privacy", annotation = "https://your-privacy-url.com")
                        pushStyle(SpanStyle(color = Color(0xFF0288D1), textDecoration = TextDecoration.Underline))
                        append("Privacy Policy")
                        pop()
                        pop()
                    }
                    ClickableText(
                        text = annotatedString,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
                                .firstOrNull()?.let { uriHandler.openUri(it.item) }
                            annotatedString.getStringAnnotations(tag = "privacy", start = offset, end = offset)
                                .firstOrNull()?.let { uriHandler.openUri(it.item) }
                        }
                    )
                }
                Spacer(Modifier.height(8.dp))
                // Sign Up button
                Button(
                    onClick = { /* TODO: Sign up */ },
                    enabled = agreed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81D4FA))
                ) {
                    Text("Sign Up", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Spacer(Modifier.height(8.dp))
                // Google sign up button
                Button(
                    onClick = { /* TODO: Google sign up */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Google", tint = Color(0xFFEA4335))
                    Spacer(Modifier.width(8.dp))
                    Text("Sign up with Google", color = Color(0xFF757575), fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(16.dp))
                // Bottom prompt
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Already have an account? ", color = Color(0xFF757575))
                    Text(
                        "Log in",
                        color = Color(0xFF0288D1),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* TODO: Log in */ }
                    )
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

enum class PasswordStrength { WEAK, MEDIUM, STRONG }

fun getPasswordStrength(password: String): PasswordStrength? {
    if (password.isEmpty()) return null
    var score = 0
    if (password.length >= 8) score++
    if (password.any { it.isDigit() }) score++
    if (password.any { it.isUpperCase() }) score++
    if (password.any { !it.isLetterOrDigit() }) score++
    return when (score) {
        0, 1 -> PasswordStrength.WEAK
        2, 3 -> PasswordStrength.MEDIUM
        else -> PasswordStrength.STRONG
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}

