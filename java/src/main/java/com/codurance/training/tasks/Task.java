package com.codurance.training.tasks;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    boolean hasId(int id) {
        return this.id == id;
    }

    void markAsDone() {
        done = true;
    }

    void markAsUnDone() {
        done = false;
    }

    String format() {
        return String.format("[%c] %d: %s", (done ? 'x' : ' '), id, description);
    }
}
