create database ledDatabase;

use ledDatabase;
show tables;

CREATE TABLE Message (
    messageId INT AUTO_INCREMENT PRIMARY KEY,
    senderId INT NOT NULL,
    recipientId INT NOT NULL,
    content TEXT NOT NULL,
    sentTime DATETIME NOT NULL
);

Describe users;
select * from users;