webApp.controller("clusterController", ['$scope', 'clustersService', function($scope, clustersService){
    var promiseClusterState = clustersService.getClusterState();
        promiseClusterState.then( function(value) {
            $scope.clusterState = value;
        });
}]);