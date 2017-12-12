/**
 * Created by Irina Kazantseva on 11.09.2017.
 */
(function () {
    'use strict';

    angular.module('app', ['ngResource', 'ui.bootstrap', 'ui.router', 'ngMaterial'])
})();

(function () {
    var app = angular.module('app');

    app.service('APIInterceptorRequest', [function () {
        var service = this;
        service.request = function (config) {
            return config;
        };
    }]);
    
    app.factory('httpResponseInterceptor',function($q, $injector) {
        return {
            'response': function (config) {
                return config;
            },
            'responseError': function(response) {
                return response;
            }
        };
    });

    app.config(function($httpProvider) {
        $httpProvider.interceptors.push('httpResponseInterceptor');
        $httpProvider.interceptors.push('APIInterceptorRequest');
    });

    app.run(['$rootScope', '$state', '$window', '$http', function ($rootScope, $state, $window, $http) {

        $http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        
    }]);

})();