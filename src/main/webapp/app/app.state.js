/**
 * Created by Irina Kazantseva on 12.12.2017.
 */
(function () {
    'use strict';
    var app = angular.module('app');
    app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/calculate");

        $stateProvider
            .state('calculate', {
                url: '/calculate',
                templateUrl: 'modules/calculate/templates/calculate.html',
                controller: 'CalculateController'
            })
    }]);
})();