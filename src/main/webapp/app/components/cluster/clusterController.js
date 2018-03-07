webApp.controller("clusterController", ['$scope', '$routeParams', '$rootScope', 'clustersService', 'routeService', function($scope, $routeParams, $rootScope, clustersService, routeService){
    var makeHealthCheck = function( clusterName ) {
        var promiseClusterState = clustersService.getClusterState( clusterName );
        promiseClusterState.then( function(value) {
            $scope.clusterState = value;
        });
    }

    var initYarnApps = function( clusterName ) {
        var promiseYarnApps = clustersService.getYarnApps( clusterName );
        promiseYarnApps.then( function(value) {
            $scope.yarnApps = value;
        });
    }

    var getYarnAndHealthCheck = function( clusterName ) {
        makeHealthCheck( clusterName );
        initYarnApps( clusterName );
    }

    var init = function() {
        routeService.setHealthCheckCallback( getYarnAndHealthCheck );
        $scope.activeTab = {
            health: "active",
            yarn: ""
        }
    }

    $scope.$on("$routeChangeSuccess", function () {
        //DEV only
        $rootScope.forceCheck = true;
        //
        init();
        var clusterName = $routeParams["id"];
        if ( clusterName !== 'undefined' ) {
            $scope.clusterName = clusterName;
            if ( $rootScope.forceCheck == true ) {
                makeHealthCheck( clusterName );
                $rootScope.forceCheck = false;
            }
            else {
                console.log( $rootScope.forceCheck );
            }

            initYarnApps( clusterName );
        }
    });

    $scope.showTab = function( tab ) {
        for ( var key in $scope.activeTab ) {
            if ( key == tab ) {
                $scope.activeTab[key] = "active";
            }
            else {
                $scope.activeTab[key] = "";
            }
        }
    }

    $scope.killApp = function( id ) {
        var promiseYarnKillApp = clustersService.killYarnApp( $scope.clusterName, id );
        promiseYarnKillApp.then( function(value) {
            $scope.isKillYarnApp = value;
            console.log( $scope.isKillYarnApp );
        });
    }
}]);