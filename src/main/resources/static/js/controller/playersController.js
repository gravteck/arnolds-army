app.controller('playersController', ($scope, $http, playerService) => {

	$scope.loader = {
		loading : false
	};
	
	var loading = (value) => $scope.loader.loading = value;

	$scope.playerStatus = {
		saved : false,
		deleted : false
	}

	$scope.loadPlayers = function() {

		playerService.query(loading(true))
		  .$promise
		    .then(players => {
		      $scope.players = players
		      loading(false)
		    }, loading(false));
	}

	$scope.$on("playerSaved", (event, args) => { $scope.playerStatus.saved = args.saved });

	angular.element(document).ready(function() {
		$scope.loadPlayers();
	});

});