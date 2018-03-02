var webApp = angular.module('healthCheckingToolApp', ["ngRoute", "ngCookies", "pascalprecht.translate"]);
webApp.config(function ($routeProvider, $translateProvider) {
    $routeProvider.when('/service/:id',
        {
            templateUrl: 'views/components/service/service.html',
            controller: 'serviceController'
        });
    $routeProvider.when('/home',
        {
            templateUrl: 'views/components/home/home.html',
            controller: 'mainController'
        }
    );

    $routeProvider.otherwise({redirectTo: '/home'});
    $translateProvider.useCookieStorage();
    $translateProvider.useStaticFilesLoader({
        prefix: 'assets/i18n/',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage('en');
});