/**
 * Created by llaine on 07/04/15.
 */


'use strict';

var app = angular.module('ngContactManager');

app.service('flashMessage', [function () {
    var rootDiv = $('#flashMessages');
    var delayFade = 2000; // 4 seconds.
    var delayFadeOut = 200; // 0.2 seconds

    /**
     * Display flash with alert-success class.
     * Actually the rootDiv is in the index.html file.
     * @param msg
     */
    function success(msg){
        if(msg && rootDiv){
            rootDiv
                .removeClass('alert-success')
                .removeClass('hidden')
                .addClass("alert-success")
                .html('<h1>' + msg + '</h1>')
                .delay(delayFade)
                .fadeOut(delayFadeOut);
        }
    }

    /**
     * Action alert-messages div with error class
     * @param msg
     */
    function error(msg){
        if(msg && rootDiv){
            rootDiv
                .removeClass('alert-warning')
                .removeClass('hidden')
                .addClass("alert-warning")
                .html('<h1>' + msg + '</h1>')
                .delay(delayFade)
                .fadeOut(delayFadeOut);
        }
    }

    return {
        success:success,
        error:error
    }
}]);