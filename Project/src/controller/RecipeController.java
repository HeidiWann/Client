package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeController {
    private MainGUIController mainGUIController;
    private ArrayList<Recipe> recipes;
    private ArrayList<FoodCategory> recipeFilters;

    public RecipeController() {
        mainGUIController = GetGUIController.getGuiController();
        mainGUIController.setRecipeController(this);
        recipes = new ArrayList<>();
        recipeFilters = new ArrayList<>();
        insertRecipes();
        GetGUIController.getRecipeCreationController().setRecipeController(this);
    }

    public void addRecipeToArray(Recipe recipe){
        recipes.add(recipe);
    }
    public void updateListOfRecipes(){
        mainGUIController.updateListOfRecipes(recipes);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<String> gatherFoodCategories() {
        ArrayList<FoodCategory> foodCategories = new ArrayList<>(Arrays.asList(FoodCategory.values()));
        ArrayList<String> categoriesToReturn = new ArrayList<>();

        for (FoodCategory category : foodCategories) {
            categoriesToReturn.add(category.name());
        }
        return categoriesToReturn;
    }

    /**
     * This method is just used to create sample data.
     *
     * @author Anton Persson
     */
    public void insertRecipes() {
        ArrayList<Ingredient> salmonIngredients = new ArrayList<>();
        ArrayList<FoodCategory> salmonCategory = new ArrayList<>();
        String salmonInstructions;

        salmonIngredients.add(new Ingredient("Lax", 30, 100, Measurement.G));
        salmonIngredients.add(new Ingredient("Ris", 25, 1, Measurement.DL));
        salmonIngredients.add(new Ingredient("Broccoli", 15, 100, Measurement.G));
        salmonIngredients.add(new Ingredient("Champinjoner", 5, 5, Measurement.ST));
        salmonIngredients.add(new Ingredient("Kikomans svampsoja", 30, 5, Measurement.CL));
        salmonCategory.add(FoodCategory.Fisk);
        salmonInstructions = "Börja första med att skära alla grönsakerna. \n" +
                "Börja sedan koka riset och sätt en timer på 10 minuter. \n" +
                "Börja steka laxen på hög värme \n " +
                "Börja steka grönsakerna direkt efter laxen lagts på stekpannen. Använda rikligt med olja. \n" +
                "Häll i soja i grönsakerna och sänk värmen på laxen till låg när det är tre minuter kvar på klockan tills riset är klart.";

        Image salmonTemp = new Image("/view/SalmonImage.jpg");
        ImageView salmonImage = new ImageView(salmonTemp);
        salmonImage.setFitWidth(50);
        salmonImage.setFitHeight(50);

        recipes.add(new Recipe("Anton", salmonInstructions, salmonImage, salmonIngredients, "Lax med ris och stekta grönsaker", salmonCategory));

        ArrayList<Ingredient> oreoShakeIngredients = new ArrayList<>();
        ArrayList<FoodCategory> oreoShakeCategory = new ArrayList<>();
        String oreoShakeInstructions;
        oreoShakeIngredients.add(new Ingredient("Oreo", 30, 8, Measurement.ST));
        oreoShakeIngredients.add(new Ingredient("Mjölk", 15, 2, Measurement.DL));
        oreoShakeIngredients.add(new Ingredient("Vanlij glass", 25, 3, Measurement.DL));
        oreoShakeCategory.add(FoodCategory.Vegetarian);
        oreoShakeInstructions = "Häll i alla ingredienser i en mixer och mixa tills allt krossats. Skulle den bli för tjock så häll bara i mer mjölk";
        Image oreoTemp = new Image("/view/oreoMilkShake.jpg");
        ImageView oreoImage = new ImageView(oreoTemp);
        oreoImage.setFitWidth(50);
        oreoImage.setFitHeight(50);
        recipes.add(new Recipe("Anton", oreoShakeInstructions, oreoImage, oreoShakeIngredients, "Oreo Milkshake", oreoShakeCategory));

        ArrayList<Ingredient> tacoIngredient = new ArrayList<>();
        ArrayList<FoodCategory> tacoCategory = new ArrayList<>();
        String tacoInstructions;
        tacoIngredient.add(new Ingredient("Sallad", 15, 1, Measurement.ST));
        tacoIngredient.add(new Ingredient("Tortilla bröd", 20, 8, Measurement.ST));
        tacoIngredient.add(new Ingredient("Salsa", 15, 2, Measurement.DL));
        tacoIngredient.add(new Ingredient("Lök", 3, 1, Measurement.ST));
        tacoIngredient.add(new Ingredient("Tomat", 5, 3, Measurement.ST));
        tacoIngredient.add(new Ingredient("Annanas", 15, 1, Measurement.Burk));
        tacoCategory.add(FoodCategory.Nöt);
        tacoInstructions = "Skär alla grönsaker. Stek sedan köttfärsen. När köttfärsen fått färg häller du i salsan. " +
                "Gör sedan din tacos med din favoritsås";
        Image tacoTemp = new Image("/view/Tacos.jpg");
        ImageView tacoImage = new ImageView(tacoTemp);
        tacoImage.setFitWidth(50);
        tacoImage.setFitHeight(50);
        recipes.add(new Recipe("Anton", tacoInstructions, tacoImage, tacoIngredient, "Tacos", tacoCategory));

        ArrayList<Ingredient> veganskSmörgåstårta = new ArrayList<>();
        ArrayList<FoodCategory> veganskSmörgåstårtaCategory = new ArrayList<>();
        String veganskSmörgåstårtaInstructions;
        veganskSmörgåstårta.add(new Ingredient("Vegansk majonnäs", 50, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Tångrom", 40, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Citronjuicen från en citron", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Färsk hackad dill", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Rättika finhackad", 10, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Rödlök finhackad", 5, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Salt", 20, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Peppar", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Himalayasalt", 35, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Näringsjäst", 10, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Gurkmeja", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Tofu", 15, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Vegansk creme fraiche", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Pepparrot", 30, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Rökt tofu", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Ruccola", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Liquid smoke", 5, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Olivolja", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Vegansk färskost", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Rostbröd", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Rödlök", 5, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Röd tångcaviar på burk", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Svart tångcaviar på burk", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Färsk Dill", 25, 1, Measurement.ST));
        veganskSmörgåstårta.add(new Ingredient("Citron", 25, 1, Measurement.ST));
        veganskSmörgåstårtaCategory.add(FoodCategory.Vegan);
        veganskSmörgåstårtaInstructions = "Instruktioner vegansk skagenröra\n" +
                "Skala och finhacka rödlöken. " +
                "Skala, skölj och skiva rättikan i mycket små tärningar ca 5 millimeter höga och breda. " +
                "Skölj och finhacka dillen.\n" +
                "Blanda rödlöken, rättikan och dillen med de övriga ingredienserna och " +
                "sätt in i kylen medan du förbereder de andra rörorna\n " + "Instruktioner falsk äggröra\n" +
                "Smula sönder tofun med dina händer, se till så det inte blir för stora smulor. " +
                "\nBlanda ner de övriga ingredienserna och smaka av med salt och peppar. " +
                "\nOm man vill ha syrligare smak kan man tillägga mer citron. " +
                "\nSätt in röran i kylen medan du förbereder de andra rörorna.\n" +"Instruktioner\n" +
                "Skölj ruccolan och låt den torka i ett durkslag under tiden du blandar ihop de övriga ingredienserna. " +
                "\nBlanda ner ruccolan och smaka av med salt och peppar. " +
                "\nMan kan lägga till mer citron och pepparrot om man önskar. " +
                "\nSätt in röran tillsammans med de andra i kylen medan du förbereder sista röran.\n" + "Instruktioner\n" +
                "\nBlanda ihop alla ingredienser och smaka av med salt och peppar. " +
                "\nOm man önskar mer sälta kan man tillföra mer citron. " +
                "\nSätt in i kylen medan du förbereder brödet genom att skiva bort kanterna\n" + "Instruktioner\n" +
                "\nTa fram en bricka som du kan ha din smörgåstårta på, lägg ut brödet för första lagret, applicera skagenröran och lägg bröd ovanpå. " +
                "\nApplicera den falska äggröran och ett nytt lager bröd ovanpå. " +
                "\nGör det samma med tofuröran och efter du applicerat sista bröd lagret ovanpå röran applicerar du toppingrörarn över hela tårtan. " +
                "nPlacera ut tandpetare för att hålla plastfolien  från toppingen och sätt tårtan i kylen över natten. Förbered all topping innan du tar ut tårtan. Rekommendation är att skiva gurkan med en osthyvel. Dekorera efter eget tycke.\n";
        Image veanskSmörgåstårtaTemp = new Image("/view/veanskSmörgåstårtaTemp.jpg");
        ImageView veanskSmörgåstårtaTempImage = new ImageView(veanskSmörgåstårtaTemp);
        veanskSmörgåstårtaTempImage.setFitWidth(50);
        veanskSmörgåstårtaTempImage.setFitHeight(50);
        recipes.add(new Recipe("Anton", veganskSmörgåstårtaInstructions, veanskSmörgåstårtaTempImage, veganskSmörgåstårta, "Vegansk Smörgåstårta", veganskSmörgåstårtaCategory));

        ArrayList<Ingredient> veganskChiliIngredients = new ArrayList<>();
        ArrayList<FoodCategory> veganskChiliCategory = new ArrayList<>();
        veganskChiliCategory.add(FoodCategory.Vegan);
        veganskChiliCategory.add(FoodCategory.Vegetarian);
        String veganskChiliInstructions;
        veganskChiliIngredients.add(new Ingredient("Chili", 0.5, 500, Measurement.G));
        veganskChiliIngredients.add(new Ingredient("Vegansk färs", 0.5, 2.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Gul lök", 0.5, 3.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Gul lök", 0.5, 1.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Barbecuesås", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Barbecuesås", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Paprika ", 0.5, 1.0, Measurement.ST));
        veganskChiliIngredients.add(new Ingredient("Krossade tomater", 0.5, 1.0, Measurement.Burk));
        veganskChiliIngredients.add(new Ingredient("Svarta bönor", 0.5, 1.0, Measurement.Burk));
        veganskChiliIngredients.add(new Ingredient("Kidneybönor", 0.5, 1.0, Measurement.Burk));
        veganskChiliIngredients.add(new Ingredient("Vita Bönor i tomatsås", 0.5, 2.0, Measurement.Burk));
        veganskChiliIngredients.add(new Ingredient("Cayennepeppar", 0.5, 1.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Cayennepeppar", 0.5, 1.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Chilipeppar", 0.5, 1.0, Measurement.TSK));
        veganskChiliIngredients.add(new Ingredient("Paprika", 0.5, 2.0, Measurement.TSK));
        veganskChiliIngredients.add(new Ingredient("Smoked paprika", 0.5, 1.0, Measurement.TSK));
        veganskChiliIngredients.add(new Ingredient("Kummin", 0.5, 0.5, Measurement.TSK));
        veganskChiliIngredients.add(new Ingredient("Chilisås", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Grönsaksfond", 0.5, 3.0, Measurement.MSK));
        veganskChiliIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        veganskChiliIngredients.add(new Ingredient("Salt", 0.5, 0.0, Measurement.KRM));
        veganskChiliIngredients.add(new Ingredient("Peppar", 0.5, 0.0, Measurement.KRM));

        veganskChiliInstructions = "Instruktioner\n" +
                "Förbered alla ingredienser innan du börjar tillaga grytan. Hacka löken, vitlöken, chilin och paprikan. Skölj de svarta bönorna och kidneybönorna tills vattnet blir klart. Mät upp de torra kryddorna. Häll valfri mängd olja i grytan och börja bryna löken, när löken är nästan transparent tillsätter du vitlöken och den veganska färsen. Tillsätt de torra kryddorna, den hackade chilin och låt dem brynas, var försiktig så du inte bränner dem. Efter mindre än en minut tillsätter du de krossade tomaterna, 2 dl vatten, grönsaksfond och de olika såserna. Rör ner kidneybönor, de svarta bönorna och låt det koka i ca 1-1.5 timmar under lock. Viktigt att röra om med jämna mellanrum och tillsätta vatten vid behov så det inte blir för torrt och riskerar brännas vid. Tillsätt de vita bönorna i tomatsås, paprikan och låt de koka i 15-20 min.\n" +
                "Smaka av med salt och peppar. Kan serveras med baguette eller kokt ris.  \n";
        Image veganskChili = new Image("/view/veganskChili.jpg");
        ImageView veganskChiliImage = new ImageView(veganskChili);

        recipes.add(new Recipe("Vegansk chili", veganskChiliInstructions, veganskChiliImage, veganskChiliIngredients, "Vegansk chili", new ArrayList<>(Arrays.asList(FoodCategory.Vegetarian, FoodCategory.Vegan))));
        ArrayList<Ingredient> chiliFläskIngredients = new ArrayList<>();
        ArrayList<FoodCategory> chiliFläskCategory = new ArrayList<>();
        chiliFläskCategory.add(FoodCategory.Gris);
        String chiliFläskInstructions;
        chiliFläskIngredients.add(new Ingredient("Chili", 0.5, 500, Measurement.G));
        chiliFläskIngredients.add(new Ingredient("Fläsk färs", 0.5, 2.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Gul lök", 0.5, 3.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Gul lök", 0.5, 1.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Barbecuesås", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Barbecuesås", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Paprika ", 0.5, 1.0, Measurement.ST));
        chiliFläskIngredients.add(new Ingredient("Krossade tomater", 0.5, 1.0, Measurement.Burk));
        chiliFläskIngredients.add(new Ingredient("Svarta bönor", 0.5, 1.0, Measurement.Burk));
        chiliFläskIngredients.add(new Ingredient("Kidneybönor", 0.5, 1.0, Measurement.Burk));
        chiliFläskIngredients.add(new Ingredient("Vita Bönor i tomatsås", 0.5, 2.0, Measurement.Burk));
        chiliFläskIngredients.add(new Ingredient("Cayennepeppar", 0.5, 1.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Cayennepeppar", 0.5, 1.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Chilipeppar", 0.5, 1.0, Measurement.TSK));
        chiliFläskIngredients.add(new Ingredient("Paprika", 0.5, 2.0, Measurement.TSK));
        chiliFläskIngredients.add(new Ingredient("Smoked paprika", 0.5, 1.0, Measurement.TSK));
        chiliFläskIngredients.add(new Ingredient("Kummin", 0.5, 0.5, Measurement.TSK));
        chiliFläskIngredients.add(new Ingredient("Chilisås", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Grönsaksfond", 0.5, 3.0, Measurement.MSK));
        chiliFläskIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliFläskIngredients.add(new Ingredient("Salt", 0.5, 0.0, Measurement.KRM));
        chiliFläskIngredients.add(new Ingredient("Peppar", 0.5, 0.0, Measurement.KRM));

        chiliFläskInstructions = "Instruktioner\n" +
                "Förbered alla ingredienser innan du börjar tillaga grytan. Hacka löken, vitlöken, chilin och paprikan. Skölj de svarta bönorna och kidneybönorna tills vattnet blir klart. Mät upp de torra kryddorna. Häll valfri mängd olja i grytan och börja bryna fläskfärsen och löken, när löken är nästan transparent tillsätter du vitlöken. Tillsätt de torra kryddorna, den hackade chilin och låt dem brynas, var försiktig så du inte bränner dem. Efter mindre än en minut tillsätter du de krossade tomaterna, 2 dl vatten, grönsaksfond och de olika såserna. Rör ner kidneybönor, de svarta bönorna och låt det koka i ca 1-1.5 timmar under lock. Viktigt att röra om med jämna mellanrum och tillsätta vatten vid behov så det inte blir för torrt och riskerar brännas vid. Tillsätt de vita bönorna i tomatsås, paprikan och låt de koka i 15-20 min.\n" +
                "Smaka av med salt och peppar. Kan serveras med baguette eller kokt ris.  \n";
        Image chiliFläsk = new Image("/view/veganskChili.jpg");
        ImageView chiliFläskImage = new ImageView(chiliFläsk);

        recipes.add(new Recipe("Heidi", chiliFläskInstructions, chiliFläskImage, chiliFläskIngredients, "Chili Con Carne", new ArrayList<>(Arrays.asList(FoodCategory.Gris))));
        ArrayList<Ingredient> chiliNötIngredients = new ArrayList<>();
        ArrayList<FoodCategory> chiliNötCategory = new ArrayList<>();
        veganskChiliCategory.add(FoodCategory.Nöt);

        String chiliNötInstructions;
        chiliNötIngredients.add(new Ingredient("Chili", 0.5, 500, Measurement.G));
        chiliNötIngredients.add(new Ingredient("Vegansk färs", 0.5, 2.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Gul lök", 0.5, 3.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Gul lök", 0.5, 1.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Vitlök", 0.5, 1.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Barbecuesås", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Barbecuesås", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Paprika ", 0.5, 1.0, Measurement.ST));
        chiliNötIngredients.add(new Ingredient("Krossade tomater", 0.5, 1.0, Measurement.Burk));
        chiliNötIngredients.add(new Ingredient("Svarta bönor", 0.5, 1.0, Measurement.Burk));
        chiliNötIngredients.add(new Ingredient("Kidneybönor", 0.5, 1.0, Measurement.Burk));
        chiliNötIngredients.add(new Ingredient("Vita Bönor i tomatsås", 0.5, 2.0, Measurement.Burk));
        chiliNötIngredients.add(new Ingredient("Cayennepeppar", 0.5, 1.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Cayennepeppar", 0.5, 1.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Tabasco sås", 0.5, 1.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Chilipeppar", 0.5, 1.0, Measurement.TSK));
        chiliNötIngredients.add(new Ingredient("Paprika", 0.5, 2.0, Measurement.TSK));
        chiliNötIngredients.add(new Ingredient("Smoked paprika", 0.5, 1.0, Measurement.TSK));
        chiliNötIngredients.add(new Ingredient("Kummin", 0.5, 0.5, Measurement.TSK));
        chiliNötIngredients.add(new Ingredient("Chilisås", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Grönsaksfond", 0.5, 3.0, Measurement.MSK));
        chiliNötIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Vatten", 0.5, 1.0, Measurement.DL));
        chiliNötIngredients.add(new Ingredient("Salt", 0.5, 0.0, Measurement.KRM));
        chiliNötIngredients.add(new Ingredient("Peppar", 0.5, 0.0, Measurement.KRM));

        chiliNötInstructions = "Instruktioner\n" +
                "Förbered alla ingredienser innan du börjar tillaga grytan. Hacka löken, vitlöken, chilin och paprikan. Skölj de svarta bönorna och kidneybönorna tills vattnet blir klart. Mät upp de torra kryddorna. Häll valfri mängd olja i grytan och börja bryna löken, när löken är nästan transparent tillsätter du vitlöken och den veganska färsen. Tillsätt de torra kryddorna, den hackade chilin och låt dem brynas, var försiktig så du inte bränner dem. Efter mindre än en minut tillsätter du de krossade tomaterna, 2 dl vatten, grönsaksfond och de olika såserna. Rör ner kidneybönor, de svarta bönorna och låt det koka i ca 1-1.5 timmar under lock. Viktigt att röra om med jämna mellanrum och tillsätta vatten vid behov så det inte blir för torrt och riskerar brännas vid. Tillsätt de vita bönorna i tomatsås, paprikan och låt de koka i 15-20 min.\n" +
                "Smaka av med salt och peppar. Kan serveras med baguette eller kokt ris.  \n";
        Image chiliNöt = new Image("/view/veganskChili.jpg");
        ImageView chiliNötImage = new ImageView(chiliNöt);

        recipes.add(new Recipe("Heidi", chiliNötInstructions, chiliNötImage, chiliNötIngredients, "Chili Con Carne", new ArrayList<>(Arrays.asList(FoodCategory.Nöt))));

        mainGUIController.updateListOfRecipes(recipes);
    }

    public ArrayList<FoodCategory> getRecipeFilters() {
        return recipeFilters;
    }

    public void addFilter(FoodCategory categoryToAdd) {
        recipeFilters.add(categoryToAdd);
    }

    public void removeFilter(FoodCategory foodCategory) {
        recipeFilters.remove(foodCategory);
    }

    public ArrayList<String> gatherMeasurements() {
        ArrayList<Measurement> measurements = new ArrayList<>(Arrays.asList(Measurement.values()));
        ArrayList<String> measurementsToReturn = new ArrayList<>();

        for (Measurement measurment : measurements) {
            measurementsToReturn.add(measurment.name());
        }
        return measurementsToReturn;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
}