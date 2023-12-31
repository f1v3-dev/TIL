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


# 1. Category - 5개
INSERT INTO Categories(CategoryName)
VALUES ('Top'),
       ('Pants'),
       ('Shoes'),
       ('Outer'),
       ('Hat');

desc products;

# 2. Product Table에 튜플이 100개
## 2-1. Top 상품 20개 insert
INSERT INTO Products
VALUES (1, 1, 'DQ5762-104', 'Nike AS W NSW PHNX FLC OOS CREW', 'DQ5762-104.jpg', 95000,
        '강렬하고 포근한 감성으로 여러분의 플리스 컬렉션에 변화를 주세요. 볼륨감 있는 핏과 과감한 골지 디테일이 돋보이는 피닉스 플리스 스웻셔츠로, 결코 심심하지 않은 룩을 연출합니다.'),
       (2, 1, 'DX1362-063', 'NIKE AS M NK SOLO SWSH FLC CRW', 'DX1362-063.jpg', 109000,
        '스우시의 힘과 단순함을 예찬합니다. 따뜻한 플리스 크루로 어깨, 가슴, 몸체의 공간이 넉넉해 편안하고 여유로우며 향수를 불러일으키는 스타일을 연출합니다. 신축성 있는 골지 오프닝이 움직이는 동안 크루를 제자리에 고정해주며 온기를 가둬 추운 날씨에도 따뜻함을 유지합니다.'),
       (3, 1, 'FD9850-010', 'NIKE AS U ACG TF CREW FLC GX', 'FD9850-010.jpg', 139000,
        '넉넉한 핏에 매우 따뜻한 ACG 크루를 입고 베이스캠프나 뒷마당의 화롯가에서 친구들과 따뜻하게 별을 세어 보세요. 나이키 써마 핏 기술이 체열을 관리해 추운 날씨에도 따뜻함이 유지됩니다. 또한 충분히 여유롭게 제작되어 레이어링하기에 좋은 아이템이므로 날이 추워져도 몸을 따뜻하게 유지할 수 있습니다.'),
       (4, 1, '23D2F16BLK', 'DIME CLASSIC BFF CREWNECK', '23D2F16BLK.jpg', 179000, NULL),
       (5, 1, '23D2F16GRY', 'DIME CLASSIC BFF CREWNECK', '23D2F16GRY.jpg', 179000, NULL),
       (6, 1, '23D2F6WHT', 'DIME VOLCANIC CREWNECK', '23D2F6WHT.jpg', 195000, NULL),
       (7, 1, '23D2F6BLU', 'DIME VOLCANIC CREWNECK', '23D2F6BLU.jpg', 195000, NULL),
       (8, 1, 'DH3088-893', 'NIKE AS U ACG TF TUFF FLC PO HOODIE', 'DH3088-893.jpg', 139000,
        '아이슬란드의 경이로운 추운 환경 속을 거닐며 영감을 받은 나이키 디자인 팀은 쌀쌀한 날의 하이킹과 겨울 산책 시에 따뜻함과 발수 기능을 선사해 줄 플리스 안감 처리된 후디를 만들었습니다. 유기농 면과 재생 폴리에스터 섬유 혼방을 사용하여 75% 이상 지속 가능한 소재로 제작하였습니다.'),
       (9, 1, 'DH3088-013', 'NIKE AS U ACG TF TUFF FLC PO HOODIE', 'DH3088-013.jpg', 139000,
        '아이슬란드의 경이로운 추운 환경 속을 거닐며 영감을 받은 나이키 디자인 팀은 쌀쌀한 날의 하이킹과 겨울 산책 시에 따뜻함과 발수 기능을 선사해 줄 플리스 안감 처리된 후디를 만들었습니다. 유기농 면과 재생 폴리에스터 섬유 혼방을 사용하여 75% 이상 지속 가능한 소재로 제작하였습니다.'),
       (10, 1, 'SG2303JK12', 'SAN SAN GEAR COMPOUND FLEECE TOP', 'SG2303JK12.jpg', 149000, NULL),
       (11, 1, 'FUAR20000', 'ARIES PREMIUM TEMPLE SWEATSHIRT', 'FUAR20000.jpg', 287000,
        '가슴 부분에 스크린 프린트된 Aries Temple 로고가 있는 남녀공용 크로스 그레인 크루넥 스웨트셔츠입니다. 프리미엄의 묵직한 브러시백 저지 플리스 소재로 제작되었습니다. 양쪽에 스포티한 골지 패널 디테일이 있습니다.'),
       (12, 1, 'FUAR20001', 'ARIES REFLECTIVE COLUMN SWEATSHIRT', 'FUAR20001.jpg', 352000,
        '브러쉬백 저지 플리스 소재의 남녀공용 크루넥 스웨트셔츠입니다. 대형 반사 Aries temple 로고 가슴 프린트와 컬럼 슬리브 프린트가 있습니다.'),
       (13, 1, 'FUAR20334', 'ARIES REVERSE FLEECE TEMPLE SWEAT', 'FUAR20334.jpg', 312000,
        '브러쉬드 저지 플리스 소재의 남녀공용 크루넥 스웨트셔츠. 옆면이 브러시 처리되어 있고 가슴 부분에 Aries temple 로고가 스크린 인쇄되어 있습니다.'),
       (14, 1, 'DQ5808-247', 'NIKE AS W ACG TF TUFF FLC HOODIE', 'DQ5808-247.jpg', 139000,
        '천천히 여유를 즐길 때나 한계를 시험해 볼 때나 편안함은 가장 중요한 승부수입니다. 즐겨 입는 티셔츠와 같이 부드럽고 여유로운 후디를 입고 일상의 모험을 즐겨보세요.'),
       (15, 1, 'DQ5843-536', 'NIKE AS W ACG WLF TREE TOP MID', 'DQ5843-536.jpg', 155000,
        '문명에서 수 마일 떨어진 침낭에서 일어나거나, 이른 아침 러닝 준비를 위해 알람 시계를 끄고 일어날 때 이 편안한 레이어를 착용해 보세요. 편안한 풀오버 핏과 모크넥이 룩에 즉각적인 보온성을 더해주어 힘차게 하루를 시작할 수 있습니다. 매우 부드럽고 레이어링에 안성맞춤인 재생 소재를 사용해 모험의 난관을 이겨내기 좋은 플리스를 제작했습니다.'),
       (16, 1, 'FD8761-072', 'NIKE AS U ACG TF CREW FLC GX', 'FD8761-072.jpg', 135000,
        '전설적인 아티스트 Ralph Steadman의 프린트 그래픽이 이 넓고 중량급 ACG 플리스 크루의 앞면과 뒷면을 우아하게 장식합니다. Nike Therma-FIT 기술은 신체의 자연적인 열을 관리하여 추운 날씨에도 따뜻하게 유지하도록 도와줍니다.'),
       (17, 1, 'DQ5762-010', 'NIKE AS W NSW PHNX FLC OOS CREW', 'DQ5762-010.jpg', 95000,
        '강렬하고 포근한 감성으로 여러분의 플리스 컬렉션에 변화를 주세요. 볼륨감 있는 핏과 과감한 골지 디테일이 돋보이는 피닉스 플리스 스웻셔츠로, 결코 심심하지 않은 룩을 연출합니다.'),
       (18, 1, 'FQ8011-237', 'NIKE AS M NSW FLC HVYWT CRW HNGD', 'FQ8011-237.jpg', 125000,
        '미니멀한 터치와 묵직한 따뜻함이 돋보이는 두꺼운 플리스 스웨트셔츠에 나이키와 한글이 만나다 로고가 자수로 새겨져 한글날을 기념합니다.'),
       (19, 1, 'FQ8011-110', 'NIKE AS M NSW FLC HVYWT CRW HNGD', 'FQ8011-110.jpg', 125000,
        '미니멀한 터치와 묵직한 따뜻함이 돋보이는 두꺼운 플리스 스웨트셔츠에 나이키와 한글이 만나다 로고가 자수로 새겨져 한글날을 기념합니다.'),
       (20, 1, 'FD8761-010', 'NIKE AS U ACG TF CREW FLC GX', 'FD8761-010', 135000,
        '찬사받는 예술가 랠프 스태드먼의 작품만큼 냉혹하고 광기 어리며 아름답게 자유로운 영혼의 삶을 연상시키는 건 없습니다. 랠프의 기이한 의인화 일러스트는 탁 트인 길 위의 모험에 대한 상상을 나타내며, 보는 이가 자신의 한계를 넘을 수 있도록 준비시킵니다. 이 무게감 있는 플리스는 나이키 써마 핏 기술을 통해 체열을 관리해 줍니다. 트레일에서 역경을 견딜 때나 모닥불에 둘러앉아 이야기를 들려줄 때 이 아이템이 도움이 될 것입니다.');


