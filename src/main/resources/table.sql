CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          imageUrl VARCHAR(255)
);

ALTER TABLE product
ALTER COLUMN description TYPE VARCHAR(500);



-- Добавление категорий
INSERT INTO category (name)
VALUES
    ('Электроника'),
    ('Одежда'),
    ('Бытовая техника'),
    ('Книги');

-- Добавление товаров
-- Для каждой категории добавим по два товара
-- Электроника
INSERT INTO product (name, description, price, stock_quantity, category_id)
VALUES
    ('Смартфон', 'Мощный смартфон с большим экраном', 599.99, 100, (SELECT id FROM category WHERE name = 'Электроника')),
    ('Ноутбук', 'Легкий и компактный ноутбук для работы и учебы', 899.99, 50, (SELECT id FROM category WHERE name = 'Электроника'));

-- Одежда
INSERT INTO product (name, description, price, stock_quantity, category_id)
VALUES
    ('Футболка', 'Удобная футболка с принтом', 29.99, 200, (SELECT id FROM category WHERE name = 'Одежда')),
    ('Джинсы', 'Качественные джинсы на каждый день', 49.99, 150, (SELECT id FROM category WHERE name = 'Одежда'));

-- Бытовая техника
INSERT INTO product (name, description, price, stock_quantity, category_id)
VALUES
    ('Холодильник', 'Просторный холодильник с низким энергопотреблением', 799.99, 30, (SELECT id FROM category WHERE name = 'Бытовая техника')),
    ('Пылесос', 'Мощный пылесос для уборки дома', 149.99, 50, (SELECT id FROM category WHERE name = 'Бытовая техника'));

-- Книги
INSERT INTO product (name, description, price, stock_quantity, category_id)
VALUES
    ('Роман', 'Увлекательный роман о любви и приключениях', 19.99, 100, (SELECT id FROM category WHERE name = 'Книги')),
    ('Учебник', 'Полезный учебник по математике для студентов', 39.99, 80, (SELECT id FROM category WHERE name = 'Книги'));
