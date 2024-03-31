package view;

import jdk.jfr.Category;
import model.FoodCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JPanel categoryPanel;
    private JComboBox<String> recipeDropdown;
    private MainFrame mainFrame;

    public MainPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000,200));

        // Create search area
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search recipe");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create category buttons
        categoryPanel = new JPanel(new FlowLayout());
        FoodCategory[] categories = FoodCategory.values();
        for (FoodCategory category : categories) {
            addCategoryButton(category.toString());
        }

        // Create recipe dropdown
        recipeDropdown = new JComboBox<>();
        recipeDropdown.setPreferredSize(new Dimension(200,30));

        // Add search and category panels to the main panel
        add(searchPanel, BorderLayout.NORTH);
        add(categoryPanel, BorderLayout.CENTER);
        add(recipeDropdown, BorderLayout.SOUTH);

        // ActionListener to search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search for: " + searchField.getText());
            }
        });
    }

    // Method to add category buttons
    private void addCategoryButton(String categoryName) {
        JButton categoryButton = new JButton(categoryName);
        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FoodCategory selectedCategory = FoodCategory.valueOf(categoryName);
                ArrayList<String> recipes = fetchRecipesFromDatabase(selectedCategory);
                populateRecipeDropdown(recipes);
            }
        });
        categoryPanel.add(categoryButton);
    }

    private ArrayList<String> fetchRecipesFromDatabase(FoodCategory category) {
        ArrayList<String> recipes = new ArrayList<>();

        recipes.add("Recipe 1 for " + category);
        recipes.add("Recipe 2 for " + category);
        recipes.add("Recipe 3 for " + category);
        return recipes;
    }

    private void populateRecipeDropdown(ArrayList<String> recipes) {
        recipeDropdown.removeAllItems();
        for (String recipe : recipes) {
            recipeDropdown.addItem(recipe);
        }
    }
}
