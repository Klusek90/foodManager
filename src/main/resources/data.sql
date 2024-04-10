INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$zuZI1SDVzFZKGLLT0BkTseCp0x5NStl9L9YvLJyEZ4xursZSVifla', 'ADMIN');

INSERT INTO product (name, life_length, type) VALUES
                  ('Cooked Penne', 5, 'Pasta'),
                  ('Cooked Spaghetti', 5, 'Pasta'),
                  ('Bolognese Sauce', 4, 'Sauce'),
                  ('Carbonara Sauce', 3, 'Sauce'),
                  ('Cooked Prosciutto', 7, 'Meat'),
                  ('Marinara Sauce', 5, 'Sauce'),
                  ('Alfredo Sauce', 4, 'Sauce'),
                  ('Pesto Sauce', 7, 'Sauce'),
                  ('Cooked Fusilli', 5, 'Pasta'),
                  ('Roasted Garlic', 7, 'Condiment'),
                  ('Sundried Tomatoes', 7, 'Condiment'),
                  ('Grilled Vegetables', 5, 'Vegetable'),
                  ('Sauteed Mushrooms', 4, 'Vegetable'),
                  ('Cooked Italian Sausage', 5, 'Meat'),
                  ('Meatballs', 6, 'Meat'),
                  ('Cooked Chicken Breast', 4, 'Meat'),
                  ('Ricotta Mixture', 6, 'Cheese'),
                  ('Mozzarella Slices', 7, 'Cheese'),
                  ('Parmesan Grated', 7, 'Cheese'),
                  ('Bechamel Sauce', 3, 'Sauce'),
                  ('Amatriciana Sauce', 4, 'Sauce'),
                  ('Caramelized Onions', 7, 'Condiment'),
                  ('Basil Pesto', 7, 'Sauce'),
                  ('Arrabbiata Sauce', 5, 'Sauce'),
                  ('Cooked Gnocchi', 5, 'Pasta'),
                  ('Antipasto Mix', 7, 'Condiment'),
                  ('Bruschetta Topping', 5, 'Condiment'),
                  ('Anchovy Paste', 7, 'Condiment'),
                  ('Tiramisu Base', 5, 'Dessert'),
                  ('Cannoli Filling', 5, 'Dessert'),
                  -- Additional products for recipes beyond the initial 30
                  ('Risotto Rice', 7, 'Grain'), -- ID 31
                  ('Seafood Mix', 4, 'Seafood'), -- ID 32
                  ('Vegetable Broth', 7, 'Broth'), -- ID 33
                  ('Canned Beans', 7, 'Vegetable'), -- ID 34
                  ('Cantaloupe', 7, 'Fruit'); -- ID 35

INSERT INTO recipe (name, price) VALUES
                 ('Spaghetti Bolognese', 15.0),
                 ('Penne Alfredo', 14.5),
                 ('Classic Carbonara', 16.0),
                 ('Margherita Pizza', 12.0),
                 ('Chicken Parmesan', 17.5),
                 ('Lasagna', 18.0),
                 ('Fettuccine Alfredo', 14.5),
                 ('Ravioli with Marinara', 13.0),
                 ('Eggplant Parmigiana', 15.5),
                 ('Pesto Pasta', 14.0),
                 ('Meatball Sub', 11.0),
                 ('Baked Ziti', 16.5),
                 ('Caprese Salad', 10.0),
                 ('Bruschetta', 8.0),
                 ('Tiramisu', 9.0),
                 ('Cannoli', 8.5),
                 ('Seafood Risotto', 19.0),
                 ('Minestrone Soup', 11.5),
                 ('Gnocchi with Pesto', 14.0),
                 ('Prosciutto e Melone', 12.5);



-- Spaghetti Bolognese
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (1, 2, 200), -- 200g Cooked Spaghetti
                                                                 (1, 3, 150), -- 150g Bolognese Sauce
                                                                 (1, 19, 20); -- 20g Parmesan Grated

