db = db.getSiblingDB('recipes');

db.createCollection('recipes');

db.recipes.insertMany([
      {
        "name": "Hearty Vegetable Stew",
        "isVegetarian": true,
        "servings": 4,
        "uniqueSignature": "HeartyVegetableStew-4-Veg",
        "ingredients": [
          {
            "name": "Carrots",
            "unit": "pcs",
            "quantity": "3"
          },
          {
            "name": "Potatoes",
            "unit": "pcs",
            "quantity": "3"
          },
          {
            "name": "Onions",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Celery Stalks",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Diced Tomatoes",
            "unit": "can",
            "quantity": "1"
          },
          {
            "name": "Vegetable Broth",
            "unit": "cups",
            "quantity": "4"
          },
          {
            "name": "Bay Leaves",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Thyme",
            "unit": "tsp",
            "quantity": "1"
          }
        ],
        "instructions": "Chop all vegetables into bite-sized pieces. In a large pot, sauté onions and celery until soft. Add the rest of the vegetables, tomatoes, broth, and herbs. Bring to a boil, then simmer for 30 minutes or until vegetables are tender.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Classic Margherita Pizza",
        "isVegetarian": true,
        "servings": 2,
        "uniqueSignature": "ClassicMargheritaPizza-2-Veg",
        "ingredients": [
          {
            "name": "Pizza Dough",
            "unit": "ball",
            "quantity": "1"
          },
          {
            "name": "Tomato Sauce",
            "unit": "cups",
            "quantity": "0.5"
          },
          {
            "name": "Fresh Mozzarella Cheese",
            "unit": "slices",
            "quantity": "6"
          },
          {
            "name": "Fresh Basil Leaves",
            "unit": "pcs",
            "quantity": "8"
          },
          {
            "name": "Olive Oil",
            "unit": "tbsp",
            "quantity": "2"
          }
        ],
        "instructions": "Preheat your oven to its highest setting. Roll out the pizza dough and place it on a baking tray. Spread the tomato sauce over the base, then add slices of mozzarella. Drizzle with olive oil and bake for 10-12 minutes. Garnish with basil leaves before serving.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Spaghetti Aglio e Olio",
        "isVegetarian": true,
        "servings": 2,
        "uniqueSignature": "SpaghettiAglioeOlio-2-Veg",
        "ingredients": [
          {
            "name": "Spaghetti",
            "unit": "grams",
            "quantity": "200"
          },
          {
            "name": "Olive Oil",
            "unit": "tbsp",
            "quantity": "4"
          },
          {
            "name": "Garlic",
            "unit": "cloves",
            "quantity": "4"
          },
          {
            "name": "Red Pepper Flakes",
            "unit": "tsp",
            "quantity": "1/2"
          },
          {
            "name": "Parsley",
            "unit": "tbsp",
            "quantity": "2"
          },
          {
            "name": "Salt",
            "unit": "tsp",
            "quantity": "to taste"
          }
        ],
        "instructions": "Cook spaghetti in salted boiling water until al dente. In a separate pan, heat olive oil over medium heat and add thinly sliced garlic and red pepper flakes. Drain pasta and add to the pan with oil, toss to coat. Garnish with parsley and serve.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Lentil Soup",
        "isVegetarian": true,
        "servings": 6,
        "uniqueSignature": "LentilSoup-6-Veg",
        "ingredients": [
          {
            "name": "Olive Oil",
            "unit": "tbsp",
            "quantity": "2"
          },
          {
            "name": "Onion",
            "unit": "pcs",
            "quantity": "1"
          },
          {
            "name": "Carrots",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Celery Stalks",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Garlic Cloves",
            "unit": "pcs",
            "quantity": "3"
          },
          {
            "name": "Tomato Paste",
            "unit": "tbsp",
            "quantity": "1"
          },
          {
            "name": "Cumin",
            "unit": "tsp",
            "quantity": "1"
          },
          {
            "name": "Lentils",
            "unit": "cups",
            "quantity": "1.5"
          },
          {
            "name": "Vegetable Broth",
            "unit": "liters",
            "quantity": "4"
          },
          {
            "name": "Spinach Leaves",
            "unit": "cups",
            "quantity": "2"
          }
        ],
        "instructions": "In a pot, heat oil over medium heat. Add chopped onions, carrots, celery, and garlic; cook until softened. Stir in tomato paste and cumin. Add lentils and broth; bring to a boil. Reduce heat and simmer until lentils are tender. Stir in spinach until wilted.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Banana Pancakes",
        "isVegetarian": true,
        "servings": 2,
        "uniqueSignature": "BananaPancakes-2-Veg",
        "ingredients": [
          {
            "name": "Bananas",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Eggs",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Baking Powder",
            "unit": "tsp",
            "quantity": "1/2"
          },
          {
            "name": "Salt",
            "unit": "pinch",
            "quantity": "1"
          },
          {
            "name": "Cinnamon",
            "unit": "tsp",
            "quantity": "1/2"
          }
        ],
        "instructions": "Mash bananas in a bowl, whisk in the eggs, baking powder, a pinch of salt, and cinnamon. Heat a non-stick skillet and pour in batter to form pancakes. Cook until bubbles appear, then flip and cook until golden brown.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Caesar Salad",
        "isVegetarian": false,
        "servings": 4,
        "uniqueSignature": "CaesarSalad-4-NonVeg",
        "ingredients": [
          {
            "name": "Romaine Lettuce",
            "unit": "head",
            "quantity": "1"
          },
          {
            "name": "Croutons",
            "unit": "cups",
            "quantity": "2"
          },
          {
            "name": "Parmesan Cheese",
            "unit": "tbsp",
            "quantity": "3"
          },
          {
            "name": "Caesar Dressing",
            "unit": "tbsp",
            "quantity": "6"
          },
          {
            "name": "Chicken Breast",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Anchovies",
            "unit": "pcs",
            "quantity": "4"
          }
        ],
        "instructions": "Grill chicken breasts until cooked and slice. Chop lettuce and combine in a bowl with croutons, shaved parmesan, and dressing. Top with chicken slices and anchovies.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Beef Stir Fry",
        "isVegetarian": false,
        "servings": 4,
        "uniqueSignature": "BeefStirFry-4-NonVeg",
        "ingredients": [
          {
            "name": "Beef Strips",
            "unit": "grams",
            "quantity": "500"
          },
          {
            "name": "Soy Sauce",
            "unit": "tbsp",
            "quantity": "3"
          },
          {
            "name": "Brown Sugar",
            "unit": "tbsp",
            "quantity": "1"
          },
          {
            "name": "Cornstarch",
            "unit": "tsp",
            "quantity": "1"
          },
          {
            "name": "Vegetable Oil",
            "unit": "tbsp",
            "quantity": "2"
          },
          {
            "name": "Mixed Vegetables",
            "unit": "cups",
            "quantity": "3"
          },
          {
            "name": "Garlic",
            "unit": "cloves",
            "quantity": "2"
          }
        ],
        "instructions": "Marinate beef in soy sauce, sugar, and cornstarch. Heat oil in a wok, stir-fry beef until browned. Add vegetables and garlic, cook until vegetables are tender-crisp.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Chicken Curry",
        "isVegetarian": false,
        "servings": 4,
        "uniqueSignature": "ChickenCurry-4-NonVeg",
        "ingredients": [
          {
            "name": "Chicken Thighs",
            "unit": "pcs",
            "quantity": "6"
          },
          {
            "name": "Onions",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Tomato Paste",
            "unit": "tbsp",
            "quantity": "2"
          },
          {
            "name": "Coconut Milk",
            "unit": "can",
            "quantity": "1"
          },
          {
            "name": "Curry Powder",
            "unit": "tbsp",
            "quantity": "1"
          },
          {
            "name": "Garlic",
            "unit": "cloves",
            "quantity": "4"
          },
          {
            "name": "Ginger",
            "unit": "tsp",
            "quantity": "1"
          }
        ],
        "instructions": "Sauté onions, garlic, and ginger until onions are translucent. Add curry powder and tomato paste, cook for a minute. Add chicken and coconut milk, simmer until chicken is cooked through.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Grilled Salmon",
        "isVegetarian": false,
        "servings": 2,
        "uniqueSignature": "GrilledSalmon-2-NonVeg",
        "ingredients": [
          {
            "name": "Salmon Fillets",
            "unit": "pcs",
            "quantity": "2"
          },
          {
            "name": "Lemon Juice",
            "unit": "tbsp",
            "quantity": "2"
          },
          {
            "name": "Olive Oil",
            "unit": "tbsp",
            "quantity": "1"
          },
          {
            "name": "Garlic",
            "unit": "cloves",
            "quantity": "2"
          },
          {
            "name": "Dill",
            "unit": "tsp",
            "quantity": "1"
          },
          {
            "name": "Salt",
            "unit": "tsp",
            "quantity": "1/2"
          },
          {
            "name": "Pepper",
            "unit": "tsp",
            "quantity": "1/2"
          }
        ],
        "instructions": "Marinate salmon with lemon juice, olive oil, crushed garlic, dill, salt, and pepper for at least 30 minutes. Grill over medium heat, skin-side down, until cooked to desired doneness.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      },
      {
        "name": "Avocado Toast",
        "isVegetarian": true,
        "servings": 1,
        "uniqueSignature": "AvocadoToast-1-Veg",
        "ingredients": [
          {
            "name": "Avocado",
            "unit": "pcs",
            "quantity": "1"
          },
          {
            "name": "Whole Grain Bread",
            "unit": "slices",
            "quantity": "2"
          },
          {
            "name": "Lemon Juice",
            "unit": "tsp",
            "quantity": "1"
          },
          {
            "name": "Red Pepper Flakes",
            "unit": "pinch",
            "quantity": "1"
          },
          {
            "name": "Salt",
            "unit": "pinch",
            "quantity": "1"
          },
          {
            "name": "Pepper",
            "unit": "pinch",
            "quantity": "1"
          }
        ],
        "instructions": "Toast bread slices to your liking. Mash avocado with lemon juice, salt, and pepper. Spread avocado mixture on toast and sprinkle with red pepper flakes.",
        "createdDate": new Date(),
        "updatedDate": new Date(),
        "version": 1
      }
    ]
);
