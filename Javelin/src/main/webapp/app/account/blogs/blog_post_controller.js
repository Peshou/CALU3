/**
 * Created by Intel on 02.03.2016.
 */
AngularApp.controller('BlogPostController', [
    '$scope','BlogPost', function ($scope,BlogPost) {
        if($scope.isAuthenticated()){
            $scope.blog = BlogPost.get({id: $scope.account.id, blogId:$scope.blogId });
        }

    }]);