## 2-2. Pants 상품 20개 insert
INSERT INTO Products
VALUES (21, 2, 'TR23FWDP03', 'FETCH X TRUE RELIGION SEPARATE CARGO PANTS (DENIM)', 'TR23FWDP03U.jpg', 318000, NULL),
       (22, 2, 'TR23FWDP02', 'TRUE RELIGION SCAR WASHED DENIM PANT', 'TR23FWDP02.jpg', 259000, NULL),
       (23, 2, 'SG230303PT', 'SAN SAN GEAR SIDE SNAP PANTS V2', 'SG230303PT.jpg', 139000, NULL),
       (24, 2, '02290181', 'PALMES SOCIETY TRACK PANTS', '02290181.jpg', 260000, NULL),
       (25, 2, '8AH002', 'OSTRYA ALPINE SOFT SHELL PANTS', '8AH002.jpg', 519000,
        '알프스 등반을 위해 설계된 소프트 쉘 팬츠. 발수와 방풍 기능이 있어 추운 환경에서 제 값을 하는 아이템'),
       (26, 2, 'DV3732-010', 'NOCTA X NIKE M NRG WARMUP PANT QS', 'DV3732-010.jpg', 149000,
        '농구에서 영감을 얻은 NOCTA 워밍업 팬츠로 코트에서 클럽까지 산뜻한 커스텀 아이템과 함께해 보세요. 경량 우븐 소재와 골반, 허벅지 부분의 여유로운 핏으로 낮이든, 밤이든 어떤 상황에도 대비할 수 있습니다. 매칭되는 NOCTA 워밍업 재킷과 함께 스타일링하여 유니폼 스타일의 룩을 연출해 보세요.'),
       (27, 2, 'FB8200-386', 'NIKE AS M ACG SMITH SUMMIT CRGAOP', 'FB8200-386.jpg', 289000, NULL),
       (28, 2, 'DV3657-479', 'NOCTA X NIKE M NRG DF ENG KNIT TIGHT QS', 'DV3657-479.jpg', 129000,
        '프리미엄 NOCTA 타이츠를 입고 스니커즈가 끌리는 소리가 나도록 열심히 움직여 보세요. 피부에 밀착되도록 가공된 니트가 꼭 맞는 핏을 선사하며 땀 발산 기능을 발휘해 쾌적하고 편안합니다. NOCTA 쇼츠 및 저지와 함께 매치하여 코트 안팎에서 조화로운 룩을 연출하세요.'),
       (29, 2, 'IK8598', 'SONG FOR THE MUTE X ADIDAS PANT', 'IK8598.jpg', 199000,
        '다리 측면의 지퍼가 시선을 사로잡는 아디다스 팬츠를 만나보세요. 지퍼를 원하는 위치까지 열어 다리를 드러내거나 모두 올려 실루엣에 변화를 주고 다양한 룩을 연출할 수 있습니다. 탄력적인 허리밴드의 이중 스트랩이 편안한 핏과 대담한 스타일을 더해줍니다. 호주 기반의 패션 브랜드인 Song for the Mute와의 협업으로 미래적 감성이 녹아든 신선하고 극적인 디테일을 선보입니다.'),
       (30, 2, 'IK8597', 'SONG FOR THE MUTE X ADIDAS PANT', 'IK8597.jpg', 199000,
        '다리 측면의 지퍼가 시선을 사로잡는 아디다스 팬츠를 만나보세요. 지퍼를 원하는 위치까지 열어 다리를 드러내거나 모두 올려 실루엣에 변화를 주고 다양한 룩을 연출할 수 있습니다. 탄력적인 허리밴드의 이중 스트랩이 편안한 핏과 대담한 스타일을 더해줍니다. 호주 기반의 패션 브랜드인 Song for the Mute와의 협업으로 미래적 감성이 녹아든 신선하고 극적인 디테일을 선보입니다.'),
       (31, 2, 'RBMW020FA0', 'ROA HIKING TECHNICAL TROUSERS SOFTSHELL', 'RBMW020FA0.jpg', 533000,
        '내부 플리스 질감이 돋보이는 기능성 소프트쉘 아웃도어 팬츠. 신치 스트랩을 사용하여 허리를 쉽게 조절할 수 있습니다. 안전한 보관을 위한 지퍼 포켓과 지퍼 잠금 장치가 있는 환기구가 있습니다.'),
       (32, 2, 'CV0661-014', 'NIKE AS M ACG TRAIL PANT', 'CV0661-014.jpg', 139000,
        '나이키 ACG 트레일 팬츠는 어떠한 아웃도어 모험에도 준비되어 있습니다. 발수 처리와 튼튼한 내마모성 디자인이 어우러져 궂은 날씨에 바위투성이 트레일에서도 편안하게 나아갈 수 있습니다. 이 제품은 75% 이상 재생 폴리에스터 및 재생 나일론 섬유로 제작되었습니다.'),
       (33, 2, 'CV0659-328', 'NIKE AS M ACG WOLF TREE PANT', 'CV0659-328.jpg', 185000,
        '궂은 날씨라고 탐험을 그만둬야 하는 것은 아닙니다. 오리건주 스미스 록에서 영감을 얻은 디자인으로 따뜻함을 유지하세요. 100% 재생 폴리에스터 섬유로 제작된 놀랍도록 부드러운 소재가 기온이 떨어질 때도 따뜻함을 유지해 줍니다. 지퍼 포켓에 소지품을 보관할 수 있습니다.'),
       (34, 2, 'CV0659-011', 'NIKE AS M ACG WOLF TREE PANT', 'CV0659-011.jpg', 185000,
        '궂은 날씨라고 탐험을 그만둬야 하는 것은 아닙니다. 오리건주 스미스 록에서 영감을 얻은 디자인으로 따뜻함을 유지하세요. 100% 재생 폴리에스터 섬유로 제작된 놀랍도록 부드러운 소재가 기온이 떨어질 때도 따뜻함을 유지해 줍니다. 지퍼 포켓에 소지품을 보관할 수 있습니다.'),
       (35, 2, 'FQ0496-010', 'NIKE AS M SB KEARNY CARGO PANT', 'FQ0496-010.jpg', 129000,
        '튼튼한 립스탑 소재로 제작되어 스케이트보딩에 최적화된 여유로운 핏을 선사하는 나이키 SB 팬츠는 내구성이 우수하며, 클래식한 카고 디자인으로 넉넉한 수납공간을 제공합니다. 6개의 포켓과 페이크로 연출된 케어 라벨 수납 포켓이 있어 보드를 탈 때든 일상에서든 소지품을 안전하게 보관할 수 있습니다.'),
       (36, 2, 'FQ8045-020', 'NIKE AS W NSW VLR HR WIDE PANT HNGD', 'FQ8045-020.jpg', 109000,
        '여성용 하이 웨이스트 와이드 레그 벨루어 팬츠 와이드 레그 스웻셔츠가 이보다 더 편안할 수 없다고 생각하셨을 때, 부드러운 벨루어 느낌을 더해 포근한 느낌을 더했습니다. 마무리로 나이키와 한글이 만나 한글 문장을 자수로 넣어 한글날과 한글을 기념합니다.'),
       (37, 2, 'FQ8045-010', 'NIKE AS W NSW VLR HR WIDE PANT HNGD', 'FQ8045-010.jpg', 109000,
        '여성용 하이 웨이스트 와이드 레그 벨루어 팬츠 와이드 레그 스웻셔츠가 이보다 더 편안할 수 없다고 생각하셨을 때, 부드러운 벨루어 느낌을 더해 포근한 느낌을 더했습니다. 마무리로 나이키와 한글이 만나 한글 문장을 자수로 넣어 한글날과 한글을 기념합니다.'),
       (38, 2, 'FJ6137-296', 'J BALVIN X JORDAN M WVN PANT QS', 'FJ6137-296.jpg', 229000,
        '넉넉한 핏에 우븐 트윌 소재의 이 팬츠는 개성을 표현할 수 있도록 디자인되었습니다. 자수 디테일과 유틸리티 포켓이 품격 있는 룩을 연출합니다.'),
       (39, 2, 'FJ6137-010', 'J BALVIN X JORDAN M WVN PANT QS', 'FJ6137-010.jpg', 229000,
        '넉넉한 핏에 우븐 트윌 소재의 이 팬츠는 개성을 표현할 수 있도록 디자인되었습니다. 자수 디테일과 유틸리티 포켓이 품격 있는 룩을 연출합니다.'),
       (40, 2, 'DV7459-263', 'A MA MANIÉRE X JORDAN AS M SNAP PANT QS', 'DV7459-263.jpg', 169000,
        '깔끔하고 심플하며 고급스러운 이 티어어웨이 팬츠는 다리 하단의 A Ma Maniére(아 마 마니에르) 브랜딩 스냅 버튼이 돋보입니다. 시대를 초월한 오프 코트 스타일과 함께 준비된 모습으로 경기에 임해 보세요.');


