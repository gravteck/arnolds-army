app.controller("addPlayerCtrl", function($scope, $http, $location,
		playerService, PLAYER_SAVED) {
	
	$scope.resetPlayer = function() {
		$scope.editedPlayer = playerService.resource;
	}
	
	$scope.save = function(editedPlayer) {
		playerService.save(editedPlayer, function(response) {
			$location.path("admin/players");
			$scope.resetPlayer();

			//broadcastService.send("playerSaved", {saved: true});
			
			$scope.$emit(PLAYER_SAVED);
		});
	}
	
	angular.element(document).ready(function(playerId) {
		$scope.resetPlayer();
	});
});