package com.smartspend;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class FinancialRules {
    private FinancialRules() {}
    static String budgetStatus(BigDecimal used, BigDecimal budget) {
        if (budget == null || budget.signum() <= 0) return "SAFE";
        BigDecimal pct = used.multiply(BigDecimal.valueOf(100)).divide(budget, 2, RoundingMode.HALF_UP);
        return pct.compareTo(BigDecimal.valueOf(100)) >= 0 ? "EXCEEDED" : pct.compareTo(BigDecimal.valueOf(80)) >= 0 ? "WARNING" : "SAFE";
    }
    static BigDecimal savingsPercentage(BigDecimal income, BigDecimal expense) {
        if (income == null || income.signum() == 0) return BigDecimal.ZERO;
        return income.subtract(expense).multiply(BigDecimal.valueOf(100)).divide(income, 2, RoundingMode.HALF_UP);
    }
    static boolean spendingIncreased(BigDecimal current, BigDecimal previous) { return current.compareTo(previous) > 0; }
    static boolean highCategorySpending(BigDecimal categoryAmount, BigDecimal totalExpense) {
        if (totalExpense == null || totalExpense.signum() <= 0) return false;
        return categoryAmount.multiply(BigDecimal.valueOf(100)).divide(totalExpense, 2, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(30)) > 0;
    }
    static boolean lowSavings(BigDecimal income, BigDecimal expense) {
        return income != null && income.signum() > 0 && savingsPercentage(income, expense).compareTo(BigDecimal.TEN) < 0;
    }
}
