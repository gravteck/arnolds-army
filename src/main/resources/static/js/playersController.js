app.controller('playersController', function($scope, $http) {

	$scope.loader = {
		loading : false,
	};

	angular.element(document).ready(function() {

		$scope.loader.loading = true;

		$http.get("/players/list").then(function(response) {
			$scope.players = response.data;

			$scope.loader.loading = false;
		});
	})
});