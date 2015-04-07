/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('friendsController', ['$scope', 'Auth', '$http', 'UsersResource',  function ($scope, Auth, $http, UsersResource) {
    $scope.newUser = {firstname: null, lastname: null, birthdayDate: null, email: null, password: null, friends: null, groups: null};

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


    function h(data, status) {
        if(status === 200){
            console.log(arguments);
            reset();
        }
    };

    $scope.addContact = function (newUser) {

        if(newUser
            && newUser.firstname
            && newUser.lastname
            && newUser.birthdayDate
            && newUser.email){

            $scope.currentUser.friends.push(newUser);


            $http({
                url:'http://localhost:9000/api/users/' + $scope.currentUser.id + '/friends',
                method:'POST',
                data:newUser
            }).success(h).error(h);

        }
    };

    $scope.updateUser = function () {

    };


    $scope.removeContact = function (contact) {
        // Removing from the Array
        $scope.currentUser.friends.splice($scope.currentUser.friends.indexOf(contact), 1);

        $http({
            method:'DELETE',
            url:'http://localhost:9000/api/users/' + $scope.currentUser.id + '/friends/' + contact.id
        }).success(h).error(h);
    };


}]);