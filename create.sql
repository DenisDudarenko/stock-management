CREATE TYPE "MeasurementUnits" AS ENUM (
  'units',
  'liters',
  'kilograms'
);

CREATE TYPE "ProductType" AS ENUM (
  'Food',
  'Chemicals',
  'Clothing',
  'Electronics'
);

CREATE TYPE "OperationType" AS ENUM (
  'Delivery',
  'Issuance'
);

CREATE TABLE IF NOT EXISTS "Products" (
   "Id" INT PRIMARY KEY,
   "Name" VARCHAR(30) NOT NULL,
   "ProductType" "ProductType" NOT NULL,
   "Quantity" NUMERIC(10, 2) NOT NULL CHECK ("Quantity" >= 0),
   "Measurement" "MeasurementUnits" NOT NULL,
   "LifeTime" TIMESTAMP NOT NULL,
   "StoragePlace" JSONB NOT NULL
);

CREATE TABLE IF NOT EXISTS "SuppliersAndConsumers" (
   "Id" INT PRIMARY KEY,
   "Inn" CHAR(12) NOT NULL UNIQUE,
   "Name" VARCHAR(30) NOT NULL,
   "ContactInfo" JSONB NOT NULL
);

CREATE TABLE IF NOT EXISTS "DeliveryAndIssuance" (
   "Id" INT PRIMARY KEY,
   "OwnerId" INT NOT NULL,
   "OperationTime" TIMESTAMP NOT NULL,
   "OperationType" "OperationType" NOT NULL,
   FOREIGN KEY ("OwnerId") REFERENCES "SuppliersAndConsumers"("Id")
);

CREATE TABLE IF NOT EXISTS "DeliveryAndIssuanceItems" (
   "Id" INT PRIMARY KEY,
   "DeliveryAndIssuanceId" INT NOT NULL,
   "ProductId" INT NOT NULL,
   "Quantity" NUMERIC(10, 2) NOT NULL CHECK("Quantity" >= 0),
   FOREIGN KEY ("DeliveryAndIssuanceId") REFERENCES "DeliveryAndIssuance"("Id"),
   FOREIGN KEY ("ProductId") REFERENCES "Products"("Id")
);