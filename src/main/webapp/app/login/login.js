/**
 * Created by llaine on 31/03/15.
 */


'use strict';

angular.module('ngContactManager')
    .controller('loginController', ['$scope', 'Auth', '$location', function($scope, Auth, $location) {

        $scope.connexion = function (user) {
            Auth.authenticate(user, function (Ok) {
                if(Ok){
                    /* Redirect to home page */
                    $location.path('/');
                }
            });
        };


    }]);