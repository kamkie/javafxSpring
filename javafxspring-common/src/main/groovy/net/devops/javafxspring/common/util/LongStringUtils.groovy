package net.devops.javafxspring.common.util

class LongStringUtils {

    public static final int MAX_CHARS = 60;
    public static final String STR_SUFFIX = " ...>";

    static String formatLongString(Object input) {
        if (input == null) {
            return "{null}";
        }
        if ("".equals(input)) {
            return "{empty}";
        }

        String string = input.toString();
        int length = string.length();
        if (length <= MAX_CHARS) {
            return string;
        } else {
            String substring = string.substring(0, MAX_CHARS - STR_SUFFIX.length());
            substring = substring.substring(0, substring.lastIndexOf(' '));
            return substring.concat(STR_SUFFIX);
        }
    }
}
