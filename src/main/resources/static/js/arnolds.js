var app = angular.module('arnoldsArmyApplication', []);

app.directive('jumbotron', function() {
	return {
		restrict : 'E',
		template : '<div class="jumbotron container-fluid text-center">'
				+ '<h1>Arnolds Army</h1>' + '</div>'
	};
});

app
		.directive(
				'top-nav',
				function() {
					return {
						template : '<nav class="navbar navbar-inverse">'
								+ '<div class="container-fluid">'
								+ '<div class="navbar-header">'
								+ '<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">'
								+ '<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>'
								+ '</button>'
								+ '<a class="navbar-brand" href="/home">{{title}}</a>'
								+ '</div>'
								+ '<div class="collapse navbar-collapse" id="myNavbar">'
								+ '<ul class="nav navbar-nav">'
								+ '<li><a class="active" href="/players">Players</a></li>'
								+ '<li><a href="/calendar">Calendar</a></li>'
								+ '<li><a href="/contact">Contact Us</a></li>'
								+ '</ul>' + '</div>' + '</div>' + '</nav>'
					};
				});