AngularApp
    .controller('SearchController', ['$scope', '$stateParams', '$rootScope', 'SearchService', function ($scope, $stateParams, $rootScope, SearchService) {
        console.log($stateParams);
        if ($stateParams.searchInput != null) {
            $scope.resultList = SearchService.getSearch($stateParams.searchInput);
            $scope.searchInput = null;
        }
        console.log($scope.resultList);

    }]);
