app.controller("addPlayerCtrl", function($scope, $rootScope, $http, $location,
		playerService, EMIT_ADMIN_ENTITY_SAVED) {

	$scope.resetPlayer = function() {
		$scope.editedPlayer = playerService.resource;
	}

	$scope.save = function(editedPlayer) {
		playerService.save(editedPlayer, function(response) {
			$location.path("admin/players");
			$scope.resetPlayer();

			// broadcastService.send("playerSaved", {saved: true});

			$rootScope.$emit(EMIT_ADMIN_ENTITY_SAVED);
		});
	}

	angular.element(document).ready(function(playerId) {
		$scope.resetPlayer();
	});
});