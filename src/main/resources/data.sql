-- Check if the users table is empty
SELECT COUNT(*) FROM users;
-- If there are no records in the users table, insert the initial user data
INSERT INTO users (username, password, role)
SELECT 'scorac', '$2a$10$zuZI1SDVzFZKGLLT0BkTseCp0x5NStl9L9YvLJyEZ4xursZSVifla', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users);

INSERT INTO product (name, life_length, type)
SELECT 'Cooked Penne', 5, 'Pasta' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cooked Spaghetti', 5, 'Pasta' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Bolognese Sauce', 4, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Carbonara Sauce', 3, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cooked Prosciutto', 7, 'Meat' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Marinara Sauce', 5, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Alfredo Sauce', 4, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Pesto Sauce', 7, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cooked Fusilli', 5, 'Pasta' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Roasted Garlic', 7, 'Condiment' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Sundried Tomatoes', 7, 'Condiment' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Grilled Vegetables', 5, 'Vegetable' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Sauteed Mushrooms', 4, 'Vegetable' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cooked Italian Sausage', 5, 'Meat' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Meatballs', 6, 'Meat' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cooked Chicken Breast', 4, 'Meat' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Ricotta Mixture', 6, 'Cheese' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Mozzarella Slices', 7, 'Cheese' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Parmesan Grated', 7, 'Cheese' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Bechamel Sauce', 3, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Amatriciana Sauce', 4, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Caramelized Onions', 7, 'Condiment' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Basil Pesto', 7, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Arrabbiata Sauce', 5, 'Sauce' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cooked Gnocchi', 5, 'Pasta' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Antipasto Mix', 7, 'Condiment' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Bruschetta Topping', 5, 'Condiment' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Anchovy Paste', 7, 'Condiment' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Tiramisu Base', 5, 'Dessert' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cannoli Filling', 5, 'Dessert' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Risotto Rice', 7, 'Grain' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Seafood Mix', 4, 'Seafood' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Vegetable Broth', 7, 'Broth' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Canned Beans', 7, 'Vegetable' WHERE NOT EXISTS (SELECT 1 FROM product) UNION ALL
SELECT 'Cantaloupe', 7, 'Fruit' WHERE NOT EXISTS (SELECT 1 FROM product);

INSERT INTO recipe (name, price)
SELECT 'Spaghetti Bolognese', 15.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Penne Alfredo', 14.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Classic Carbonara', 16.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Margherita Pizza', 12.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Chicken Parmesan', 17.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Lasagna', 18.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Fettuccine Alfredo', 14.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Ravioli with Marinara', 13.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Eggplant Parmigiana', 15.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Pesto Pasta', 14.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Meatball Sub', 11.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Baked Ziti', 16.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Caprese Salad', 10.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Bruschetta', 8.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Tiramisu', 9.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Cannoli', 8.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Seafood Risotto', 19.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Minestrone Soup', 11.5 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Gnocchi with Pesto', 14.0 WHERE NOT EXISTS (SELECT 1 FROM recipe) UNION ALL
SELECT 'Prosciutto e Melone', 12.5 WHERE NOT EXISTS (SELECT 1 FROM recipe);


INSERT INTO recipe_product (recipe_id, product_id, quantity)
SELECT 1, 2, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Spaghetti
SELECT 1, 3, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Bolognese Sauce
SELECT 1, 19, 20 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 20g Parmesan Grated

-- Penne Alfredo
SELECT 2, 1, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Penne
SELECT 2, 7, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Alfredo Sauce
SELECT 2, 15, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Cooked Chicken Breast

-- Classic Carbonara
SELECT 3, 2, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Spaghetti
SELECT 3, 4, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Carbonara Sauce
SELECT 3, 5, 50 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 50g Cooked Prosciutto

-- Margherita Pizza
SELECT 4, 21, 1 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 1 Pizza Base
SELECT 4, 6, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Marinara Sauce
SELECT 4, 18, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Mozzarella Slices
SELECT 4, 10, 30 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 30g Roasted Garlic

-- Chicken Parmesan
SELECT 5, 15, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Cooked Chicken Breast
SELECT 5, 6, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Marinara Sauce
SELECT 5, 18, 50 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 50g Mozzarella Slices

-- Lasagna
SELECT 6, 3, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Bolognese Sauce
SELECT 6, 17, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Ricotta Mixture
SELECT 6, 18, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Mozzarella Slices

-- Fettuccine Alfredo
SELECT 7, 8, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Fusilli SELECT substituting for Fettuccine)
SELECT 7, 7, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Alfredo Sauce

-- Ravioli with Marinara
SELECT 8, 25, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Gnocchi SELECT substitute for Ravioli, assuming pre-made)
SELECT 8, 6, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Marinara Sauce

-- Eggplant Parmigiana
SELECT 9, 14, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Grilled Vegetables SELECT assuming Eggplant included)
SELECT 9, 6, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Marinara Sauce
SELECT 9, 18, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Mozzarella Slices

-- Pesto Pasta
SELECT 10, 1, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Penne
SELECT 10, 8, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Pesto Sauce

-- Meatball Sub
SELECT 11, 15, 300 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 300g Cooked Italian Sausage SELECT substitute for meatballs)
SELECT 11, 6, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Marinara Sauce

-- Baked Ziti
SELECT 12, 1, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Penne SELECT substitute for Ziti)
SELECT 12, 6, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Marinara Sauce
SELECT 12, 18, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Mozzarella Slices

-- Caprese Salad
SELECT 13, 18, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Mozzarella Slices
SELECT 13, 27, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Bruschetta Topping SELECT substitute for fresh tomatoes and basil)

-- Bruschetta
SELECT 14, 27, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Bruschetta Topping

-- Tiramisu
SELECT 15, 29, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Tiramisu Base

-- Cannoli
SELECT 16, 30, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Cannoli Filling

-- Seafood Risotto
SELECT 17, 31, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Risotto Rice
SELECT 17, 32, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Seafood Mix

-- Minestrone Soup
SELECT 18, 33, 500 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 500ml Vegetable Broth
SELECT 18, 34, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Canned Beans

-- Gnocchi with Pesto
SELECT 19, 25, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 200g Cooked Gnocchi
SELECT 19, 8, 150 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 150g Pesto Sauce

-- Prosciutto e Melone
SELECT 20, 5, 100 WHERE NOT EXISTS (SELECT 1 FROM recipe_product)  UNION ALL -- 100g Cooked Prosciutto
SELECT 20, 35, 200 WHERE NOT EXISTS (SELECT 1 FROM recipe_product);

