webApp.factory('clustersService',['$http', '$q', function ($http, $q) {
    return{
        getClusters: function() {
            var deferred = $q.defer();
            $http({method: 'GET', url: 'clusters.json', params: {}}).
            then(function(response) {
                    deferred.resolve(response.data.clusters);
                },
                function(response) {
                    deferred.reject(response.status);
                });

            return deferred.promise;
        }
    }
}]);