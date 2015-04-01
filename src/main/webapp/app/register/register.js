/**
 * Created by llaine on 31/03/15.
 */

'use strict';

angular.module('ngContactManager')
    .controller('registerController', ['$scope', 'UsersResource', function($scope, UsersResource) {

        $scope.register = function (user) {
            console.log(user);
        };

    }]);