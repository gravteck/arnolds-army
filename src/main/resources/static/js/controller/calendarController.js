app.controller('calendarController', function($scope, $http, $location) {

	$scope.loader = {
		loading : false,
	};

	angular.element(document).ready(function() {

		$scope.loader.loading = true;

		$http.get("/seasons/list").then(function(response) {
			$scope.seasons = response.data;

			$scope.loader.loading = false;
		});
	})

	$scope.arnoldsId = 1;
});