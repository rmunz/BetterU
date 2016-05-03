CREATE EVENT progressEntryCreation
    ON SCHEDULE
      EVERY 1 DAY
      STARTS '2016-05-04 00:00:00' ON COMPLETION PRESERVE ENABLE 
    DO
      INSERT INTO BetterU.Progress(UserId, LogDate, CaloriesIn, CaloriesOut, Weight, Miles, Steps) SELECT DISTINCT id, UNIX_TIMESTAMP(CURDATE()), '0', '0', '0', '0', '0' FROM User;
