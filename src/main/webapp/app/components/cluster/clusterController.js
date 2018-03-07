webApp.controller("clusterController", ['$scope', '$routeParams', '$rootScope', 'clustersService', 'routeService', function($scope, $routeParams, $rootScope, clustersService, routeService){
    var makeHealthCheck = function( clusterName ) {
        var promiseClusterState = clustersService.getClusterState( clusterName );
        promiseClusterState.then( function(value) {
            $scope.clusterState = value;
        });
    }

    $scope.$on("$routeChangeSuccess", function () {
        //DEV only
        $rootScope.forceCheck = true;
        //
        routeService.setHealthCheckCallback( makeHealthCheck );
        var clusterName = $routeParams["id"];
        if ( clusterName !== 'undefined' ) {
            if ( $rootScope.forceCheck == true ) {
                makeHealthCheck( clusterName );
                $rootScope.forceCheck = false;
            }
            else {
                console.log( $rootScope.forceCheck );
            }
        }
    });
}]);