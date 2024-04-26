package model;

import java.io.Serial;
import java.io.Serializable;

public class Ingredient implements Serializable {
    @Serial
    private static final long serialVersionUID = 333444555L;
    private String nameOfIngredient;
    private double costOfIngredient;
    private double amountOfIngredient;
    private Measurement measurement;
    private Store store;
    public Ingredient(String nameOfIngredient, double costOfIngredient, double amountOfIngredient, Measurement measurement) {
        this.nameOfIngredient = nameOfIngredient;
        this.costOfIngredient = costOfIngredient;
        this.amountOfIngredient = amountOfIngredient;
        this.measurement = measurement;
    }

    public void setAmountOfIngredient(double amountOfIngredient) {
        this.amountOfIngredient = amountOfIngredient;
    }

    public double getCostOfIngredient() {
        return costOfIngredient;
    }

    public void setCostOfIngredient(double costOfIngredient) {
        this.costOfIngredient = costOfIngredient;
    }

    public double getAmountOfIngredient() {
        return amountOfIngredient;
    }

    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
    public String toString() {
        return String.format("%s    |    %skr   |   %s%s", nameOfIngredient, costOfIngredient, amountOfIngredient, measurement);
    }
}
