/**
 * Created by llaine on 31/03/15.
 */

'use strict';

var app = angular.module('ngContactManager');

/* Login resource, made a simple call to api/authenticate with the right params. */
app.factory('Login', ['$resource', function ($resource) {
    return $resource('http://localhost:9000/api/authenticate', {},
        {
            authenticate: {
                method: 'POST',
                headers : {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        })
}]);


app.service('Auth', ['$http', '$cookieStore', 'Login', 'UsersResource', function ($http, $cookieStore, Login, UsersResource) {

    /**
     * Check if the user is well connected by testing the backend and the cookies.
     * @param location
     */
    function validateUserAuthentification(cb){
        var promise = $http.get('http://localhost:9000/api/users')
            .success(function () {
                cb(arguments);
            })
            .error(function () {
                cb(arguments[0]); // Returning only the errors stacktrace which describe also
            });
        return promise;
    }

    /**
     * Verify if there is a $cookieSession
     * @param cb
     */
    function isAuth(cb){

        if(!$cookieStore.get("currentUser")) {

            validateUserAuthentification(function () {
                /* Getting the status from function arguments */
                var status = arguments[0].status;

                switch (status){
                    case 200:
                        cb(true);
                        break;
                    case 401:
                        $cookieStore.remove('currentUser');
                        cb(false);
                        break;
                    default:
                        $cookieStore.remove('currentUser');
                        cb(false);
                        break
                }

            });

        }else{
            cb(true);
        }
    }

    /**
     * Call the /authenticate route of the api and try to connect the user in param.
     * @param user
     * @param cb
     */
    function authenticate(user, cb){
        if(user && user.username && user.password){
            Login.authenticate($.param({username: user.username, password: user.password}), function (data, status) {
                console.log(status);
                /* Response is OK, we can store the user in cookies */
                if(data && data.firstname && data.lastname){
                    $cookieStore.put("currentUser", data);
                    cb(true);
                }

            });
        }
    }

    /**
     * Call the GET -> /api/logout method and remove the currentUser in cookies
     * @param cb
     */
    function logout(cb){
        $cookieStore.remove('currentUser');
        $http.get('http://localhost:9000/api/logout').success(function () {
            cb();
        });
    }


    function currentUser(){
        if($cookieStore.get('currentUser')){
            return UsersResource.get({id:$cookieStore.get('currentUser').id});
        }
    }

    return {
        logout: logout,
        isAuth: isAuth,
        validate: validateUserAuthentification,
        authenticate: authenticate,
        currentUser:currentUser
    }

}]);