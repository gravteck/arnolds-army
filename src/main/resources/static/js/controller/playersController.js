app.controller('playersController', function($scope, $http, playerService) {

	$scope.loader = {
		loading : false,
	};

	$scope.playerStatus = {
		saved : false,
		deleted : false
	}

	$scope.loadPlayers = function() {
		$scope.loader.loading = true;

		$scope.players = playerService.resource.query(function(response) {
			$scope.loader.loading = false;
		});
	}

	$scope.$on("playerSaved", function(event, args) {
		$scope.playerStatus.saved = args.saved;
	});

	angular.element(document).ready(function() {
		$scope.loadPlayers();
	});

});