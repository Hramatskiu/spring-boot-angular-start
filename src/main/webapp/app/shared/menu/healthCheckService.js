webApp.factory('healthCheckService',['$rootScope', '$location', 'clustersService', function ($rootScope, $location, clustersService) {
    return{
        makeHealthCheck: function( clusterName ) {
            $rootScope.checkClusterHealth = true;
            if ( $location.path().indexOf( "cluster/" + clusterName ) == -1 ) {
                $location.url( "/cluster/" + clusterName );
            }
            else {
                //stub
                console.log( "haha" );
            }
        }
    }
}]);
