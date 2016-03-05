AngularApp.controller('BlogController', [
    '$scope', 'Blog', '$state', 'Principal', '$http', function ($scope, Blog, $state, Principal, $http) {
        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

        });
        $scope.blog = {};
        $scope.createBlog = function () {
            Blog.save({blogName: $scope.blog.blogName, blogDescription: $scope.blog.blogDescription},function(){
                $scope.blogs = Blog.query();
            });
        };

        $scope.blogs = Blog.query();

    }]);