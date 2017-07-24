(function() {
    'use strict';

    angular
        .module('nulmApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('loginUsers', {        
            url: '/loginUsers',
            data: {
                /*authorities: ['ROLE_USER','ROLE_ADMIN']*/
            },
            views: {
            	header: {
                    templateUrl: 'app/layouts/header/header.html'},
                    
                content: {
                    templateUrl: 'app/user/user.html',
                    controller: 'UserController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                /*mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]*/
            }
        });
    }
})();
