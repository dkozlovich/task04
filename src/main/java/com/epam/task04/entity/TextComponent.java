package com.epam.task04.entity;

import java.util.List;

public interface TextComponent {

    void add(TextComponent component);
    void remove(TextComponent component);
    List<TextComponent> getList();
    int size();
    ComponentType getType();
    String toString();

    void setComponents(List<TextComponent> components);
}
