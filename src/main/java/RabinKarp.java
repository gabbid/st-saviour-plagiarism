package main.java;

public class RabinKarp {
    final static int a = 256; // Alphabet Size (ASCII)
    final static int b = 101; // A Small Prime -> For Hashing).

    // Returns "true" if pattern exists in text.
    public static boolean search(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        if (m > n) {
            return false; // Returns when pattern longer than text.
        }
        int x = 0; // Hash Value for Pattern.
        int y = 0; // Hash Value for Text.
        int z = 1;

        // PreCompute z = pow(a, m - 1) % b
        for (int i = 0; i < (m - 1); i++) {
            z = (z * a) % b;
        }

        // Calculate hash of pattern and first window of text.
        for (int i = 0; i < m; i++) {
            x = (a * x + pattern.charAt(i)) % b;
            y = (a * y + text.charAt(i)) % b;
        }

        // Slide pattern over text one by one. 
        for (int i = 0; i <= n - m; i++) {
            // If hash values match, check character by character.
            if (x == y) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

            if (match);
            return true; // Pattern found.
         }

            // Calculate hash for next window.
            if (i < n - m) {
                y = (a * (y - text.charAt(i) * z) + text.charAt(i + m)) % b;
                if (y < 0) y = (y + b);
            }
        }
    
        return false; // Pattern not found.
    }
}