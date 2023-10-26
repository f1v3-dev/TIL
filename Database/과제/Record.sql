# 요구사항
# 파인만 레코드사에서 녹음을 한 음악가들은 MusicianID, Name Address, TelephoneNo를 가진다. 이 중 같은 주소를 사용하는 음악가들도 있는데, 한 주소에는 한 전화번호만 있다.
# 파인만 레코드사에서 녹음에 사용되는 악기는 종류(기타, 신디사이저, 드럼 등)와 음조(C, B-Flat 등)을 가진다.
# 녹음된 앨범은 Title, RegisteredDate, ISBN을 가진다.
# 모든 노래는 제목과 작가를 가진다.
# 한 음악가가 여러 악기를 연주할 수 있으며, 한 악기를 여러 음악가가 연주할 수 있다.
# 한 앨범에는 여러 곡이 들어있지만, 노래 한 곡은 단 하나의 앨범에만 수록 될 수 있다.
# 여러 음악가가 협동하여 한 곡을 만들 수 있고, 한 음악가는 여러 곡을 만들 수 있다.
# 각 앨범에는 프로듀서로 한 음악가가 참여한다. 한 음악가는 여러 곡의 프로듀서가 될 수 있다.

DROP DATABASE IF EXISTS record_label;

-- 1. 데이터베이스 생성
CREATE DATABASE record_label;
USE record_label;

-- 2. 테이블 생성

-- 2-1. 주소록 테이블: 같은 주소를 사용하는 음악가도 있고, 한 주소에는 한 전화번호만 있기에 테이블 분리
CREATE TABLE address_book
(
    Address   VARCHAR(30) PRIMARY KEY,
    Telephone VARCHAR(15) NOT NULL
);

-- 2-2. 음악가 테이블
CREATE TABLE musician
(
    MusicianID INT PRIMARY KEY,
    Name       VARCHAR(30) NOT NULL,
    Address    VARCHAR(30),
    FOREIGN KEY (Address) REFERENCES address_book (Address)
);

-- 2-3. 악기 테이블
CREATE TABLE instrument
(
    InstrumentName VARCHAR(30) PRIMARY KEY,
    Pitch          VARCHAR(30) NOT NULL
);

-- 2.4 음악가-악기 연주 테이블 (중간 테이블)
CREATE TABLE musician_instrument
(
    MusicianID     INT,
    InstrumentName VARCHAR(30),
    PRIMARY KEY (MusicianID, InstrumentName),
    FOREIGN KEY (MusicianID) REFERENCES musician (MusicianID),
    FOREIGN KEY (InstrumentName) REFERENCES instrument (InstrumentName)
);


-- 2.5 노래 테이블
CREATE TABLE music
(
    MusicID INT PRIMARY KEY AUTO_INCREMENT,
    Title   VARCHAR(50) NOT NULL,
    Writer  VARCHAR(50) NOT NULL
);

-- 2.6 음악가-노래 참여 테이블 (중간 테이블)
CREATE TABLE musician_music
(
    MusicianID INT,
    MusicID    INT,
    PRIMARY KEY (MusicianID, MusicID),
    FOREIGN KEY (MusicianID) REFERENCES musician (MusicianID),
    FOREIGN KEY (MusicID) REFERENCES music (MusicID)
);


-- 2.7 앨범 테이블 (프로듀서는 한 음악가가 참여한다.)
CREATE TABLE album
(
    ISBN           VARCHAR(13) PRIMARY KEY,
    Title          VARCHAR(30) NOT NULL,
    RegisteredDate DATE        NOT NULL,
    ProducerID     INT         NOT NULL,
    FOREIGN KEY (ProducerID) REFERENCES musician (MusicianID)
);


-- 2.8 앨범-노래 테이블 (한 곡은 단 하나의 앨범에만 수록)
CREATE TABLE album_music
(
    ISBN    VARCHAR(13),
    MusicID INT,
    PRIMARY KEY (ISBN, MusicID),
    FOREIGN KEY (ISBN) REFERENCES album (ISBN),
    FOREIGN KEY (MusicID) REFERENCES music (MusicID)
);


show tables;