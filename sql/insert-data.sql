insert into player values(
	1,
	'Matt',
	'Heineke',
	'5134047250',
	'matthew.heineke@gmail.com'
);

insert into player values(
	2,
	'Ray',
	'Piening',
	'5138059969',
	'<need.email@email.com>'
);

insert into player values(
	3,
	'Eric',
	'Ihlendorf',
	'5132314332',
	'<need.email@email.com>'
);

insert into player values(
	4,
	'Rob',
	'WeidMarschen',
	'5131314092',
	'<need.email@email.com>'
);

insert into season values(1, 1);
insert into season values(2, 2);
insert into season values(3, 3);

insert into team values(1, 'Arnolds Army');
insert into team values(2, 'Team 2');
insert into team values(3, 'Team 3');
insert into team values(4, 'Team 4');

insert into statistical_year values(
	1,
	1,
	1,
	526,
	69,
	156,
	84,
	59,
	102
);

insert into statistical_year values(
	2,
	1,
	2,
	469,
	82,
	151,
	84,
	70,
	106
);

insert into game values(
	1, 
	current_timestamp - interval '14 day',
	1,
	2,
	3,
	4,
	1
);

insert into game values(
	2, 
	current_timestamp - interval '7 day',
	1,
	3,
	9,
	2,
	1
);

insert into game values(
	3, 
	current_timestamp - interval '14 day',
	2,
	1,
	9,
	4,
	2
);

insert into game values(
	4, 
	current_timestamp - interval '7 day',
	3,
	1,
	9,
	12,
	2
);