-- Penne Alfredo
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (2, 1, 200), -- 200g Cooked Penne
                                                                 (2, 7, 150), -- 150g Alfredo Sauce
                                                                 (2, 15, 100); -- 100g Cooked Chicken Breast

-- Classic Carbonara
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (3, 2, 200), -- 200g Cooked Spaghetti
                                                                 (3, 4, 150), -- 150g Carbonara Sauce
                                                                 (3, 5, 50); -- 50g Cooked Prosciutto

-- Margherita Pizza
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (4, 21, 1), -- 1 Pizza Base
                                                                 (4, 6, 100), -- 100g Marinara Sauce
                                                                 (4, 18, 150), -- 150g Mozzarella Slices
                                                                 (4, 10, 30); -- 30g Roasted Garlic

-- Chicken Parmesan
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (5, 15, 150), -- 150g Cooked Chicken Breast
                                                                 (5, 6, 100), -- 100g Marinara Sauce
                                                                 (5, 18, 50); -- 50g Mozzarella Slices

-- Lasagna
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (6, 3, 200), -- 200g Bolognese Sauce
                                                                 (6, 17, 100), -- 100g Ricotta Mixture
                                                                 (6, 18, 100); -- 100g Mozzarella Slices

-- Fettuccine Alfredo
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (7, 8, 200), -- 200g Cooked Fusilli (substituting for Fettuccine)
                                                                 (7, 7, 150); -- 150g Alfredo Sauce

-- Ravioli with Marinara
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (8, 25, 200), -- 200g Cooked Gnocchi (substitute for Ravioli, assuming pre-made)
                                                                 (8, 6, 150); -- 150g Marinara Sauce

-- Eggplant Parmigiana
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (9, 14, 200), -- 200g Grilled Vegetables (assuming Eggplant included)
                                                                 (9, 6, 150), -- 150g Marinara Sauce
                                                                 (9, 18, 100); -- 100g Mozzarella Slices

-- Pesto Pasta
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (10, 1, 200), -- 200g Cooked Penne
                                                                 (10, 8, 150); -- 150g Pesto Sauce

-- Meatball Sub
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (11, 15, 300), -- 300g Cooked Italian Sausage (substitute for meatballs)
                                                                 (11, 6, 100); -- 100g Marinara Sauce

-- Baked Ziti
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (12, 1, 200), -- 200g Cooked Penne (substitute for Ziti)
                                                                 (12, 6, 150), -- 150g Marinara Sauce
                                                                 (12, 18, 100); -- 100g Mozzarella Slices

-- Caprese Salad
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (13, 18, 150), -- 150g Mozzarella Slices
                                                                 (13, 27, 100); -- 100g Bruschetta Topping (substitute for fresh tomatoes and basil)

-- Bruschetta
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
    (14, 27, 150); -- 150g Bruschetta Topping

-- Tiramisu
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
    (15, 29, 200); -- 200g Tiramisu Base

-- Cannoli
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
    (16, 30, 150); -- 150g Cannoli Filling

-- Seafood Risotto
-- Assuming 'Risotto Rice' = 31, 'Seafood Mix' = 32
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (17, 31, 200), -- 200g Risotto Rice
                                                                 (17, 32, 150); -- 150g Seafood Mix

-- Minestrone Soup
-- Assuming 'Vegetable Broth' = 33, 'Canned Beans' = 34
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (18, 33, 500), -- 500ml Vegetable Broth
                                                                 (18, 34, 150); -- 150g Canned Beans

-- Gnocchi with Pesto
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (19, 25, 200), -- 200g Cooked Gnocchi
                                                                 (19, 8, 150); -- 150g Pesto Sauce

-- Prosciutto e Melone
-- Assuming 'Cantaloupe' = 35
INSERT INTO recipe_product (recipe_id, product_id, quantity) VALUES
                                                                 (20, 5, 100), -- 100g Cooked Prosciutto
                                                                 (20, 35, 200); -- 200g Cantaloupe (substitute for fresh melon)

