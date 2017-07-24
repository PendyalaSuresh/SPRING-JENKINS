(function() {
    'use strict';

    angular
        .module('nulmApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {    
        	/*parent:'entity',*/
            url: '/home',
            data: {
                /*authorities: ['ROLE_USER','ROLE_ADMIN']*/
            },
            views: {
            	/*header: {
                    templateUrl: 'app/layouts/header/header.html'},*/
                    
                content: {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController', 
                    controllerAs: 'vm'
                }
            },
           
            resolve: {       	
               
            }
        });
    }
})();
