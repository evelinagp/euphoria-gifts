-- -- INSERT ROLES -------------------------------------------
INSERT INTO roles (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER');

-- -- INSERT USERS -------------------------------------------

INSERT INTO users (id, age, email, first_name, last_name, password, username)
VALUES (1, 20, 'admin@example.com', 'Admin', 'Adminov',
        '23b95ca352fe19b348e8f1b5a5a1cc1e23c54910ae9a89a35234f1e4791426e0b4af400a98cabb2e',
        'admin'),
       (2, 18, 'user@example.com', 'User', 'Userov',
        'faece043d5eac617582f4dd2cde7e2849606c5294c938d1eae7b4778018904b0eaab4846cff44d78',
        'user');

-- -- INSERT USERS_ROLES -------------------------------------------
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);

-- -- INSERT CATEGORIES -------------------------------------------
-- INSERT INTO categories (id, name)
-- VALUES

-- -- INSERT ADDRESSES -------------------------------------------
-- INSERT INTO addresses(id, city, postal_code, street, customer_id)
-- VALUES

INSERT INTO pictures (id, public_id, title, url)
VALUES (1, 'AshtraySceleton_sq5oum', 'AshtraySceleton',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338284/Home/Ashtrays/AshtraySceleton_sq5oum.jpg'),
       (2, 'AshtrayBlackDiamond_sht5fj', 'AshtrayBlackDiamond',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338284/Home/Ashtrays/AshtrayBlackDiamond_sht5fj.jpg'),
       (3, 'AshtrayWhiteDiamond_lagfpt', 'AshtrayWhiteDiamond',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338284/Home/Ashtrays/AshtrayWhiteDiamond_lagfpt.jpg'),
       (4, 'GoldenCandlestick_xqwmd0', 'GoldenCandlestick',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338295/Home/Candlesticks%20and%20Candles/GoldenCandlestick_xqwmd0.jpg'),
       (5, 'HomeSweetHomeCandle_vddzqg', 'HomeSweetHomeCandle',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338295/Home/Candlesticks%20and%20Candles/HomeSweetHomeCandle_vddzqg.jpg'),
       (6, 'RoseCandle_cikwtm', 'RoseCandle',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338295/Home/Candlesticks%20and%20Candles/RoseCandle_cikwtm.jpg'),
       (7, 'RoseCandlestick_yrnre2', 'Rose Candlestick',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338295/Home/Candlesticks%20and%20Candles/RoseCandlestick_yrnre2.jpg'),
       (8, 'SeaWorldCandlestick_wyy5cz', 'SeaWorldCandlestick',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338295/Home/Candlesticks%20and%20Candles/SeaWorldCandlestick_wyy5cz.jpg'),
       (9, 'SummerMorningCandle_gphbxw', 'SummerMorningCandle',
        'https://res.cloudinary.com/ddktqessk/image/upload/v1658338295/Home/Candlesticks%20and%20Candles/SummerMorningCandle_gphbxw.jpg');

INSERT INTO gifts (id, description, name, price, quantity, category_id, user_entity_id, picture_id)
VALUES (1, 'Decorative ashtray "Skeleton" - made of polyresin.', 'Ashtray Sceleton', 15, 0, 1, 1, 1),
       (2, 'Ashtray from the "Black Diamond" series - large, made of solid glass with an elegant shape.',
        'Ashtray Black Diamond', 65, 0, 1, 1, 2),
       (3, 'Ashtray from the "Diamond" series - large, made of solid glass with an elegant shape.',
        'Ashtray White Diamond', 65, 0, 1, 1, 3),
       (4,
        'Candlestick from the "Golden extravaganza" series, made of embossed glass with an original design. The set consists of a round candle holder suitable for a tea light and a small plate in the shape of a leaf.',
        'Golden Candlestick', 17, 0, 1, 1, 4),
       (5, 'The aroma of roses favors achieving harmony in the home. Contributes to home comfort.',
        'Home Sweet Home Candle', 4.70, 0, 1, 1, 5),
       (6, 'The three-layer candle with a light and delicate scent of rose favors the return of a good mood.',
        'Rose Candle', 7, 0, 1, 1, 6),
       (7, 'Rose candlestick, made of polyresin and spectacularly decorated with gold brocade.',
        'Rose Candlestick', 8, 0, 1, 1, 7),
       (8,
        'Decorative candlestick figurine from the Sea World series. Original candlestick in the shape of a seashell, a fish, a rudder and a starfish. Suitable decoration for both home and cafes, restaurants and hotels.',
        'Sea World Candlestick', 10, 0, 1, 1, 8),
       (9, 'The candle creates a feeling of romance and tranquility, brings the freshness of quiet summer mornings.',
        'Summer Morning Candle', 4.70, 0, 1, 1, 9);


INSERT INTO comments (is_approved, message, created_on, gift_entity_id, user_id)
values (true, 'Awesome gift', '2021-11-18 8:10:40', 1, 1),
       (true, 'Amazing gift', '2021-11-18 8:15:50', 1, 2);



