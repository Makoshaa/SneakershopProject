INSERT INTO t_permission (id, role)
VALUES (1, 'ROLE_CLIENT'),
       (2, 'ROLE_ADMIN');

INSERT INTO t_users(id,email, full_name, password)
VALUES (9, 'aldik@mail.ru', 'Алдияр Марат', '$2a$10$mhKtsVnKu4AOQ.Ues0sBk.YLRbBoe9V0AXM0yiOvPrv2.wPyCSZ1C'),
       (31, 'nurabil@mail.ru', 'Сейдахмет Нурабил', '$2a$10$n/Zf2a57Cn6xbv9xJYZrI.yk8ZjOhZk27PrqeNjq0NqXNbtXk1RmS'),
       (1, 'magzhan@gmail.com', 'Sakibekov Magzhan', '$2a$10$XbQsaAZie4mYNJCnJGgTcuP77mTAU2etc17qdyOx1f/K6xt2TH5lG'),
       (2, 'zhandos@mail.ru', 'Orazaly Zhandos', '$2a$10$.3M8s9CuVNwheLnuqne/XOpQ7JiXc7BN3HKxVFY4Q/dku8pVsV4Sq');

INSERT INTO t_users_permissions(user_id, permissions_id)
VALUES (1, 2),
       (2, 1),
       (9, 1),
       (31,1);

INSERT INTO t_sneakers (id, color, name, price)
VALUES
    (8, 'lightyellow', 'Air Jordan 1 Elevate High', 124),
    (9, 'red', 'Air Jordan 1 Low', 110),
    (3, 'black', 'Air Jordan 5 Retro', 150),
    (7, 'black', 'Air Jordan 13 Retro', 210),
    (28, 'white', 'Nike Dunk Low Retro', 110),
    (29, 'black', 'Nike Air Max Solo', 100),
    (30, 'lightblue', 'Nike Air VaporMax Plus', 210);


