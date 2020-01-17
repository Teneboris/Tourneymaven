create database Tourney;
use Tourney;

select * from Tourneyplan;

Drop table if exists Tourneyplan;
CREATE TABLE Tourneyplan (
     TourneyplanID int auto_increment,
     Titel varchar(255),
     Ort varchar(255),
     VonDate date,
     BisDate date,
     Link varchar(255) ,
     primary key(TourneyplanID)
    );



ALTER TABLE `Tourneyplan`
  ADD COLUMN `VonDate` date,
  ADD COLUMN `BisDate` date;
