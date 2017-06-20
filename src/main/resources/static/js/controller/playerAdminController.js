app.controller("playerAdminCtrl", ($scope, $rootScope, $http,
		$location, playerService, urlService, EMIT_ADMIN_ENTITY_SAVED) => {

	$scope.loader = {
		loading : false
	};

	var loading = value => $scope.loader.loading = value;

	$scope.resetPlayer = () => $scope.editedPlayer = new playerService.resource;

	$scope.save = editedPlayer => {
		playerService.save(editedPlayer, function(response) {
			$location.path("admin/players");
			$scope.resetPlayer();
			$rootScope.$emit(EMIT_ADMIN_ENTITY_SAVED);
		});
	}

	angular.element(document)
			.ready(
					function() {
						if (urlService.add()) {
							$scope.resetPlayer();
						} else if (urlService.edit()) {
							playerService.get(urlService.entityId(),
									loading(true)).$promise.then( player => {
								$scope.editedPlayer = player;
								loading(false);
							}, () => loading(false));
						}
					});
});