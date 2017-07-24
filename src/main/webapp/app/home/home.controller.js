(function() {
    'use strict';

    angular
        .module('nulmApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state', 'HomeService'];

    function HomeController ($scope,  $state, HomeService) {
        var vm = this;
        vm.licenseNumber = 1;
    	vm.licenseCount = 0;
    	vm.maxCap = 100;
    	
    	vm.userLicenses = [];
    	vm.updateCount = updateCount;
    	vm.updateMaxLicense = updateMaxLicense;
    	vm.updateLoginHistory = updateLoginHistory;
    	
    	loadAll();
    	
    	function loadAll (){
    		HomeService.query({}, onSuccess, onError).$promise;
    		
    		function onSuccess(data, headers) {
                //vm.links = ParseLinks.parse(headers('link'));
               // vm.totalItems = headers('X-Total-Count');
                //vm.queryCount = vm.totalItems;
    			vm.userLicenses = data;
                //vm.page = pagingParams.page;
            }
            function onError(error) {
            	console.log(error);
                //AlertService.error(error.data.message);
            }
    	}
    	function updateCount(userLicense){
    		vm.maxAvailablelicense = 0;
    		vm.licenseCount = 0;
    		angular.forEach(userLicense.licenses, function(license) {
    			vm.licenseCount = vm.licenseCount + license.usedLicense;
    		});
    		
    		vm.maxAvailablelicense = userLicense.maxLicense - vm.licenseCount;
    	}
    	
    	function updateMaxLicense(userLicense){
    		HomeService.update(userLicense, onSaveSuccess, onSaveError)
    	}
    	
    	function updateLoginHistory(userLicense){
    		HomeService.update(userLicense, onSaveSuccess, onSaveError)
    	}
    	
    }
})();
