app.controller('calendarController', ($scope, $http, $location) => {

	$scope.loader = {
		loading : false,
	};
	
	var loading = (value) => $scope.loader.loading = value;

	angular.element(document).ready(function() {
	  
	  loading(true);

		$http.get("/seasons/list").then(response => {
			$scope.seasons = response.data;
			loading(false);
		});
	})

	$scope.arnoldsId = 1;

});