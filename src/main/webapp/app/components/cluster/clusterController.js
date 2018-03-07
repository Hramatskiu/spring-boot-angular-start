webApp.controller("clusterController", ['$scope', '$routeParams', 'clustersService', function($scope, $routeParams, clustersService){

    $scope.$on("$routeChangeSuccess", function () {
        var clusterName = $routeParams["id"];
        if ( clusterName !== 'undefined' ){
            var promiseClusterState = clustersService.getClusterState( clusterName );
            promiseClusterState.then( function(value) {
                $scope.clusterState = value;
            });
        }
    });
}]);