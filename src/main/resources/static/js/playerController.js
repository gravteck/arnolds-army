app.controller('playerController', function($scope, $http, $location) {

	angular.element(document).ready(function(playerId) {
		var playerId = $location.absUrl().split(/[\s/]+/).pop();

		$http.get("/s/player/get/" + playerId).then(function(response) {
			$scope.player = response.data;
		});
	})
});