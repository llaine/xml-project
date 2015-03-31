/**
 * Created by llaine on 31/03/15.
 */


'use strict';

var app = angular.module('ngContactManager');

app.directive('navigationBar', function () {
    return {
        restrict: 'AE',
        transclude: true,
        scope: {},
        templateUrl: 'app/components/directives/templates/navigation-bar.html',
        link: function (scope, element) {

        }
    }
});