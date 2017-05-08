app.controller('adminController', function($scope, $http) {

	$scope.loader = {
		loading : false,
	};

	angular.element(document).ready(function() {

		$scope.loader.loading = true;

		$http.get("/admin/players/get/list").then(function(response) {
			$scope.dto = response.data;

			$scope.loader.loading = false;
		});
	})
});