app.controller('calendarController', function($scope, $http, $location) {
	$http.get("/s/seasons").then(function(response) {
		$scope.seasons = response.data;
	});
	
	$scope.arnoldsId = 1;
});