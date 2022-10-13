package me.hyperbone.faygo;

import java.util.ArrayList;
import java.util.List;

public class DependencyBuilder {

    private static final List<Dependency> dependencies = new ArrayList<>();

    public DependencyBuilder add(Dependency dependency) {
        dependencies.add(dependency);
        return this;
    }

    public void load() {
        dependencies.forEach(Dependency::downloadAndLoad);
    }
}