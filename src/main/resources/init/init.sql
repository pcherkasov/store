CREATE SCHEMA "wonderStore";

SET SCHEMA "wonderStore";

CREATE TABLE "user"
(
  "id" int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  "name" VARCHAR(30) NOT NULL,
  "password" VARCHAR(30)
);

CREATE TABLE "order"
(
  "id" int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  "user_id" int NOT NULL,
  "total_price" int,
  FOREIGN KEY ("user_id") REFERENCES "user" ("id")
);
CREATE UNIQUE INDEX order_user_id_uindex ON "order" ("user_id");

CREATE TABLE "item"
(
  "id" int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  "title" VARCHAR(30) NOT NULL,
  "price" int DEFAULT 0 NOT NULL
);
CREATE UNIQUE INDEX item_title_uindex ON "item" ("title");

CREATE TABLE "order_item"
(
  "id" int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  "order_id" int,
  "item_id" int,
  FOREIGN KEY ("order_id") REFERENCES "order" ("id"),
  FOREIGN KEY ("item_id") REFERENCES "item" ("id")
);

INSERT INTO "item" ("title", "price")
VALUES ('Catapult', 300),
       ('Book', 20),
       ('Sword', 70),
       ('Bubble Gum', 2),
       ('Knife', 15),
       ('Phone', 200),
       ('Horse', 2000),
       ('Pen', 5),
       ('Apple', 670),
       ('Pineapple', 12),
       ('Pony', 600);