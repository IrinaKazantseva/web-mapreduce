/**
 * Created by Irina Kazantseva on 11.09.2017.
 */


(function () {
    'use strict';
    var app = angular.module('app');

    app.controller('CalculateController', [ '$scope', 'CalculateService', function($scope, CalculateService) {

        $scope.active = 'stress';

        $scope.activeFunction = {id:0, name:"Значение не выбрано"};
        $scope.functions = [{id:1, name:"Аналитическая функция"}, {id:2, name:"Интегральная функция"}, {id:3, name:"Рекурсивная функция"}];
        $scope.calculationActiveType = {id:0, name:"Значение не выбрано"};
        $scope.calculationTypes = [{id:1, name:"Параллельное"},{id:2, name:"Последовательное"}];
        $scope.setActive = function(active){
            $scope.active = active;
        };
        $scope.changeFunctionType = function (activeFunction) {
            $scope.activeFunction = activeFunction;
        };

        $scope.changeCalculationType = function (calculationActiveType) {
            $scope.calculationActiveType = calculationActiveType;
        };


    }]);
})();
