app.controller("addTeamCtrl", function($scope, $rootScope, $http, $location,
		teamService, EMIT_ADMIN_ENTITY_SAVED) {

	$scope.resetTeam = function() {
		$scope.editedTeam = new teamService.resource;
	}

	$scope.save = function(editedTeam) {
		teamService.save(editedTeam, function(response) {
			$location.path("admin/teams")
			$scope.resetTeam();

			$rootScope.$emit(EMIT_ADMIN_ENTITY_SAVED);
		});
	}

	angular.element(document).ready(function() {
		$scope.resetTeam();
	});
});