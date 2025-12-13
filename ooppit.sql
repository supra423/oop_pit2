CREATE DATABASE Junkshop;
USE Junkshop;

DROP TABLE Material;

CREATE TABLE IF NOT EXISTS Material (
	materialId INT AUTO_INCREMENT PRIMARY KEY,
    materialName VARCHAR(50) NOT NULL,
    unitOfMeasure VARCHAR(5) NOT NULL,
    buyPrice DECIMAL(10, 2) NOT NULL,
	sellPrice DECIMAL(10, 2) NOT NULL,
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

INSERT INTO Material (materialName, unitOfMeasure, buyPrice, sellPrice, stockQuantity) VALUES
("copper wire", "kg", 200.00, 250.00, 100),
("plastic bottle", "kg", 20.00, 25.00, 100),
("steel can", "kg", 3.00, 3.50, 100),
("cardboard box", "kg", 2.50, 3.00, 100),
("glass bottle", "kg", 2.00, 3.00, 100);

SELECT SUM(totalAmount) as total FROM Transaction_ WHERE transactionType = "buy";
SELECT AVG(totalAmount) as average FROM Transaction_ WHERE transactionType = "buy";
 
DELETE FROM Material WHERE materialId = 6;