## 2-3. Shoes 상품 20개 insert
INSERT INTO Products
VALUES (41, 3, '1134991', 'UGG CLASSIC MINI PLATFORM', '1134991.jpg', 278000,
        '아이콘을 현대적으로 해석한 이 Classic Mini는 다리가 길어지는 2인치 플랫폼으로 새로운 차원에 도달했습니다. 그 어느 때보다 대담한 룩을 제공하며, 오리지널과 동일한 풍부한 스웨이드와 시그니처 부드러움을 위한 UGGplush™ 울 혼방으로 제작되었습니다.'),
       (42, 3, '1135092', 'UGG CLASSIC ULTRA MINI PLATFORM', '1135092.jpg', 268000,
        '아이콘을 현대적으로 해석한 Ultra Mini는 다리가 길어지는 2인치 플랫폼으로 새로운 차원에 도달했습니다. 그 어느 때보다 대담한 룩을 제공하며 오리지널과 동일한 풍부한 스웨이드에 UGGplush™ 울 혼방으로 시그니처 부드러움을 선사합니다.'),
       (43, 3, '1146390', 'UGG TAZZLITA', '1146390.jpg', 268000,
        'Tazzlita는 상징적인 Tasman의 아늑함을 한 단계 더 끌어올렸습니다. 부드러운 UGGplush™ 업사이클 울 내부가 고급 스웨이드 갑피 위로 17mm 테이블그레이드 칼라로 흘러나옵니다. 더 많은 패션 실루엣을 위한 높은 플랫폼을 자랑하는 이 슬리퍼는 소파에서 영화를 보는 것처럼 식료품점에 있는 집처럼 느껴집니다.'),
       (44, 3, '1113474', 'UGG FUNKETTE', '1113474.jpg', 198000,
        'Funkette는 당사의 상징적인 Coquette Slipper를 발전시켜 레이어드 플랫폼 밑창으로 디자인을 업데이트하여 펑키하고 복고풍 스타일을 표현합니다. 오리지널과 동일한 슬립온 실루엣과 부드러운 양가죽 안감을 유지하면서 그래픽 UGG 로고 백스트랩을 추가하여 발을 안전하게 보호합니다.'),
       (45, 3, 'IE7011', 'ADIDAS SAMBA OG W', 'IE7011.jpg', 139000,
        '50년대에 축구화로 태어난 아디다스 삼바는 스포츠 세계를 뛰어넘어 스트리트와 런웨이를 사로잡으며 진정한 스타일 아이콘으로 진화했습니다. 데일리 슈즈로 새롭게 돌아온 이번 버전은 부드러운 가죽 갑피와 매끈한 고무 아웃솔이 탑재된 편안한 디자인을 선보입니다. 톱니 라인을 살린 3-스트라이프, 견고한 T-토가 전설적인 스니커즈의 깊고 풍부한 역사를 재조명합니다.'),
       (46, 3, 'IE7012', 'ADIDAS SAMBA OG W', 'IE7012.jpg', 139000,
        '50년대에 축구화로 태어난 아디다스 삼바는 스포츠 세계를 뛰어넘어 스트리트와 런웨이를 사로잡으며 진정한 스타일 아이콘으로 진화했습니다. 데일리 슈즈로 새롭게 돌아온 이번 버전은 부드러운 가죽 갑피와 매끈한 고무 아웃솔이 탑재된 편안한 디자인을 선보입니다. 톱니 라인을 살린 3-스트라이프, 견고한 T-토가 전설적인 스니커즈의 깊고 풍부한 역사를 재조명합니다.'),
       (47, 3, 'IE4195', 'ADIDAS SUPERSTAR 82', 'IE4195.jpg', 159000,
        '농구 코트를 석권한 후 아이콘이 된 아디다스 슈퍼스타 신발입니다. 히스토리를 모르는 사람들도 슈퍼스타의 쉘토는 알고 있습니다. 1982년 모델로부터 복고풍 형태 등을 복각하여 만들었으며, 어퍼 부분을 재구축하여 클래식한 스타일을 완성시키고 있습니다.'),
       (48, 3, 'GY7037', 'ADIDAS SUPERSTAR 82', 'GY7037.jpg', 159000,
        '프리미엄 소재와 아이코닉한 실루엣을 하나로 녹여낸 슈퍼스타 82 슈즈입니다. 유기농 면 소재로 제작된 무염색 트윌 캔버스 갑피가 깔끔한 룩을 선사합니다. 럭셔리한 스웨이드와 스티치 디테일이 대비감을 더해줍니다. 시그니처 쉘토부터 3-스트라이프와 힐 탭까지 오리지널 슈퍼스타를 전설로 만든 모든 디자인 요소가 담겨 있습니다.'),
       (49, 3, '207393-76L', 'SALEHE BEMBURY X THE POLLEX CLOG', '207393-76L.jpg', 119000, NULL),
       (50, 3, '208685-1MC', 'SALEHE BEMBURY X CROCS THE POLLEX SLIDE', '208685-1MC.jpg', 99000, NULL),
       (51, 3, 'DZ4636-100', 'SOCIAL STATUS X NIKE MAC ATTACK SP QS', 'DZ4636-100.jpg', 169000,
        '전방에 존 매켄로 출현! 이 신발은 존 매켄로의 아이코닉한 80년대 룩을 그대로 재현합니다. 존의 반항적인 성격을 나타내는 대담한 컬러 블로킹과 개성적인 그래픽으로 수많은 새하얀 스니커즈들 속에서도 단연 돋보입니다.'),
       (52, 3, 'A08686C', 'KASINA X CONVERSE WEAPON', 'A08686C.jpg', 175000,
        'Kasina X Converse 거침없이 당당하게 당신의 영감을 펼쳐보세요. 카시나 매장들의 핸드 드로잉 도안과 건축물에서 영감을 얻은 리미티드 에디션 컨버스 웨폰을 출시합니다. 이번 디자인을 통해 자신만의 아이디어와 크리에이티브 여정을 향해 나아가는 자유로움과 자신감을 기념합니다. 카시나의 첫 번째 매장과 그 계보를 잇는 다른 매장들처럼 이번 리미티드 에디션 스타일은 자유로운 사고, 창의성, 미적 표현을 위한 기회를 제공합니다.'),
       (53, 3, 'FD7039-200', 'NIKE AIR FORCE 1 LOW RETRO QS', 'FD7039-200.jpg', 179000,
        'AF1 컬렉션에 강렬함을 더하는 이 아이템은 농구 코트에서 스트리트로 이어진 아이콘을 재해석해 시선을 사로잡는 열기를 더합니다. 대비되는 컬러가 어떤 옷차림에도 대담한 느낌을 더하고, 나이키 에어 쿠셔닝이 발 아래의 포인트가 되어줍니다.'),
       (54, 3, '39330602', 'RHUIGI X PUMA TRINOMIC XT-2', '3930602.jpg', 229000,
        '이번 시즌 푸마와 루이지 콜라보레이션에서는 젊은 문화를 가장 잘 투영한 뉴욕시에서 영감을 받아 푸마의 헤리티지를 보여줍니다. 힙합의 탄생부터 세계적인 농구 선수 월트 클라이드의 온/오프 코트 요소들이 있는 뉴욕의 풍부한 문화적 역사를 투영하였습니다. 루이지에서 영감을 받은 아카이브 실루엣과 프리미엄 소재와 컬러가 사용된 것이 특징입니다.'),
       (55, 3, 'FD7039-101', 'NIKE AIR FORCE 1 LOW RETRO QS', 'FD7039-101.jpg', 179000,
        'Color of the Month(컬러 오브 더 먼스) 시리즈를 통해 원하는 것을 찾고 잘 알려지지 않은 나이키의 역사를 기념할 수 있습니다. 단종 위기에 처한 에어 포스 1을 구해낸 것은 1984년의 오리지널 Color of the Month(컬러 오브 더 먼스) 시리즈였을 수도 있습니다. 깔끔한 소재부터 빳빳한 화이트 앤 포레스트 그린 컬러웨이, 신발을 닦는 솔까지 이번 에디션은 모두가 원하는 코트 밖 스타일에 대한 교훈을 전합니다.'),
       (56, 3, 'FB2348-700', 'NIKE W AIR FORCE 1 WILD', 'FB2348-700.jpg', 169000,
        '야외는 모두를 위한 공간입니다. 에어 포스 1도 마찬가지죠. 아이콘에 실용적인 끈 조임, 튼튼한 소재, 스티치를 더해 탁월하게 견고한 구조를 완성하여 모험에 적합한 아이템으로 재해석했습니다. 직물 소재로 감싼 미드솔은 가볍고 유연하며 뒤꿈치 필로우가 추가되어 편안함을 선사합니다. 자수 액센트가 부츠 특유의 디테일과 통기성이 뛰어난 메쉬와 대조를 이루며 다양한 룩을 연출할 수 있습니다.'),
       (57, 3, 'L47293700', 'SALOMON XT-6', 'L47293700.jpg', 260000,
        'OVERVIEW CODE : L47293700 [트레일 러닝의 역사적인 러너들이 모두 인정하는 레전드 슈즈]XT-6은 2013년 처음 출시된 이후 혹독한 조건을 이겨내고 장시간 러닝을 하는 세계적인 선수들이 선호하는 러닝슈즈로 자리매김했습니다. 최고의 쿠셔닝, 내구성, 컨트롤 능력은 그대로 유지하면서 컬러와 소재를 더욱 업그레이드했습니다.'),
       (58, 3, 'L47291800', 'SALOMON XT-6 GTX', 'L47291800.jpg', 280000,
        'XT-6 GTX는 살로몬의 트레일 레전드 슈즈로 트레일화에 필요한 기능이 모두 담긴 스니커즈 스타일 슈즈입니다. 궂은 도시 날씨에 적합한 XT-6 GORE-TEX는 혁신적인 ePE 멤브레인, PFC-프리, 안티-디브리스 메쉬 구조가 적용되었습니다. 또한 내구성이 뛰어난 쿠셔닝이 안정감과 우수한 착용감을 선사합니다.'),
       (59, 3, 'L47298400', 'SALOMON RX SLIDE 3.0', 'L47298400.jpg', 138000,
        'RX SLIDE 3.0은 살로몬의 클래식 리커버리 슈즈의 업데이트 버전 입니다. 새로운 소재, 완성도 높은 디자인 및 다채로운 색상 옵션을 제공 합니다.'),
       (60, 3, 'L47384900', 'SALOMON ODYSSEY ELMT ADVANCED', 'L47384900.jpg', 350000,
        'ODYSSEY ELMT ADVANCED는 편안함과 기능성이 우수한 슈즈입니다. 아웃도어 액티비티는 물론, 일상 생활 속에서도 편하게 신을 수 있습니다. 혁신적인 이중 어퍼와 보호 기능이 뛰어난 게이터가 함께 설계되어 발을 편안하고 안정적으로 보호해줍니다. 센시핏이 정확한 지지력을 만들어주고 특수 섀시 구조가 뛰어난 안정감과 쿠션감을 선사합니다.');


