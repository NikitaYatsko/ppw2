CREATE TABLE ppw2.users (
                            id SERIAL PRIMARY KEY,
                            username VARCHAR(100) NOT NULL,
                            age INT NOT NULL,
                            email VARCHAR(150) UNIQUE NOT NULL
);



create table if not exists "ppw2".products(
    id SERIAL PRIMARY KEY ,
    title varchar(255) NOT NULL ,
    description text not null
);
CREATE TABLE ppw2.orders (
                             id SERIAL PRIMARY KEY,
                             quantity INT NOT NULL,
                             order_date DATE DEFAULT CURRENT_DATE,
                             user_id INT NOT NULL,
                             product_id INT NOT NULL,
                             CONSTRAINT fk_user
                                 FOREIGN KEY(user_id) REFERENCES ppw2.users(id)
                                     ON DELETE CASCADE,
                             CONSTRAINT fk_product
                                 FOREIGN KEY(product_id) REFERENCES ppw2.products(id)
                                     ON DELETE CASCADE
);
