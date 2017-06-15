app.controller("seasonCtrl", function($scope, urlService, seasonService) {

	$scope.loader = {
		loading : false
	};

	angular.element(document).ready(function() {
		var seasonId = urlService.entityId();

		$scope.loader.loading = true;

		$scope.season = seasonService.get(seasonId);

		$scope.loader.loading = false;
	});
});