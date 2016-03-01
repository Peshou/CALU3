/*

AngularApp
    .service('fileUpload', ['$http', function ($http) {
       /!* this.uploadFileToUrl = function(file, uploadUrl){
            var fd = new FormData();
            fd.append('file', file);
            $http.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                .success(function(){
                    window.alert("success");
                })
                .error(function(){
                });
        }*!/

        this.downloadFile = function(){
            $http({
                method: 'GET',
                url:'api/photo2'
            }).success(function(data,status,headers,config){
                console.log(data);
                return data;
            })
        }
    }]);*/
