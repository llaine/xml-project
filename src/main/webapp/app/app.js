/**
 * Created by llaine on 31/03/15.
 */



'use strict';

angular.module('ngContactManager', ['ngRoute', 'ngCookies'])
    .run(['$rootScope', 'Auth', '$location', function($rootScope, Auth, $location) {
        $rootScope.$on('$routeChangeSuccess', function () {
            Auth.validate().then(function (data) {
                if(data === 401){
                    alert('ok');
                }
            });
        });
    }])
    /* Routing configuration */
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: '/app/home/home.html',
            controller: 'homeController'
        }).when('/login', {
            templateUrl: '/app/login/login.html',
            controller: 'loginController',
            login: false
        }).otherwise('/');
    }]);