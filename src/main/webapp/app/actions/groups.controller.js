/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('groupsController', ['$scope', 'Auth', function ($scope, Auth) {

    $scope.currentUser = Auth.currentUser();


    $scope.createGroup = function (newGroup) {

    };



}]);