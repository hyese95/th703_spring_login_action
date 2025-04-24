DROP DATABASE IF EXISTS login_study;
DROP USER IF EXISTS 'login_study_dev'@'%';
CREATE DATABASE login_study CHAR SET utf8;
CREATE USER 'login_study_dev'@'%' IDENTIFIED BY '12345678';
GRANT SELECT,INSERT,UPDATE,DELETE ON login_study.* TO  'login_study_dev'@'%';
flush PRIVILEGES;
USE login_study;
CREATE TABLE users(
    id VARCHAR(255) PRIMARY KEY,
    pw VARCHAR(255),
    picture VARCHAR(255),
    oauth ENUM('GOOGLE','KAKAO','NAVER','GITHUB'),
    role ENUM('GUEST','USER','MANAGER','ADMIN'),
    name VARCHAR(255) NOT NULL
);
INSERT INTO users(id,pw,role,name)
VALUES ('user1','$2a$10$COBGaSoDQXJDtb0xj9P8puB4mQmak7ylgXzm6ifs8wDFHtvdUY0FS','USER','사용자'),
       ('guest1','$2a$10$COBGaSoDQXJDtb0xj9P8puB4mQmak7ylgXzm6ifs8wDFHtvdUY0FS','GUEST','게스트'),
       ('manager1','$2a$10$COBGaSoDQXJDtb0xj9P8puB4mQmak7ylgXzm6ifs8wDFHtvdUY0FS','MANAGER','매니저'),
       ('admin1','$2a$10$COBGaSoDQXJDtb0xj9P8puB4mQmak7ylgXzm6ifs8wDFHtvdUY0FS','ADMIN','관리자');