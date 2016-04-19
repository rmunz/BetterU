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
('5', '1460592000', '1800', '200', '157', '2', '2700'),
('5', '1460332800', '1800', '200', '157', '2', '2700'),
('5', '1457481600', '1800', '200', '157', '2', '2700'),
('5', '1457913600', '1800', '200', '157', '2', '2700'),
('5', '1459468800', '1800', '200', '157', '2', '2700'),
('5', '1461110400', '1800', '200', '157', '2', '2700'),
('1', '1461110400', '1970', '600', '158', '4', '3100'),
('2', '1461110400', '1970', '600', '158', '4', '3100'),
('3', '1457481600', '1530', '180', '160', '1', '2000'),
('4', '1459468800', '1620', '0', '153', '0', '5000');