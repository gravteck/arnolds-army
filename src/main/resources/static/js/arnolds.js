var app = angular.module("arnoldsArmyApplication",
		[ "ngRoute", "ui.bootstrap" ], function($locationProvider) {
			$locationProvider.html5Mode({
				enabled : true,
				requireBase : true
			});
		});

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
	});
})