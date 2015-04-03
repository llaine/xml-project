/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('groupsController', ['$scope', 'UsersResource', function ($scope, UsersResource) {

    $scope.friends = UsersResource.query();



}]);