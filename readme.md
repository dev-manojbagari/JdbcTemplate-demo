CREATE TABLE Student(
   ID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);


DELIMITER $$

DROP PROCEDURE IF EXISTS companyHR.getRecord $$
CREATE PROCEDURE companyHR.getRecord (
IN in_id INTEGER,
OUT out_name VARCHAR(20),
OUT out_age  INTEGER)
BEGIN
   SELECT name, age
   INTO out_name, out_age
   FROM Student where id = in_id;
END $$

DELIMITER ;
USE `companyhr`;
DROP function IF EXISTS `get_student_name`;

DELIMITER $$
USE `companyhr`$$
CREATE FUNCTION companyhr.get_student_name(in_id INTEGER)
RETURNS varchar(200) READS SQL DATA
BEGIN
DECLARE out_name VARCHAR(200);
   SELECT name
   INTO out_name
   FROM Student where id = in_id;
RETURN out_name; 
End$$

DELIMITER ;