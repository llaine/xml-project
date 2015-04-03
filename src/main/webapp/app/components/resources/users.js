/**
 * Created by llaine on 31/03/15.
 */

'use strict';


var app = angular.module('ngContactManager');

app.factory('UsersResource', ['$resource', function ($resource) {
    /**
     * GET -> api/users
     */
    return $resource('http://localhost:9000/api/users/:id',
        {id: '@id'},
        {"update":{method:"PUT"}},
        {'save':{method:"POST"}}
    );




}]);