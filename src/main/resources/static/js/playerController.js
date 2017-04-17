app.controller('playerController', function($scope, $http, $location) {

	$scope.loader = {
		loading : false,
	};

	angular.element(document).ready(function(playerId) {

		$scope.loader.loading = true;

		var playerId = $location.absUrl().split(/[\s/]+/).pop();

		$http.get("/player/get/" + playerId).then(function(response) {
			$scope.player = response.data;

			$scope.loader.loading = false;
		});
	})
});