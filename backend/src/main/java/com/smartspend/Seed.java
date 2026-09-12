package com.smartspend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;

@Configuration class SeedConfig {
  private static final Map<String,String> CATEGORIES = new LinkedHashMap<>();
  static {
    CATEGORIES.put("Food", "#f97316"); CATEGORIES.put("Transport", "#2563eb"); CATEGORIES.put("Shopping", "#7c3aed");
    CATEGORIES.put("Bills", "#eab308"); CATEGORIES.put("Education", "#4f46e5"); CATEGORIES.put("Entertainment", "#db2777");
    CATEGORIES.put("Health", "#dc2626"); CATEGORIES.put("Travel", "#0891b2"); CATEGORIES.put("Other", "#64748b");
    CATEGORIES.put("EMI", "#0f766e"); CATEGORIES.put("Borrow", "#8b5cf6"); CATEGORIES.put("Rent", "#059669");
    CATEGORIES.put("Fuels", "#f59e0b"); CATEGORIES.put("Electronics", "#0ea5e9"); CATEGORIES.put("Groceries", "#16a34a");
    CATEGORIES.put("Medical & Medicines", "#dc2626"); CATEGORIES.put("Software & Subscriptions", "#9333ea"); CATEGORIES.put("Pets", "#a16207");
    CATEGORIES.put("Movies & Streaming", "#7c3aed"); CATEGORIES.put("Fitness", "#65a30d"); CATEGORIES.put("Clothing", "#be185d");
    CATEGORIES.put("Dining Out", "#ea580c"); CATEGORIES.put("Mobile & Internet", "#06b6d4"); CATEGORIES.put("Insurance", "#0f766e");
    CATEGORIES.put("Personal Care", "#c026d3"); CATEGORIES.put("Banking & Finance", "#0284c7"); CATEGORIES.put("Gifts", "#ec4899");
    CATEGORIES.put("Home Maintenance", "#059669"); CATEGORIES.put("Vehicle Maintenance", "#475569"); CATEGORIES.put("Parking & Tolls", "#2563eb");
    CATEGORIES.put("Public Transport", "#0891b2"); CATEGORIES.put("Books & Stationery", "#4f46e5"); CATEGORIES.put("Games", "#9333ea");
    CATEGORIES.put("Coffee & Snacks", "#b45309"); CATEGORIES.put("Household", "#64748b"); CATEGORIES.put("Vacation", "#0ea5e9");
    CATEGORIES.put("Pet Care", "#a16207"); CATEGORIES.put("Investment", "#15803d"); CATEGORIES.put("Taxes", "#b91c1c");
  }
  @Bean CommandLineRunner demoData(CategoryRepo c, UserRepo u, BCryptPasswordEncoder e) {
    return x -> {
      CATEGORIES.forEach((name, color) -> {
        Category category = c.findByNameIgnoreCase(name).orElseGet(() -> c.save(new Category(name)));
        if (category.color == null || "#38bdf8".equalsIgnoreCase(category.color)) { category.color = color; c.save(category); }
      });
      if (u.count() == 0) { User a = new User(); a.name="Demo Admin"; a.email="admin@smartspend.local"; a.password=e.encode("Admin@123"); a.role=Role.ADMIN; u.save(a); }
    };
  }
}