## 2-4. Outer 상품 20개 insert
INSERT INTO Products
VALUES (61, 4, '8AA001', 'OSTRYA HENSON DOWN PARKA', '8AA001.jpg', 1569000,
        '오스트리야의 아이코닉 아이템. 패커블로 휴대가 용이하다. 가볍고 내구성이 뛰어난 소재로 극한의 날씨의 활동에 적합하다. 850 필파워 트레이서블 화이트 구스 다운으로 가벼움을 유지하면서 보온성이 뛰어나다.'),
       (62, 4, '8AA002', 'OSTRYA SQUALL DOWN PARKA', '8AA002.jpg', 989000,
        '도시의 추운 날씨에 적합한 자켓. 가벼운 소재와 기능성 디자인이 특징. 트레이서블 다운(Traceable Down : 유통 과정 추적 다운)은 살아 있는 거위와 오리에서 억지로 뽑은 털을 쓰지 않는 것을 전제로 한 투명하고 윤리적인 유통 과정 추적.'),
       (63, 4, '8AA003', 'OSTRYA SAPWOOD DOWN JACKET', '8AA003.jpg', 669000,
        '위킹(흡수 확산) 미드레이어 플리스로 야외 활동에 적합하다. 하프 집업 형태로 열 컨트롤이 자유로우며, 가슴의 집업 포켓으로 작은 소지품 수납이 가능하다. 부드럽고 신축성 있는 플리스 원단으로 어떤 활동에도 실용적인 아이템이다.'),
       (64, 4, 'FB7853-410', 'NIKE AS M NK SOLO SWSH PUFFER', 'FB7853-410.jpg', 345000,
        '1990년대 시그니처 파카의 모습을 그대로 담은 Solo Swoosh 퍼퍼는 Solo Swoosh 컬렉션의 대명사인 깔끔하고 클래식한 스타일을 선사합니다. 후드와 조임 끈은 휴대 가능하므로 하늘이 맑을 때 깔끔한 모습을 유지할 수 있고 비나 눈이 계획을 위협할 때 포장을 풀 수 있습니다. 발수성 원단과 따뜻하고 견고한 단열재의 조합으로 어떤 날씨에도 견딜 수 있다는 확신을 갖고 외출할 수 있다는 자신감을 갖게 됩니다.'),
       (65, 4, '62087601', 'PLEASURES X PUMA ZIP-OFF JACKET', '62087601.jpg', 399000, NULL),
       (66, 4, 'YL3386-010', 'COLUMBIA W\'S ANNE ROAD™ DOWN JACKET', 'YL3386-010.jpg', 199000,
        '세로패턴과 은은한 광택감이 깔끔한 포인트룩으로 연출하기 좋은 여성 프리미엄 라이프스타일 구스 슬림다운 자켓입니다.'),
       (67, 4, 'YL3382-010', 'COLUMBIA W\'S BRANCH LAKE™ INSULATION JACKET', 'YL3382-010.jpg', 219000,
        '옴니히트 인피니티 골드패턴 안감과 옴니히트 충전재가 적용되어 한 겨울에도 체온을 효과적으로 보온해줍니다. 은은한 광과 다이아몬드 퀼팅 디자인이 여성스러운 무드를 제공하며, 카라와 소매안감에 코듀로이 소재 포인트로 캐주얼한 느낌으로 연출이 가능합니다.핸드포켓이 적용되어 간단한 소지품을 수납하기 좋습니다.'),
       (68, 4, 'YM3382-302', 'COLUMBIA M\'S STATION LAKE™ INSULATION JACKET', 'YM3382-302.jpg', 239000,
        '효과적으로 체온을 보호해주는 옴니히트 인피니티 골드패턴 안감이 적용되어 한 겨울까지 다양하게 스타일링이 가능합니다. 여유있는 핏과 깔끔한 U넥 디자인으로 일상/아웃도어 활동시 편안하게 착용할 수 있습니다.'),
       (69, 4, 'DV9416-025', 'NIKE AS M ACG SF CASCADE RAIN JKT', 'DV9416-025.jpg', 209000,
        '캐스케이드 산 자세 범위는 태평양 북서부의 서쪽 부분은 비를 많이 얻을 수 있도록합니다. 비는 기후를 아름답게 만들어 주지만, 환경을 편안하게 즐기기 어렵게 하는 요인이 되기도 합니다. 나이키 스톰 핏 기술을 활용한 이 윈드프루프 및 생활 방수 재킷은 솔기를 완전히 봉합한 디자인에 내수성 지퍼를 더해 빗물과 차가운 공기가 스며드는 것을 막아줍니다. 어떤 날씨에도 야생을 탐험하는 시간을 즐길 수 있습니다.'),
       (70, 4, 'DQ5772-328', 'NIKE AS M ACG SFADV MSRY RIDGE JKT', 'DQ5772-328.jpg', 575000,
        '루즈하고 넉넉한 핏의 나이키 ACG GORE-TEX ‘Misery Ridge’ 셸은 베이스 및 미드레이어 위에 입을 수 있도록 제작되었습니다. 튼튼한 생활 방수 디자인과 충분한 커버력으로 아웃도어에 적합합니다. 포켓에 필수 아이템을 안전하게 보관할 수 있습니다.'),
       (71, 4, 'IK8648', 'SONG FOR THE MUTE X ADIDAS JACKET', 'IK8648.jpg', 309000,
        '독특한 우븐 텍스처를 살려주는 넉넉한 웰트 포켓에 필수품을 휴대할 수 있습니다. 후드 양쪽 끝의 조절끈을 사용해 간편하게 핏을 조절하고 미사용시에는 깔끔하게 숨길 수 있습니다. 냉기를 차단하는 탄력적인 밑단과 커프스, 목 끝까지 올릴 수 있는 전장 지퍼가 강화된 커버력을 더해줍니다. 호주 기반의 패션 브랜드인 Song for the Mute와의 협업으로 미래적 감성이 녹아든 대담하고 독특한 스타일을 선보입니다.'),
       (72, 4, 'DV0364-011', 'NIKE AS M ACG TFADV ROPE DE DOPE JK', 'DV0364-011.jpg', 249000,
        '추운 날씨는 새로운 발견의 기회일 뿐입니다. 나이키 ACG 써마 핏 ADV "Rope de Dope" 재킷은 혁신적인 기술과 보온성을 위한 디자인을 결합해 어떤 날씨에도 편안한 착용감을 선사합니다.'),
       (73, 4, 'DH3071-681', 'NIKE AS U ACG TFADV LUNAR LAKE JKT', 'DH3071-681.jpg', 405000,
        '추운 날씨에 트레킹을 떠났을 때, 보온성과 발수성을 갖춘다면 한기도 금방 잊어버릴 수 있을 것입니다. 나이키는 추운 환경에 대처할 수 있는 장비를 연구하고 개발하기 위해 아이슬란드를 방문했습니다. 그 결과는요? 머리부터 밑단까지 따뜻하고 여유롭게 커버해주는 패딩 재킷이 탄생했죠. 재생 폴리에스터와 재생 나일론 섬유를 혼방하여 75% 이상 지속 가능한 소재로 제작되었습니다.'),
       (74, 4, 'FD7846-010', 'NIKE AS M NK AU VARSITY JKT LTHR SL', 'FD7846-010.jpg', 575000,
        '경기에 나가거나 시내를 돌아다니기에 완벽한 이 클래식 대표팀 재킷은 우리의 Authentics 컬렉션에서 여러분이 좋아하는 레터맨 재킷의 시대를 초월한 품질을 다시 최전선으로 가져옵니다. 이 고급스러운 가죽 슬리브 에디션은 클래식과 동일한 스트라이프 골지, 가죽 트리밍 핸드 포켓, 스냅 전면 잠금장치가 특징입니다. 또한 가벼운 단열재와 부드러운 태피터 안감으로 제작되어 쉽게 편안하고 레이어드할 수 있습니다.'),
       (75, 4, 'FQ8005-010', 'NIKE AS M NL HARRINGTON JKT CORD HN', 'FQ8005-010.jpg', 139000,
        '평생 입어도 좋을 만큼 우수한 내구성을 자랑하는 나이키 라이프 컬렉션의 풀집 코듀로이 재킷입니다. 넉넉한 핏으로 여유롭게 레이어링할 수 있으며 부드러운 코듀로이가 캐주얼하면서도 유행을 타지 않는 스타일을 연출합니다.'),
       (76, 4, 'FQ0513-010', 'NIKE AS U SB PADDED FLANNEL JKT', 'FQ0513-010.jpg', 209000,
        '악천후에 대담하게 맞설 수 있도록 제작된 이 나이키 SB 플란넬 스케이트보딩 재킷은 따뜻한 충전재를 적용해 ‏매우 추운 날씨에도 걱정 없습니다. 내구성이 뛰어난 우븐 트윌 소재는 매일같이 자주 착용해도 튼튼하며, 입을 수록 멋지게 길이 듭니다. 넉넉한 핏으로 완성됐으며, 앞쪽의 풀 스냅을 사용하여 원하는 방식으로 스타일링할 수 있습니다.'),
       (77, 4, '62088456', 'RHUIGI X PUMA T7 SUMMER JACKET', '62088456.jpg', 239000, NULL),
       (78, 4, 'CTAR70000', 'ARIES VINTAGE DRILL TRUCKER JACKET', 'CTAR70000.jpg', 819000, NULL),
       (79, 4, 'FJXKA80896', 'FJ X KASINA ANORAK', 'FJXKA80896.jpg', 358000,
        '스트리트패션 브랜드 KASINA(카시나)의 레트로한 무드와 FJ 1857년 헤리티지 감성이 만나 탄생한 FJ X KASINA 콜라보레이션 오버핏 반팔 아노락 자켓입니다. 스트레치성이 좋고 부드러운 면터치의 기능성 발수 소재를 사용하여 높은 활동성을 보장하고 가벼운 비에도 대응이 가능합니다. FJ X Kasina 로고 & 슬로건 프린트, 밑단 배색 우븐과 스트링으로 포인트를 주고, 등판 케이프 벤틸레이션 시스템으로 통기성을 확보하였습니다.'),
       (80, 4, '3SJCUR', 'CHILL SPORTS CLUB CHILL URBAN RESERCH JACKET', '3SJCUR.jpg', 238000,
        '4WAY 스트레치 나일론/스판 원단으로 활동성을 극대화한 집업 바람막이 자켓입니다. 프론트 풀집과 지퍼 풀러로 클로징이 가능하며, 조절가능한 3D 후디와 지퍼 인사이드 포켓이 있습니다. 등 환기구멍과 안감 메쉬로 가볍게 착용됩니다.');

