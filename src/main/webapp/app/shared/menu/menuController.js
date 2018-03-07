webApp.controller("menuController",['$scope', 'clustersService', 'routeService', function($scope, clustersService, routeService){
   var promiseClusters=clustersService.getClusters();
   promiseClusters.then( function(value) {
       $scope.clusters = value;
       if ( $scope.clusters.length > 0 ) {
           $scope.cluster = $scope.clusters[0];
       }
   });

   $scope.showEditCluster = function( clusterName ) {
        for ( index in $scope.clusters ) {
            if ( $scope.clusters[index].name == clusterName ) {
                $scope.tempCluster = $scope.clusters[index];
            }
        }

        $('#editClusterModal').modal('show');
   }

   $scope.editCluster = function() {
       //stub
   }

   $scope.checkClusterHealth = function( clusterName ) {
        routeService.routeToHealthCheck( clusterName );
   }
}]);