-- Table Challenges
CREATE TABLE Challenges (
    Name varchar(255)  NOT NULL,
    Description varchar(255)  NOT NULL,
    ChallengeType varchar(255)  NOT NULL,
    PointsAwarded int  NOT NULL,
    Ind int  NOT NULL,
    GoalType int  NOT NULL,
    CONSTRAINT Challenges_pk PRIMARY KEY (Name)
);

-- Table Progress
CREATE TABLE Progress (
    id int NOT NULL,
    Day int  NOT NULL,
    CaloriesIn int,
    CaloriesOut int,
    Weight int,
    Miles int, 
    Steps int,
    CONSTRAINT Progress_pk PRIMARY KEY (id)
);

-- Table User
CREATE TABLE User (
    id int  NOT NULL AUTO_INCREMENT,
    FirstName varchar(255)  NOT NULL,
    LastName varchar(255)  NOT NULL,
    Username varchar(255)  NOT NULL,
    Password varchar(64)  NOT NULL,
    Age int  NOT NULL,
    Gender char(1)  NOT NULL,
    Height int  NOT NULL,
    Weight int  NOT NULL,
    Units char(1)  NOT NULL,
    Email varchar(255)  NOT NULL,
    Points int  NOT NULL,
    ActivityLevel int  NOT NULL,
    security_question INT NOT NULL,
    security_answer VARCHAR (255) NOT NULL,
    BMR int  NOT NULL,
    GoalType int,
    GoalWeight int  NOT NULL,
    ActivityGoal varchar(255)  NOT NULL, 
    DailyChallengeIndex int,
    DCSkipped varchar(255),
    WeeklyChallengeIndex int,
    WCSkipped int,
    SecurityQuestion int NOT NULL,
    SecurityAnswer varchar(255) NOT NULL,   
    Breakfast varchar(255),
    Lunch varchar(255),
    Dinner varchar(255),
    Snack varchar(255),
    Photo varchar(62000),
    CONSTRAINT User_pk PRIMARY KEY (id)
);

-- User/Progress Relation
CREATE TABLE UsersProgress (
    uid int REFERENCES User(id),
    pid int REFERENCES Progress(id),
    PRIMARY KEY (uid, pid)
);

-- User profile picture
CREATE TABLE Photo
(
   id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
   extension ENUM('jpeg', 'jpg', 'png') NOT NULL,
   user_id INT,
   FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

