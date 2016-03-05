AngularApp.controller('AllBlogController', [
    '$scope', 'Blog', '$state', '$stateParams', 'Principal', '$http', 'SearchService', 'ApiBlogs', function ($scope, Blog, $state, $stateParams, Principal, $http, SearchService, ApiBlogs) {
        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.$state = $state;
        });

     //   console.log($state.params);
      //  console.log($stateParams.searchInput);
        if ($state.params.searchInput != null) {
            $scope.resultBlogs = SearchService.getSearch($state.params.searchInput);
            $scope.searched=true;
        //    console.log($scope.resultBlogs);
        }


        $http({
            method: 'GET',
            url: 'api/blogs/all'
        }).then(function success(response) {
            $scope.blogs = response.data;
        });
        $scope.apiblogs = new ApiBlogs();
       //onsole.log($scope.apiblogs.items[i.user.username);

    }]);


