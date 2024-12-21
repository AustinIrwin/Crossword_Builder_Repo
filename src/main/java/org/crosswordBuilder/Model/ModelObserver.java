package org.crosswordBuilder.Model;

public interface ModelObserver {
    /** When a model value is changed, the model calls update() on all active ModelObserver objects */
    void update(IModel model);
}
