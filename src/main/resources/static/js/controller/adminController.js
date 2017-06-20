app.controller('adminCtrl', ($scope, $rootScope,
		EMIT_ADMIN_ENTITY_SAVED, BROADCAST_ADMIN_ENTITY_SAVED,
		EMIT_ADMIN_ENTITY_DELETED, adminEntityStatus) => {

	$scope.adminEntityStatus = adminEntityStatus;

	$scope.$on("$routeChangeStart", () => {
		$scope.adminEntityStatus.status.saved = false;
		$scope.adminEntityStatus.status.deleted = false;
	});

	$rootScope.$on(EMIT_ADMIN_ENTITY_SAVED, () => {
		$scope.$broadcast("BROADCAST_ADMIN_ENTITY_SAVED");
		$scope.adminEntityStatus.status.saved = true;
	});

	$scope.$on(EMIT_ADMIN_ENTITY_DELETED, () => {
		$scope.adminEntityStatus.status.deleted = true;
	})
	
});