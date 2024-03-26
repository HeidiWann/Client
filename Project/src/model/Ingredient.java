package model;

public class Ingredient {
    private String nameOfIngredient;
    private int costOfIngredient;
    private double amountOfIngredient;
    public Ingredient(String nameOfIngredient, int costOfIngredient, double amountOfIngredient) {

    }

    public void setAmountOfIngredient(double amountOfIngredient) {
        this.amountOfIngredient = amountOfIngredient;
    }

    public int getCostOfIngredient() {
        return costOfIngredient;
    }

    public void setCostOfIngredient(int costOfIngredient) {
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
}
