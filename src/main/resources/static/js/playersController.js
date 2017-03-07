app.controller('playersController', function($scope, $http) {
	$http.get("/players/list").then(function(response) {
		$scope.players = response.data;
	});

	$scope.setCurrentPlayer = function(playerId) {
		$http.get("/players/" + playerId).then(function(response) {
			$scope.currentPlayer = response.data;
		});
	}
});