package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry — a proper, thread-safe, lazy-initialized Singleton.
 *
 * Protections:
 * 1) Private constructor — prevents external instantiation
 * 2) Double-checked locking with volatile — thread-safe lazy init
 * 3) Reflection guard — throws exception if constructor called when instance
 * exists
 * 4) readResolve() — preserves singleton across serialization/deserialization
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // volatile ensures visibility of the INSTANCE reference across threads
    private static volatile MetricsRegistry INSTANCE;
    private final Map<String, Long> counters = new HashMap<>();

    // Private constructor + reflection guard
    private MetricsRegistry() {
        // If someone tries to use reflection to create a second instance, block it
        if (INSTANCE != null) {
            throw new IllegalStateException(
                    "Singleton already constructed — use MetricsRegistry.getInstance()");
        }
    }

    // Double-checked locking: lazy, thread-safe singleton initialization
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) { // 1st check (no lock)
            synchronized (MetricsRegistry.class) { // lock
                if (INSTANCE == null) { // 2nd check (with lock)
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // Preserve singleton on deserialization — return the existing instance
    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}
