package com.epam.task04.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private List<TextComponent> components = new ArrayList<>();
    private ComponentType componentType;

    public TextComposite() {

    }

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;

    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public List<TextComponent> getList() {
        return components;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override()
    public ComponentType getType() {
        return componentType;
    }

    public void setComponents(List<TextComponent> components) {
        if (components != null) {
            this.components.removeAll(this.components);
            this.components.addAll(components);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = componentType.getDelimiter();
        for (TextComponent textComponent : components) {
            sb.append(componentType + ", ").append(textComponent.toString()).append(delimiter);
        }
        return sb.toString();
    }
}
