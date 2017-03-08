app.controller('playersController', function($scope, $http) {

	angular.element(document).ready(function() {
		$http.get("/players/list").then(function(response) {
			$scope.players = response.data;
		});
	})
});