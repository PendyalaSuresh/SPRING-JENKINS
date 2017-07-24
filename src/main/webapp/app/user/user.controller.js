(function() {
    'use strict';

    angular
        .module('nulmApp')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$state'];

    function UserController ($scope,  $state) {
        var vm = this;
        vm.loggedInUsers = [{'name':'allen','loggedInTime':'12:00', 'loggedOutTime':'1:00'},
        	{'name':'abir','loggedInTime':'13:00', 'loggedOutTime':'14:00'},
        	{'name':'vinit','loggedInTime':'15:00', 'loggedOutTime':'16:00'}];
        
    }
})();
