/**
 * Created by llaine on 07/04/15.
 */


'use strict';

var app = angular.module('ngContactManager');


function printDiv(divName){
    window.print();
}

app.directive('printBtn', [function () {
    return {
        restrict: 'AE',
        transclude: true,
        scope: {
            divToPrint:'=div'
        },
        template: '<button type="button" title="print the informations of this page" class="btn btn-default btn-sm no-print" ng-click="printDiv()"><span class="glyphicon glyphicon-print"></span></button>',
        link: function (scope, element, attrs) {
            scope.printDiv = function () {
                printDiv(attrs.divToPrint)
            };
        }
    }
}]);