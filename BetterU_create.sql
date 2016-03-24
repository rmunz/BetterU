-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-03-24 18:47:45.761




-- tables
-- Table Challenges
CREATE TABLE Challenges (
    Name varchar(255)  NOT NULL,
    Description varchar(255)  NOT NULL,
    ChallengeType varchar(255)  NOT NULL,
    PointsAwarded int  NOT NULL,
    CONSTRAINT Challenges_pk PRIMARY KEY (Name)
);

-- Table Goal
CREATE TABLE Goal (
    id int  NOT NULL,
    GoalWeight int  NOT NULL,
    FinishDate int  NOT NULL,
    ActivityGoal varchar(255)  NOT NULL,
    MuscleGroup varchar(255)  NOT NULL,
    Steps int  NOT NULL,
    CONSTRAINT Goal_pk PRIMARY KEY (id)
);

-- Table Progress
CREATE TABLE Progress (
    id int  NOT NULL,
    Day date  NOT NULL,
    CaloriesIn int  NOT NULL,
    CaloriesOut int  NOT NULL,
    Weight int  NOT NULL,
    Carbs int  NOT NULL,
    Protein int  NOT NULL,
    Fat int  NOT NULL,
    Steps int  NOT NULL,
    CONSTRAINT Progress_pk PRIMARY KEY (id)
);

-- Table User
CREATE TABLE User (
    id int  NOT NULL,
    FirstName varchar(255)  NOT NULL,
    LastName varchar(255)  NOT NULL,
    Username varchar(255)  NOT NULL,
    Password varchar(64)  NOT NULL,
    Age int  NOT NULL,
    Gender char(1)  NOT NULL,
    Height int  NOT NULL,
    Weight int  NOT NULL,
    GoalID int  NOT NULL,
    Units char(1)  NOT NULL,
    Email varchar(255)  NOT NULL,
    Points int  NOT NULL,
    ActivityLevel int  NOT NULL,
    BMR int  NOT NULL,
    CurrentDailyChallenge varchar(255)  NOT NULL,
    CurrentWeeklyChallenge varchar(255)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
);






-- End of file.