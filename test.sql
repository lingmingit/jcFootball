
USE jcFootball;


SELECT COUNT(*) FROM t_jc_football WHERE jcDate = '2015-03-09'
SELECT * FROM t_jc_football WHERE jcDate = '2015-03-09'

SELECT * FROM t_jc_football WHERE homeTeamName = '那不勒斯'
SELECT * FROM t_jc_football  WHERE gameno ='5033';

SELECT * FROM t_jc_football WHERE matchName= '美职联'

SELECT * FROM t_jc_football WHERE gn='5028' AND gameno='5028' AND dataGamecode='64373' AND dataGameid='430830' AND letDataGameid='430846'

SELECT * FROM t_jc_football WHERE DATE_FORMAT(createTime , '%Y-%m-%d') = '2015-03-11';


SELECT DATE_FORMAT(createTime , '%Y-%m-%d') FROM t_jc_football

DELETE FROM t_jc_football WHERE jcDate = '2015-03-09'
DELETE FROM t_jc_football WHERE id = '402896e74be87a6f014be93573c50021'


SELECT * FROM t_jc_footballresult;
SELECT COUNT(*) FROM t_jc_footballresult WHERE jcDate = '2015-02-27'; 33
SELECT * FROM t_jc_footballresult WHERE jcDate = '2015-02-27';


SELECT * FROM t_jc_matchtype;




SELECT MAX(CAST(SUBSTRING(numbers , 11) AS SIGNED INTEGER)) FROM t_jc_fbLottery;
SELECT MAX(CAST(SUBSTRING(numbers,11) AS UNSIGNED INTEGER)) FROM t_jc_fbLottery;

SELECT MAX(CAST(SUBSTRING(numbers,11) AS SIGNED INTEGER)) FROM t_jc_fbLottery

SELECT * FROM t_jc_fbLottery;
SELECT * FROM t_jc_fbLotteryEntry WHERE parent_Id = '402896e74c066fe8014c06ad216c0007';

SELECT * FROM t_jc_football WHERE id IN( '402896e74c025959014c026050ca0024' , '402896e74c025959014c026050b10022' , '402896e74c025959014c0260509e0021')


SELECT * FROM t_jc_fbLottery WHERE CreateTime;
SELECT * FROM t_jc_fbLotteryEntry;


DELETE FROM t_jc_fbLotteryEntry;
DELETE FROM t_jc_fbLottery;



/***备份表sql语句***/
DROP TABLE t_bak_jc_football;
CREATE TABLE t_bak_jc_football (SELECT * FROM t_jc_football); 
SELECT * FROM t_bak_jc_football;






