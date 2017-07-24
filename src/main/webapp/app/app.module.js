(function() {
    'use strict';

    angular
        .module('nulmApp', [
            /*'ngStorage',
            'tmh.dynamicLocale',
            'pascalprecht.translate',*/
            'ngResource',
            /*'ngCookies',
            'ngAria',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',*/
            /*'ui.bootstrap.datetimepicker',*/
            'ui.router'//,
            /*'infinite-scroll',
            'angularUtils.directives.dirPagination',
*/            // jhipster-needle-angularjs-add-module JHipster will add new module here
           /* 'angular-loading-bar',
            'as.sortable',
            'ngTable',
            'fixed.table.header',
            'angular.filter',
            'checklist-model'*/
        ])
        .run(run);

    
    run.$inject = ['stateHandler', 'translationHandler'];

    function run(stateHandler, translationHandler) {
        stateHandler.initialize();
        translationHandler.initialize();

    }
})();


//angular.bootstrap('document',['nulmApp']);
  

