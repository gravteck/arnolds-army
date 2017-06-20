app.controller("addSeasonCtrl", ($scope, $rootScope, $http, $location,
		seasonService, EMIT_ADMIN_ENTITY_SAVED) => {

	$scope.resetSeason = () => { $scope.editedSeason = new seasonService.resource; }

	$scope.save = (editedSeason) => {
		seasonService.save(editedSeason, function(response) {
			$location.path("admin/seasons");
			$scope.resetSeason();
			$rootScope.$emit(EMIT_ADMIN_ENTITY_SAVED);
		});
	}

	angular.element(document).ready(function() {
		$scope.resetSeason();
	});
});