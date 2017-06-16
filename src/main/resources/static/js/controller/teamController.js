app.controller("teamCtrl", function($scope, urlService, teamService) {

	$scope.loader = {
		loading : false
	};

	angular.element(document).ready(function() {
		var teamId = urlService.entityId();

		$scope.loader.loading = true;

		$scope.team = teamService.get(teamId);

		$scope.loader.loading = false;
	});
});