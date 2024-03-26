INSERT INTO "products" ("id", "name", "type", "quantity", "measurement", "lifetime", "storage_place")
VALUES 
    (1, 'Яблоко', 'Food', 50, 'Kilograms', INTERVAL '7 day' * RANDOM() + CURRENT_DATE, '{"roomNumber": 1, "shelfNumber": 2}'),
    (2, 'Отбеливатель', 'Chemicals', 20, 'Liters',  INTERVAL '1 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 2, "shelfNumber": 3}'),
    (3, 'Футболка', 'Clothing', 30, 'Units', INTERVAL '4 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 3, "shelfNumber": 4}'),
    (4, 'Ноутбук', 'Electronics', 10, 'Units', INTERVAL '10 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 4, "shelfNumber": 5}'),
    (5, 'Банан', 'Food', 40, 'Kilograms', INTERVAL '5 day' * RANDOM() + CURRENT_DATE, '{"roomNumber": 5, "shelfNumber": 6}'),
    (6, 'Моющее средство', 'Chemicals', 15, 'Liters', INTERVAL '1 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 6, "shelfNumber": 7}'),
    (7, 'Джинсы', 'Clothing', 25, 'Units', INTERVAL '10 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 7, "shelfNumber": 8}'),
    (8, 'Смартфон', 'Electronics', 12, 'Units', INTERVAL '7 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 8, "shelfNumber": 9}'),
    (9, 'Апельсин', 'Food', 35, 'Kilograms', INTERVAL '10 day' * RANDOM() + CURRENT_DATE, '{"roomNumber": 9, "shelfNumber": 10}'),
    (10, 'Мыло', 'Chemicals', 18, 'Liters', INTERVAL '1 year' * RANDOM() + CURRENT_DATE, '{"roomNumber": 10, "shelfNumber": 11}');


INSERT INTO "suppliers_and_consumers" ("id", "inn", "name", "contact_info")
VALUES 
    (1, '123456789012', 'ООО "РосАвто"', '{"email": "info@rosavto.ru", "phone": "+7(495)123-45-67", "address": "Москва, улица Ленина, дом 1"}'),
    (2, '234567890123', 'ЗАО "Сибирский Лес"', '{"email": "sibirles@mail.ru", "phone": "+7(383)234-56-78", "address": "Новосибирск, улица Кирова, дом 2"}'),
    (3, '345678901234', 'ОАО "УралПром"', '{"email": "info@uralprom.com", "phone": "+7(343)345-67-89", "address": "Екатеринбург, улица Гагарина, дом 3"}'),
    (4, '456789012345', 'ИП "ВолгаСтрой"', '{"email": "volgastroi@gmail.com", "phone": "+7(844)456-78-90", "address": "Волгоград, проспект Ленина, дом 4"}'),
    (5, '567890123456', 'ООО "Поволжский Авто"', '{"email": "info@povolzhavto.ru", "phone": "+78552)567-89-01", "address": "Казань, улица Баумана, дом 5"}'),
    (6, '678901234567', 'ЗАО "Северный Транс"', '{"email": "severtrans@inbox.ru", "phone": "+7(812)678-90-12", "address": "Архангельск, улица Советская, дом 6"}');


INSERT INTO "delivery_and_issuance" ("id", "owner_id", "operation_time", "operation_type")
VALUES 
    (1, 1, CURRENT_TIMESTAMP - (INTERVAL '1 year' * RANDOM()), 'Issuance'),
    (2, 2, CURRENT_TIMESTAMP - (INTERVAL '1 year' * RANDOM()), 'Delivery'),
    (3, 5, CURRENT_TIMESTAMP - (INTERVAL '1 year' * RANDOM()), 'Delivery'),
    (4, 3, CURRENT_TIMESTAMP - (INTERVAL '1 year' * RANDOM()), 'Issuance');

INSERT INTO "delivery_and_issuance_items" ("id", "delivery_and_issuance_id", "product_id", "quantity")
VALUES 
    (1, 2, 1, 5),
    (2, 1, 2, 2.5),
    (3, 2, 3, 3),
    (4, 1, 3, 5),
    (5, 3, 5, 4),
    (6, 4, 6, 2),
    (7, 4, 7, 5);
