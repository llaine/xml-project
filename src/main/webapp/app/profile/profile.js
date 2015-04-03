/**
 * Created by llaine on 03/04/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('profileController', ['$scope', '$http', 'UsersResource', 'Auth',  function ($scope, $http, UsersResource, Auth) {

    $scope.user = Auth.currentUser();


    $scope.update = function () {
        console.log($scope.user);

        function handleResponse(){
            console.log(arguments);
        }

        // TODO REST API
        $http({
            method:'PUT',
            url:"http://localhost:9000/api/users/1",
            data: {user:$scope.user}
        }).success(handleResponse).error(handleResponse);
    };
    
    $scope.remove = function () {
        
    };




}]);