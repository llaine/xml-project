/**
 * Created by llaine on 31/03/15.
 */

'use strict';

angular.module('ngContactManager')
    .controller('registerController', ['$scope', 'UsersResource', '$http', '$location', function($scope, UsersResource, $http, $location) {
        $scope.user = {firstname: null, lastname: null, birthdayDate: null, email: null, password: null, friends: null, groups: null};


        $('#firstname').on('keyup', function (e, data) {

            if(e.target.value){
                $http({
                    method:'GET',
                    url:'http://localhost:9000/api/check/' + e.target.value
                }).success(function (isExists, status) {
                    if(isExists){
                        $('#messages').html('This name already exists');
                        $('#btnSubmit').attr('disabled', true);
                    }else{
                        $('#messages').empty();
                        $('#btnSubmit').attr('disabled', false);
                    }
                });
            }

        });

        $scope.register = function (user) {

            $http({
                method:'POST',
                url:'http://localhost:9000/api/register',
                data:user
            }).success(function (data, status) {
                if(status === 201){
                    $location.path('/login');
                }
            }).error(function (status) {

            });

        };

    }]);