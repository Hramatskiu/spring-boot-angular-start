webApp.controller("homeController", ['$scope', 'clustersService', function($scope, clustersService){
    var promiseObj=clustersService.getClusters();
    promiseObj.then( function(value) {
        $scope.clusters = value;
        if ( $scope.clusters.length > 0 ) {
            $scope.cluster = $scope.clusters[0];
        }
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