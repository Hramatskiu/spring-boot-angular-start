webApp.controller("menuController",['$scope', 'clustersService', function($scope, clustersService){
   var promiseClusters=clustersService.getClusters();
   promiseClusters.then( function(value) {
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
   }
}]);