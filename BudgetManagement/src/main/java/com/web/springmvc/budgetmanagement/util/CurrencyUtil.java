package com.web.springmvc.budgetmanagement.util;

public class CurrencyUtil {
    public static long transform(long amount, String from, String to) {
        from = from.substring(0, 3);
        long total = amount;
        total = switch (from) {
            case "USD" -> amount;
            case "EUR" -> Math.round(total * 1.083);
            case "JPY" -> Math.round(total * 0.00622);
            default -> Math.round(total * 0.00003934);
        };
        to = to.substring(0, 3);
        return switch (to) {
            case "USD" -> total;
            case "EUR" -> Math.round(total * 0.9233);
            case "JPY" -> Math.round(total * 160.89);
            default -> Math.round(total * 25419.4204);
        };
    }
}
