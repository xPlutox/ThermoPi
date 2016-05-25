package be.nvl.thermopi.util;

/**
 * @author vanlooni
 * @since 16/05/2016
 */
public class StringUtil {
    public static String formatByteAsBit(byte buteVar) {
        return "0b" + ("0000000" + Integer.toBinaryString(0xFF & buteVar)).replaceAll(".*(.{8})$", "$1");
    }
}
