app.controller('adminListCtrl', function($scope, $http, playerService,
		BROADCAST_ADMIN_ENTITY_SAVED, EMIT_ADMIN_ENTITY_DELETED) {

	$scope.loader = {
		loading : false,
	};

	$scope.$on(BROADCAST_ADMIN_ENTITY_SAVED, function() {
		$scope.loadList();
	});

	$scope.loadList = function() {

		$scope.loader.loading = true;

		// TODO abstract the get URL
		$http.get("/admin/players/get/list").then(function(response) {
			$scope.dto = response.data;

			$scope.loader.loading = false;
		});
	}

	var rootInitialized = false;

	angular.element(document).ready(function(playerId) {

		if (!rootInitialized) {
			$scope.loadList();
			rootInitialized = true;
		}

		$scope.loadList();
	});

	$scope.confirmDelete = function(item) {
		$http.post($scope.dto.deletePath + item.href).then(function() {
			$scope.loadList();
			$scope.$emit(EMIT_ADMIN_ENTITY_DELETED);
		}, function() {
			// TODO - find a more graceful way of expressing the error.
			alert("There was an error while deleting.")
		});
	}

});