## 2-5. Hat 상품 20개 insert
INSERT INTO Products
VALUES (81, 5, 'FUAR90014', 'ARIES FAST FOOD TRUCKER CAP', 'FUAR90014.jpg', 95000,
        '메쉬 패널과 조절 가능한 플라스틱 스냅백이 있는 클래식 트러커 캡입니다. 앞면에 퍼프 프린트의 아카이브 Aries Fast Food 그래픽이 있고 뒷면에 ​​Aries 그리스 자수가 있습니다.'),
       (82, 5, 'FUAR90005', 'ARIES MOTO CORDUROY CAP', 'FUAR90005.jpg', 144000,
        '100% 면 코듀로이 소재의 남녀공용 5패널 캡으로 조절 가능한 플라스틱 스냅백이 있습니다. 꼭대기에 수양 뿔이 수놓여 있고 모토 스타일로 짜여진 패치와 자수가 장식되어 있습니다.'),
       (83, 5, '01120108', 'PALMES WRITTEN WOOL 6-PANEL CAP', '01120108.jpg', 149000,
        '앞면에 자수가 있는 6패널 구조입니다. 후면 클로저로 사이즈 조절이 가능합니다.'),
       (84, 5, 'FB5367-063', 'NIKE U FLY CAP U FB TCH FLC L', 'FB5367-063.jpg', 39000,
        '나이키 플라이 캡을 쓰고 테크 플리스의 가볍고 따뜻한 감각을 느껴보세요. 2013년부터 많은 사랑을 받아온 소재와 아이코닉한 디테일을 그대로 적용했습니다. 또한 5패널 캠프 모자 디자인은 로우 프로파일 스타일을 선사하며, 여유로운 주말의 휴식에서 이른 아침의 하이킹에 이르기까지 모든 활동에 적합합니다.'),
       (85, 5, 'VD7033-111', 'VANDY THE PINK PATTERN KHAKI TRUCKER', 'VD7033-111.jpg', 95000, NULL),
       (86, 5, 'CTAR90000', 'ARIES NO PROBLEMO CAP', 'CTAR90000.jpg', 99000, NULL),
       (87, 5, '3SHA7OF', 'CHILL SPORTS CLUB 7ORIGINAL 5 PANEL LOGO FLAP', '3SHA7OF.jpg', 68000,
        '뉴 패러다임의 5패널 나일론 플랩 테크 캡입니다. UV 100% 프로텍트의 플랩이 특징인 캡으로 플랩과 볼마커는 탈부착이 가능합니다. 스트레치 코드로 모자 사이즈를 조절할 수 있으며, 여분의 스트레치 코드를 제공합니다. 플랩에는 CHILL SPORTS CLUB 로고 스카시 디지털 프린팅이 있습니다. 모자와 플랩의 원단차이로 칼라의 차이가 조금 있습니다.'),
       (88, 5, '3SHASL', 'CHILL SPORTS CLUB 6PANEL SCRIPT LOGO', '3SHASL.jpg', 48000,
        '뉴 패러다임의 6패널 프리미엄 코튼 볼캡입니다. 스트레치 코드로 모자 사이즈를 조절할 수 있으며, 여분의 스트레치 코드를 제공합니다. 볼마커는 탈부착이 가능합니다.'),
       (89, 5, 'FJXKA35954', 'FJ X KASINA CAMP CAP', 'FJXKA35954.jpg', 78000,
        '스트리트패션 브랜드 KASINA(카시나)의 레트로한 무드와 FJ 1857년 헤리티지 감성이 만나 탄생한 FJ X KASINA 콜라보레이션 캠프 캡입니다. 스트리트 캐주얼 느낌이 물씬 나는 캠프 캡 형태의 모양으로 젊고 트렌디한 연출이 가능합니다. 콜라보레이션 제품에만 적용된 FJ x Kasina 로고로 디자인 포인트를 주었습니다.'),
       (90, 5, 'STAR90000', 'ARIES NO PROBLEMO CAP', 'STAR90000.jpg', 89000,
        '가죽 백 스트랩이 있는 100% 면 드릴로 만든 6 패널 캡입니다. 앞면에는 No Problemo 3D 자수가, 뒷면에는 고딕 A 자수가 있습니다.'),
       (91, 5, '8AQ001', 'OSTRYA SWIRL MERINO BEANIE', '8AQ001.jpg', 92000, '메리노 울 혼방의 가볍고 따뜻한 비니'),
       (92, 5, '8AQ002', 'OSTRYA NORDIC KNIT BEANIE', '8AQ002.jpg', 79000, '털실 방울이 매력적인 빈티지 노르딕 니트 비니.'),
       (93, 5, 'RBUW202YA', 'ROA HIKING BEANIE LOGO', 'RBUW202YA.jpg', 98000,
        '콘트라스트 니트 소재의 Roa 메리노 울 비니. 울 섬유의 자연적인 흡수 능력은 습한 환경에서도 편안하고 건조한 따뜻함과 상쾌함을 선사합니다.'),
       (94, 5, 'FB6529-010', 'NIKE U NK PEAK BEANIE TC SWSH L QS', 'FB6529-010.jpg', 33000, NULL),
       (95, 5, 'PACC11K007', 'RASSVET MEN BIG LOGO BEANIE KNIT', 'PACC11K007.jpg', 98000,
        '아늑하고 편안하게 제작된 Rassvet(PACCBET) 빅 로고 비니는 야외에서 보내는 추운 날에 적합합니다. 착용하는 동안 머리를 감싸는 부드러운 울 혼방 구조와 맞춤형 로고 자수가 전면을 대담한 라인워크로 장식합니다.'),
       (96, 5, 'PACC11K005', 'RASSVET MEN LOGO BEANIE KNIT', 'PACC11K005.jpg', 72000,
        '러시아 스케이터 브랜드 Rassvet은 클래식한 스트리트웨어 실루엣에 소비에트 미학을 섬세하게 결합한 디자인을 선보입니다. 로고 패치 비니 햇은 니트 직조의 기능성 혼방 소재로 제작되었으며, 라운드 크라운, 접어 올린 브림, 앞면 로고 패치와 전체 그래픽 프린트가 특징입니다.'),
       (97, 5, 'ERL05K056', 'ERL UNISEX GRADIENT BEANIE KNIT', 'ERL05K056.jpg', 180000,
        'ERL의 UNISEX GRADIENT BEANIE 입니다. 그라데이션된 컬러와 높은 보온성의 모헤어 소재로 제작되었으며, 전면 ERL 로고로 마무리 하였습니다.'),
       (98, 5, '00730068', 'PALMES HORNE REVERSIBLE BUCKET HAT', '00730068.jpg', 175000,
        '대비 스티칭과 루프에 라벨 포인트가 있는 리버서블 버킷 햇 입니다. 재활용 폴리에스터 65%와 유기농 면 35%으로 제작되었습니다.'),
       (99, 5, 'FJXKA35952', 'FJ X KASINA HAT', 'FJXKA35952.jpg', 108000,
        '스트리트패션 브랜드 KASINA(카시나)의 레트로한 무드와 FJ 1857년 헤리티지 감성이 만나 탄생한 FJ X KASINA 콜라보레이션 햇 입니다. 경량소재의 햇으로 가볍고 청량한 느낌의 소재를 사용하여 여름까지 오래 착용이 가능합니다. 길게 늘어진 스트랩으로 스트리트 감성을 담은 디자인에 콜라보레이션 제품에만 적용된 FJ x Kasina 로고가 포인트입니다. 내부 머리둘레 사이즈 조절이 가능합니다.'),
       (100, 5, 'EX22SP07', 'EXTRAORDINARY PATTERN BEANIE', 'EX22SP07.jpg', 32000,
        '해당 제품은 브랜드 사정에 따라 3월 22일부터 순차배송 될 예정입니다.');


