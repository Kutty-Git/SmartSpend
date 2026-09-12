package com.smartspend;
import org.springframework.web.bind.annotation.*;import org.springframework.security.core.Authentication;import jakarta.validation.Valid;import java.util.*;
@RestController @RequestMapping("/api/savings-goal") class SavingsGoalController extends Base{final SavingsGoalRepo goals;final UserRepo users;final AuditService audit;SavingsGoalController(SavingsGoalRepo g,UserRepo u,AuditService a){goals=g;users=u;audit=a;}
Map<String,Object> out(SavingsGoal g){return Map.of("exists",true,"id",g.id,"title",g.title,"targetAmount",g.targetAmount,"currentAmount",g.currentAmount,"deadline",g.deadline==null?"":g.deadline);}
@GetMapping Map<String,Object> get(Authentication a){return goals.findByUserId(me(a,users).id).map(this::out).orElse(Map.of("exists",false));}
@PostMapping Map<String,Object> save(@Valid @RequestBody SavingsGoalRequest r,Authentication a){User u=me(a,users);SavingsGoal g=goals.findByUserId(u.id).orElseGet(()->new SavingsGoal());boolean creating=g.id==null;g.user=u;g.title=r.title().trim();g.targetAmount=r.targetAmount();g.currentAmount=r.currentAmount();g.deadline=r.deadline();SavingsGoal saved=goals.save(g);audit.record(u,creating?"SAVINGS_GOAL_CREATE":"SAVINGS_GOAL_UPDATE","Saved savings goal "+saved.id);return out(saved);}
@DeleteMapping void delete(Authentication a){User u=me(a,users);goals.findByUserId(u.id).ifPresent(g->{goals.delete(g);audit.record(u,"SAVINGS_GOAL_DELETE","Deleted savings goal "+g.id);});}
}
