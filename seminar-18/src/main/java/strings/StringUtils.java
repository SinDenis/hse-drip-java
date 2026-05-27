package strings;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isPalindrome(String value) {
        if (value == null) {
            return false;
        }
        String normalized = value.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0;
        int right = normalized.length() - 1;
        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static String reverse(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        return new StringBuilder(value).reverse().toString();
    }

    public static int countVowels(String value) {
        if (value == null) {
            return 0;
        }
        int count = 0;
        for (char c : value.toLowerCase().toCharArray()) {
            if ("aeiouy".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }
}
