webApp.controller("homeController", ['$scope', 'clustersService', function($scope, clustersService){
    var promiseClusters=clustersService.getClusters();
    promiseClusters.then( function(value) {
        $scope.clusters = value;
        if ( $scope.clusters.length > 0 ) {
            $scope.cluster = $scope.clusters[0];
        }
    });

    var promiseClusterState = clustersService.getClusterState();
        promiseClusterState.then( function(value) {
            $scope.clusterState = value;
        });

    $scope.editCluster = function() {
        //stub
    }

    $scope.checkClusterHealth = function() {
        $('#healthCheckResult').modal('show');
        $scope.clusterHealth = {
            status: "Good"
        }
    }
}]);