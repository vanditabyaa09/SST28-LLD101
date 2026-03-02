package com.example.tickets;

/**
 * Service layer that creates and "updates" tickets.
 *
 * REFACTORED:
 * - Uses IncidentTicket.Builder for creation (no post-creation mutation).
 * - "Updates" return a NEW IncidentTicket instance via toBuilder().
 * - All validation is delegated to Builder.build().
 */
public class TicketService {

    /**
     * Creates a brand-new ticket with sensible defaults.
     * All validation happens inside Builder.build().
     */
    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    /**
     * Returns a NEW ticket that is an escalated copy of the original.
     * The original ticket is NOT modified.
     */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    /**
     * Returns a NEW ticket with the assignee set.
     * The original ticket is NOT modified.
     */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
