/**
 * Created by Irina Kazantseva on 23.05.2017.
 */

(function() {
    'use strict';
    var app = angular.module('app');

    app.factory("CalculateService", ['$http', '$q','ConfigService','$window',  function($http, $q, ConfigService, $window) {

        var factory = {};

        var rootPath = ConfigService.getRootPath()+"/calculate";

        factory.getStressAnalyticalResult = function(stressRequestObject) {
            var defer = $q.defer();
            $http.post(rootPath+'/stress/analytical', stressRequestObject).then(function(response) {
                defer.resolve(response.data);
            }).catch(function (error) {
                defer.reject(error);
            });
            return defer.promise;
        };

        factory.getStressIntegralResult = function(stressRequestObject) {
            var defer = $q.defer();
            $http.post(rootPath+'/stress/integral', stressRequestObject).then(function(response) {
                defer.resolve(response.data);
            }).catch(function (error) {
                defer.reject(error);
            });
            return defer.promise;
        };


        factory.getStressRecursionResult = function(stressRequestObject) {
            var defer = $q.defer();
            $http.post(rootPath+'/stress/recursion', stressRequestObject).then(function(response) {
                defer.resolve(response.data);
            }).catch(function (error) {
                defer.reject(error);
            });
            return defer.promise;
        };

        factory.getAnalyticalResult = function(requestObject) {
            var defer = $q.defer();
            $http.post(rootPath+'/analytical', requestObject).then(function(response) {
                defer.resolve(response.data);
            }).catch(function (error) {
                defer.reject(error);
            });
            return defer.promise;
        };

        factory.getIntegralResult = function(requestObject) {
            var defer = $q.defer();
            $http.post(rootPath+'/integral', requestObject).then(function(response) {
                defer.resolve(response.data);
            }).catch(function (error) {
                defer.reject(error);
            });
            return defer.promise;
        };


        factory.getRecursionResult = function(requestObject) {
            var defer = $q.defer();
            $http.post(rootPath+'/recursion', requestObject).then(function(response) {
                defer.resolve(response.data);
            }).catch(function (error) {
                defer.reject(error);
            });
            return defer.promise;
        };

        return factory;
    }]);
})();