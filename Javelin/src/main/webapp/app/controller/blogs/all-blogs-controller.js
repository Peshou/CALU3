AngularApp.controller('AllBlogController', [
    '$scope', 'Blog', '$state', '$stateParams', 'Principal', '$http', 'SearchService', function ($scope, Blog, $state, $stateParams, Principal, $http, SearchService) {
        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
        console.log($stateParams);
        if ($stateParams.searchInput === undefined) {
            $http({
                method: 'GET',
                url: 'api/blogs/all'
            }).then(function success(response) {
          //      console.log(response);
                $scope.blogs = response.data;
            });
        } else {
            $scope.blogs = SearchService.getSearch($stateParams.searchInput);
            $scope.searchInput = null;
            console.log("went inside");
        }
    }]);


