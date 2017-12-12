/**
 * Created by Irina Kazantseva on 11.05.2017.
 */
(function () {
    'use strict';
    var app = angular.module('app');
    app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/hello-world");

        $stateProvider
            .state('hello-world', {
                url: '/hello-world',
                templateUrl: 'modules/hello-world/templates/hello-world.html',
                controller: 'HelloWorldController'
            })
    }]);
})();