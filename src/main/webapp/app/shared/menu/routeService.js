webApp.factory('routeService',['$rootScope', '$location', 'clustersService', function ($rootScope, $location, clustersService) {
    var healthCheckCallback;

    return{
        routeToHealthCheck: function( clusterName ) {
            $rootScope.forceCheck = true;

            if ( $location.path().indexOf( "cluster/" + clusterName ) == -1 ) {
                $location.url( "/cluster/" + clusterName );
            }
            else {
                //stub
                healthCheckCallback( clusterName );
            }
        },
        setHealthCheckCallback: function( callback ) {
            healthCheckCallback = callback;
        }
    }
}]);
