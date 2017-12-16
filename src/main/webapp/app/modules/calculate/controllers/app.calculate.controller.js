/**
 * Created by Irina Kazantseva on 11.09.2017.
 */


(function () {
    'use strict';
    var app = angular.module('app');

    app.controller('CalculateController', [ '$scope', 'CalculateService', function($scope, CalculateService) {

        $scope.active = 'stress';
        $scope.resultTime = null;
        $scope.stress = {
            yMax:0,
            yMin:0,
            yStep:0,
            nMax:0,
            nMin:0,
            nStep:0,
            tMax:0,
            tMin:0,
            tStep:0,
            mMax:0,
            mMin:0,
            mStep:0,
            kMax:0,
            kMin:0,
            kStep:0,
            parallel: false
        };

        $scope.simple= {
            n:0,
            t:0,
            m:0,
            k:0,
            y:0,
            parallel: false
        };


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

        $scope.getResult = function(){
            if($scope.calculationActiveType.id == 0) return;
            if($scope.activeFunction.id == 0) return;
            var request = $scope.active === 'stress' ? angular.copy($scope.stress) : angular.copy($scope.simple);
            if($scope.calculationActiveType.id == 1) request.parallel = true;
            switch($scope.activeFunction.id){
                case 1 :{
                    $scope.sendAnaliticRequest(request);
                    break;
                }
                case 2: {
                    $scope.sendIntegralRequest(request);
                    break;
                }
                case 3: {
                    $scope.sendRecursionRequest(request);
                    break;
                }
            }
        };

        $scope.sendAnaliticRequest = function(request){
           if($scope.active === 'stress'){
               CalculateService.getStressAnalyticalResult(request).then(function(response){
                    $scope.resultTime = response;
               }).catch(function(error){
                   console.log(error);
               });
           }else{
               CalculateService.getAnalyticalResult(request).then(function(response){
                   $scope.resultTime = response;
               }).catch(function(error){
                   console.log(error);
               });
           }
        };

        $scope.sendIntegralRequest = function(request){
            if($scope.active === 'stress'){
                CalculateService.getStressIntegralResult(request).then(function(response){
                    $scope.resultTime = response;
                }).catch(function(error){
                    console.log(error);
                });
            }else{
                CalculateService.getIntegralResult(request).then(function(response){
                    $scope.resultTime = response;
                }).catch(function(error){
                    console.log(error);
                });
            }
        };

        $scope.sendRecursionRequest = function(request){
            if($scope.active === 'stress'){
                CalculateService.getStressRecursionResult(request).then(function(response){
                    $scope.resultTime = response;
                }).catch(function(error){
                    console.log(error);
                });
            }else{
                CalculateService.getRecursionResult(request).then(function(response){
                    $scope.resultTime = response;
                }).catch(function(error){
                    console.log(error);
                });
            }
        };

    }]);
})();
