/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.service('Auth', ['$http', '$cookies', function ($http, $cookies) {

    /**
     * Check if the user is well connected
     * @param location
     */
    function validateUserAuthentification(){
        var promise = $http.get('http://localhost:9000/api/users')
            .success(function (data) {
                return data;
            })
            .error(function (error, statusCode) {
                return error;
            });

        return promise;
    }

    return {
        validate: validateUserAuthentification
    }

}]);