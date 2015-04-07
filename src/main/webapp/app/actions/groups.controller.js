/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

app.controller('groupsController', ['$scope', 'Auth', '$http', function ($scope, Auth, $http) {
    $scope.newGroup = {name: null, members:null};

    $scope.currentUser = Auth.currentUser();

    $scope.friends = $scope.currentUser.friends;

    function h() {
        console.log(arguments);
    };

    $scope.createGroup = function (newGroup) {
        $scope.currentUser.groups.push(newGroup);

        $http({
            method:'POST',
            url:'http://localhost:9000/api/users/' + $scope.currentUser.id + '/groups',
            data:newGroup
        }).success(h).error(h);

    };

    $scope.addMember = function (idContact, idGroup) {
        var idUser = $scope.currentUser.id;

        $http({
            method:'PUT',
            url:'http://localhost:9000/api/users/' +
                idUser  + '/groups/' +
                idGroup + '/friends/' +
                idContact
        }).success(h).error(h);

    };

    $scope.deleteGroup = function (group) {
        $scope.currentUser.groups.splice($scope.currentUser.groups.indexOf(group), 1);

        $http({
            method:'DELETE',
            url:'http://localhost:9000/api/users/' + $scope.currentUser.id + '/groups/' + group.id
        }).success(h).error(h);
    };

    $scope.removeFromGroup = function (member, group) {
        $http({
            method:'DELETE',
            url:'http://localhost:9000/api/users/' +
                $scope.currentUser.id + '/groups/' +
                group.id + '/friends/' +
                member.id
        }).success(h).error(h);

    };


}]);