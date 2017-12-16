(function () {
    'use strict';
    var app = angular.module('app');
    app.directive('noComma', function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attrs, ctrl) {
                elm.on('keydown', function (event) {
                    if ([188].indexOf(event.which) > -1) {
                        event.preventDefault();
                        return false;
                    }
                    else {
                        return true;
                    }
                });
            }
        }
    });
})();