webApp.factory('routeService',['$rootScope', '$location', 'clustersService', function ($rootScope, $location, clustersService) {
    return{
        routeToHealthCheck: function( clusterName ) {
            $rootScope.checkClusterHealth = true;
            if ( $location.path().indexOf( "cluster/" + clusterName ) == -1 ) {
                $location.url( "/cluster/" + clusterName + "?check=true" );
            }
            else {
                //stub
                console.log( "haha" );
            }
        }
    }
}]);
