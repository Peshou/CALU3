AngularApp.controller('BlogPostController', [
    '$scope','BlogPost','Principal', function ($scope,BlogPost,Principal) {
        if($scope.isAuthenticated == true){
            $scope.blog = BlogPost.get({id: $scope.account.id, blogId:$scope.blogId });
        }

    }]);