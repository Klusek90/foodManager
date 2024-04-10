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


