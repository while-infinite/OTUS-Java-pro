create table categories (
    id bigserial primary key,
    title varchar(255)
);

create table authors (
    id bigserial primary key,
    full_name varchar(255)
);

create table books (
    id bigserial primary key,
    title varchar(255),
    genre varchar(255),
    author_id bigint,
    foreign key (author_id) references authors (id)
);

create table reviews (
    id bigserial primary key,
    text varchar(255),
    author varchar(255),
    rating int,
    added date,
    book_id bigint,
    foreign key (book_id) references books (id)
);

create table books_details (
    id bigserial primary key,
    book_id bigint,
    description varchar(255),
    foreign key (book_id) references books (id)
);

insert into categories (title) values ('Категория 1'), ('Категория 2'), ('Категория 3');

insert into authors (full_name) values ('Толкиен'), ('Роулинг'), ('Сандерсон'), ('Азимов'), ('Рафаэль Саббатини'), ('Жюль Верн');

insert into books (title, author_id, genre) values
  ('Властелин колец: Братство кольца', 1, 'FANTASY'),
  ('Гарри Поттер и Философский камень', 2, 'FANTASY'),
  ('Рожденный туманом: Пепел и сталь', 3, 'FANTASY'),
  ('Рожденный туманом: Источник вознесения', 3, 'FANTASY'),
  ('Рожденный туманом: Герой веков', 3, 'FANTASY'),
  ('Архив Буресвета: Путь королей', 3, 'FANTASY'),
  ('Академия', 4, 'SCIFI'),
  ('Дети капитана Гранта', 5, 'ADVENTURE'),
  ('Путешествие к центру земли', 6, 'ADVENTURE'),
  ('Вокруг света за 80 дней', 6, 'ADVENTURE'),
  ('20 000 лье под водой', 6, 'ADVENTURE'),
  ('Плавучий остров', 6, 'ADVENTURE'),
  ('Плавайющий город', 6, 'ADVENTURE');


insert into books_details (book_id, description) values
  (1, 'Книга про Властелина колец'),
  (2, 'Книга про Гарри Поттера'),
  (3, 'Книга про Рожденного туманом'),
  (4, 'Книга про Рожденного туманом'),
  (5, 'Книга про Рожденного туманом'),
  (6, 'Книга про Архив Буресвета'),
  (7, 'Книга про Академию. Основная трилогия'),
  (8, 'Дети капитана Гранта'),
  (9, 'Путешествие к центру земли'),
  (10, 'Вокруг света за 80 дней'),
  (11, '20 000 лье под водой'),
  (12, 'Плавучий остров'),
  (13, 'Плавайющий город');

insert into reviews (text, author, rating, added, book_id) values
  ('', 'Alex', 6, '2023-12-01', 2),
  ('', 'Tom', 7, '2023-12-01', 3),
  ('', 'Sam', 8, '2023-12-01', 1),
  ('', 'Kilian', 9, '2023-12-01', 1),
  ('', 'Kris', 5, '2023-12-01', 4),
  ('', 'Anton', 7, '2023-12-01', 4),
  ('', 'Igor', 9, '2023-12-01', 5),
  ('', 'James', 9, '2023-12-01', 5),
  ('', 'Anna', 9, '2023-12-01', 5),
  ('', 'Lena', 3, '2023-12-01', 7),
  ('', 'Karl', 6, '2023-10-01', 11),
  ('', 'Bill', 4, '2023-12-01', 10),
  ('', 'Bobby', 6, '2023-12-01', 6),
  ('', 'Garry', 3, '2023-12-01', 6),
  ('', 'Ron', 2, '2023-12-01', 1);
