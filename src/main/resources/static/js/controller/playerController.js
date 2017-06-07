app.controller("playerController", function($scope, $http, $location,
		playerService, broadcastService) {

	$scope.loader = {
		loading : false,
	};

	$scope.resetPlayer = function() {
		$scope.editedPlayer = playerService.resource;
	}

	$scope.save = function(editedPlayer) {
		playerService.save(editedPlayer, function(response) {
			$location.path("admin/players");
			$scope.resetPlayer();

			broadcastService.send("playerSaved", {saved: true});
		});
	}

	angular.element(document).ready(function(playerId) {

		$scope.resetPlayer();
		$scope.loader.loading = true;

		var playerId = $location.absUrl().split(/[\s/]+/).pop();

		/*
		 * $http.get("/player/get/" + playerId).then(function(response) {
		 * $scope.player = response.data;
		 * 
		 * $scope.loader.loading = false; });
		 */

		/*
		 * playerService.get(playerId, function(response) { $scope.player =
		 * response });
		 */

		$scope.player = playerService.resource.get({
			id : playerId
		});

		$scope.loader.loading = false;

	});
});