# 3. 고객 50명
DESC Customers;

INSERT INTO Customers (Name, EmailAddress, Password)
VALUES ('Seungjo', 'f1v3@kakao.com', 'seungjo1'),
       ('Jeongwoo', 'jeongwoo@naver.com', 'jeongwoo2'),
       ('Damho', 'damho@gmail.com', 'damho3'),
       ('Jaehun', 'jaehun@naver.com', 'jaehoon4'),
       ('Yongjun', 'yongjun@gmail.com', 'yongjun5'),
       ('Jessica', 'jessica@example.com', 'password6'),
       ('Dean', 'dean@example.com', 'password7'),
       ('Sophia', 'sophia@example.com', 'password8'),
       ('Minho', 'minho@example.com', 'password9'),
       ('Olivia', 'olivia@example.com', 'password10'),
       ('Andrew', 'andrew@example.com', 'password11'),
       ('Ava', 'ava@example.com', 'password12'),
       ('William', 'william@example.com', 'password13'),
       ('Isabella', 'isabella@example.com', 'password14'),
       ('Joseph', 'joseph@example.com', 'password15'),
       ('Mia', 'mia@example.com', 'password16'),
       ('James', 'james@example.com', 'password17'),
       ('Abigail', 'abigail@example.com', 'password18'),
       ('Benjamin', 'benjamin@example.com', 'password19'),
       ('Grace', 'grace@example.com', 'password20'),
       ('Henry', 'henry@example.com', 'password21'),
       ('Charlotte', 'charlotte@example.com', 'password22'),
       ('David', 'david@example.com', 'password23'),
       ('Elizabeth', 'elizabeth@example.com', 'password24'),
       ('Alex', 'alex@example.com', 'password25'),
       ('Victoria', 'victoria@example.com', 'password26'),
       ('John', 'john@example.com', 'password27'),
       ('Scarlett', 'scarlett@example.com', 'password28'),
       ('Ryan', 'ryan@example.com', 'password29'),
       ('Chloe', 'chloe@example.com', 'password30'),
       ('Samuel', 'samuel@example.com', 'password31'),
       ('Natalie', 'natalie@example.com', 'password32'),
       ('Ethan', 'ethan@example.com', 'password33'),
       ('Lily', 'lily@example.com', 'password34'),
       ('Willy', 'Willy@example.com', 'password35'),
       ('Avery', 'avery@example.com', 'password36'),
       ('Daniel', 'daniel@example.com', 'password37'),
       ('Harper', 'harper@example.com', 'password38'),
       ('Matthew', 'matthew@example.com', 'password39'),
       ('Sofia', 'sofia@example.com', 'password40'),
       ('Alexander', 'alexander@example.com', 'password41'),
       ('Amelia', 'amelia@example.com', 'password42'),
       ('Jessi', 'jessi@example.com', 'password43'),
       ('Mila', 'mila@example.com', 'password44'),
       ('Jenny', 'jenny@example.com', 'password45'),
       ('Ella', 'ella@example.com', 'password46'),
       ('Dave', 'dave@example.com', 'password47'),
       ('Aria', 'aria@example.com', 'password48'),
       ('Lee', 'lee@example.com', 'password49'),
       ('Emma', 'emma@example.com', 'password50');

