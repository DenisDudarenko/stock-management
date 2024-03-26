CREATE TYPE measurement_units AS ENUM (
  'Units',
  'Liters',
  'Kilograms'
);

CREATE TYPE product_type AS ENUM (
  'Food',
  'Chemicals',
  'Clothing',
  'Electronics'
);

CREATE TYPE operation_type AS ENUM (
  'Delivery',
  'Issuance'
);

CREATE TABLE IF NOT EXISTS products (
   id SERIAL PRIMARY KEY,
   name VARCHAR(30) NOT NULL,
   type product_type NOT NULL,
   quantity NUMERIC(10, 2) NOT NULL CHECK (quantity >= 0),
   measurement measurement_units NOT NULL,
   lifetime TIMESTAMP NOT NULL,
   storage_place JSONB NOT NULL
);

CREATE TABLE IF NOT EXISTS suppliers_and_consumers (
   id SERIAL PRIMARY KEY,
   inn CHAR(12) NOT NULL UNIQUE,
   name VARCHAR(30) NOT NULL,
   contact_info JSONB NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery_and_issuance (
   id SERIAL PRIMARY KEY,
   owner_id INT NOT NULL,
   operation_time TIMESTAMP NOT NULL,
   operation_type operation_type NOT NULL,
   FOREIGN KEY (owner_id) REFERENCES suppliers_and_consumers(id)
);

CREATE TABLE IF NOT EXISTS delivery_and_issuance_items (
   id SERIAL PRIMARY KEY,
   delivery_and_issuance_id INT NOT NULL,
   product_id INT NOT NULL,
   quantity NUMERIC(10, 2) NOT NULL CHECK(quantity >= 0),
   FOREIGN KEY (delivery_and_issuance_id) REFERENCES delivery_and_issuance(id),
   FOREIGN KEY (product_id) REFERENCES products(id)
);

create function product_type_cast(varchar) returns product_type as $$
	select case $1
		when 'Food' then 'Food'::product_type
		when 'Chemicals' then 'Chemicals'::product_type
		when 'Electronics' then 'Electronics'::product_type
      when 'Clothing' then 'Clothing'::product_type
	end;
$$ language sql;

create cast (varchar AS product_type) with function product_type_cast(varchar) as assignment;

create function measurement_units_cast(varchar) returns measurement_units as $$
	select case $1
		when 'Units' then 'Units'::measurement_units
		when 'Liters' then 'Liters'::measurement_units
		when 'Kilograms' then 'Kilograms'::measurement_units
	end;
$$ language sql;

create cast (varchar AS measurement_units) with function measurement_units_cast(varchar) as assignment;

create function operation_type_cast(varchar) returns operation_type as $$
	select case $1
		when 'Delivery' then 'Delivery'::operation_type
		when 'Issuance' then 'Issuance'::operation_type
	end;
$$ language sql;

create cast (varchar AS operation_type) with function operation_type_cast(varchar) as assignment;
