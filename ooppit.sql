CREATE DATABASE Junkshop;
USE Junkshop;

CREATE TABLE IF NOT EXISTS Material (
	materialId INT AUTO_INCREMENT PRIMARY KEY,
    materialName VARCHAR(50) NOT NULL,
    unitOfMeasure VARCHAR(5) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stockQuantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Transaction_ ( # turns out transaction is already a keyword in mysql lol
	transactionId INT AUTO_INCREMENT PRIMARY KEY,
    transactionDate DATE NOT NULL,
    totalAmount DECIMAL(10, 2) NOT NULL,
    transactionType VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS TransactionItem (
	transactionItemId INT AUTO_INCREMENT PRIMARY KEY,
    transactionId INT NOT NULL,
    materialId INT NOT NULL,
    quantity INT NOT NULL,
    subTotal DECIMAL(10, 2)
);

INSERT INTO Material (materialName, unitOfMeasure, price, stockQuantity) VALUES
("copper wire", "kg", 200.00, 100),
("plastic bottle", "kg", 20.00, 100),
("steel can", "kg", 3.00, 100),
("cardboard box", "kg", 2.50, 100),
("glass bottle", "kg", 2.00, 100);

INSERT INTO Material (materialName, unitOfMeasure, price, stockQuantity) VALUES
("dummy material", "kg", 200.00, 100);

INSERT INTO Transaction_ (transactionDate, totalAmount, transactionType) VALUES
("2025-12-08", 3004, "buy"),
("2025-12-07", 4500, "sell"),
("2025-12-06", 1010, "buy"),
("2025-12-05", 6000, "sell"),
("2025-12-04", 1300, "buy"),
("2025-12-03", 1790, "sell"),
("2025-12-02", 9100, "buy"),
("2025-12-01", 2356, "sell"),
("2025-11-08", 3000, "buy"),
("2025-11-07", 4000, "sell"),
("2025-11-06", 5000, "buy"),
("2025-11-05", 123, "sell");

SELECT SUM(totalAmount) as total FROM Transaction_ WHERE transactionType = "buy";
SELECT AVG(totalAmount) as average FROM Transaction_ WHERE transactionType = "buy";
 
DELETE FROM Material WHERE materialId = 6;