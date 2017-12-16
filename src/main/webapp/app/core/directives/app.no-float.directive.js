(function () {
    'use strict';
    var app = angular.module('app');
    app.directive('noFloat', function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attrs, ctrl) {
                elm.on('keydown', function (event) {
                    if ([110, 190].indexOf(event.which) > -1) {
                        // dot and numpad dot
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