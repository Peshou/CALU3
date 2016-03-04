AngularApp.controller('BlogPostController', [
    '$scope','BlogPost','Principal','$stateParams', function ($scope,BlogPost,Principal,$stateParams) {

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

            if($scope.isAuthenticated()){
                $scope.postMaker = true;
            }else{
                $scope.postMaker = false;
            }
            console.log($scope.isAuthenticated());
            console.log($scope.account);

        });


        console.log($scope.postMaker);
       // console.log($stateParams);
            $scope.post = BlogPost.get({id: $stateParams.id, postId:$stateParams.blogPostId });
           // console.log($scope.blog)
      //  }

    }]);