# 4. 쇼핑몰에 사용될 쿼리문
# [4-1]. 고객이 로그인을 한 경우 (아이디 = EmailAddress, 비밀번호 = Password)
# 아이디가 f1v3@kakao.com, 비밀번호는 seungjo1인 회원
SELECT *
FROM Customers
WHERE EmailAddress = 'f1v3@kakao.com'
  AND Password = 'seungjo1';

# 아이디가 jessi@example.com, 비밀번호는 password43인 회원
SELECT *
FROM Customers
WHERE EmailAddress = 'jessi@example.com'
  AND Password = 'password43';

# [4-2]. 고객이 장바구니에 상품을 담는 경우
DESC ShoppingCart;

# CartID -> CustomerID (seungjo)
# 1번 회원이 93번의 상품을 1개 담은 경우
INSERT INTO ShoppingCart (CartID, Quantity, ProductID, DateCreated)
VALUES ('1', 1, 93, NOW());

INSERT INTO ShoppingCart (CartID, Quantity, ProductID, DateCreated)
VALUES ('1', 1, 17, NOW());

# 비회원일 경우 -> UUID(Random)로 하면 될 것 같은데 잘 모르겠음. (참고: https://www.mysqltutorial.org/mysql-uuid/)
# INSERT INTO ShoppingCart (CartID, Quantity, ProductID, DateCreated)
# VALUES (UUID(), 2, 84, NOW());

