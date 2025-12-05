package org.example;

// records are basically just a quicker way of writing classes with a canonical constructor
// and getters but with NO SETTERS because data encapsulated inside a particular instance
// from a record is IMMUTABLE
// you can write this instead:

// the canonical constructor here can be used for fetching material objects from the database
public record Material(int materialId, String name, String unitOfMeasure, double price, double stockQuantity) {
    // this overloaded constructor can be used for instantiating new material objects that are yet to be added
    // to the database
    public Material(String name, String unitOfMeasure, double price, double stockQuantity) {
        this(0, name, unitOfMeasure, price, stockQuantity);
    }
}