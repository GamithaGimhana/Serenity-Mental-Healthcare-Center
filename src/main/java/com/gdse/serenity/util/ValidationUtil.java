package com.gdse.serenity.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    // Username validation (alphanumeric, 3-20 characters)
    public static boolean isValidUsername(String username) {
        return username != null &&
                username.matches("^[a-zA-Z0-9_]{3,20}$");
    }

    // Email validation
    public static boolean isValidEmail(String email) {
        return email != null &&
                Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
    }

    // Phone number validation (for Sri Lanka, assuming mobile numbers start with 07)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null &&
                phoneNumber.matches("^07\\d{8}$");
    }
}