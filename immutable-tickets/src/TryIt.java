import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo that shows immutability in action after refactoring.
 *
 * - Building a ticket via Builder
 * - "Updating" produces a NEW instance; the original stays unchanged
 * - External tag mutation via getTags() has NO effect
 */
public class TryIt {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        // ── 1. Create a ticket via the service ──────────────────────
        IncidentTicket original = service.createTicket(
                "TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("1) Created ticket:");
        System.out.println("   " + original);

        // ── 2. Assign — returns a NEW ticket; original is unchanged ─
        IncidentTicket assigned = service.assign(original, "agent@example.com");
        System.out.println("\n2) After assign (new instance returned):");
        System.out.println("   assigned  = " + assigned);
        System.out.println("   original  = " + original);
        System.out.println("   Same object? " + (original == assigned)); // false

        // ── 3. Escalate — again returns a NEW ticket ────────────────
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\n3) After escalation (new instance returned):");
        System.out.println("   escalated = " + escalated);
        System.out.println("   assigned  = " + assigned); // still MEDIUM
        System.out.println("   original  = " + original); // still MEDIUM, no assignee

        // ── 4. External tag mutation has NO effect ───────────────────
        List<String> tags = escalated.getTags();
        System.out.println("\n4) Attempting external tag mutation:");
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("   WARNING: List was mutable! (should NOT happen)");
        } catch (UnsupportedOperationException e) {
            System.out.println("   Tags list is unmodifiable — mutation blocked!");
        }
        System.out.println("   escalated = " + escalated);

        // ── 5. Build a ticket directly with the Builder ─────────────
        IncidentTicket custom = new IncidentTicket.Builder()
                .id("TCK-2002")
                .reporterEmail("student@university.edu")
                .title("Cannot access lab portal")
                .description("Receiving 403 error on lab portal since morning")
                .priority("HIGH")
                .source("EMAIL")
                .slaMinutes(120)
                .customerVisible(true)
                .addTag("LAB")
                .addTag("URGENT")
                .build();
        System.out.println("\n5) Custom-built ticket via Builder:");
        System.out.println("   " + custom);

        System.out.println("\nAll demonstrations passed — tickets are truly immutable!");
    }
}
