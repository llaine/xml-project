/**
 * Created by llaine on 31/03/15.
 */

'use strict';

angular.module('ngContactManager')
    .controller('registerController', ['$scope', 'UsersResource', '$http', '$location', 'flashMessage', function($scope, UsersResource, $http, $location, flashMessage) {
        $scope.user = {firstname: null, lastname: null, birthdayDate: null, email: null, password: null, friends: null, groups: null};


        /**
         * Little to check that the username has not already been taken.
         */
        $('#firstname').on('keyup', function (e, data) {
            if(e.target.value){
                $http({
                    method:'GET',
                    url:'http://localhost:9000/api/check/' + e.target.value
                }).success(function (isExists, status) {
                    if(isExists){
                        $('#messages').html('This username is already taken');
                        $('#btnSubmit').attr('disabled', true);
                    }else{
                        $('#messages').empty();
                        $('#btnSubmit').attr('disabled', false);
                    }
                });
            }

        });


        $scope.register = function (user) {
            if(user.firstname === null || user.password === null) {
                flashMessage.error("Please fill the form");
            }else{
                $http({
                    method:'POST',
                    url:'http://localhost:9000/api/register',
                    data:user
                }).success(function (data, status) {
                    if(status === 201){
                        flashMessage.success("You can now log in :) !");
                        $location.path('/login');
                    }
                }).error(function (status) {
                    flashMessage.error("Somethings wen\'t wrong " + status);
                });
            }

        };

    }]);