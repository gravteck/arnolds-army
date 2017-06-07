var app = angular.module("arnoldsArmyApplication", [ "ngRoute", "ui.bootstrap",
		"ngResource" ], function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		requireBase : true
	});
});

app.constant("baseApiUrl", "http://localhost:5000/api/");
app.constant("PLAYER_SAVED", "playerSaved");

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

	var playerStatus = {
		saved : false,
		deleted : false
	};

	return {
		resource : Player,
		get : function(playerId, success, error) {
			return playersResource.get({
				id : playerId
			}, success, error)
		},
		save : function(player, success, error) {
			return new Player(player).$save({}, success, error)
		},
		query : function(success, error) {
			return playersResource.query({}, success, error)
		},
		remove : function(player, success, error) {
			return player.$remove({
				id : playerId
			}, success, error)
		}
	}
});