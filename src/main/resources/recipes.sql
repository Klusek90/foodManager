INSERT INTO recipe (name) VALUES
('Spaghetti Bolognese'),
    ('Penne Alfredo'),
    ('Classic Carbonara'),
    ('Margherita Pizza'),
    ('Chicken Parmesan'),
    ('Lasagna'),
    ('Fettuccine Alfredo'),
    ('Ravioli with Marinara'),
    ('Eggplant Parmigiana'),
    ('Pesto Pasta'),
    ('Meatball Sub'),
    ('Baked Ziti'),
    ('Caprese Salad'),
    ('Bruschetta'),
    ('Tiramisu'),
    ('Cannoli'),
    ('Seafood Risotto'),
    ('Minestrone Soup'),
    ('Gnocchi with Pesto'),
    ('Prosciutto e Melone');


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
