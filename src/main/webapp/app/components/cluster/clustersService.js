webApp.factory('clustersService',['$http', '$q', function ($http, $q) {
    return{
        getClusters: function() {
            var deferred = $q.defer();
            $http({method: 'GET', url: '/clusters', params: {}}).
            then(function(response) {
                    deferred.resolve(response.data);
                },
                function(response) {
                    deferred.reject(response.status);
                });

            return deferred.promise;
        },
        getClusterState: function() {
            var deferred = $q.defer();
            $http({method: 'GET', url: 'clusters_state.json', params: {}}).
            then(function(response) {
                    deferred.resolve(response.data);
                },
                function(response) {
                    deferred.reject(response.status);
                });

            return deferred.promise;
        }
    }
}]);