AngularApp.controller('BlogController', [
    '$scope','Blog','$state','Principal', function ($scope,Blog,$state,Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        $scope.blogs = Blog.query();
      //  console.log($scope.blogs);
      //  console.log($state.$stateParams);
       // console.log($scope.account);
    }]);