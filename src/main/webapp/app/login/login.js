/**
 * Created by llaine on 31/03/15.
 */


'use strict';

var app = angular.module('ngContactManager');
app.controller('loginController', ['$scope', 'Auth', '$location', 'flashMessage', function($scope, Auth, $location, flashMessage) {

    $scope.connexion = function (user) {
        if(!user) flashMessage.error("Please fill the form");

        Auth.authenticate(user, function (Ok) {
            if(Ok){
                /* Redirect to home page */
                location.reload();
                $location.path('/');
            }else{
                $('#errors').html('The username or password does not exists!');
            }
        });
    };

}]);


app.controller('logoutController', ['$location', '$cookieStore', 'Auth', function ($location, $cookieStore, Auth) {

    Auth.logout(function () {
        /* We are well disconnected ! */
        location.reload();
        $location.path('/connexion');
    });

}]);
