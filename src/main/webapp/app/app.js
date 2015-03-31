/**
 * Created by llaine on 31/03/15.
 */



'use strict';

angular.module('ngContactManager', ['ngRoute'])
    /* Routing */
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: '/app/home/home.html',
            controller: 'homeController',
            login: true
        }).when('/login', {
            templateUrl: '/app/login/login.html',
            controller: 'loginController',
            login: false
        }).otherwise('/');
    }]);