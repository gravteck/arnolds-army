app.controller('adminCtrl', function($scope, PLAYER_SAVED) {

	$scope.resetPlayerStatus = function() {
		$scope.playerStatus = {
			saved : false,
			deleted : false
		}
	}

	$scope.$on("$routeChangeSuccess", function() {
		$scope.resetPlayerStatus();
	});

	$scope.$on(PLAYER_SAVED, function() {
		$scope.playerStatus.saved = true;
		$scope.$broadcast(PLAYER_SAVED);
	});

	var rootInitialized = false;

	angular.element(document).ready(function(playerId) {

		if (!rootInitialized) {
			// $scope.loadPlayers();
			$scope.resetPlayerStatus();
			rootInitialized = true;
		}

		// $scope.loadPlayers();
	});

});