package com.smartspend;
import org.junit.jupiter.api.Test;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import java.math.BigDecimal;import static org.junit.jupiter.api.Assertions.*;
class SmartSpendApplicationTests {
 @Test void passwordsAreHashedAndVerified(){var encoder=new BCryptPasswordEncoder();String raw="secret123";String hash=encoder.encode(raw);assertNotEquals(raw,hash);assertTrue(encoder.matches(raw,hash));assertFalse(encoder.matches("wrong",hash));}
 @Test void budgetStatusThresholdsAreDeterministic(){assertEquals("SAFE",status(79.99));assertEquals("WARNING",status(80));assertEquals("WARNING",status(99.99));assertEquals("EXCEEDED",status(100));}
 private String status(double percentage){return percentage>=100?"EXCEEDED":percentage>=80?"WARNING":"SAFE";}
 @Test void savingsPercentageHandlesZeroIncome(){assertEquals(BigDecimal.ZERO,FinancialRules.savingsPercentage(BigDecimal.ZERO,BigDecimal.TEN));}
 @Test void sharedFinancialRulesCoverInsightThresholds(){assertTrue(FinancialRules.highCategorySpending(new BigDecimal("31"),new BigDecimal("100")));assertFalse(FinancialRules.highCategorySpending(new BigDecimal("30"),new BigDecimal("100")));assertTrue(FinancialRules.lowSavings(new BigDecimal("1000"),new BigDecimal("950")));assertFalse(FinancialRules.lowSavings(new BigDecimal("1000"),new BigDecimal("900")));assertTrue(FinancialRules.spendingIncreased(new BigDecimal("101"),new BigDecimal("100")));}
 @Test void budgetStatusUsesSafeWarningExceededBoundaries(){assertEquals("SAFE",FinancialRules.budgetStatus(new BigDecimal("79.99"),BigDecimal.valueOf(100)));assertEquals("WARNING",FinancialRules.budgetStatus(new BigDecimal("80"),BigDecimal.valueOf(100)));assertEquals("EXCEEDED",FinancialRules.budgetStatus(new BigDecimal("100"),BigDecimal.valueOf(100)));}
}
