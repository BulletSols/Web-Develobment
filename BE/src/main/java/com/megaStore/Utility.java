package com.megaStore;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Utility {

    public static String hashPassword(String password) {
         int strength = 12; // Adjust the cost factor as needed
         return BCrypt.withDefaults().hashToString(strength, password.toCharArray());
    }

}
