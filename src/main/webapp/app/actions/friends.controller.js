/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('friendsController', ['$scope', 'UsersResource', 'Auth',  function ($scope, UsersResource, Auth) {
    $scope.newUser = $scope.newUser = {firstname: null, lastname: null, birthdayDate: null, email: null, password: null, friends: null, groups: null};

    $scope.currentUser = Auth.currentUser();

    function reset(){
        $scope.newUser = {
            firstname: null,
            lastname: null,
            birthdayDate: null,
            email: null,
            password: null,
            friends: null,
            groups: null
        }
    }

    $scope.addContact = function (newUser) {

        if(newUser
            && newUser.firstname
            && newUser.lastname
            && newUser.birthdayDate
            && newUser.email){

            $scope.currentUser.friends.push(newUser);

            // TODO REST API

            reset();
        }
    };


}]);