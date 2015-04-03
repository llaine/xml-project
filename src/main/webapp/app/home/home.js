/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('homeController', ['$scope', 'UsersResource', function ($scope, UsersResource) {

    $scope.friends = UsersResource.query();

}]);