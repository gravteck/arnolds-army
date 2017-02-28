	create table player(
		id integer not null,
		first_name varchar(255),
		last_name varchar(255),
		phone varchar(255),
		email varchar(255)
	);

	alter table player add primary key(id);

	create table season(
		id integer not null,
		year integer,
		last_name varchar(255)
	);

	alter table season add primary key(id);

	create table statistical_year(
		id integer not null,
		player_id integer not null,
		season_id integer not null,
		at_bats integer,
		runs integer,
		hits integer,
		doubles integer,
		triples integer,
		home_runs integer,
		rbi integer,
		bb integer,
		k integer
	);

	alter table statistical_year add primary key(id);
	alter table statistical_year add constraint fk_statistical_year_to_player foreign key(player_id) references player(id);
	alter table statistical_year add constraint fk_statistical_year_to_season foreign key(season_id) references season(id);
	
	create table team(
		id integer not null,
		name varchar(255)
	);

	alter table team add primary key(id);

	create table game(
		id integer not null,
		local_date_time TIMESTAMP not null,
		home_team_id integer not null,
		away_team_id integer not null,
		home_score integer,
		away_score integer,
		season_id integer not null
	);

	alter table game add primary key(id);
	alter table game add constraint fk_game_home_team_to_team foreign key(home_team_id) references team(id);
	alter table game add constraint fk_game_away_team_to_team foreign key(away_team_id) references team(id);
	alter table game add constraint fk_game_season_to_season foreign key(home_team_id) references team(id);