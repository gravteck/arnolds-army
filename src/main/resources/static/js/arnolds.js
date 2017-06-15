var app = angular.module("arnoldsArmyApplication", [ "ngRoute", "ui.bootstrap",
		"ngResource" ], function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		requireBase : true
	});
});

app.constant("baseApiUrl", "http://localhost:5000/api/");
app.constant("EMIT_ADMIN_ENTITY_SAVED", "emitAdminEntitySaved");
app.constant("BROADCAST_ADMIN_ENTITY_SAVED", "broadcastAdminEntitySaved");
app.constant("EMIT_ADMIN_ENTITY_DELETED", "emitAdminEntityDeleted");
app.constant("BROADCAST_ADMIN_ENTITY_DELETED", "broadcastAdminEntityDeleted");
app.constant("arnoldsId", 1);

app.value("adminEntityStatus", {
	status : {}
});

app.value("functionalArea", {
	data : {
		area : ""
	}
})

app
		.filter(
				"gameItem",
				[
						"arnoldsId",
						function(arnoldsId) {
							return function(game, format) {

								if (format === "time") {
									var hour = game.localDateTime.hour > 12 ? game.localDateTime.hour - 12
											: game.localDateTime.hour;

									var minute = game.minuteInterval;

									var period = game.localDateTime.hour > 12 ? "pm"
											: "am";

									return hour + ":" + minute + " " + period;
								} else if (format === "date") {
									return game.localDateTime.monthValue + "/"
											+ game.localDateTime.dayOfMonth
											+ "/" + game.localDateTime.year;
								} else if (format === "matchup") {
									return arnoldsId === game.homeTeam.id ? 'vs. '
											+ game.awayTeam.name
											: '@ ' + game.homeTeam.name;
								} else if (format === "winLoss") {
									return arnoldsId == game.homeTeam.id ? game.homeScore > game.awayScore ? 'W'
											: 'L'
											: game.homeScore < game.awayScore ? 'W'
													: 'L';
								} else if (format === "score") {
									return arnoldsId == game.homeTeam.id ? game.homeScore
											+ ' - ' + game.awayScore
											: game.awayScore + ' - '
													+ game.homeScore;
								}
							}
						} ]);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "/static/html/home.html"
	}).when("/home", {
		templateUrl : "/static/html/home.html"
	}).when("/players", {
		templateUrl : "/static/html/players.html"
	}).when("/player/:id", {
		templateUrl : "/static/html/player.html"
	}).when("/calendar", {
		templateUrl : "/static/html/calendar.html"
	}).when("/contact", {
		templateUrl : "/static/html/contact.html"
	}).when("/admin", {
		templateUrl : "/static/html/admin/admin.html"
	}).when("/admin/login", {
		templateUrl : "/static/html/admin/login.html"
	}).when("/admin/player/add", {
		templateUrl : "/static/html/admin/player-add.html"
	}).when("/admin/players", {
		templateUrl : "/static/html/admin/admin.html"
	}).when("/admin/teams", {
		templateUrl : "/static/html/admin/admin.html"
	}).when("/admin/team/add", {
		templateUrl : "/static/html/admin/team-add.html"
	}).when("/admin/games", {
		templateUrl : "/static/html/admin/admin.html"
	}).when("/admin/seasons", {
		templateUrl : "/static/html/admin/admin.html"
	}).when("/admin/statistical-years", {
		templateUrl : "/static/html/admin/admin.html"
	});
});

app.factory('broadcastService', function($rootScope) {
	return {
		send : function(msg, args) {
			var val = $rootScope.$broadcast(msg, args);
		}
	}
});

app.factory("playerService", function($resource, baseApiUrl) {
	var Player = $resource(baseApiUrl + "players/:id", {
		id : "@id"
	});

	// TODO implement all resource methods
	return {
		resource : Player,
		get : function(playerId, success, error) {
			return Player.get({
				id : playerId
			}, success, error)
		},
		save : function(player, success, error) {
			return new Player(player).$save({}, success, error)
		},
		query : function(success, error) {
			return Player.query({}, success, error)
		},
		remove : function(player, success, error) {
			return player.$remove({
				id : playerId
			}, success, error)
		}
	}
});

app.factory("teamService", function($resource, baseApiUrl) {
	var Team = $resource(baseApiUrl + "teams/:id", {
		id : "@id"
	});

	// TODO implement all resource methods
	return {
		resource : Team,
		fresh : function() { return new Team(); },
		get : function(teamId, success, error) {
			return Team.get({
				id : teamId
			}, success, error)
		},
		save : function(team, success, error) {
			return new Team(team).$save({}, success, error)
		},
		query : function(success, error) {
			return Team.query({}, success, error)
		},
		remove : function(team, success, error) {
			return team.$remove({
				id : teamId
			}, success, error)
		}
	}
});

app.factory("seasonService", function($resource, baseApiUrl) {
	var Season = $resource(baseApiUrl + "seasons/:id", {
		id : "@id"
	});

	return {
		resource : Season,
		get : function(seasonId, success, error) {
			return Season.get({
				id : seasonId
			}, success, error)
		},
		save : function(season, success, error) {
			return new Season(season).$save({}, success, error)
		},
		query : function(success, error) {
			return Season.query({}, success, error)
		},
		remove : function(season, success, error) {
			return season.$remove({
				id : seasonId
			}, success, error)
		}
	}
});
