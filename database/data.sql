-- match service
-- script sql di dati fittizzi usati per il test

-- tabella squadre
INSERT INTO match_pool.team (name) VALUES
	 ('inter'),
	 ('milan'),
	 ('roma'),
	 ('lazio');

-- tabella partite
INSERT INTO match_pool.match_info (home_score,away_score,start_time,end_time,`status`,home_team,away_team) VALUES
	 (0,0,'2023-01-02 12:39:28',NULL,'A',1,3);

