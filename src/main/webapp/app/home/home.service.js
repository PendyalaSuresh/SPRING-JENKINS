(function() {
    'use strict';
    angular
        .module('nulmApp')
        .factory('HomeService', HomeService);

    HomeService.$inject = ['$resource'];

    function HomeService ($resource) {
        var resourceUrl =  'userlicenses/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
           'get': {
                method: 'GET'/*,
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createDate = DateUtils.convertLocalDateFromServer(data.createDate);
                    }
                    return data;
                }*/
            } ,
            'update': {
                method: 'PUT'                
            }/*,
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.createDate = DateUtils.convertLocalDateToServer(copy.createDate);
                    return angular.toJson(copy);
                }
            }*/
        });
    }
   
})();