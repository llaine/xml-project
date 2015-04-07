/**
 * Created by llaine on 07/04/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('dependencyController', ['$scope', '$routeParams', '$http', 'Auth', function ($scope, $routeParams, $http, Auth) {

    var contactId = $routeParams.user;

    var user = Auth.currentUser();

    $scope.userId = contactId;


    function h(deps, status) {
        if(status === 200){
            $scope.groups = deps;
            $scope.nbGroups = deps.length;
        }
    };


    setTimeout(function () {
        $http({
            method: 'GET',
            url: 'http://localhost:9000/api/users/' + user.id + '/dependencies/' + contactId
        }).success(h).error(h);
    }, 200);



}]);