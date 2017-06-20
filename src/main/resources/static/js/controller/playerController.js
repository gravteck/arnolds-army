app.controller("playerController", function($scope, $http, $location,
		playerService, urlService) {

	$scope.loader = {
		loading : false,
	};
	
	var loading = (value) => $scope.loader.loading = value;

	$scope.resetPlayer = function() {
		$scope.editedPlayer = new playerService.resource;
	}

	angular.element(document).ready(function() {

		$scope.resetPlayer();

		var playerId = urlService.entityId();

		playerService.get(playerId, loading(true))
		  .$promise
		    .then(player => {
		      $scope.player = player;
		      loading(false);
		    }, () => loading(false));

	});
});