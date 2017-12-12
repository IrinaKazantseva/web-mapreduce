/**
 * Created by Irina Kazantseva on 18.05.2017.
 */


(function() {
    'use strict';

    var app = angular.module('app');

    app.factory('ConfigService', ['$location','$http','$q', function($location, $http, $q) {

        var factory = {};

        factory.getProtocol = function() {
            return $location.protocol();
        };

        factory.getRootPath = function() {
            return $location.absUrl().split('/#!/')[0];
        };
        
        return factory;

    }]);

})();