# 4번 회원이 83번 상품을 2개 담은 경우
INSERT INTO ShoppingCart (CartID, Quantity, ProductID, DateCreated)
VALUES ('4', 2, 83, NOW());

# 1번 회원의 장바구니에 93번 상품의 수량을 3개로 변경한 경우
UPDATE ShoppingCart
SET Quantity = 3
WHERE CartID = '1'
  AND ProductID = 93;

SELECT *
FROM ShoppingCart;


# # 1번 회원의 장바구니에 93번 상품을 삭제한 경우
# DELETE
# FROM ShoppingCart
# WHERE CartID = '1'
#   AND ProductID = 93;

# [4-3]. 고객이 상품을 주문한 경우 (주문 (ShipDate = 주문 예정일? 아니면 실제 도착일?)

# 1번 회원이 주문한 경우
INSERT INTO Orders
VALUES (1, 1, NOW(), NULL);

# 도착일은 3일 후로 설정
UPDATE Orders
SET ShipDate = DATE_ADD(OrderDate, INTERVAL 3 DAY)
WHERE OrderID = 1;

# 4번 회원이 주문한 경우
INSERT INTO Orders
VALUES (2, 4, NOW(), DATE_ADD(OrderDate, INTERVAL 5 DAY));

# [4-4]. 주문 상세
# 1번 회원의 주문 상세
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitCost)
VALUES (1, 93, 3, 98000);


# 4번 회원의 주문 상세
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitCost)
VALUES (2, 83, 2, 149000);

## [4-5]. 고객이 리뷰를 작성한 경우
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comments)
VALUES (18, 1, 5, '한글날을 기념해서 나온 옷이라길래 사봤는데 정말 마음에 드네요!'),
       (25, 2, 4, '추운 겨울에 입으려고 샀어요 ㅎㅎ'),
       (84, 4, 1, '나이키 믿고 샀는데 왜 이럼?'),
       (30, 29, 5, '측면에 있는 포인트가 정말 매력적입니다. 추천해요!'),
       (58, 3, 5, '잘 입겠습니다. 빠르게 배송와서 좋네요'),
       (98, 5, 5, '이쁜 모자 잘 받았습니다.'),
       (84, 32, 5, '믿고사는 나이키'),
       (84, 50, 4, '다 좋은데 배송이 너무 느리네요..');


# [4-6]. 장바구니에 담은 물건을 주문한 경우
# 6번 회원이 13번 상품 1개, 5번 상품 2개, 86번 상품 1개를 장바구니에 담음
INSERT INTO Orders
VALUES (4, 6, NOW(), DATE_ADD(OrderDate, INTERVAL 3 DAY));

SELECT *
FROM Orders;

INSERT INTO ShoppingCart (CartID, Quantity, ProductID, DateCreated)
VALUES ('6', 1, 13, NOW()),
       ('6', 2, 5, NOW()),
       ('6', 1, 86, NOW());

INSERT INTO OrderDetails
SELECT (SELECT OrderID
        FROM Orders
        WHERE CustomerID = 6), ProductID, Quantity, (SELECT UnitCost
                                        FROM Products
                                        WHERE ProductID = ShoppingCart.ProductID)
FROM ShoppingCart
WHERE CartID = '6';


SELECT *
FROM OrderDetails;

# 38번 회원이 28번 상품 2개를 장바구니에 담음
INSERT INTO ShoppingCart (CartID, Quantity, ProductID, DateCreated)
VALUES ('38', 2, 28, NOW());

# 38번 회원이 주문
INSERT INTO Orders
VALUES (3, 38, NOW(), DATE_ADD(OrderDate, INTERVAL 3 DAY));

# 주문 상세에 대한 정보는 장바구니에서 가져온다.
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitCost)
VALUES (3, 28, 2, (SELECT UnitCost
                   FROM Products
                   WHERE ProductID = 28));


# [4-7]. 초기 홈페이지 상품 구성
# 이름순으로 정렬 한다면 (오름차순)
SELECT *
FROM Products
ORDER BY ModelName ASC;

## 가격순으로 정렬한다면 (오름차순)
SELECT *
FROM Products
ORDER BY UnitCost ASC;


# [4-8]. 카테고리별 상품 보여주기
# 상의 카데고리인 상품만 가져오고 가격이 낮은 순으로 정렬해서 보여주기
SELECT *
FROM Products AS P JOIN Categories AS C
    ON  P.CategoryID = C.CategoryID
WHERE C.CategoryName = 'TOP'
ORDER BY P.UnitCost ASC;

# 아우터 카테고리인 상품만 가져오고 모델명을 기준으로 내림차순으로 정렬
SELECT *
FROM Products AS P JOIN Categories AS C
    ON P.CategoryID = C.CategoryID
WHERE C.CategoryName = 'OUTER'
ORDER BY P.ModelName DESC;

# [4-9]. 84번 상품에 대한 모든 리뷰 보여주기
SELECT *
FROM Reviews
WHERE ProductID = 84;