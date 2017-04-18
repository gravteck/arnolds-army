app.controller('adminController', function($scope, $http) {

	$scope.loader = {
		loading : false,
	};

	$scope.entityStatus = {
		saved : false,
		deleted : false
	}

	$scope.functionalArea = "";
	$scope.title = "Title";

	angular.element(document).ready(function() {

		$scope.loader.loading = true;

		$http.get("/admin/players/list").then(function(response) {
			$scope.list = response.data;

			$scope.loader.loading = false;
		});
	})
});