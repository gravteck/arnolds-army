app.controller("teamAdminCtrl", function($scope, $rootScope, $http, $location,
		teamService, urlService, EMIT_ADMIN_ENTITY_SAVED) {

	$scope.loader = {
		loading : false
	};

	var loading = (value) => $scope.loader.loading = value;

	$scope.resetTeam = () => $scope.editedTeam = new teamService.resource;

	$scope.save = (editedTeam) => {
		teamService.save(editedTeam, function(response) {
			$location.path("admin/teams")
			$scope.resetTeam();

			$rootScope.$emit(EMIT_ADMIN_ENTITY_SAVED);
		});
	}

	angular.element(document).ready(() => {

		if (urlService.edit()) {
			teamService.get(urlService.entityId(), loading(true))
				.$promise.then(team => {
						$scope.editedTeam = team; 
						loading(false)
					}, 
					() => loading(false));
		} else {
		  $scope.resetTeam();
		}
	});
});