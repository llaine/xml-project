/**
 * Created by llaine on 31/03/15.
 */



'use strict';

angular.module('ngContactManager', ['ngRoute', 'ngCookies', 'ngResource'])
    .run(['$rootScope', 'Auth', '$location', function($rootScope, Auth, $location) {
        $rootScope.$on('$routeChangeSuccess', function () {

            /* Very basic verification of authentification */
            if($location.path() !== "/login" && $location.path() !== "/register"){
                Auth.isAuth(function (isAuthent) {
                    if(!isAuthent){
                        $location.path('/login');
                    }
                });
            }

        });
    }])
    /* Routing configuration */
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: '/app/home/home.html',
            controller: 'homeController'
        }).when('/login', {
            templateUrl: '/app/login/login.html',
            controller: 'loginController'
        }).when('/register', {
            templateUrl: '/app/register/register.html',
            controller: 'registerController'
        }).otherwise('/');
    }]);