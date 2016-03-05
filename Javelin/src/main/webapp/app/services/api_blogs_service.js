AngularApp.factory('ApiBlogs', ['$http', function ($http) {
    var Apiblogs = function () {
        this.items = [];
        this.page = 0;
        this.size = 5;
        this.busy = false;
    };
    Apiblogs.prototype.nextPage = function () {
        if (this.busy) return;
        this.busy = true;

        var url = "api/blogs/all?page=" + this.page + "&size=" + this.size;
        $http({
            method: 'GET',
            url: url
        }).then(function success(data) {
            var items = data.data;
            for (var i = 0; i < items.length; i++) {
                this.items.push(items[i]);
            }
            this.page += 1;
            this.busy = false;

        }.bind(this));
    };

    return Apiblogs;
}]);