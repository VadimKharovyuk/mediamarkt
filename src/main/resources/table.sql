CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          imageUrl VARCHAR(255)
);

ALTER TABLE product
ALTER COLUMN description TYPE VARCHAR(500);



-- Добавление категорий
INSERT INTO category (name) VALUES
                                  ('Мобильные телефоны'),
                                  ('Ноутбуки'),
                                  ('Бытовая техника'),
                                  ('Аксессуары'),
                                  ('Электроинструменты'),
                                  ('Светотехника'),
                                  ('Кабельная продукция'),
                                  ('Розетки и выключатели'),
                                  ('Электрические плиты и духовки'),
                                  ('Климатическая техника'),
                                  ('Умный дом'),
                                  ('Батареи и аккумуляторы');



