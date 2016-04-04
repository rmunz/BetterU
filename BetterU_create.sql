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
    Day date  NOT NULL,
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

INSERT INTO User (FirstName, LastName, Username, Password, Age, Gender, Height, Weight, Units, Email, Points, ActivityLevel, BMR, GoalType,
    GoalWeight, ActivityGoal, DailyChallengeIndex, DCSkipped, WeeklyChallengeIndex, WCSkipped, SecurityQuestion, SecurityAnswer) VALUES
('John', 'Doe', 'jdoe', 'password', '20', 'M', '65', '155', 'I', 'jdoe@vt.edu', '0', '0', '1724', '4', '130', 'Very Active', '4', '1', '2', '1', '3', 'Virginia'),
('Ralph', 'Jones', 'rjones', 'password', '25', 'M', '72', '185', 'I', 'rjones@vt.edu', '0', '1', '2200', '1', '200', 'Active', '5', '12', '6', '4', '0', 'Doe' ),
('Jessica', 'Smith', 'jsmith', 'password', '22', 'F', '61', '140', 'I', 'jsmith@vt.edu', '0', '1', '1800', '2', '160', 'Slightly Active', '2', '0', '4', '1', '5', '2005');

INSERT INTO Challenges (Name, Description, ChallengeType, PointsAwarded, Ind, GoalType) VALUES 
('Run a mile', 'Go outside and run a distance of one mile.', 'Daily', '20', '3', '2'),
('25 push ups', 'Do 25 push ups with a complete range of motion.', 'Daily', '25', '2', '1'),
('10 pull ups', 'Do 10 pull ups everyday for a week', 'Weekly', '50', '3', '4');

INSERT INTO Progress (id, Day, CaloriesIn, CaloriesOut, Weight, Miles, Steps) VALUES 
('1', '2016-03-16', '1800', '200', '157', '2', '2700'),
('2', '2016-03-16', '1970', '600', '158', '4', '3100'),
('3', '2016-03-17', '1530', '180', '160', '1', '2000'),
('4', '2016-03-17', '1620', '0', '153', '0', '5000');
