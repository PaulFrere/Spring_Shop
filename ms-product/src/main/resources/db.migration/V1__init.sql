create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

insert into categories (title)
values ('First'),
       ('Second'),
       ('Third'),
       ('Fourth'),
       ('Fifth');


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigserial,
    cost        decimal(10, 2)
);
insert into products (title, cost, category_id)
values  ('Book', 250, 2),
        ('Notebook', 60000, 2),
        ('Phone', 50000, 3),
        ('Milk', 59, 4),
        ('Bread', 28, 5),
        ('Butter', 150.5, 2),
        ('Box', 15, 3),
        ('TV', 60000, 4),
        ('Knife', 55, 1),
        ('Glass', 30, 3),
        ('Cup', 70, 3),
        ('Spoon', 20, 4),
        ('Fork', 25, 4),
        ('Table', 5000, 2),
        ('Door', 4000, 3),
        ('Disk', 50, 2),
        ('Picture', 260, 4),
        ('Water', 40, 4),
        ('Apple', 60, 1),
        ('Headphones', 150, 2),
        ('Sugar', 10, 5),
        ('Salt', 50, 5),
        ('Batter', 300, 5),
        ('Chocolate', 100, 5),
        ('Cheese', 700, 5),
        ('Cake', 500, 5),
        ('Ice cream', 50, 5),
        ('Fish', 300, 5),
        ('Pasta', 100, 5),
        ('Toaster', 4000, 2),
        ('Vacuum cleaner', 7000, 2),
        ('Gum', 10, 5),
        ('Chicken', 350, 5),
        ('Beef', 350, 5),
        ('Pork', 350, 5),
        ('Lamp', 200, 4),
        ('Flashlight', 500, 4)
;
