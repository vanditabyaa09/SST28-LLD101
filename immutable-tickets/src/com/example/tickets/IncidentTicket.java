package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An immutable incident ticket.
 *
 * REFACTORED:
 * - All fields are private and final
 * - No setters — state cannot change after construction
 * - Tags list is defensively copied and returned as unmodifiable
 * - Created exclusively via the nested Builder
 * - All validation is centralized in Builder.build()
 * - "Updates" are done by copying into a new Builder via toBuilder()
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority; // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags; // defensively copied, unmodifiable
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes; // optional
    private final String source; // e.g. "CLI", "WEBHOOK", "EMAIL"

    // ─── Private constructor — only Builder can create instances ──────
    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    // ─── Getters only (no setters!) ──────────────────────────────────
    public String getId() {
        return id;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public List<String> getTags() {
        return tags;
    } // unmodifiable — safe

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public boolean isCustomerVisible() {
        return customerVisible;
    }

    public Integer getSlaMinutes() {
        return slaMinutes;
    }

    public String getSource() {
        return source;
    }

    // ─── Convenience: copy this ticket into a new Builder ────────────
    public Builder toBuilder() {
        return Builder.from(this);
    }

    // ══════════════════════════════════════════════════════════════════
    // Builder — the ONLY way to create an IncidentTicket
    // ══════════════════════════════════════════════════════════════════
    public static class Builder {

        // Required fields
        private String id;
        private String reporterEmail;
        private String title;

        // Optional fields (sensible defaults)
        private String description = null;
        private String priority = null;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail = null;
        private boolean customerVisible = false;
        private Integer slaMinutes = null;
        private String source = null;

        public Builder() {
        }

        /** Copy-from factory: pre-fills every field from an existing ticket. */
        public static Builder from(IncidentTicket existing) {
            Builder b = new Builder();
            b.id = existing.id;
            b.reporterEmail = existing.reporterEmail;
            b.title = existing.title;
            b.description = existing.description;
            b.priority = existing.priority;
            b.tags = new ArrayList<>(existing.tags); // defensive copy
            b.assigneeEmail = existing.assigneeEmail;
            b.customerVisible = existing.customerVisible;
            b.slaMinutes = existing.slaMinutes;
            b.source = existing.source;
            return b;
        }

        // ── Fluent setters ───────────────────────────────────────────
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String email) {
            this.reporterEmail = email;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder assigneeEmail(String email) {
            this.assigneeEmail = email;
            return this;
        }

        public Builder customerVisible(boolean visible) {
            this.customerVisible = visible;
            return this;
        }

        public Builder slaMinutes(Integer minutes) {
            this.slaMinutes = minutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = (tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
            return this;
        }

        public Builder addTag(String tag) {
            this.tags.add(tag);
            return this;
        }

        // ── Build with CENTRALIZED validation ────────────────────────
        public IncidentTicket build() {
            // --- Required fields ---
            Validation.requireTicketId(id); // non-blank, <= 20 chars, [A-Z0-9-]
            Validation.requireEmail(reporterEmail, "reporterEmail"); // valid email
            Validation.requireNonBlank(title, "title"); // non-blank
            Validation.requireMaxLen(title, 80, "title"); // <= 80 chars

            // --- Optional fields (validate only when present) ---
            Validation.requireOneOf(priority, "priority",
                    "LOW", "MEDIUM", "HIGH", "CRITICAL"); // valid priority
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes"); // 5–7200

            if (assigneeEmail != null && !assigneeEmail.trim().isEmpty()) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail"); // valid email
            }

            return new IncidentTicket(this);
        }
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
