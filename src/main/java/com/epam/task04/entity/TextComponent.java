package com.epam.task04.entity;

import java.util.List;

public interface TextComponent {

    void add(TextComponent component);

    void remove(TextComponent component);

    List<TextComponent> getChild();

    int size();

    ComponentType getType();

    int hashCode();

    void setComponents(List<TextComponent> components);


    String toString();
}
