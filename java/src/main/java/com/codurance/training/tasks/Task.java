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

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUnDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%c] %d: %s", (this.isDone() ? 'x' : ' '), this.getId(), this.getDescription());
    }
}
