app.controller("gameAdminCtrl", ($scope, $location, $http, gameService, urlService, EMIT_ADMIN_ENTITY_SAVED) => {
  $scope.loader = {
          loading : false
  };

  var loading = (value) => $scope.loader.loading = value;

  $scope.resetGame = () => $scope.editedGame = new gameService.resource;
  
  $scope.getMonthName = (month) => {
    return Object.keys(month)[0];
  }

  $scope.save = (editedGame) => {
    gameService.save(editedGame, function(response) {
    $location.path("admin/games")
    $scope.resetGame();

    $rootScope.$emit(EMIT_ADMIN_ENTITY_SAVED);
    });
  }

  angular.element(document).ready(() => {

    loading(true);
      
    $http.get("/admin/dto/game")
      .then(response => {
        $scope.dto = response.data; 
        loading(false);
        }, 
        () => loading(false));
      }); 
});