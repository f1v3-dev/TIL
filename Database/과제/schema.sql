DROP DATABASE IF EXISTS DatamotionMall;

CREATE DATABASE IF NOT EXISTS DatamotionMall;

USE DatamotionMall;

CREATE TABLE Categories
(
    CategoryID   INT auto_increment,
    CategoryName varchar(50),

    CONSTRAINT pk_Categories PRIMARY KEY (CategoryID)
);

CREATE TABLE Products
(
    ProductID    INT auto_increment,
    CategoryID   INT,
    ModelNumber  nvarchar(10),
    ModelName    nvarchar(120),
    ProductImage nvarchar(30),
    UnitCost     decimal(15),
    Description  text,

    CONSTRAINT pk_Products PRIMARY KEY (ProductID),
    CONSTRAINT fk_Products_Categories FOREIGN KEY (CategoryID) REFERENCES Categories (CategoryID)
);


CREATE TABLE Customers
(
    CustomerID   int auto_increment,
    Name         varchar(10),
    EmailAddress varchar(100) UNIQUE,
    Password     varchar(12),

    CONSTRAINT pk_Customer PRIMARY KEY (CustomerID)
);

CREATE TABLE Reviews
(
    ReviewID   int auto_increment,
    ProductID  int,
    CustomerID int,
    Rating     int,
    Comments   text,

    CONSTRAINT pk_ReviewID PRIMARY KEY (ReviewID),
    CONSTRAINT fk_Review_Products FOREIGN KEY (ProductID) REFERENCES Products (ProductID),
    CONSTRAINT fk_Review_Customer FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)
);

CREATE TABLE Orders
(
    OrderID    int auto_increment,
    CustomerID int,
    OrderDate  Datetime,
    ShipDate   Datetime,

    CONSTRAINT pk_Orders PRIMARY KEY (OrderID),
    CONSTRAINT fk_Orders_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)
);

CREATE TABLE OrderDetails
(
    OrderID   int,
    ProductID int,
    Quantity  int,
    UnitCost  decimal(15),

    CONSTRAINT pk_OrderDetails PRIMARY KEY (OrderID, ProductID),
    CONSTRAINT fk_OrderDetails_Orders FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
    CONSTRAINT fk_OrderDetails_Products FOREIGN KEY (ProductID) REFERENCES Products (ProductID)
);

CREATE TABLE ShoppingCart
(
    RecordID     int auto_increment,
    CartID       nvarchar(150),
    Quantity     int,
    ProductID    int,
    DateCreated Datetime DEFAULT NOW(),

    CONSTRAINT pk_RecordID PRIMARY KEY (RecordID),
    CONSTRAINT fk_cart_ProductID FOREIGN KEY (ProductID) REFERENCES Products (ProductID)
);

INSERT INTO Categories(CategoryName)
VALUES ('Top'),
       ('Pants'),
       ('Shoes'),
       ('Outer'),
       ('Hat');

SELECT *
FROM Categories;

INSERT INTO Products VALUES(1, 1, 'DQ5762-104', 'Nike AS W NSW PHNX FLC OOS CREW', 'DQ5762-104.jpg', 95000, '강렬하고 포근한 감성으로 여러분의 플리스 컬렉션에 변화를 주세요. 볼륨감 있는 핏과 과감한 골지 디테일이 돋보이는 피닉스 플리스 스웻셔츠로, 결코 심심하지 않은 룩을 연출합니다.'),
                           (2, 1, 'DX1362-063', 'NIKE AS M NK SOLO SWSH FLC CRW', 'DX1362-063.jpg', 109000, '스우시의 힘과 단순함을 예찬합니다. 따뜻한 플리스 크루로 어깨, 가슴, 몸체의 공간이 넉넉해 편안하고 여유로우며 향수를 불러일으키는 스타일을 연출합니다. 신축성 있는 골지 오프닝이 움직이는 동안 크루를 제자리에 고정해주며 온기를 가둬 추운 날씨에도 따뜻함을 유지합니다.'),
                           (3, 1, 'FD9850-010', 'NIKE AS U ACG TF CREW FLC GX', 'FD9850-010.jpg', 139000, '넉넉한 핏에 매우 따뜻한 ACG 크루를 입고 베이스캠프나 뒷마당의 화롯가에서 친구들과 따뜻하게 별을 세어 보세요. 나이키 써마 핏 기술이 체열을 관리해 추운 날씨에도 따뜻함이 유지됩니다. 또한 충분히 여유롭게 제작되어 레이어링하기에 좋은 아이템이므로 날이 추워져도 몸을 따뜻하게 유지할 수 있습니다.');

SELECT * FROM Products;